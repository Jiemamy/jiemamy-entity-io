/*
 * Copyright 2007-2009 Jiemamy Project and the Others.
 * Created on Apr 5, 2009
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
import java.util.Calendar;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import org.jiemamy.entity.io.meta.CannotCreatePropertyException;
import org.jiemamy.entity.io.meta.ColumnMetaFactory;
import org.jiemamy.entity.io.meta.EntityMeta;
import org.jiemamy.entity.io.meta.JoinColumnMeta;
import org.jiemamy.entity.io.meta.PropertyMeta;
import org.jiemamy.entity.io.meta.PropertyMetaFactory;
import org.jiemamy.entity.io.meta.RelationshipType;
import org.jiemamy.entity.io.meta.exception.BothMappedByAndJoinColumnException;
import org.jiemamy.entity.io.meta.exception.IdGeneratorNotFoundException;
import org.jiemamy.entity.io.meta.exception.JoinColumnNameAndReferencedColumnNameMandatoryException;
import org.jiemamy.entity.io.meta.exception.LazyFetchSpecifiedException;
import org.jiemamy.entity.io.meta.exception.MappedByMandatoryException;
import org.jiemamy.entity.io.meta.exception.OneToManyNotGenericsException;
import org.jiemamy.entity.io.meta.exception.OneToManyNotListException;
import org.jiemamy.entity.io.meta.exception.RelationshipNotEntityException;
import org.jiemamy.entity.io.meta.exception.TemporalTypeNotSpecifiedException;
import org.jiemamy.entity.io.meta.exception.UnsupportedRelationshipException;
import org.jiemamy.entity.io.meta.exception.VersionPropertyNotNumberException;
import org.jiemamy.entity.io.meta.generator.IdentityIdGeneratorMeta;
import org.jiemamy.entity.io.meta.generator.SequenceIdGeneratorMeta;
import org.jiemamy.entity.io.meta.generator.TableIdGeneratorMeta;
import org.jiemamy.utils.ClassUtil;
import org.jiemamy.utils.ModifierUtil;
import org.jiemamy.utils.ReflectionUtil;

/**
 * {@link PropertyMetaFactory}の実装クラス。
 * 
 * @author j5ik2o
 */
public class PropertyMetaFactoryImpl implements PropertyMetaFactory {
	
	private ColumnMetaFactory columnMetaFactory;
	
	/** デフォルトの{@link SequenceGenerator} */
	protected static final SequenceGenerator DEFAULT_SEQUENCE_GENERATOR =
			PropertyMetaFactoryImpl.class.getAnnotation(SequenceGenerator.class);
	
	/** デフォルトの{@link TableGenerator} */
	protected static final TableGenerator DEFAULT_TABLE_GENERATOR =
			PropertyMetaFactoryImpl.class.getAnnotation(TableGenerator.class);
	

	/**
	* インスタンスを生成する。
	* 
	* @param columnMetaFactory {@link ColumnMetaFactory}
	*/
	public PropertyMetaFactoryImpl(ColumnMetaFactory columnMetaFactory) {
		Validate.notNull(columnMetaFactory);
		this.columnMetaFactory = columnMetaFactory;
	}
	
	public PropertyMeta createPropertyMeta(EntityMeta entityMeta, Field field) throws CannotCreatePropertyException {
		Validate.notNull(field);
		Validate.notNull(entityMeta);
		PropertyMeta propertyMeta = new PropertyMeta();
		doField(propertyMeta, field, entityMeta);
		doName(propertyMeta, field, entityMeta);
		doTransient(propertyMeta, field, entityMeta);
		if (propertyMeta.isTransient() == false) {
			Object relationshipAnnotation = getRelationshipAnnotation(field);
			if (relationshipAnnotation == null) {
				doColumnMeta(propertyMeta, field, entityMeta);
				try {
					doId(propertyMeta, field, entityMeta);
					doFetchType(propertyMeta, field, entityMeta);
					doTemporal(propertyMeta, field, entityMeta);
					doEnum(propertyMeta, field, entityMeta);
					doVersion(propertyMeta, field, entityMeta);
					doLob(propertyMeta, field, entityMeta);
				} catch (Throwable t) {
					throw new CannotCreatePropertyException(t);
				}
				
			} else {
				try {
					doRelationship(propertyMeta, field, entityMeta, relationshipAnnotation);
				} catch (Throwable t) {
					throw new CannotCreatePropertyException(t);
				}
			}
		}
		doCustomize(propertyMeta, field, entityMeta);
		return propertyMeta;
		
	}
	
