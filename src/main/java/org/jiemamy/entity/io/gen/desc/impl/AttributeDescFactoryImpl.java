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

import java.util.regex.Pattern;

import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

import org.apache.commons.lang.Validate;

import org.jiemamy.entity.io.convensition.PersistenceConvention;
import org.jiemamy.entity.io.gen.desc.AttributeDesc;
import org.jiemamy.entity.io.gen.desc.AttributeDescFactory;
import org.jiemamy.entity.io.gen.dialect.GenDialect;
import org.jiemamy.entity.io.gen.meta.DbColumnMeta;
import org.jiemamy.entity.io.gen.meta.DbTableMeta;
import org.jiemamy.entity.io.utils.AnnotationUtil;
import org.jiemamy.utils.ClassUtil;

/**
 * {@link AttributeDescFactory}の実装クラスです。
 * 
 * @author j5ik2o
 */
public class AttributeDescFactoryImpl implements AttributeDescFactory {
	
	/** バージョンカラム名のパターン */
	protected Pattern versionColumnNamePattern;
	
	/** エンティティの識別子の生成方法を示す列挙型 、生成しない場合は{@code null} */
	protected GenerationType generationType;
	
	/** エンティティの識別子の初期値 */
	protected int initialValue;
	
	/** エンティティの識別子の割り当てサイズ */
	protected int allocationSize;
	
	/** SQL方言 */
	protected GenDialect dialect;
	
	private PersistenceConvention persistenceConvention;
	

	/**
	 * インスタンスを構築する。
	 * 
	 * @param persistenceConvention 永続化層の命名規約
	 * @param dialect SQL方言
	 * @param versionColumnNamePattern バージョンカラム名のパターン
	 * @param generationType エンティティの識別子の生成方法を示す列挙型 、生成しない場合は{@code null}
	 * @param initialValue エンティティの識別子の初期値、指定しない場合は{@code null}
	 * @param allocationSize エンティティの識別子の割り当てサイズ、指定しない場合は{@code null}
	 * @throws IdentityNotSupportedException IDENTITYがサポートされていない場合
	 */
	public AttributeDescFactoryImpl(PersistenceConvention persistenceConvention, GenDialect dialect,
			Pattern versionColumnNamePattern, GenerationType generationType, Integer initialValue,
			Integer allocationSize) throws IdentityNotSupportedException {
		Validate.notNull(dialect);
		Validate.notNull(versionColumnNamePattern);
		this.persistenceConvention = persistenceConvention;
		this.dialect = dialect;
		this.versionColumnNamePattern = versionColumnNamePattern;
		this.generationType =
				generationType == GenerationType.AUTO ? dialect.getDefaultGenerationType() : generationType;
		if (this.generationType == GenerationType.IDENTITY) {
			if (dialect.supportsIdentity() == false) {
				throw new IdentityNotSupportedException();
			}
		} else if (this.generationType == GenerationType.SEQUENCE) {
			SequenceGenerator generator = AnnotationUtil.getDefaultSequenceGenerator();
			this.initialValue = initialValue != null ? initialValue : generator.initialValue();
			this.allocationSize = allocationSize != null ? allocationSize : generator.allocationSize();
		} else if (this.generationType == GenerationType.TABLE) {
			TableGenerator generator = AnnotationUtil.getDefaultTableGenerator();
			this.initialValue = initialValue != null ? initialValue : generator.initialValue();
			this.allocationSize = allocationSize != null ? allocationSize : generator.allocationSize();
		}
	}
	
