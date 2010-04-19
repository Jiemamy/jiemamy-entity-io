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
package org.jiemamy.entity.io.convensition;

/**
 * 永続化層の命名規約を表すインターフェース。
 * 
 * @author j5ik2o
 */
public interface PersistenceConvention {
	
	/**
	 * カラム名をプロパティ名に変換する。
	 * 
	 * @param columnName カラム名
	 * @return プロパティ名
	 */
	String fromColumnNameToPropertyName(String columnName);
	
	/**
	 * エンティティ名をテーブル名に変更する。
	 * 
	 * @param entityName エンティティ名
	 * @return テーブル名
	 */
	String fromEntityNameToTableName(String entityName);
	
	/**
	 * フィールド名をプロパティ名に変換する。
	 * 
	 * @param fieldName フィールド名
	 * @return プロパティ名
	 */
	String fromFieldNameToPropertyName(String fieldName);
	
	/**
	 * プロパティ名をカラム名に変換する。
	 * 
	 * @param propertyName プロパティ名
	 * @return カラム名
	 */
	String fromPropertyNameToColumnName(String propertyName);
	
	/**
	 * プロパティ名をフィールド名に変換する。
	 * 
	 * @param propertyName プロパティ名
	 * @return フィールド名
	 */
	String fromPropertyNameToFieldName(String propertyName);
	
	/**
	 * テーブル名をエンティティ名に変換する。
	 * 
	 * @param tableName テーブル名
	 * @return エンティティ名
	 */
	String fromTableNameToEntityName(String tableName);
}