	private void doColumnMeta(PropertyMeta propertyMeta, Field field, EntityMeta entityMeta) {
		Validate.notNull(propertyMeta);
		Validate.notNull(field);
		Validate.notNull(entityMeta);
		propertyMeta.setColumnMeta(columnMetaFactory.createColumnMeta(field, entityMeta, propertyMeta));
	}
	
	private void doCustomize(PropertyMeta propertyMeta, Field field, EntityMeta entityMeta) {
		// TODO Auto-generated method stub
	}
	
	private void doEnum(PropertyMeta propertyMeta, Field field, EntityMeta entityMeta) {
		Validate.notNull(propertyMeta);
		Validate.notNull(field);
		Validate.notNull(entityMeta);
		if (propertyMeta.getPropertyClass().isEnum() == false) {
			return;
		}
		Enumerated enumerated = field.getAnnotation(Enumerated.class);
		if (enumerated == null) {
			return;
		}
		propertyMeta.setEnumType(enumerated.value());
	}
	
	private void doFetchType(PropertyMeta propertyMeta, Field field, EntityMeta entityMeta)
			throws LazyFetchSpecifiedException {
		Validate.notNull(propertyMeta);
		Validate.notNull(field);
		Validate.notNull(entityMeta);
		final Basic basic = field.getAnnotation(Basic.class);
		if (basic == null) {
			propertyMeta.setFetchType(FetchType.EAGER);
			return;
		}
		if (propertyMeta.isId() && basic.fetch() == FetchType.LAZY) {
			throw new LazyFetchSpecifiedException(entityMeta.getName(), propertyMeta.getName());
		}
		propertyMeta.setFetchType(basic.fetch());
	}
	
	private void doField(PropertyMeta propertyMeta, Field field, EntityMeta entityMeta) {
		Validate.notNull(propertyMeta);
		Validate.notNull(field);
		Validate.notNull(entityMeta);
		propertyMeta.setField(field);
		propertyMeta.setPropertyClass(field.getType());
	}
	
	private void doId(PropertyMeta propertyMeta, Field field, EntityMeta entityMeta)
			throws IdGeneratorNotFoundException {
		Validate.notNull(propertyMeta);
		Validate.notNull(field);
		Validate.notNull(entityMeta);
		propertyMeta.setId(field.getAnnotation(Id.class) != null);
		GeneratedValue generatedValue = field.getAnnotation(GeneratedValue.class);
		if (generatedValue == null) {
			return;
		}
		GenerationType generationType = generatedValue.strategy();
		propertyMeta.setGenerationType(generationType);
		switch (generationType) {
			case AUTO:
				doIdentityIdGenerator(propertyMeta, entityMeta);
				doSequenceIdGenerator(propertyMeta, generatedValue, entityMeta);
				doTableIdGenerator(propertyMeta, generatedValue, entityMeta);
				break;
			case IDENTITY:
				doIdentityIdGenerator(propertyMeta, entityMeta);
				break;
			case SEQUENCE:
				if (!doSequenceIdGenerator(propertyMeta, generatedValue, entityMeta)) {
					throw new IdGeneratorNotFoundException(entityMeta.getName(), propertyMeta.getName(), generatedValue
						.generator());
				}
				break;
			case TABLE:
				if (!doTableIdGenerator(propertyMeta, generatedValue, entityMeta)) {
					throw new IdGeneratorNotFoundException(entityMeta.getName(), propertyMeta.getName(), generatedValue
						.generator());
				}
				break;
			default:
		}
	}
	
