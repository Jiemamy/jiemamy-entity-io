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
package org.jiemamy.entity.io.meta.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.jiemamy.entity.io.meta.DefaultEntityMetaReaderContext;
import org.jiemamy.entity.io.meta.DefaultEntityMetaReaderFactory;
import org.jiemamy.entity.io.meta.EntityMeta;
import org.jiemamy.entity.io.meta.EntityMetaReader;
import org.jiemamy.entity.io.meta.RelationshipType;
import org.jiemamy.utils.CollectionsUtil;
import org.jiemamy.utils.ResourceUtil;

/**
 * TODO for kato
 * 
 * @author kato
 */
public class EntityMetaReaderImplTest {
	
	private EntityMetaReader entityMetaReader;
	
	private static final Logger LOG = LoggerFactory.getLogger(EntityMetaReaderImplTest.class);
	

	/**
	 * Test method for {@link org.jiemamy.entity.io.meta.impl.EntityMetaReaderImpl#read()}.
	 * @throws Exception 例外
	 */
	@Test
	public void testRead() throws Exception {
		DefaultEntityMetaReaderContext context = new DefaultEntityMetaReaderContext();
		context.setJavaFileEncoding("UTF-8");
		List<File> classPathDirs = CollectionsUtil.newArrayList();
		classPathDirs.add(ResourceUtil.getBuildDirNoException(example.entity.Employee.class).getCanonicalFile());
		context.setClassPathDirs(classPathDirs);
		context.setPackageName("example.entity");
		context.setReadComment(true);
		List<File> javaFileSrcDirs = CollectionsUtil.newArrayList();
		javaFileSrcDirs.add(new File("src/test/java"));
		context.setJavaSrcFileDirs(javaFileSrcDirs);
		context.setEntityMetaFactory(new EntityMetaFactoryImpl(new TableMetaFactoryImpl(), new PropertyMetaFactoryImpl(
				new ColumnMetaFactoryImpl())));
		entityMetaReader = new DefaultEntityMetaReaderFactory().createEntityMetaReader(context);
		List<EntityMeta> entityMetas = entityMetaReader.read();
		
		EntityMeta em = entityMetas.get(0);
		if ("depertment".equals(em.getName())) {
			// entity name null check
			assertThat(em.getName(), is(notNullValue()));
			// entity name equal check
			assertThat(em.getName(), is("depertment"));
			LOG.debug(em.toString());
			
			// property null check
			assertThat(em.getPropertyMeta("depertmentId"), is(notNullValue()));
			assertThat(em.getPropertyMeta("depertmentName"), is(notNullValue()));
			assertThat(em.getPropertyMeta("version"), is(notNullValue()));
			/*
			// comment null check
			assertThat(EntityMetaUtil.getComment(em), is(notNullValue()));
			assertThat(PropertyMetaUtil.getComment(em.getPropertyMeta("depertmentId")), is(notNullValue()));
			assertThat(PropertyMetaUtil.getComment(em.getPropertyMeta("depertmentName")), is(notNullValue()));
			assertThat(PropertyMetaUtil.getComment(em.getPropertyMeta("version")), is(notNullValue()));
			// comment equal check
			assertThat(EntityMetaUtil.getComment(em), is("部署"));
			assertThat(PropertyMetaUtil.getComment(em.getPropertyMeta("depertmentId")), is("部署ID"));
			assertThat(PropertyMetaUtil.getComment(em.getPropertyMeta("depertmentName")), is("部署名"));
			assertThat(PropertyMetaUtil.getComment(em.getPropertyMeta("version")), is("バージョン"));
			*/
			// property name null check
			assertThat(em.getPropertyMeta("depertmentId").getName(), is(notNullValue()));
			assertThat(em.getPropertyMeta("depertmentName").getName(), is(notNullValue()));
			assertThat(em.getPropertyMeta("version").getName(), is(notNullValue()));
			// property name equal check
			assertThat(em.getPropertyMeta("depertmentId").getName(), is("depertmentId"));
			assertThat(em.getPropertyMeta("depertmentName").getName(), is("depertmentName"));
			assertThat(em.getPropertyMeta("version").getName(), is("version"));
			// property class null check
			assertThat(em.getPropertyMeta("depertmentId").getPropertyClass(), is(notNullValue()));
			assertThat(em.getPropertyMeta("depertmentName").getPropertyClass(), is(notNullValue()));
			assertThat(em.getPropertyMeta("version").getPropertyClass(), is(notNullValue()));
			// property class name null check
			assertThat(em.getPropertyMeta("depertmentId").getPropertyClass().getName(), is(notNullValue()));
			assertThat(em.getPropertyMeta("depertmentName").getPropertyClass().getName(), is(notNullValue()));
			assertThat(em.getPropertyMeta("version").getPropertyClass().getName(), is(notNullValue()));
			// property class name equal check
			assertThat(em.getPropertyMeta("depertmentId").getPropertyClass().getName(), is("java.lang.Long"));
			assertThat(em.getPropertyMeta("depertmentName").getPropertyClass().getName(), is("java.lang.String"));
			assertThat(em.getPropertyMeta("version").getPropertyClass().getName(), is("java.lang.Long"));
			// column name null check
			assertThat(em.getPropertyMeta("depertmentId").getColumnMeta().getName(), is(notNullValue()));
			assertThat(em.getPropertyMeta("depertmentName").getColumnMeta().getName(), is(notNullValue()));
			assertThat(em.getPropertyMeta("version").getColumnMeta().getName(), is(notNullValue()));
			// column name equal check
			assertThat(em.getPropertyMeta("depertmentId").getColumnMeta().getName(), is("DEPERTMENT_ID"));
			assertThat(em.getPropertyMeta("depertmentName").getColumnMeta().getName(), is("DEPERTMENT_NAME"));
			assertThat(em.getPropertyMeta("version").getColumnMeta().getName(), is("VERSION"));
			
			// property attribute check
			assertThat(em.getPropertyMeta("depertmentId").isId(), is(true));
			assertThat(em.getPropertyMeta("version").isVersion(), is(true));
		}
		em = entityMetas.get(1);
		if ("employee".equals(em.getName())) {
			// entity name null check
			assertThat(em.getName(), is(notNullValue()));
			// entity name equal check
			assertThat(em.getName(), is("employee"));
			LOG.debug(em.toString());
			// property null check
			assertThat(em.getPropertyMeta("employeeId"), is(notNullValue()));
			assertThat(em.getPropertyMeta("employeeName"), is(notNullValue()));
			assertThat(em.getPropertyMeta("version"), is(notNullValue()));
			assertThat(em.getPropertyMeta("depertment"), is(notNullValue()));
			/*
			// comment null check
			assertThat(EntityMetaUtil.getComment(em), is("従業員"));
			assertThat(PropertyMetaUtil.getComment(em.getPropertyMeta("employeeId")), is(notNullValue()));
			assertThat(PropertyMetaUtil.getComment(em.getPropertyMeta("employeeName")), is(notNullValue()));
			assertThat(PropertyMetaUtil.getComment(em.getPropertyMeta("version")), is(notNullValue()));
			// comment equal check
			assertThat(EntityMetaUtil.getComment(em), is("従業員"));
			assertThat(PropertyMetaUtil.getComment(em.getPropertyMeta("employeeId")), is("従業員ID"));
			assertThat(PropertyMetaUtil.getComment(em.getPropertyMeta("employeeName")), is("従業員名"));
			assertThat(PropertyMetaUtil.getComment(em.getPropertyMeta("version")), is("バージョン"));
			*/
			// property name null check
			assertThat(em.getPropertyMeta("employeeId").getName(), is(notNullValue()));
			assertThat(em.getPropertyMeta("employeeName").getName(), is(notNullValue()));
			assertThat(em.getPropertyMeta("version").getName(), is(notNullValue()));
			// property name equal check
			assertThat(em.getPropertyMeta("employeeId").getName(), is("employeeId"));
			assertThat(em.getPropertyMeta("employeeName").getName(), is("employeeName"));
			assertThat(em.getPropertyMeta("version").getName(), is("version"));
			// property class null check
			assertThat(em.getPropertyMeta("employeeId").getPropertyClass(), is(notNullValue()));
			assertThat(em.getPropertyMeta("employeeName").getPropertyClass(), is(notNullValue()));
			assertThat(em.getPropertyMeta("version").getPropertyClass(), is(notNullValue()));
			// property class name null check
			assertThat(em.getPropertyMeta("employeeId").getPropertyClass().getName(), is(notNullValue()));
			assertThat(em.getPropertyMeta("employeeName").getPropertyClass().getName(), is(notNullValue()));
			assertThat(em.getPropertyMeta("version").getPropertyClass().getName(), is(notNullValue()));
			// property class name equal check
			assertThat(em.getPropertyMeta("employeeId").getPropertyClass().getName(), is("java.lang.Long"));
			assertThat(em.getPropertyMeta("employeeName").getPropertyClass().getName(), is("java.lang.String"));
			assertThat(em.getPropertyMeta("version").getPropertyClass().getName(), is("java.lang.Long"));
			// column name null check
			assertThat(em.getPropertyMeta("employeeId").getColumnMeta().getName(), is(notNullValue()));
			assertThat(em.getPropertyMeta("employeeName").getColumnMeta().getName(), is(notNullValue()));
			assertThat(em.getPropertyMeta("version").getColumnMeta().getName(), is(notNullValue()));
			// column name equal check
			assertThat(em.getPropertyMeta("employeeId").getColumnMeta().getName(), is("EMPLOYEE_ID"));
			assertThat(em.getPropertyMeta("employeeName").getColumnMeta().getName(), is("EMPLOYEE_NAME"));
			assertThat(em.getPropertyMeta("version").getColumnMeta().getName(), is("VERSION"));
			// property attribute check
			assertThat(em.getPropertyMeta("employeeId").isId(), is(true));
			assertThat(em.getPropertyMeta("version").isVersion(), is(true));
			assertThat(em.getPropertyMeta("depertment").isRelationship(), is(true));
			assertThat(em.getPropertyMeta("depertment").getRelationshipClass(), is(notNullValue()));
			assertThat(em.getPropertyMeta("depertment").getRelationshipType(), is(notNullValue()));
			assertThat(em.getPropertyMeta("depertment").getRelationshipClass().getName(),
					is("example.entity.Depertment"));
			assertThat(em.getPropertyMeta("depertment").getRelationshipType(), is(RelationshipType.MANY_TO_ONE));
		}
	}
}
