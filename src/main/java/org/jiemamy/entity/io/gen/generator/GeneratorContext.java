/*
 * Copyright 2007-2009 Jiemamy Project and the Others.
 * Created on Apr 13, 2009
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
package org.jiemamy.entity.io.gen.generator;

import java.io.File;

/**
 * {@link Generator}のためのコンテキストインターフェイス。
 * 
 * @author j5ik2o
 */
public interface GeneratorContext {
	
	/**
	 * エンコーディングを取得する。
	 * 
	 * @return エンコーディング
	 */
	String getEncoding();
	
	/**
	 * 生成するファイルを取得する。
	 * 
	 * @return 生成するファイル
	 */
	File getFile();
	
	/**
	 * データモデルを取得する。
	 * 
	 * @return データモデル
	 */
	Object getModel();
	
	/**
	 * テンプレート名を取得する。
	 * 
	 * @return テンプレート名
	 */
	String getTemplateName();
	
	/**
	 * 上書きフラグを取得する。
	 * 
	 * @return 上書きする場合は{@code true}、しない場合は{@code false}
	 */
	boolean isOverwrite();
}
