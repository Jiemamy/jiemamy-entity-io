/*
 * Copyright 2007-2009 Jiemamy Project and the Others.
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
package org.jiemamy.entity.io.gen.model.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.jiemamy.entity.io.gen.desc.AssociationDesc;
import org.jiemamy.entity.io.gen.desc.AssociationType;
import org.jiemamy.entity.io.gen.desc.AttributeDesc;
import org.jiemamy.entity.io.gen.desc.CompositeUniqueConstraintDesc;
import org.jiemamy.entity.io.gen.desc.EntityDesc;
import org.jiemamy.entity.io.gen.model.AssociationModel;
import org.jiemamy.entity.io.gen.model.AssociationModelFactory;
import org.jiemamy.entity.io.gen.model.AttributeModel;
import org.jiemamy.entity.io.gen.model.AttributeModelFactory;
import org.jiemamy.entity.io.gen.model.CompositeUniqueConstraintModel;
import org.jiemamy.entity.io.gen.model.CompositeUniqueConstraintModelFactory;
import org.jiemamy.entity.io.gen.model.EntityModel;
import org.jiemamy.entity.io.gen.model.EntityModelFactory;
import org.jiemamy.utils.ClassUtil;

/**
 * {@link EntityModelFactory}の実装クラス。
 * 
 * @author j5ik2o
 */
public class EntityModelFactoryImpl implements EntityModelFactory {
	
	/** パッケージ名、デフォルトパッケージの場合は{@code null} */
	protected String packageName;
	
	/** 属性モデルのファクトリ */
	protected AttributeModelFactory attributeModelFactory;
	
	/** 関連モデルのファクトリ */
	protected AssociationModelFactory associationModelFactory;
	
	/** 複合一意制約モデルのファクトリ */
	protected CompositeUniqueConstraintModelFactory compositeUniqueConstraintModelFactory;
	
	/** エンティティクラスでアクセサを使用する場合 {@code true} */
	protected boolean useAccessor;
	
	/** コメントを使用する場合{@code true} */
	protected boolean useComment;
	
	/** カタログ名を表示する場合{@code true} */
	protected boolean showCatalogName;
	
	/** スキーマ名を表示する場合{@code true} */
	protected boolean showSchemaName;
	
	/** テーブル名を表示する場合{@code true} */
	protected boolean showTableName;
	
	/** エンティティのスーパークラス、スーパークラスを持たない場合は{@code null} */
	protected Class<?> superclass;
	
	/** クラスモデルのサポート */
	protected ClassModelSupport classModelSupport = new ClassModelSupport();
	
	/** 生成モデルのサポート */
	protected GeneratedModelSupport generatedModelSupport = new GeneratedModelSupport();
	

	/**
	 * インスタンスを生成する。
	 * 
	 * @param packageName パッケージ名、デフォルトパッケージの場合は{@code null}
	 * @param superclass エンティティのスーパークラス、スーパークラスを持たない場合は{@code null}
	 * @param attributeModelFactory 属性モデルのファクトリ
	 * @param associationModelFactory 関連モデルのファクトリ
	 * @param compositeUniqueConstraintModelFactory 複合一意制約モデルのファクトリ
	 * @param useAccessor エンティティクラスでアクセサを使用する場合 {@code true}
	 * @param useComment コメントを使用する場合{@code true}
	 * @param showCatalogName カタログ名を表示する場合{@code true}
	 * @param showSchemaName スキーマ名を表示する場合{@code true}
	 * @param showTableName テーブル名を表示する場合{@code true}
	 */
	public EntityModelFactoryImpl(String packageName, Class<?> superclass, AttributeModelFactory attributeModelFactory,
			AssociationModelFactory associationModelFactory,
			CompositeUniqueConstraintModelFactory compositeUniqueConstraintModelFactory, boolean useAccessor,
			boolean useComment, boolean showCatalogName, boolean showSchemaName, boolean showTableName) {
		if (attributeModelFactory == null) {
			throw new NullPointerException("attributeModelFactory");
		}
		if (associationModelFactory == null) {
			throw new NullPointerException("associationModelFactory");
		}
		if (compositeUniqueConstraintModelFactory == null) {
			throw new NullPointerException("compositeUniqueConstraintModelFactory");
		}
		this.packageName = packageName;
		this.superclass = superclass;
		this.attributeModelFactory = attributeModelFactory;
		this.associationModelFactory = associationModelFactory;
		this.compositeUniqueConstraintModelFactory = compositeUniqueConstraintModelFactory;
		this.useAccessor = useAccessor;
		this.useComment = useComment;
		this.showCatalogName = showCatalogName;
		this.showSchemaName = showSchemaName;
		this.showTableName = showTableName;
//		if (superclass != null) {
//			if (superclass.isAnnotationPresent(MappedSuperclass.class)) {
//				mappedSuperclassBeanDesc = BeanDescFactory.getBeanDesc(superclass);
//			}
//		}
	}
	
	/**
	 * 関連モデルを処理する。
	 * 
	 * @param entityModel エンティティモデル
	 * @param entityDesc エンティティ記述
	 */
	protected void doAssociationModel(EntityModel entityModel, EntityDesc entityDesc) {
		for (AssociationDesc associationDesc : entityDesc.getAssociationDescList()) {
			if (superclass != null && ClassUtil.getFieldNoException(superclass, associationDesc.getName()) != null) {
				continue;
			}
			AssociationModel associationModel = associationModelFactory.getAssociationModel(associationDesc);
			entityModel.addAssociationModel(associationModel);
		}
	}
	
