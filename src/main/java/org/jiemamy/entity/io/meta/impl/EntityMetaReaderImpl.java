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
package org.jiemamy.entity.io.meta.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.Entity;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.jiemamy.entity.io.meta.EntityClassNotFoundException;
import org.jiemamy.entity.io.meta.EntityMeta;
import org.jiemamy.entity.io.meta.EntityMetaCommand;
import org.jiemamy.entity.io.meta.EntityMetaFactory;
import org.jiemamy.entity.io.meta.EntityMetaReader;
import org.jiemamy.entity.io.meta.EntityMetaReaderContext;
import org.jiemamy.entity.io.meta.NonEntityException;
import org.jiemamy.utils.ClassLoaderUtil;
import org.jiemamy.utils.ClassTraversal;
import org.jiemamy.utils.ClassUtil;
import org.jiemamy.utils.CollectionsUtil;
import org.jiemamy.utils.FileUtil;
import org.jiemamy.utils.LogMarker;
import org.jiemamy.utils.TraversalHandlerException;
import org.jiemamy.utils.ClassTraversal.ClassHandler;

/**
 * {@link EntityMetaReader}の実装クラス。
 * 
 * @author j5ik2o
 */
public class EntityMetaReaderImpl implements EntityMetaReader {
	
	private static final Logger LOG = LoggerFactory.getLogger(EntityMetaReaderImpl.class);
	
	private final EntityMetaReaderContext entityMetaReaderContext;
	
	private EntityMetaCommand logicalNameReadCommand = new EntityLogicalNameReadCommand();
	

	/**
	* インスタンスを生成する。
	* 
	* @param entityMetaReaderContext コンテキスト
	*/
	public EntityMetaReaderImpl(EntityMetaReaderContext entityMetaReaderContext) {
		Validate.notNull(entityMetaReaderContext);
		Validate.notNull(entityMetaReaderContext.getClassPathDirs());
		Validate.notEmpty(entityMetaReaderContext.getClassPathDirs());
		Validate.notNull(entityMetaReaderContext.getJavaFileEncoding());
		Validate.notNull(entityMetaReaderContext.getPackageName());
		Validate.notNull(entityMetaReaderContext.getEntityMetaFactory());
		this.entityMetaReaderContext = entityMetaReaderContext;
	}
	
	/**
	 * クラスローダを生成する。
	 * 
	 * @return {@link ClassLoader}
	 * @throws IOException 入出力が失敗した場合
	 */
	private ClassLoader createClassLoader() throws IOException {
		List<URL> urlList = toURLs(entityMetaReaderContext.getClassPathDirs());
		final URLClassLoader urlClassLoader = new URLClassLoader(urlList.toArray(new URL[urlList.size()]));
		return urlClassLoader;
	}
	
	private boolean isIgnoreShortClassName(String shortClassName) {
		Validate.notNull(shortClassName);
		if (entityMetaReaderContext.getIgnoreShortClassNamePatterns().isEmpty()) {
			return false;
		}
		for (Pattern pattern : entityMetaReaderContext.getIgnoreShortClassNamePatterns()) {
			if (pattern.matcher(shortClassName).matches()) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isShortClassName(String shortClassName) {
		Validate.notNull(shortClassName);
		if (entityMetaReaderContext.getShortClassNamePatterns().isEmpty()) {
			return true;
		}
		for (Pattern pattern : entityMetaReaderContext.getShortClassNamePatterns()) {
			if (pattern.matcher(shortClassName).matches()) {
				return true;
			}
		}
		return false;
		
	}
	
	public List<EntityMeta> read() throws IOException, EntityClassNotFoundException {
		final EntityMetaFactory factory = entityMetaReaderContext.getEntityMetaFactory();
		try {
			final List<EntityMeta> entityMetas = CollectionsUtil.newArrayList();
			final String entityFullPackageName = entityMetaReaderContext.getPackageName();
			final ClassLoader classLoader = createClassLoader();
			for (File classPathDir : entityMetaReaderContext.getClassPathDirs()) {
				
				try {
					ClassTraversal.forEach(classPathDir, new ClassHandler() {
						
						public void processClass(String packageName, String shortClassName)
								throws TraversalHandlerException {
							if (packageName.equals(entityFullPackageName) == false) {
								return;
							}
							if (isIgnoreShortClassName(shortClassName)) {
								return;
							}
							if (isShortClassName(shortClassName) == false) {
								return;
							}
							try {
								String entityClassName = ClassUtil.concatName(packageName, shortClassName);
								Class<?> entityClass = ClassLoaderUtil.loadClass(classLoader, entityClassName);
								if (entityClass.isAnnotationPresent(Entity.class)) {
									EntityMeta entityMeta = factory.getEntityMeta(entityClass);
									LOG.debug(LogMarker.DETAIL, entityClassName);
									entityMetas.add(entityMeta);
								}
							} catch (ClassNotFoundException e) {
								throw new TraversalHandlerException(e);
							} catch (NonEntityException e) {
								throw new TraversalHandlerException(e);
							}
						}
					});
				} catch (TraversalHandlerException e) {
					throw new IOException();
				}
			}
			if (entityMetas.isEmpty()) {
				throw new EntityClassNotFoundException();
			}
			if (entityMetaReaderContext.isReadComment()) {
				readComment(entityMetas);
			}
			return entityMetas;
		} finally {
			factory.dispose();
		}
	}
	
	/**
	 * コメントを読みコメントをメタデータに設定する。
	 * 
	 * @param entityMetaList エンティティメタデータのリスト
	 * @throws IOException 入出力が失敗した場合
	 */
	protected void readComment(List<EntityMeta> entityMetaList) throws IOException {
		logicalNameReadCommand.execute(entityMetaList);
	}
	
	private List<URL> toURLs(List<File> files) throws MalformedURLException {
		List<URL> urlList = new ArrayList<URL>();
		for (File classPathDir : entityMetaReaderContext.getClassPathDirs()) {
			urlList.add(FileUtil.toURL(classPathDir));
		}
		return urlList;
	}
}