	/**
	 * カラムを処理する。
	 * 
	 * @param tableMeta テーブルメタデータ
	 * @param columnMeta カラムメタデータ
	 * @param attributeDesc 属性記述
	 */
	protected void doColumn(DbTableMeta tableMeta, DbColumnMeta columnMeta, AttributeDesc attributeDesc) {
		attributeDesc.setColumnName(columnMeta.getName());
		attributeDesc.setColumnTypeName(columnMeta.getTypeName());
		attributeDesc.setLength(columnMeta.getLength());
		attributeDesc.setPrecision(columnMeta.getLength());
		attributeDesc.setScale(columnMeta.getScale());
		attributeDesc.setNullable(columnMeta.isNullable());
		attributeDesc.setUnique(columnMeta.isUnique());
		attributeDesc.setComment(columnMeta.getComment());
		GenDialect.ColumnType columnType = dialect.getColumnType(columnMeta.getTypeName(), columnMeta.getSqlType());
		if (columnType != null) {
			Class<?> clazz =
					columnType.getAttributeClass(columnMeta.getLength(), columnMeta.getLength(), columnMeta.getScale());
			attributeDesc.setAttributeClass(clazz);
			String defaultValue = attributeDesc.getGenerationType() != null ? null : columnMeta.getDefaultValue();
			String definition =
					columnType.getColumnDefinition(columnMeta.getLength(), columnMeta.getLength(), columnMeta
						.getScale(), defaultValue);
			attributeDesc.setColumnDefinition(definition);
			attributeDesc.setLob(columnType.isLob());
		} else {
			attributeDesc.setUnsupportedColumnType(true);
			attributeDesc.setAttributeClass(String.class);
			attributeDesc.setLob(false);
		}
	}
	
	/**
	 * 識別子を処理する。
	 * 
	 * @param tableMeta テーブルメタデータ
	 * @param columnMeta カラムメタデータ
	 * @param attributeDesc 属性記述
	 */
	protected void doId(DbTableMeta tableMeta, DbColumnMeta columnMeta, AttributeDesc attributeDesc) {
		if (columnMeta.isPrimaryKey()) {
			attributeDesc.setId(true);
			if (!tableMeta.hasCompositePrimaryKey()) {
				if (columnMeta.isAutoIncrement()) {
					attributeDesc.setGenerationType(GenerationType.IDENTITY);
				} else {
					attributeDesc.setGenerationType(generationType);
					attributeDesc.setInitialValue(initialValue);
					attributeDesc.setAllocationSize(allocationSize);
				}
			}
		}
	}
	
	/**
	 * 名前を処理する。
	 * 
	 * @param tableMeta テーブルメタデータ
	 * @param columnMeta カラムメタデータ
	 * @param attributeDesc 属性記述
	 */
	protected void doName(DbTableMeta tableMeta, DbColumnMeta columnMeta, AttributeDesc attributeDesc) {
		attributeDesc.setName(persistenceConvention.fromColumnNameToPropertyName(columnMeta.getName()));
	}
	
	/**
	 * 一時的なプロパティを処理する。
	 * 
	 * @param tableMeta テーブルメタデータ
	 * @param columnMeta カラムメタデータ
	 * @param attributeDesc 属性記述
	 */
	protected void doTransient(DbTableMeta tableMeta, DbColumnMeta columnMeta, AttributeDesc attributeDesc) {
	}
	
	/**
	 * バージョンを処理する。
	 * 
	 * @param tableMeta テーブルメタデータ
	 * @param columnMeta カラムメタデータ
	 * @param attributeDesc 属性記述
	 */
	protected void doVersion(DbTableMeta tableMeta, DbColumnMeta columnMeta, AttributeDesc attributeDesc) {
		if (isVersionAnnotatable(attributeDesc.getAttributeClass())) {
			if (versionColumnNamePattern.matcher(columnMeta.getName()).matches()) {
				attributeDesc.setVersion(true);
			}
		}
	}
	
	public AttributeDesc getAttributeDesc(DbTableMeta tableMeta, DbColumnMeta columnMeta) {
		AttributeDesc attributeDesc = new AttributeDesc();
		doName(tableMeta, columnMeta, attributeDesc);
		doId(tableMeta, columnMeta, attributeDesc);
		doTransient(tableMeta, columnMeta, attributeDesc);
		doColumn(tableMeta, columnMeta, attributeDesc);
		doVersion(tableMeta, columnMeta, attributeDesc);
		return attributeDesc;
	}
	
	/**
	 * {@link Version}を注釈できるクラスの場合{@code true}
	 * 
	 * @param clazz クラス
	 * @return {@link Version}を注釈できるクラスの場合{@code true}
	 */
	protected boolean isVersionAnnotatable(Class<?> clazz) {
		Class<?> wrapperClass = ClassUtil.getWrapperClassIfPrimitive(clazz);
		return wrapperClass == Integer.class || wrapperClass == Long.class;
	}
	
}
