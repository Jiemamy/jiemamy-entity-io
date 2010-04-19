/*
 * Copyright 2007-2009 Jiemamy Project and the Others.
 * Created on Apr 6, 2009
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
package org.jiemamy.entity.io.meta.generator;

import javax.persistence.TableGenerator;

import org.jiemamy.entity.io.meta.EntityMeta;
import org.jiemamy.entity.io.meta.PropertyMeta;

/**
 * {@link TableGenerator}のメタデータを保持するクラス。
 * 
 * @author j5ik2o
 */
public class TableIdGeneratorMeta extends AbstractPreAllocateIdGeneratorMeta {
	
	private String catalogName;
	
	private String pkColumnName;
	
	private String pkColumnValue;
	
	private String valueColumnName;
	
	private String schemaName;
	
	private String tableName;
	

	/**
	 * インスタンスを生成する。
	 * 
	 * @param entityMeta {@link EntityMeta}
	 * @param propertyMeta {@link PropertyMeta}
	 * @param tableGenerator {@link TableGenerator}
	 */
	public TableIdGeneratorMeta(EntityMeta entityMeta, PropertyMeta propertyMeta, TableGenerator tableGenerator) {
		super(entityMeta, propertyMeta, tableGenerator.name(), tableGenerator.initialValue(), tableGenerator
			.allocationSize());
		catalogName = tableGenerator.catalog();
		pkColumnName = tableGenerator.pkColumnName();
		pkColumnValue = tableGenerator.pkColumnValue();
		schemaName = tableGenerator.schema();
		tableName = tableGenerator.table();
	}
	
	/**
	 * 採番テーブルのカタログ名を取得する。
	 * 
	 * @return 採番テーブルのカタログ名
	 */
	public String getCatalogName() {
		return catalogName;
	}
	
	/**
	 * 採番テーブルのカラム名を取得する。
	 * 
	 * @return 採番テーブルのカラム名
	 */
	public String getPkColumnName() {
		return pkColumnName;
	}
	
	/**
	 * 採番テーブルの識別子の値を取得する。
	 * 
	 * @return 採番テーブルの識別子の値
	 */
	public String getPkColumnValue() {
		return pkColumnValue;
	}
	
	/**
	 *  採番テーブルの識別子の値を取得する。
	 * 
	 * @return 採番テーブルの識別子の値
	 */
	public String getSchemaName() {
		return schemaName;
	}
	
	/**
	 * 採番テーブル名を取得する。
	 * 
	 * @return 採番テーブル名
	 */
	public String getTableName() {
		return tableName;
	}
	
	/**
	 * 採番テーブルの値のカラム名を取得する。
	 * 
	 * @return 採番テーブルの値のカラム名
	 */
	public String getValueColumnName() {
		return valueColumnName;
	}
	
}
