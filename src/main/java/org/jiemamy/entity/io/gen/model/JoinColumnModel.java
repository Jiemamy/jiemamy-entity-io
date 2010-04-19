/*
 * Copyright 2007-2009 Jiemamy Project and the Others.
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
package org.jiemamy.entity.io.gen.model;

/**
 * 結合カラムモデル。
 * 
 * @author j5ik2o
 */
public class JoinColumnModel {
	
	/** 参照する側のカラム名 */
	protected String name;
	
	/** 参照される側のカラム名 */
	protected String referencedColumnName;
	

	/**
	 * 参照する側のカラム名を取得する。
	 * 
	 * @return 参照する側のカラム名
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 参照される側のカラム名を取得する。
	 * 
	 * @return 参照される側のカラム名
	 */
	public String getReferencedColumnName() {
		return referencedColumnName;
	}
	
	/**
	 * 参照する側のカラム名を取得する。
	 * 
	 * @param name 参照する側のカラム名
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 参照される側のカラム名を設定する。
	 * 
	 * @param referencedColumnName 参照される側のカラム名
	 */
	public void setReferencedColumnName(String referencedColumnName) {
		this.referencedColumnName = referencedColumnName;
	}
	
}