	private void doIdentityIdGenerator(PropertyMeta propertyMeta, EntityMeta entityMeta) {
		Validate.notNull(propertyMeta);
		Validate.notNull(entityMeta);
		propertyMeta.setIdentityIdGenerator(new IdentityIdGeneratorMeta(entityMeta, propertyMeta));
	}
	
	private void doJoinColumn(PropertyMeta propertyMeta, Field field, EntityMeta entityMeta)
			throws JoinColumnNameAndReferencedColumnNameMandatoryException {
		Validate.notNull(propertyMeta);
		Validate.notNull(field);
		Validate.notNull(entityMeta);
		JoinColumn joinColumn = field.getAnnotation(JoinColumn.class);
		if (joinColumn != null) {
			JoinColumnMeta meta = new JoinColumnMeta(joinColumn.name(), joinColumn.referencedColumnName());
			propertyMeta.addJoinColumnMeta(meta);
		} else {
			JoinColumns joinColumns = field.getAnnotation(JoinColumns.class);
			if (joinColumns != null) {
				JoinColumn[] array = joinColumns.value();
				for (int i = 0; i < array.length; i++) {
					JoinColumn jc = array[i];
					JoinColumnMeta meta = new JoinColumnMeta(jc.name(), jc.referencedColumnName());
					if (i > 0 && (meta.getName() == null || meta.getReferencedColumnName() == null)) {
						throw new JoinColumnNameAndReferencedColumnNameMandatoryException(entityMeta.getName(),
								propertyMeta.getName(), i + 1);
					}
					propertyMeta.addJoinColumnMeta(meta);
				}
			}
		}
	}
	
	private void doLob(PropertyMeta propertyMeta, Field field, EntityMeta entityMeta) {
		Validate.notNull(propertyMeta);
		Validate.notNull(field);
		Validate.notNull(entityMeta);
		propertyMeta.setLob(field.getAnnotation(Lob.class) != null);
	}
	
	private void doManyToOne(PropertyMeta propertyMeta, Field field, EntityMeta entityMeta, ManyToOne manyToOne)
			throws RelationshipNotEntityException {
		Validate.notNull(propertyMeta);
		Validate.notNull(field);
		Validate.notNull(entityMeta);
		Validate.notNull(manyToOne);
		propertyMeta.setRelationshipType(RelationshipType.MANY_TO_ONE);
		Class<?> relationshipClass = field.getType();
		if (relationshipClass.getAnnotation(Entity.class) == null) {
			throw new RelationshipNotEntityException(entityMeta.getName(), propertyMeta.getName(), relationshipClass);
		}
		propertyMeta.setRelationshipClass(relationshipClass);
	}
	
	private void doName(PropertyMeta propertyMeta, Field field, EntityMeta entityMeta) {
		Validate.notNull(propertyMeta);
		Validate.notNull(field);
		Validate.notNull(entityMeta);
		propertyMeta.setName(fromFieldNameToPropertyName(field.getName()));
	}
	
	private void doOneToMany(PropertyMeta propertyMeta, Field field, EntityMeta entityMeta, OneToMany oneToMany)
			throws OneToManyNotListException, OneToManyNotGenericsException, RelationshipNotEntityException,
			BothMappedByAndJoinColumnException, MappedByMandatoryException {
		Validate.notNull(propertyMeta);
		Validate.notNull(field);
		Validate.notNull(entityMeta);
		Validate.notNull(oneToMany);
		propertyMeta.setRelationshipType(RelationshipType.ONE_TO_MANY);
		if (List.class.isAssignableFrom(field.getType()) == false) {
			throw new OneToManyNotListException(entityMeta.getName(), propertyMeta.getName());
		}
		Class<?> relationshipClass = ReflectionUtil.getElementTypeOfList(field.getGenericType());
		if (relationshipClass == null) {
			throw new OneToManyNotGenericsException(entityMeta.getName(), propertyMeta.getName());
		}
		if (relationshipClass.getAnnotation(Entity.class) == null) {
			throw new RelationshipNotEntityException(entityMeta.getName(), propertyMeta.getName(), relationshipClass);
		}
		propertyMeta.setRelationshipClass(relationshipClass);
		String mappedBy = oneToMany.mappedBy();
		if (!StringUtils.isEmpty(mappedBy)) {
			if (propertyMeta.getJoinColumnMetaList().size() > 0) {
				throw new BothMappedByAndJoinColumnException(entityMeta.getName(), propertyMeta.getName());
			}
			propertyMeta.setMappedBy(mappedBy);
		} else {
			throw new MappedByMandatoryException(entityMeta.getName(), propertyMeta.getName());
		}
	}
	
