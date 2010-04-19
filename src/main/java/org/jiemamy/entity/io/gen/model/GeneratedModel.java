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
 * 生成されるモデル。
 * 
 * @author j5ik2o
 */
public abstract class GeneratedModel {
	
	/** 生成情報のリスト */
	protected List<String> generatedInfoList = new ArrayList<String>();
	

	/**
	 * 生成情報を追加する。
	 * 
	 * @param generatedInfo 生成情報
	 */
	public void addGeneratedInfo(String generatedInfo) {
		generatedInfoList.add(generatedInfo);
	}
	
	/**
	 * 生成情報のリストを取得する。
	 * 
	 * @return 生成情報のリスト
	 */
	public List<String> getGeneratedInfoList() {
		return Collections.unmodifiableList(generatedInfoList);
	}
	
}