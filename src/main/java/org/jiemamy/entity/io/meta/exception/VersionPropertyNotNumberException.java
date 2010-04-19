/*
 * Copyright 2007-2009 Jiemamy Project and the Others.
 * Created on Apr 6, 2009
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
package org.jiemamy.entity.io.meta.exception;

/**
 * バージョンプロパティが数値型でない場合の例外クラス。
 * 
 * @author j5ik2o
 */
@SuppressWarnings("serial")
public class VersionPropertyNotNumberException extends Exception {
	
	/**
	 * インスタンスを生成する。
	 * 
	 * @param entityName エンティティ名
	 * @param propertyName プロパティ名
	 */
	public VersionPropertyNotNumberException(String entityName, String propertyName) {
		super(String.format("Version property is not number.(entityName = %s, propertyName = %s)", entityName,
				propertyName));
	}
	
}
