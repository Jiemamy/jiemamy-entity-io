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
 * データベースの一意キーメタデータ。
 * 
 * @author j5ik2o
 */
public class DbUniqueKeyMeta {
	
	/** 名前 */
	protected String name;
	
	/** 主キーの場合{@code true} */
	protected boolean primaryKey;
	
	/** カラム名 */
	protected List<String> columnNameList = new ArrayList<String>();
	

	/**
	 * カラム名を追加する。
	 * 
	 * @param columnName カラム名
	 */
	public void addColumnName(String columnName) {
		columnNameList.add(columnName);
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
	 * 名前を取得する。
	 * 
	 * @return 名前
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 複合一意キーの場合{@code true}、そうでない場合{@code false}を取得する。
	 * 
	 * @return 複合一意キーの場合{@code true}、そうでない場合{@code false}
	 */
	public boolean isComposite() {
		return columnNameList.size() > 1;
	}
	
	/**
	 * 主キーの場合{@code true}、そうでない場合{@code false}を取得する。
	 * 
	 * @return 主キーの場合{@code true}、そうでない場合{@code false}
	 */
	public boolean isPrimaryKey() {
		return primaryKey;
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
	 * 主キーの場合{@code true}、そうでない場合{@code false}を設定する。
	 * 
	 * @param primaryKey 主キーの場合{@code true}、そうでない場合{@code false}
	 */
	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}
	
}
