/*
 * Copyright 2007-2009 Jiemamy Project and the Others.
 * Created on Apr 4, 2009
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

/**
 * エンティティではない場合の例外クラス。
 * 
 * @author j5ik2o
 */
@SuppressWarnings("serial")
public class NonEntityException extends Exception {
	
	/**
	 * インスタンスを生成する。
	 */
	public NonEntityException() {
		super();
	}
	
	/**
	 * インスタンスを生成する。
	 * 
	 * @param message メッセージ
	 */
	public NonEntityException(String message) {
		super(message);
	}
	
	/**
	 * インスタンスを生成する。
	 * 
	 * @param message メッセージ
	 * @param cause 原因
	 */
	public NonEntityException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * インスタンスを生成する。
	 * 
	 * @param cause 原因
	 */
	public NonEntityException(Throwable cause) {
		super(cause);
	}
	
}
