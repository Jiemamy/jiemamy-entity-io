/*
 * Copyright 2007-2009 Jiemamy Project and the Others.
 * Created on Apr 3, 2009
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
package org.jiemamy.entity.io.meta;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * JPA用のテーブルメタデータクラス。
 * 
 * @author j5ik2o
 * 
 */
public class TableMeta {
	
	/** 名前 */
	protected String name;
	
	/** 論理名 */
	protected String logicalName;
	
	/** カタログ */
	protected String catalog;
	
	/** スキーマ */
	protected String schema;
	

	/**
	 * カタログを取得する。
	 * 
	 * @return カタログ
	 */
	public String getCatalog() {
		return catalog;
	}
	
	/**
	 * カタログやスキーマを含んだ完全な名前を取得する。
	 * 
	 * @return カタログやスキーマを含んだ完全な名前
	 */
	public String getFullName() {
		StringBuilder buf = new StringBuilder();
		if (catalog != null) {
			buf.append(catalog).append(".");
		}
		if (schema != null) {
			buf.append(schema).append(".");
		}
		return buf.append(name).toString();
	}
	
	/**
	 * 論理名を取得する。
	 * 
	 * @return 論理名
	 */
	public String getLogicalName() {
		return logicalName;
	}
	
	/**
	 * 名前を取得する。
	 * 
	 * @return 名前
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * スキーマを取得する。
	 * 
	 * @return スキーマ
	 */
	public String getSchema() {
		return schema;
	}
	
	/**
	 * カタログを設定する。
	 * 
	 * @param catalog カタログ
	 */
	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}
	
	/**
	 * 論理名を設定する。
	 * 
	 * @param logicalName 論理名
	 */
	public void setLogicalName(String logicalName) {
		this.logicalName = logicalName;
	}
	
	/**
	 * 名前を設定する。
	 * 
	 * @param name 名前
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * スキーマを設定する。
	 * 
	 * @param schema スキーマ
	 */
	public void setSchema(String schema) {
		this.schema = schema;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
