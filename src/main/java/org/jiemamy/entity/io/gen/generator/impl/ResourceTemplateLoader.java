/*
 * Copyright 2007-2009 Jiemamy Project and the Others.
 * Created on Apr 7, 2009
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

import java.net.URL;

import freemarker.cache.TemplateLoader;
import freemarker.cache.URLTemplateLoader;

import org.apache.commons.lang.Validate;

import org.jiemamy.utils.ResourceUtil;

/**
 * リソースを扱う{@link TemplateLoader}の実装クラス。
 * <p>
 * JARファイルに含まれたリソースを扱う。
 * </p>
 * 
 * @author j5ik2o
 */
public class ResourceTemplateLoader extends URLTemplateLoader {
	
	/** ベースとなるパス */
	protected String basePath;
	

	/**
	 * インスタンスを生成する。
	 * 
	 * @param basePath ベースとなるパス
	 */
	public ResourceTemplateLoader(String basePath) {
		Validate.notNull(basePath);
		this.basePath = basePath;
	}
	
	@Override
	protected URL getURL(String name) {
		Validate.notNull(name);
		String path = basePath + "/" + name;
		URL result = ResourceUtil.getResourceNoException(path);
		return result;
	}
	
}
