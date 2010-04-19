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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 複合一意制約モデル。
 * 
 * @author j5ik2o
 */
public class CompositeUniqueConstraintModel {
	
	/** カラム名のリスト */
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
}