	/**
	 * 属性モデルを処理する。
	 * 
	 * @param entityModel エンティティモデル
	 * @param entityDesc エンティティ記述
	 */
	protected void doAttributeModel(EntityModel entityModel, EntityDesc entityDesc) {
		for (AttributeDesc attributeDesc : entityDesc.getAttributeDescList()) {
			if (superclass != null && ClassUtil.getFieldNoException(superclass, attributeDesc.getName()) != null) {
				continue;
			}
			AttributeModel attributeModel = attributeModelFactory.getAttributeModel(attributeDesc);
			entityModel.addAttributeModel(attributeModel);
		}
	}
	
	/**
	 * 複合一意制約モデルを処理する。
	 * 
	 * @param entityModel エンティティモデル
	 * @param entityDesc エンティティ記述
	 */
	protected void doCompositeUniqueConstraintModel(EntityModel entityModel, EntityDesc entityDesc) {
		for (CompositeUniqueConstraintDesc compositeUniqueConstraintDesc : entityDesc
			.getCompositeUniqueConstraintDescList()) {
			CompositeUniqueConstraintModel compositeUniqueConstraintModel =
					compositeUniqueConstraintModelFactory.getUniqueConstraintModel(compositeUniqueConstraintDesc);
			entityModel.addCompositeUniqueConstraintModel(compositeUniqueConstraintModel);
		}
	}
	
	/**
	 * 生成情報を処理する。
	 * 
	 * @param entityModel エンティティモデル
	 * @param entityDesc エンティティ記述
	 */
	protected void doGeneratedInfo(EntityModel entityModel, EntityDesc entityDesc) {
		generatedModelSupport.fillGeneratedInfo(this, entityModel);
	}
	
	/**
	 * インポート名を処理する。
	 * 
	 * @param model エンティティクラスのモデル
	 * @param entityDesc エンティティ記述
	 */
	protected void doImportName(EntityModel model, EntityDesc entityDesc) {
		classModelSupport.addImportName(model, Entity.class);
		classModelSupport.addImportName(model, Serializable.class);
		classModelSupport.addImportName(model, Generated.class);
		if (model.getCatalogName() != null || model.getSchemaName() != null || model.getTableName() != null) {
			classModelSupport.addImportName(model, Table.class);
		}
		if (superclass != null) {
			classModelSupport.addImportName(model, superclass);
		}
		for (AttributeModel attr : model.getAttributeModelList()) {
			if (attr.isId()) {
				classModelSupport.addImportName(model, Id.class);
				if (attr.getGenerationType() != null) {
					classModelSupport.addImportName(model, GeneratedValue.class);
					classModelSupport.addImportName(model, GenerationType.class);
					if (attr.getGenerationType() == GenerationType.SEQUENCE) {
						classModelSupport.addImportName(model, SequenceGenerator.class);
					} else if (attr.getGenerationType() == GenerationType.TABLE) {
						classModelSupport.addImportName(model, TableGenerator.class);
					}
				}
			}
			if (attr.isLob()) {
				classModelSupport.addImportName(model, Lob.class);
			}
			if (attr.getTemporalType() != null) {
				classModelSupport.addImportName(model, Temporal.class);
				classModelSupport.addImportName(model, TemporalType.class);
			}
			if (attr.isTransient()) {
				classModelSupport.addImportName(model, Transient.class);
			} else {
				classModelSupport.addImportName(model, Column.class);
			}
			if (attr.isVersion()) {
				classModelSupport.addImportName(model, Version.class);
			}
			classModelSupport.addImportName(model, attr.getAttributeClass());
		}
		for (AssociationModel asso : model.getAssociationModelList()) {
			AssociationType associationType = asso.getAssociationType();
			if (associationType == AssociationType.ONE_TO_MANY) {
				classModelSupport.addImportName(model, List.class);
			}
			classModelSupport.addImportName(model, associationType.getAnnotation());
			if (asso.getJoinColumnModel() != null) {
				classModelSupport.addImportName(model, JoinColumn.class);
			}
			if (asso.getJoinColumnsModel() != null) {
				classModelSupport.addImportName(model, JoinColumn.class);
				classModelSupport.addImportName(model, JoinColumns.class);
			}
		}
		if (!model.getCompositeUniqueConstraintModelList().isEmpty()) {
			classModelSupport.addImportName(model, Table.class);
			classModelSupport.addImportName(model, UniqueConstraint.class);
		}
	}
	
	public EntityModel getEntityModel(EntityDesc entityDesc) {
		EntityModel entityModel = new EntityModel();
		if (showCatalogName) {
			entityModel.setCatalogName(entityDesc.getCatalogName());
		}
		if (showSchemaName) {
			entityModel.setSchemaName(entityDesc.getSchemaName());
		}
		if (showTableName) {
			entityModel.setTableName(entityDesc.getTableName());
		}
		entityModel.setPackageName(packageName);
		entityModel.setShortClassName(entityDesc.getName());
		if (superclass != null) {
			entityModel.setShortSuperclassName(superclass.getSimpleName());
		}
		entityModel.setCompositeId(entityDesc.hasCompositeId());
		entityModel.setUseAccessor(useAccessor);
		entityModel.setComment(entityDesc.getComment());
		entityModel.setUseComment(useComment);
		doAttributeModel(entityModel, entityDesc);
		doAssociationModel(entityModel, entityDesc);
		doCompositeUniqueConstraintModel(entityModel, entityDesc);
		doImportName(entityModel, entityDesc);
		doGeneratedInfo(entityModel, entityDesc);
		return entityModel;
	}
}
