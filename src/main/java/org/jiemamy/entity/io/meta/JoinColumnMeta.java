/*
 * Copyright 2007-2009 Jiemamy Project and the Others.
 * Created on Apr 5, 2009
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

import org.apache.commons.lang.StringUtils;

/**
 * 関連のカラムを指定するためのメタデータ。
 * 
 * @author j5ik2o
 */
public class JoinColumnMeta {
	
	/** 外部キーのカラム名 */
	private String name;
	
	/** 関連テーブルの主キーのカラム名 */
	private String referencedColumnName;
	

	/**
	 * {@link JoinColumnMeta}を作成する。
	 */
	public JoinColumnMeta() {
	}
	
	/**
	 * {@link JoinColumnMeta}を作成する。
	 * 
	 * @param name 外部キーのカラム名
	 * @param referencedColumnName 関連テーブルの主キーのカラム名
	 */
	public JoinColumnMeta(String name, String referencedColumnName) {
		if (StringUtils.isBlank(name) == false) {
			this.name = name;
		}
		if (StringUtils.isBlank(referencedColumnName) == false) {
			this.referencedColumnName = referencedColumnName;
		}
	}
	
	/**
	 * 外部キーのカラム名を取得する。
	 * 
	 * @return 外部キーのカラム名
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 関連テーブルの主キーのカラム名を取得する。
	 * 
	 * @return 関連テーブルの主キーのカラム名
	 */
	public String getReferencedColumnName() {
		return referencedColumnName;
	}
	
	/**
	 * 外部キーのカラム名を設定する。
	 * 
	 * @param name 外部キーのカラム名
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 関連テーブルの主キーのカラム名を設定する。
	 * 
	 * @param referencedColumnName 関連テーブルの主キーのカラム名
	 */
	public void setReferencedColumnName(String referencedColumnName) {
		this.referencedColumnName = referencedColumnName;
	}
}
