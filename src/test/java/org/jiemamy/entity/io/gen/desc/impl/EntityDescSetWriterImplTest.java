/*
 * Copyright 2007-2009 Jiemamy Project and the Others.
 * Created on Apr 14, 2009
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
package org.jiemamy.entity.io.gen.desc.impl;

import java.io.File;
import java.io.IOException;
import java.sql.Types;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.jiemamy.Jiemamy;
import org.jiemamy.dialect.generic.GenericDialect;
import org.jiemamy.entity.io.gen.desc.DefaultEntityDescSetWriterContext;
import org.jiemamy.entity.io.gen.desc.DefaultEntityDescSetWriterFactory;
import org.jiemamy.entity.io.gen.desc.EntityDescSetWriter;
import org.jiemamy.entity.io.gen.dialect.StandardGenDialect;
import org.jiemamy.entity.io.gen.meta.DbColumnMeta;
import org.jiemamy.entity.io.gen.meta.DbTableMeta;
import org.jiemamy.entity.io.gen.meta.DbTableMetaReader;
import org.jiemamy.internal.test.ReflectionDialectProvider;
import org.jiemamy.model.RootModel;
import org.jiemamy.utils.ClassUtil;
import org.jiemamy.utils.CollectionsUtil;
import org.jiemamy.utils.ResourceNotFoundException;
import org.jiemamy.utils.ResourceUtil;

/**
 * {@link EntityDescSetWriterImpl}のテスト。
 * 
 * @author j5ik2o
 */
public class EntityDescSetWriterImplTest {
	
	private static class DbTableMetaReaderImpl implements DbTableMetaReader {
		
		List<DbTableMeta> dbTableMetas = CollectionsUtil.newArrayList();
		

		public List<DbTableMeta> read() {
			return dbTableMetas;
		}
		
	}
	

	private EntityDescSetWriter entityDescSetWriter;
	
	private static final Logger LOG = LoggerFactory.getLogger(EntityDescSetWriterImplTest.class);
	
	private Jiemamy jiemamy;
	
	private RootModel rootModel;
	

	/**
	 * テストを初期化する。
	 * 
	 * @throws Exception 例外が発生した場合
	 */
	@Before
	public void setUp() throws Exception {
		jiemamy = Jiemamy.newInstance(new ReflectionDialectProvider());
		rootModel = jiemamy.getFactory().getRootModel();
		rootModel.setDialectClassName(GenericDialect.class.getName());
	}
	
	/**
	 * Test method for {@link org.jiemamy.entity.io.gen.desc.impl.EntityDescSetWriterImpl#write(java.util.List)}.
	 * @throws IOException 
	 * @throws IdentityNotSupportedException 
	 * @throws ClassNotFoundException 
	 * @throws ResourceNotFoundException 
	 */
	@Test
	@Ignore
	public void testWrite() throws IOException, IdentityNotSupportedException, ClassNotFoundException,
			ResourceNotFoundException {
		DefaultEntityDescSetWriterContext context = new DefaultEntityDescSetWriterContext();
		
		//context.setDialect(new TestDialect(jiemamy.getFactory(), jiemamy.getDialect(rootModel)));
		DbTableMetaReaderImpl dbTableMetaReader = new DbTableMetaReaderImpl();
		context.setDbTableMetaReader(dbTableMetaReader);
		context.setDialect(new StandardGenDialect());
		context.setUseAccessor(true);
		context.setOverwrite(true);
		context.setJavaSrcFileEncoding("UTF-8");
		context.setRootPackageName("test");
		context.setEntityPackageName("entity");
		context.setTemplateName("entity.ftl");
		context.setTemplateFileEncoding("UTF-8");
		context.setVersionColumnNamePattern(Pattern.compile("VERSION([_]?NO)?"));
		String packageName = ClassUtil.getPackageName(getClass());
		File dir = ResourceUtil.getResourceAsFile(packageName.replace('.', '/'));
		
		context.setJavaSrcFileDestDir(dir);
		entityDescSetWriter = new DefaultEntityDescSetWriterFactory().createEntityDescSetWriter(context);
		List<DbTableMeta> dbTableMetaList = dbTableMetaReader.dbTableMetas;
		DbTableMeta dbTableMeta = new DbTableMeta();
		dbTableMeta.setName("EMPLOYEE");
		
		DbColumnMeta columnMeta = new DbColumnMeta();
		columnMeta.setName("EMPLOYEE_ID");
		columnMeta.setNullable(false);
		columnMeta.setPrimaryKey(true);
		columnMeta.setSqlType(Types.BIGINT);
		columnMeta.setTypeName("BIGINT");
		dbTableMeta.addColumnMeta(columnMeta);
		
		columnMeta = new DbColumnMeta();
		columnMeta.setName("EMPLOYEE_NAME");
		columnMeta.setNullable(false);
		columnMeta.setSqlType(Types.VARCHAR);
		columnMeta.setTypeName("VARCHAR");
		dbTableMeta.addColumnMeta(columnMeta);
		
		columnMeta = new DbColumnMeta();
		columnMeta.setName("VERSION_NO");
		columnMeta.setNullable(false);
		columnMeta.setSqlType(Types.BIGINT);
		columnMeta.setTypeName("BIGINT");
		dbTableMeta.addColumnMeta(columnMeta);
		
		dbTableMetaList.add(dbTableMeta);
		entityDescSetWriter.write();
	}
}
