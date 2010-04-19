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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * データベースのテーブルメタデータ。
 * 
 * @author j5ik2o
 */
public class DbTableMeta {
	
	/** カタログ名 */
	protected String catalogName;
	
	/** スキーマ名 */
	protected String schemaName;
	
	/** 名前 */
	protected String name;
	
	/** 完全な名前 */
	protected String fullName;
	
	/** コメント */
	protected String comment;
	
	/** カラムメタデータのリスト */
	protected List<DbColumnMeta> columnMetaList = new ArrayList<DbColumnMeta>();
	
	/** 主キーのカラムメタデータのリスト */
	protected List<DbColumnMeta> primaryKeyColumnMetaList = new ArrayList<DbColumnMeta>();
	
	/** 外部キーメタデータのリスト */
	protected List<DbForeignKeyMeta> foreignKeyMetaList = new ArrayList<DbForeignKeyMeta>();
	
	/** 一意キーメタデータのリスト */
	protected List<DbUniqueKeyMeta> uniqueKeyMetaList = new ArrayList<DbUniqueKeyMeta>();
	

	/**
	 * カラムのメタデータを追加する。
	 * 
	 * @param columnMeta カラム記述
	 */
	public void addColumnMeta(DbColumnMeta columnMeta) {
		columnMetaList.add(columnMeta);
		if (columnMeta.isPrimaryKey()) {
			primaryKeyColumnMetaList.add(columnMeta);
		}
	}
	
	/**
	 * 外部キーメタデータを追加する。
	 * 
	 * @param foreignKeyMeta 外部キーメタデータ
	 */
	public void addForeignKeyMeta(DbForeignKeyMeta foreignKeyMeta) {
		foreignKeyMetaList.add(foreignKeyMeta);
	}
	
	/**
	 * 一意キーメタデータを追加する。
	 * 
	 * @param uniqueKeyMeta 一意キーメタデータ
	 */
	public void addUniqueKeyMeta(DbUniqueKeyMeta uniqueKeyMeta) {
		uniqueKeyMetaList.add(uniqueKeyMeta);
	}
	
	/**
	 * カタログ名を取得する。
	 * 
	 * @return カタログ名
	 */
	public String getCatalogName() {
		return catalogName;
	}
	
	/**
	 * カラムのメタデータのリストを取得する。
	 * 
	 * @return カラムのメタデータのリスト
	 */
	public List<DbColumnMeta> getColumnMetaList() {
		return Collections.unmodifiableList(columnMetaList);
	}
	
	/**
	 * コメントを取得する。
	 * 
	 * @return コメント
	 */
	public String getComment() {
		return comment;
	}
	
	/**
	 * 外部キーメタデータのリストを取得する。
	 * 
	 * @return 外部キーメタデータのリスト
	 */
	public List<DbForeignKeyMeta> getForeignKeyMetaList() {
		return Collections.unmodifiableList(foreignKeyMetaList);
	}
	
	/**
	 * 完全なテーブル名を取得する。
	 * 
	 * @return 完全なテーブル名
	 */
	public String getFullName() {
		return fullName;
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
	 * 主キーのカラムメタデータのリストを取得する。
	 * 
	 * @return 主キーのカラムメタデータのリスト
	 */
	public List<DbColumnMeta> getPrimaryKeyColumnMetaList() {
		return Collections.unmodifiableList(primaryKeyColumnMetaList);
	}
	
	/**
	 * スキーマ名を取得する。
	 * 
	 * @return スキーマ名
	 */
	public String getSchemaName() {
		return schemaName;
	}
	
	/**
	 * 一意キーメタデータのリストを取得する。
	 * 
	 * @return 一意キーメタデータのリスト
	 */
	public List<DbUniqueKeyMeta> getUniqueKeyMetaList() {
		return Collections.unmodifiableList(uniqueKeyMetaList);
	}
	
	/**
	 * 複合主キーを持つ場合{@code true}を取得する。
	 * 
	 * @return 複合主キーを持つ場合{@code true}、そうでない場合{@code false}
	 */
	public boolean hasCompositePrimaryKey() {
		return primaryKeyColumnMetaList.size() > 1;
	}
	
	/**
	 * カタログ名を設定する。
	 * 
	 * @param catalogName カタログ名
	 */
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
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
	 * 完全な名前を設定する。
	 * 
	 * @param fullName 完全な名前
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
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
	 * スキーマ名を設定する。
	 * 
	 * @param schemaName スキーマ名
	 */
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
}