	private void doOneToOne(PropertyMeta propertyMeta, Field field, EntityMeta entityMeta, OneToOne oneToOne)
			throws RelationshipNotEntityException, BothMappedByAndJoinColumnException {
		Validate.notNull(propertyMeta);
		Validate.notNull(field);
		Validate.notNull(entityMeta);
		Validate.notNull(oneToOne);
		propertyMeta.setRelationshipType(RelationshipType.ONE_TO_ONE);
		Class<?> relationshipClass = field.getType();
		if (relationshipClass.getAnnotation(Entity.class) == null) {
			throw new RelationshipNotEntityException(entityMeta.getName(), propertyMeta.getName(), relationshipClass);
		}
		propertyMeta.setRelationshipClass(relationshipClass);
		String mappedBy = oneToOne.mappedBy();
		if (!StringUtils.isEmpty(mappedBy)) {
			if (propertyMeta.getJoinColumnMetaList().size() > 0) {
				throw new BothMappedByAndJoinColumnException(entityMeta.getName(), propertyMeta.getName());
			}
			propertyMeta.setMappedBy(mappedBy);
		}
	}
	
	private void doRelationship(PropertyMeta propertyMeta, Field field, EntityMeta entityMeta,
			Object relationshipAnnotation) throws UnsupportedRelationshipException,
			JoinColumnNameAndReferencedColumnNameMandatoryException, RelationshipNotEntityException,
			BothMappedByAndJoinColumnException, OneToManyNotListException, OneToManyNotGenericsException,
			MappedByMandatoryException {
		Validate.notNull(propertyMeta);
		Validate.notNull(field);
		Validate.notNull(entityMeta);
		Validate.notNull(relationshipAnnotation);
		doJoinColumn(propertyMeta, field, entityMeta);
		if (OneToOne.class.isInstance(relationshipAnnotation)) {
			doOneToOne(propertyMeta, field, entityMeta, OneToOne.class.cast(relationshipAnnotation));
		} else if (OneToMany.class.isInstance(relationshipAnnotation)) {
			doOneToMany(propertyMeta, field, entityMeta, OneToMany.class.cast(relationshipAnnotation));
		} else if (ManyToOne.class.isInstance(relationshipAnnotation)) {
			doManyToOne(propertyMeta, field, entityMeta, ManyToOne.class.cast(relationshipAnnotation));
		} else {
			throw new UnsupportedRelationshipException(entityMeta.getName(), propertyMeta.getName());
		}
	}
	
	private boolean doSequenceIdGenerator(PropertyMeta propertyMeta, GeneratedValue generatedValue,
			EntityMeta entityMeta) {
		Validate.notNull(propertyMeta);
		Validate.notNull(generatedValue);
		Validate.notNull(entityMeta);
		String name = generatedValue.generator();
		SequenceGenerator sequenceGenerator;
		if (StringUtils.isEmpty(name)) {
			sequenceGenerator = DEFAULT_SEQUENCE_GENERATOR;
		} else {
			sequenceGenerator = propertyMeta.getField().getAnnotation(SequenceGenerator.class);
			if (sequenceGenerator == null || !name.equals(sequenceGenerator.name())) {
				sequenceGenerator = entityMeta.getEntityClass().getAnnotation(SequenceGenerator.class);
				if (sequenceGenerator == null || !name.equals(sequenceGenerator.name())) {
					return false;
				}
			}
		}
		propertyMeta.setSequenceIdGenerator(new SequenceIdGeneratorMeta(entityMeta, propertyMeta, sequenceGenerator));
		return true;
	}
	
