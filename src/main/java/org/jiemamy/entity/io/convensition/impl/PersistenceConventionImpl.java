/*
 * Copyright 2007-2009 Jiemamy Project and the Others.
 * Created on Apr 15, 2009
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
package org.jiemamy.entity.io.convensition.impl;

import org.apache.commons.lang.Validate;

import org.jiemamy.entity.io.convensition.PersistenceConvention;
import org.jiemamy.utils.StringUtil;

/**
 * {@link PersistenceConvention}の実装クラス。
 * 
 * @author j5ik2o
 */
public class PersistenceConventionImpl implements PersistenceConvention {
	
	private String ignoreTablePrefix;
	
	private boolean noNameConversion = false;
	

	public String fromColumnNameToPropertyName(String columnName) {
		Validate.notNull(columnName);
		if (noNameConversion) {
			return columnName;
		}
		return StringUtil.decapitalize(StringUtil.camelize(columnName));
	}
	
	public String fromEntityNameToTableName(String entityName) {
		Validate.notNull(entityName);
		if (noNameConversion) {
			return entityName;
		}
		String tableName = StringUtil.decamelize(entityName);
		if (ignoreTablePrefix != null) {
			tableName = ignoreTablePrefix + tableName;
		}
		return tableName;
	}
	
	public String fromFieldNameToPropertyName(String fieldName) {
		return fieldName;
	}
	
	public String fromPropertyNameToColumnName(String propertyName) {
		Validate.notNull(propertyName);
		if (noNameConversion) {
			return propertyName;
		}
		return StringUtil.decamelize(propertyName);
	}
	
	public String fromPropertyNameToFieldName(String propertyName) {
		return propertyName;
	}
	
	public String fromTableNameToEntityName(String tableName) {
		Validate.notNull(tableName);
		if (noNameConversion) {
			return tableName;
		}
		return StringUtil.camelize(StringUtil.trimPrefix(tableName, ignoreTablePrefix));
	}
	
	/**
	 * 無視するテーブルの<code>prefix</code>を取得する。
	 * 
	 * @return 無視するテーブルの<code>prefix</code>
	 */
	public String getIgnoreTablePrefix() {
		return ignoreTablePrefix;
	}
	
	/**
	 * 名前を変換しないかどうかを返します。
	 * 
	 * @return 名前を変換しないかどうか
	 */
	public boolean isNoNameConversion() {
		return noNameConversion;
	}
	
	/**
	 * 無視するテーブルの<code>prefix</code>を設定します。
	 * 
	 * @param ignoreTablePrefix 無視するテーブルの<code>prefix</code>
	 */
	public void setIgnoreTablePrefix(String ignoreTablePrefix) {
		this.ignoreTablePrefix = ignoreTablePrefix;
	}
	
	/**
	 * 名前を変換しないかどうかを設定します。
	 * 
	 * @param noNameConversion trueの場合変換しない
	 */
	public void setNoNameConversion(boolean noNameConversion) {
		this.noNameConversion = noNameConversion;
	}
}
