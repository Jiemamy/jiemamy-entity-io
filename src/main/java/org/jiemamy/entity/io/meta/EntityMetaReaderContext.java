/*
 * Copyright 2007-2009 Jiemamy Project and the Others.
 * Created on Apr 3, 2009
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

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

/**
 * {@link EntityMetaReader}のコンテキストインターフェイス。
 * 
 * @author j5ik2o
 */
public interface EntityMetaReaderContext {
	
	/**
	 * クラスパスのリストを取得する。
	 * 
	 * @return クラスパスのリスト
	 */
	List<File> getClassPathDirs();
	
	/**
	 * {@link EntityMetaFactory}を取得する。
	 * 
	 * @return {@link EntityMetaFactory}
	 */
	EntityMetaFactory getEntityMetaFactory();
	
	/**
	 * 無視するクラス名のパターンのリストを取得する。
	 * 
	 * @return 無視するクラス名パターンのリスト
	 */
	List<Pattern> getIgnoreShortClassNamePatterns();
	
	/**
	 * Javaファイルのエンコーディングを取得する。
	 * 
	 * @return Javaファイルのエンコーディング
	 */
	String getJavaFileEncoding();
	
	/**
	 * Javaソースファイルのディレクトリのリストを取得する。
	 * 
	 * @return Javaソースファイルのディレクトリのリスト
	 */
	List<File> getJavaSrcFileDirs();
	
	/**
	 * パッケージ名を取得する。
	 * 
	 * @return パッケージ名
	 */
	String getPackageName();
	
	/**
	 * 短いクラス名のパターンのリストを取得する。
	 * 
	 * @return 短いクラス名のパターンのリスト
	 */
	List<Pattern> getShortClassNamePatterns();
	
	/**
	 * コメントを処理するかどうかのフラグを取得する。
	 * 
	 * @return コメント処理フラグ
	 */
	boolean isReadComment();
	
}
