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
package org.jiemamy.entity.io.gen.generator.impl;

import java.io.File;

import org.jiemamy.entity.io.gen.generator.GeneratorContext;


/**
 * {@link GeneratorContext}の実装クラス。
 * 
 * @author j5ik2o
 */
public class GeneratorContextImpl implements GeneratorContext {
	
	private String encoding;
	
	private File file;
	
	private Object model;
	
	private String templateName;
	
	private boolean overwrite;
	

	public String getEncoding() {
		return encoding;
	}
	
	public File getFile() {
		return file;
	}
	
	public Object getModel() {
		return model;
	}
	
	public String getTemplateName() {
		return templateName;
	}
	
	public boolean isOverwrite() {
		return overwrite;
	}
	
	/**
	 * エンコーディングを設定する。
	 * 
	 * @param encoding エンコーディング
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
	/**
	 * 生成するファイルを設定する。
	 * 
	 * @param file 生成するファイル
	 */
	public void setFile(File file) {
		this.file = file;
	}
	
	/**
	 * データモデルを設定する。
	 * 
	 * @param model データモデル
	 */
	public void setModel(Object model) {
		this.model = model;
	}
	
	/**
	 * 上書きフラグを設定する。
	 * 
	 * @param overwrite 上書きフラグ
	 */
	public void setOverwrite(boolean overwrite) {
		this.overwrite = overwrite;
	}
	
	/**
	 * テンプレート名を設定する。
	 * 
	 * @param templateName テンプレート名
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	
}
