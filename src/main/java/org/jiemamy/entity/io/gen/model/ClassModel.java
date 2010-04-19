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

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * クラスのモデル。
 * 
 * @author j5ik2o
 */
public abstract class ClassModel extends GeneratedModel {
	
	/** インポート名のソートされたセット */
	protected SortedSet<String> importNameSet = new TreeSet<String>();
	
	/** staticインポート名のソートされたセット */
	protected SortedSet<String> staticImportNameSet = new TreeSet<String>();
	
	/** パッケージ名 */
	protected String packageName;
	
	/** クラスの単純名 */
	protected String shortClassName;
	

	/**
	 * インポート名を追加する。
	 * 
	 * @param name インポート名
	 */
	public void addImportName(String name) {
		importNameSet.add(name);
	}
	
	/**
	 * staticインポート名を追加する。
	 * 
	 * @param name staticインポート名
	 */
	public void addStaticImportName(String name) {
		staticImportNameSet.add(name);
	}
	
	/**
	 * インポート名のソートされたセットを取得する。
	 * 
	 * @return インポート名のソートされたセット
	 */
	public SortedSet<String> getImportNameSet() {
		return Collections.unmodifiableSortedSet(importNameSet);
	}
	
	/**
	 * パッケージ名を取得する。
	 * 
	 * @return パッケージ名
	 */
	public String getPackageName() {
		return packageName;
	}
	
	/**
	 * クラスの単純名を取得する。
	 * 
	 * @return クラスの単純名
	 */
	public String getShortClassName() {
		return shortClassName;
	}
	
	/**
	 * staticインポート名のソートされたセットを取得する。
	 * 
	 * @return staticインポート名のソートされたセット
	 */
	public SortedSet<String> getStaticImportNameSet() {
		return Collections.unmodifiableSortedSet(staticImportNameSet);
	}
	
	/**
	 * パッケージ名を設定する。
	 * 
	 * @param packageName パッケージ名
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	/**
	 * クラスの単純名を設定する。
	 * 
	 * @param shortClassName クラスの単純名
	 */
	public void setShortClassName(String shortClassName) {
		this.shortClassName = shortClassName;
	}
	
}
