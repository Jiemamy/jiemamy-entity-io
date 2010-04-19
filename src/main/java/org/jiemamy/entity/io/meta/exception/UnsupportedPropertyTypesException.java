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
 * サポートしていないプロパティの型の例外クラス。
 * 
 * @author j5i2ko
 */
@SuppressWarnings("serial")
public class UnsupportedPropertyTypesException extends Exception {
	
	/**
	 * インスタンスを生成する。
	 * 
	 * @param entityName エンティティ名
	 * @param propertyName プロパティ名
	 * @param propertyClass プロパティクラス
	 */
	public UnsupportedPropertyTypesException(String entityName, String propertyName, Class<?> propertyClass) {
		super(String.format("Unsupported property types.(entityName = %s, propertyName = %s, propertyClassName = %s)",
				entityName, propertyName, propertyClass.getName()));
	}
	
}
