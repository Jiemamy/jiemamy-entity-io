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
package org.jiemamy.entity.io.gen.desc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 関連記述インターフェイス。
 * 
 * @author j5ik2o
 */
public class AssociationDesc {
	
	/** 名前 */
	protected String name;
	
	/** 関連先のエンティティ記述 */
	protected EntityDesc referencedEntityDesc;
	
	/** 関連タイプ */
	protected AssociationType associationType;
	
	/** 関連の所有者側のプロパティの名前 */
	protected String mappedBy;
	
	/** カラム名のリスト */
	protected List<String> columnNameList = new ArrayList<String>();
	
	/** 参照されるテーブルのカタログ名 */
	protected String referencedCatalogName;
	
	/** 参照されるテーブルのスキーマ名 */
	protected String referencedSchemaName;
	
	/** 参照されるテーブル名 */
	protected String referencedTableName;
	
	/** 参照されるカラム名のリスト */
	protected List<String> referencedColumnNameList = new ArrayList<String>();
	

	/**
	 * カラム名を追加する。
	 * 
	 * @param columnName カラム名
	 */
	public void addColumnName(String columnName) {
		columnNameList.add(columnName);
	}
	
	/**
	 * 参照されるカラム名を追加する。
	 * 
	 * @param referencedColumnName 参照されるカラム名
	 */
	public void addReferencedColumnName(String referencedColumnName) {
		referencedColumnNameList.add(referencedColumnName);
	}
	
	/**
	 * 関連タイプを取得する。
	 * 
	 * @return 関連タイプ
	 */
	public AssociationType getAssociationType() {
		return associationType;
	}
	
	/**
	 * カラム名のリストを取得する。
	 * 
	 * @return カラム名のリスト
	 */
	public List<String> getColumnNameList() {
		return Collections.unmodifiableList(columnNameList);
	}
	
	/**
	 * 関連の所有者側のプロパティの名前を取得する。
	 * 
	 * @return 関連の所有者側のプロパティの名前
	 */
	public String getMappedBy() {
		return mappedBy;
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
	 * 参照されるテーブルのカタログ名を取得する。
	 * 
	 * @return 参照されるテーブルのカタログ名
	 */
	public String getReferencedCatalogName() {
		return referencedCatalogName;
	}
	
	/**
	 * 参照されるカラム名のリストを取得する。
	 * 
	 * @return 参照されるカラム名のリスト
	 */
	public List<String> getReferencedColumnNameList() {
		return Collections.unmodifiableList(referencedColumnNameList);
	}
	
	/**
	 * 関連先のエンティティ記述を取得する。
	 * 
	 * @return 関連先のエンティティ記述
	 */
	public EntityDesc getReferencedEntityDesc() {
		return referencedEntityDesc;
	}
	
	/**
	 * 参照されるテーブルのスキーマ名を取得する。
	 * 
	 * @return 参照されるテーブルのスキーマ名
	 */
	public String getReferencedSchemaName() {
		return referencedSchemaName;
	}
	
	/**
	 * 参照されるテーブル名を取得する。
	 * 
	 * @return 参照されるテーブル名
	 */
	public String getReferencedTableName() {
		return referencedTableName;
	}
	
	/**
	 * 関連タイプを設定する。
	 * 
	 * @param associationType 関連タイプ
	 */
	public void setAssociationType(AssociationType associationType) {
		this.associationType = associationType;
	}
	
	/**
	 * 関連の所有者側のプロパティの名前を設定する。
	 * 
	 * @param mappedBy 関連の所有者側のプロパティの名前
	 */
	public void setMappedBy(String mappedBy) {
		this.mappedBy = mappedBy;
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
	 * 参照されるテーブルのカタログ名を設定する。
	 * 
	 * @param referencedCatalogName 参照されるテーブルのカタログ名
	 */
	public void setReferencedCatalogName(String referencedCatalogName) {
		this.referencedCatalogName = referencedCatalogName;
	}
	
	/**
	 * 関連先のエンティティ記述を設定する。
	 * 
	 * @param relationshipEntityDesc 関連先のエンティティ記述
	 */
	public void setReferencedEntityDesc(EntityDesc relationshipEntityDesc) {
		referencedEntityDesc = relationshipEntityDesc;
	}
	
	/**
	 * 参照されるテーブルのスキーマ名を設定する。
	 * 
	 * @param referencedSchemaName 参照されるテーブルのスキーマ名
	 */
	public void setReferencedSchemaName(String referencedSchemaName) {
		this.referencedSchemaName = referencedSchemaName;
	}
	
	/**
	 * 参照されるテーブル名を設定する。
	 * 
	 * @param referencedTableName 参照されるテーブル名
	 */
	public void setReferencedTableName(String referencedTableName) {
		this.referencedTableName = referencedTableName;
	}
	
}
