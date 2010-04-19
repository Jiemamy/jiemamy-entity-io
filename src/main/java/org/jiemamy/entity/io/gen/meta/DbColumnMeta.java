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
package org.jiemamy.entity.io.gen.meta;

/**
 * データベースのカラムメタデータです。
 * 
 * @author j5ik2o
 */
public class DbColumnMeta {
	
	/** 名前 */
	protected String name;
	
	/** SQL型 */
	protected int sqlType;
	
	/** 型名 */
	protected String typeName;
	
	/** 長さ */
	protected int length;
	
	/** スケール */
	protected int scale;
	
	/** デフォルト値 */
	protected String defaultValue;
	
	/** NULL可能の場合{@code true} */
	protected boolean nullable;
	
	/** 主キーの場合{@code true} */
	protected boolean primaryKey;
	
	/** 値が自動的に増分される場合{@code true} */
	protected boolean autoIncrement;
	
	/** 一意の場合{@code true} */
	protected boolean unique;
	
	/** コメント */
	protected String comment;
	

	/**
	 * コメントを取得する。
	 * 
	 * @return コメント
	 */
	public String getComment() {
		return comment;
	}
	
	/**
	 * デフォルト値を取得する。
	 * 
	 * @return デフォルト値
	 */
	public String getDefaultValue() {
		return defaultValue;
	}
	
	/**
	 * 長さを取得する。
	 * 
	 * @return 長さ
	 */
	public int getLength() {
		return length;
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
	 * スケールを取得する。
	 * 
	 * @return スケール
	 */
	public int getScale() {
		return scale;
	}
	
	/**
	 * SQL型を取得する。
	 * 
	 * @return SQL型
	 */
	public int getSqlType() {
		return sqlType;
	}
	
	/**
	 * 型名を取得する。
	 * 
	 * @return 型名
	 */
	public String getTypeName() {
		return typeName;
	}
	
	/**
	 * 値が自動的に増分される場合{@code true}を取得する。
	 * 
	 * @return 値が自動的に増分される場合{@code true}、そうでない場合{@code false}
	 */
	public boolean isAutoIncrement() {
		return autoIncrement;
	}
	
	/**
	 * NULL可能の場合{@code true}を取得する。
	 * 
	 * @return NULL可能の場合{@code true}、そうでない場合{@code false}
	 */
	public boolean isNullable() {
		return nullable;
	}
	
	/**
	 * 主キーの場合{@code true}を取得する。
	 * 
	 * @return 主キーの場合{@code true}、そうでない場合{@code false}
	 */
	public boolean isPrimaryKey() {
		return primaryKey;
	}
	
	/**
	 * 一意の場合{@code true}、そうでない場合{@code false}を取得する。
	 * 
	 * @return 一意の場合{@code true}、そうでない場合{@code false}
	 */
	public boolean isUnique() {
		return unique;
	}
	
	/**
	 * 値が自動的に増分される場合{@code true}を設定する。
	 * 
	 * @param autoIncrement 値が自動的に増分される場合{@code true}、そうでない場合{@code false}
	 */
	public void setAutoIncrement(boolean autoIncrement) {
		this.autoIncrement = autoIncrement;
	}
	
	/**
	 * コメントを設定する。
	 * 
	 * @param comment コメント
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	/**
	 * デフォルト値を設定する
	 * 
	 * @param defaultValue デフォルト値
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	/**
	 * 長さを設定する。
	 * 
	 * @param length 長さ
	 */
	public void setLength(int length) {
		this.length = length;
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
	 * NULL可能の場合{@code true}を設定する。
	 * 
	 * @param nullable NULL可能の場合{@code true}
	 */
	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}
	
	/**
	 * 主キーの場合 {@code true}を設定する。
	 * 
	 * @param primaryKey 主キーの場合 {@code true}
	 * 
	 */
	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}
	
	/**
	 * スケールを設定する。
	 * 
	 * @param scale スケール
	 */
	public void setScale(int scale) {
		this.scale = scale;
	}
	
	/**
	 * SQL型をセットする。
	 * 
	 * @param sqlType SQL型
	 */
	public void setSqlType(int sqlType) {
		this.sqlType = sqlType;
	}
	
	/**
	 * SQL型の名前を設定する。
	 * 
	 * @param typeName SQL型の名前
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	/**
	 * 一意の場合{@code true}、そうでない場合{@code false}を設定する。
	 * 
	 * @param unique 一意の場合{@code true}
	 */
	public void setUnique(boolean unique) {
		this.unique = unique;
	}
	
}
