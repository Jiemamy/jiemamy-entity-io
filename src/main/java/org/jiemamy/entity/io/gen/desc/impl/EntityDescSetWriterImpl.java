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
package org.jiemamy.entity.io.gen.desc.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang.Validate;

import org.jiemamy.entity.io.convensition.PersistenceConvention;
import org.jiemamy.entity.io.convensition.impl.PersistenceConventionImpl;
import org.jiemamy.entity.io.gen.desc.EntityDesc;
import org.jiemamy.entity.io.gen.desc.EntityDescSetWriter;
import org.jiemamy.entity.io.gen.desc.EntityDescSetWriterContext;
import org.jiemamy.entity.io.gen.desc.EntitySetDesc;
import org.jiemamy.entity.io.gen.desc.EntitySetDescFactory;
import org.jiemamy.entity.io.gen.generator.GenerateException;
import org.jiemamy.entity.io.gen.generator.Generator;
import org.jiemamy.entity.io.gen.generator.impl.GeneratorContextImpl;
import org.jiemamy.entity.io.gen.generator.impl.GeneratorImpl;
import org.jiemamy.entity.io.gen.model.AssociationModelFactory;
import org.jiemamy.entity.io.gen.model.AttributeModelFactory;
import org.jiemamy.entity.io.gen.model.CompositeUniqueConstraintModelFactory;
import org.jiemamy.entity.io.gen.model.EntityModel;
import org.jiemamy.entity.io.gen.model.EntityModelFactory;
import org.jiemamy.entity.io.gen.model.impl.AssociationModelFactoryImpl;
import org.jiemamy.entity.io.gen.model.impl.AttributeModelFactoryImpl;
import org.jiemamy.entity.io.gen.model.impl.CompositeUniqueConstraintModelFactoryImpl;
import org.jiemamy.entity.io.gen.model.impl.EntityModelFactoryImpl;
import org.jiemamy.entity.io.utils.FileUtil;
import org.jiemamy.utils.ClassUtil;
import org.jiemamy.utils.StringUtil;

/**
 * EntityMetaWriterContextの実装クラス。
 * 
 * @author j5ik2o
 */
public class EntityDescSetWriterImpl implements EntityDescSetWriter {
	
	private Generator generator;
	
	private EntityDescSetWriterContext entityDescSetWriterContext;
	
	private EntityModelFactory entityModelFactory;
	
	private EntitySetDescFactory entitySetDescFactory;
	

	/**
	 * インスタンスを生成する。
	 * 
	 * @param entityMetaWriterContext {@link EntityMetaWriterContext}
	 * @throws IdentityNotSupportedException 
	 * @throws IOException 
	 */
	public EntityDescSetWriterImpl(EntityDescSetWriterContext entityDescSetWriterContext)
			throws IdentityNotSupportedException, IOException {
		Validate.notNull(entityDescSetWriterContext);
		Validate.notNull(entityDescSetWriterContext.getDbTableMetaReader());
		Validate.notNull(entityDescSetWriterContext.getJavaSrcFileEncoding());
		Validate.notNull(entityDescSetWriterContext.getTemplateName());
		this.entityDescSetWriterContext = entityDescSetWriterContext;
		PersistenceConvention persistenceConvention = entityDescSetWriterContext.getPersistenceConvention();
		if (persistenceConvention == null) {
			persistenceConvention = new PersistenceConventionImpl();
		}
		entityModelFactory = createEntityModelFactory(entityDescSetWriterContext, persistenceConvention);
		entitySetDescFactory = createEntitySetDescFactory(entityDescSetWriterContext, persistenceConvention);
	}
	
	private EntityModelFactory createEntityModelFactory(EntityDescSetWriterContext entityDescSetWriterContext,
			PersistenceConvention persistenceConvention) {
		AttributeModelFactory attributeModelFactory =
				new AttributeModelFactoryImpl(persistenceConvention, entityDescSetWriterContext.isShowColumnName(),
						entityDescSetWriterContext.isShowColumnDefinition(), entityDescSetWriterContext
							.isUseTemporalType());
		AssociationModelFactory associationModelFactory =
				new AssociationModelFactoryImpl(entityDescSetWriterContext.isShowJoinColumn());
		CompositeUniqueConstraintModelFactory compositeUniqueConstraintModelFactory =
				new CompositeUniqueConstraintModelFactoryImpl();
		EntityModelFactory result =
				new EntityModelFactoryImpl(ClassUtil.concatName(entityDescSetWriterContext.getRootPackageName(),
						entityDescSetWriterContext.getEntityPackageName()), entityDescSetWriterContext
					.getEntitySuperClass(), attributeModelFactory, associationModelFactory,
						compositeUniqueConstraintModelFactory, entityDescSetWriterContext.isUseAccessor(),
						entityDescSetWriterContext.isApplyDbCommentToJava(), entityDescSetWriterContext
							.isShowCatalogName(), entityDescSetWriterContext.isShowSchemaName(),
						entityDescSetWriterContext.isShowTableName());
		return result;
	}
	
	private EntitySetDescFactory createEntitySetDescFactory(EntityDescSetWriterContext entityDescSetWriterContext,
			PersistenceConvention persistenceConvention) throws IdentityNotSupportedException {
		EntitySetDescFactory result =
				new EntitySetDescFactoryImpl(entityDescSetWriterContext.getDbTableMetaReader(), persistenceConvention,
						entityDescSetWriterContext.getDialect(), entityDescSetWriterContext
							.getVersionColumnNamePattern(), entityDescSetWriterContext.getPluralFormFile(),
						entityDescSetWriterContext.getIdGenerationType(), entityDescSetWriterContext.getInitialValue(),
						entityDescSetWriterContext.getAllocationSize());
		return result;
	}
	
	public void write() throws IOException {
		generator =
				new GeneratorImpl(entityDescSetWriterContext.getTemplateFileEncoding(), entityDescSetWriterContext
					.getTemplateFilePrimaryDir());
		GeneratorContextImpl context = new GeneratorContextImpl();
		context.setEncoding(entityDescSetWriterContext.getJavaSrcFileEncoding());
		context.setOverwrite(entityDescSetWriterContext.isOverwrite());
		context.setTemplateName(entityDescSetWriterContext.getTemplateName());
		
		String packageName =
				ClassUtil.concatName(entityDescSetWriterContext.getRootPackageName(), entityDescSetWriterContext
					.getEntityPackageName());
		EntitySetDesc entitySetDesc = entitySetDescFactory.getEntitySetDesc();
		for (EntityDesc entityDesc : entitySetDesc.getEntityDescList()) {
			File file =
					FileUtil.createJavaFile(entityDescSetWriterContext.getJavaSrcFileDestDir(), packageName, StringUtil
						.camelize(entityDesc.getName()));
			context.setFile(file);
			EntityModel entityModel = entityModelFactory.getEntityModel(entityDesc);
			context.setModel(entityModel);
			try {
				generator.generate(context);
			} catch (GenerateException e) {
				throw new IOException();
			}
		}
		
	}
	
}
