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

import org.apache.commons.lang.builder.ToStringBuilder;

import org.jiemamy.utils.CollectionsUtil;

/**
 * {@link EntityMetaReader}のコンテキストクラス。
 * 
 * @author j5ik2o
 */
public class DefaultEntityMetaReaderContext implements EntityMetaReaderContext {
	
	private List<File> classPathDirs;
	
	private String packageName;
	
	private EntityMetaFactory entityMetaFactory;
	
	private List<Pattern> shortClassNamePatterns = CollectionsUtil.newArrayList();
	
	private List<Pattern> ignoreShortClassNamePatterns = CollectionsUtil.newArrayList();
	
	private boolean readComment;
	
	private List<File> javaSrcFileDirs;
	
	private String javaFileEncoding;
	

	public List<File> getClassPathDirs() {
		return classPathDirs;
	}
	
	public EntityMetaFactory getEntityMetaFactory() {
		return entityMetaFactory;
	}
	
	public List<Pattern> getIgnoreShortClassNamePatterns() {
		return ignoreShortClassNamePatterns;
	}
	
	public String getJavaFileEncoding() {
		return javaFileEncoding;
	}
	
	public List<File> getJavaSrcFileDirs() {
		return javaSrcFileDirs;
	}
	
	public String getPackageName() {
		return packageName;
	}
	
	public List<Pattern> getShortClassNamePatterns() {
		return shortClassNamePatterns;
	}
	
	public boolean isReadComment() {
		return readComment;
	}
	
	/**
	 * クラスパスのリストを設定する。
	 * 
	 * @param classPathDirs クラスパスのリスト
	 */
	public void setClassPathDirs(List<File> classPathDirs) {
		this.classPathDirs = classPathDirs;
	}
	
	/**
	 * {@link EntityMetaFactory}を設定する。
	 * 
	 * @param entityMetaFactory {@link EntityMetaFactory}
	 */
	public void setEntityMetaFactory(EntityMetaFactory entityMetaFactory) {
		this.entityMetaFactory = entityMetaFactory;
	}
	
	/**
	 * 無視する短いクラス名のパターンのリストを設定する。
	 * 
	 * @param ignoreShortClassNamePatterns 無視する短いクラス名のパターンのリスト
	 */
	public void setIgnoreShortClassNamePatterns(List<Pattern> ignoreShortClassNamePatterns) {
		this.ignoreShortClassNamePatterns = ignoreShortClassNamePatterns;
	}
	
	/**
	 * Javaファイルのエンコーディングを設定する。
	 * 
	 * @param javaFileEncoding Javaファイルのエンコーディング
	 */
	public void setJavaFileEncoding(String javaFileEncoding) {
		this.javaFileEncoding = javaFileEncoding;
	}
	
	/**
	 * Javaソースファイルのディレクトリのリストを設定する。
	 * 
	 * @param javaFileSrcDirs Javaソースファイルのディレクトリのリスト
	 */
	public void setJavaSrcFileDirs(List<File> javaFileSrcDirs) {
		javaSrcFileDirs = javaFileSrcDirs;
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
	 * コメントを処理するかどうかのフラグを設定する。
	 * 
	 * @param readComment コメント処理フラグ
	 */
	public void setReadComment(boolean readComment) {
		this.readComment = readComment;
	}
	
	/**
	 * 短いクラス名のパターンのリストを取得する。
	 * 
	 * @param shortClassNamePatterns 短いクラス名のパターンのリストを
	 */
	public void setShortClassNamePatterns(List<Pattern> shortClassNamePatterns) {
		this.shortClassNamePatterns = shortClassNamePatterns;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
