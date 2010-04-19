/*
 * Copyright 2007-2009 Jiemamy Project and the Others.
 * Created on Apr 7, 2009
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
package org.jiemamy.entity.io.gen.desc.impl;

import org.apache.commons.lang.Validate;

import org.jiemamy.entity.io.convensition.PersistenceConvention;
import org.jiemamy.entity.io.gen.desc.AttributeDesc;
import org.jiemamy.entity.io.gen.desc.AttributeDescFactory;
import org.jiemamy.entity.io.gen.desc.CompositeUniqueConstraintDesc;
import org.jiemamy.entity.io.gen.desc.CompositeUniqueConstraintDescFactory;
import org.jiemamy.entity.io.gen.desc.EntityDesc;
import org.jiemamy.entity.io.gen.desc.EntityDescFactory;
import org.jiemamy.entity.io.gen.meta.DbColumnMeta;
import org.jiemamy.entity.io.gen.meta.DbTableMeta;
import org.jiemamy.entity.io.gen.meta.DbUniqueKeyMeta;

/**
 * {@link EntityDescFactory}の実装クラスです。
 * 
 * @author j5ik2o
 */
public class EntityDescFactoryImpl implements EntityDescFactory {
	
	/** 属性記述のファクトリ */
	protected AttributeDescFactory attributeDescFactory;
	
	/** 複合一意制約記述のファクトリ */
	protected CompositeUniqueConstraintDescFactory compositeUniqueConstraintDescFactory;
	
	/** スキーマが指定されている場合{@code true} */
	protected boolean schemaSpecified;
	
	/** 永続化層の命名規約 */
	protected PersistenceConvention persistenceConvention;
	

	/**
	 * インスタンスを生成する。
	 * 
	 * @param persistenceConvention 永続化層の命名規約
	 * @param compositeUniqueConstraintDescFactory 複合一意制約記述のファクトリ
	 * @param attributeDescFactory 属性記述のファクトリ
	 */
	public EntityDescFactoryImpl(PersistenceConvention persistenceConvention,
			AttributeDescFactory attributeDescFactory,
			CompositeUniqueConstraintDescFactory compositeUniqueConstraintDescFactory) {
		Validate.notNull(persistenceConvention);
		Validate.notNull(attributeDescFactory);
		Validate.notNull(compositeUniqueConstraintDescFactory);
		this.persistenceConvention = persistenceConvention;
		this.attributeDescFactory = attributeDescFactory;
		this.compositeUniqueConstraintDescFactory = compositeUniqueConstraintDescFactory;
	}
	
	/**
	 * 属性記述を処理する。
	 * 
	 * @param tableMeta テーブルメタデータ
	 * @param entityDesc エンティティ記述
	 */
	protected void doAttributeDesc(DbTableMeta tableMeta, EntityDesc entityDesc) {
		for (DbColumnMeta columnMeta : tableMeta.getColumnMetaList()) {
			AttributeDesc attributeDesc = attributeDescFactory.getAttributeDesc(tableMeta, columnMeta);
			entityDesc.addAttributeDesc(attributeDesc);
		}
	}
	
	/**
	 * 複合一意制約記述を処理する。
	 * 
	 * @param tableMeta テーブルメタデータ
	 * @param entityDesc エンティティ記述
	 */
	protected void doCompositeUniqueConstraintDesc(DbTableMeta tableMeta, EntityDesc entityDesc) {
		for (DbUniqueKeyMeta uniqueKeyMeta : tableMeta.getUniqueKeyMetaList()) {
			CompositeUniqueConstraintDesc compositeUniqueConstraintDesc =
					compositeUniqueConstraintDescFactory.getCompositeUniqueConstraintDesc(uniqueKeyMeta);
			if (compositeUniqueConstraintDesc != null) {
				entityDesc.addCompositeUniqueConstraintDesc(compositeUniqueConstraintDesc);
			}
		}
	}
	
	/**
	 * 名前を処理する。
	 * 
	 * @param tableMeta テーブルメタ情報
	 * @param entityDesc エンティティ記述
	 */
	protected void doName(DbTableMeta tableMeta, EntityDesc entityDesc) {
		entityDesc.setName(persistenceConvention.fromTableNameToEntityName(tableMeta.getName()));
	}
	
	/**
	 * テーブルを処理する。
	 * 
	 * @param tableMeta テーブルメタ情報
	 * @param entityDesc エンティティ記述
	 */
	protected void doTable(DbTableMeta tableMeta, EntityDesc entityDesc) {
		entityDesc.setCatalogName(tableMeta.getCatalogName());
		entityDesc.setSchemaName(tableMeta.getSchemaName());
		entityDesc.setTableName(tableMeta.getName());
		entityDesc.setComment(tableMeta.getComment());
	}
	
	public EntityDesc getEntityDesc(DbTableMeta tableMeta) {
		EntityDesc entityDesc = new EntityDesc();
		doName(tableMeta, entityDesc);
		doTable(tableMeta, entityDesc);
		doAttributeDesc(tableMeta, entityDesc);
		doCompositeUniqueConstraintDesc(tableMeta, entityDesc);
		return entityDesc;
	}
	
}
