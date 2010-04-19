/*
 * Copyright 2007-2009 Jiemamy Project and the Others.
 * Created on Apr 4, 2009
 *
 * This file is part of Jiemamy.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.jiemamy.entity.io.meta.impl;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;

import javassist.NotFoundException;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import org.jiemamy.entity.io.meta.CannotCreatePropertyException;
import org.jiemamy.entity.io.meta.ColumnDuplicatedException;
import org.jiemamy.entity.io.meta.EntityMeta;
import org.jiemamy.entity.io.meta.EntityMetaFactory;
import org.jiemamy.entity.io.meta.NonEntityException;
import org.jiemamy.entity.io.meta.PropertyDuplicatedException;
import org.jiemamy.entity.io.meta.PropertyMetaFactory;
import org.jiemamy.entity.io.meta.TableMeta;
import org.jiemamy.entity.io.meta.TableMetaFactory;
import org.jiemamy.entity.io.meta.exception.FieldDuplicatedException;
import org.jiemamy.entity.io.meta.exception.UnsupportedInheritanceException;
import org.jiemamy.utils.ArrayMap;
import org.jiemamy.utils.ClassUtil;
import org.jiemamy.utils.CollectionsUtil;
import org.jiemamy.utils.JmStringUtil;
import org.jiemamy.utils.ModifierUtil;

/**
 * {@link EntityMetaFactory}の実装クラス。
 * 
 * @author j5ik2o
 */
public class EntityMetaFactoryImpl implements EntityMetaFactory {
	
	private ConcurrentHashMap<String, EntityMeta> entityMetaCache = CollectionsUtil.newConcurrentHashMap();
	
	private TableMetaFactory tableMetaFactory;
	
	private PropertyMetaFactory propertyMetaFactory;
	

	/**
	 * インスタンスを生成する。
	 * 
	 * @param tableMetaFactory テーブルメタファクトリ
	 * @param propertyMetaFactory プロパティメタファクトリ
	 */
	public EntityMetaFactoryImpl(TableMetaFactory tableMetaFactory, PropertyMetaFactory propertyMetaFactory) {
		Validate.notNull(tableMetaFactory);
		Validate.notNull(propertyMetaFactory);
		this.tableMetaFactory = tableMetaFactory;
		this.propertyMetaFactory = propertyMetaFactory;
	}
	
	private EntityMeta createEntityMeta(Class<?> entityClass) throws NonEntityException {
		Validate.notNull(entityClass);
		Entity entityAnnotation = entityClass.getAnnotation(Entity.class);
		if (entityAnnotation == null) {
			throw new NonEntityException(String.format("%s is not entity class.", entityClass.getName()));
		}
		EntityMeta entityMeta = new EntityMeta();
		doEntityClass(entityMeta, entityClass);
		doName(entityMeta, entityAnnotation);
		doTableMeta(entityMeta);
		try {
			doPropertyMeta(entityMeta);
		} catch (Exception e) {
			throw new NonEntityException(e);
		}
		//doCustomize(entityMeta);
		return entityMeta;
	}
	
	public void dispose() {
		entityMetaCache.clear();
	}
	
	private void doEntityClass(EntityMeta entityMeta, Class<?> entityClass) {
		Validate.notNull(entityMeta);
		Validate.notNull(entityClass);
		entityMeta.setEntityClass(entityClass);
	}
	
	private void doName(EntityMeta entityMeta, Entity entityAnnotation) {
		Validate.notNull(entityMeta);
		Validate.notNull(entityAnnotation);
		String entityName = entityAnnotation.name();
		if (StringUtils.isEmpty(entityName)) {
			entityName = JmStringUtil.toUnCapital(entityMeta.getEntityClass().getSimpleName());
		}
		entityMeta.setName(entityName);
	}
	
	private void doPropertyMeta(EntityMeta entityMeta) throws ColumnDuplicatedException, PropertyDuplicatedException,
			NotFoundException, NoSuchFieldException, UnsupportedInheritanceException, FieldDuplicatedException,
			CannotCreatePropertyException {
		Validate.notNull(entityMeta);
		Field[] fields = getFields(entityMeta.getEntityClass());
		for (Field f : fields) {
			f.setAccessible(true);
			entityMeta.addPropertyMeta(propertyMetaFactory.createPropertyMeta(entityMeta, f));
		}
	}
	
	private void doTableMeta(EntityMeta entityMeta) {
		Validate.notNull(entityMeta);
		TableMeta tableMeta = tableMetaFactory.createTableMeta(entityMeta);
		entityMeta.setTableMeta(tableMeta);
	}
	
	public EntityMeta getEntityMeta(Class<?> entityClass) throws NonEntityException {
		Validate.notNull(entityClass);
		EntityMeta entityMeta = entityMetaCache.get(entityClass.getName());
		if (entityMeta != null) {
			return entityMeta;
		}
		entityMeta = createEntityMeta(entityClass);
		EntityMeta entityMeta2 = entityMetaCache.putIfAbsent(entityClass.getName(), entityMeta);
		return entityMeta2 != null ? entityMeta2 : entityMeta;
	}
	
	private Field[] getFields(Class<?> entityClass) throws NotFoundException, NoSuchFieldException,
			UnsupportedInheritanceException, FieldDuplicatedException {
		Validate.notNull(entityClass);
		ArrayMap<String, Field> fields = new ArrayMap<String, Field>();
		for (Field f : ClassUtil.getDeclaredFields(entityClass)) {
			if (!ModifierUtil.isInstanceField(f)) {
				continue;
			}
			fields.put(f.getName(), f);
		}
		for (Class<?> clazz = entityClass.getSuperclass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
			if (clazz.isAnnotationPresent(Entity.class)) {
				throw new UnsupportedInheritanceException(entityClass);
			}
			if (clazz.isAnnotationPresent(MappedSuperclass.class)) {
				for (Field f : ClassUtil.getDeclaredFields(clazz)) {
					if (ModifierUtil.isInstanceField(f) == false) {
						continue;
					}
					String name = f.getName();
					if (fields.containsKey(name) == false) {
						fields.put(name, f);
					} else {
						throw new FieldDuplicatedException(f);
					}
				}
			}
		}
		return fields.toArray(new Field[fields.size()]);
	}
}