	private boolean doTableIdGenerator(PropertyMeta propertyMeta, GeneratedValue generatedValue, EntityMeta entityMeta) {
		Validate.notNull(propertyMeta);
		Validate.notNull(generatedValue);
		Validate.notNull(entityMeta);
		String name = generatedValue.generator();
		TableGenerator tableGenerator;
		if (StringUtils.isEmpty(name)) {
			tableGenerator = DEFAULT_TABLE_GENERATOR;
		} else {
			tableGenerator = propertyMeta.getField().getAnnotation(TableGenerator.class);
			if (tableGenerator == null || !name.equals(tableGenerator.name())) {
				tableGenerator = entityMeta.getEntityClass().getAnnotation(TableGenerator.class);
				if (tableGenerator == null || !name.equals(tableGenerator.name())) {
					return false;
				}
			}
		}
		propertyMeta.setTableIdGenerator(new TableIdGeneratorMeta(entityMeta, propertyMeta, tableGenerator));
		return true;
	}
	
	private void doTemporal(PropertyMeta propertyMeta, Field field, EntityMeta entityMeta)
			throws TemporalTypeNotSpecifiedException {
		Validate.notNull(propertyMeta);
		Validate.notNull(field);
		Validate.notNull(entityMeta);
		if (propertyMeta.getPropertyClass() != java.util.Date.class
				&& propertyMeta.getPropertyClass() != Calendar.class) {
			return;
		}
		Temporal temporal = field.getAnnotation(Temporal.class);
		if (temporal == null) {
			throw new TemporalTypeNotSpecifiedException(entityMeta.getName(), propertyMeta.getName());
		}
		propertyMeta.setTemporalType(temporal.value());
	}
	
	private void doTransient(PropertyMeta propertyMeta, Field field, EntityMeta entityMeta) {
		Validate.notNull(propertyMeta);
		Validate.notNull(field);
		Validate.notNull(entityMeta);
		propertyMeta.setTransient(field.getAnnotation(Transient.class) != null || ModifierUtil.isTransient(field));
	}
	
	private void doVersion(PropertyMeta propertyMeta, Field field, EntityMeta entityMeta)
			throws VersionPropertyNotNumberException {
		Validate.notNull(propertyMeta);
		Validate.notNull(field);
		Validate.notNull(entityMeta);
		if (field.getAnnotation(Version.class) == null) {
			return;
		}
		Class<?> clazz = ClassUtil.getWrapperClassIfPrimitive(field.getType());
		if (clazz != Integer.class && clazz != Long.class && clazz != int.class && clazz != long.class) {
			throw new VersionPropertyNotNumberException(entityMeta.getName(), propertyMeta.getName());
		}
		propertyMeta.setVersion(true);
	}
	
	private String fromFieldNameToPropertyName(String name) {
		Validate.notNull(name);
		return name;
	}
	
	/**
	 * フィールドに関連のアノテーションが指定されていればそれを取得する。
	 * 
	 * @param field フィールド
	 * @return 関連のアノテーションまたは<code>null</code>
	 */
	protected Object getRelationshipAnnotation(Field field) {
		Validate.notNull(field);
		final OneToOne oneToOne = field.getAnnotation(OneToOne.class);
		if (oneToOne != null) {
			return oneToOne;
		}
		final OneToMany oneToMany = field.getAnnotation(OneToMany.class);
		if (oneToMany != null) {
			return oneToMany;
		}
		final ManyToOne manyToOne = field.getAnnotation(ManyToOne.class);
		if (manyToOne != null) {
			return manyToOne;
		}
		final ManyToMany manyToMany = field.getAnnotation(ManyToMany.class);
		if (manyToMany != null) {
			return manyToMany;
		}
		return null;
	}
	
}
