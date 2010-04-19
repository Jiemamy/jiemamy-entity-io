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
package org.jiemamy.entity.io.gen.model;

import java.util.Collections;
import java.util.List;

import org.jiemamy.utils.CollectionsUtil;

/**
 * エンティティクラスのモデルです。
 * 
 * @author j5ik2o
 */
public class EntityModel extends ClassModel {
	
	/** カタログ名 */
	protected String catalogName;
	
	/** スキーマ名 */
	protected String schemaName;
	
	/** テーブル名 */
	protected String tableName;
	
	/** スーパークラスの単純名 */
	protected String shortSuperclassName;
	
	/** 複合識別子を持つ場合{@code true} */
	protected boolean compositeId;
	
	/** エンティティクラスでアクセサを使用する場合 {@code true} */
	protected boolean useAccessor;
	
	/** コメントを使用する場合 {@code true} */
	protected boolean useComment;
	
	/** コメント */
	protected String comment;
	
	/** 属性モデルのリスト */
	protected List<AttributeModel> attributeModelList = CollectionsUtil.newArrayList();
	
	/** 関連モデルのリスト */
	protected List<AssociationModel> associationModelList = CollectionsUtil.newArrayList();
	
	/** 複合一意制約モデルのリスト */
	protected List<CompositeUniqueConstraintModel> compositeUniqueConstraintModelList = CollectionsUtil.newArrayList();
	

	/**
	 * 関連モデルを追加する。
	 * 
	 * @param associationModel 関連モデル
	 */
	public void addAssociationModel(AssociationModel associationModel) {
		associationModelList.add(associationModel);
	}
	
	/**
	 * 属性モデルを追加する。
	 * 
	 * @param attributeModel 属性モデル
	 */
	public void addAttributeModel(AttributeModel attributeModel) {
		attributeModelList.add(attributeModel);
	}
	
	/**
	 * 複合一意制約モデルを追加する。
	 * 
	 * @param compositeUniqueConstraintModel 複合一意制約モデル
	 */
	public void addCompositeUniqueConstraintModel(CompositeUniqueConstraintModel compositeUniqueConstraintModel) {
		compositeUniqueConstraintModelList.add(compositeUniqueConstraintModel);
	}
	
	/**
	 * 関連モデルのリストを取得する。
	 * 
	 * @return 関連モデルのリスト
	 */
	public List<AssociationModel> getAssociationModelList() {
		return Collections.unmodifiableList(associationModelList);
	}
	
	/**
	 * 属性モデルを取得する。
	 * 
	 * @return 属性モデル
	 */
	public List<AttributeModel> getAttributeModelList() {
		return Collections.unmodifiableList(attributeModelList);
	}
	
	/**
	 * カタログ名を取得する。
	 * 
	 * @return カタログ名
	 */
	public String getCatalogName() {
		return catalogName;
	}
	
	/**
	 * コメントを取得する。
	 * 
	 * @return コメント
	 */
	public String getComment() {
		return comment;
	}
	
	/**
	 * 複合一意制約モデルのリストを取得する。
	 * 
	 * @return 複合一意制約モデルのリスト
	 */
	public List<CompositeUniqueConstraintModel> getCompositeUniqueConstraintModelList() {
		return Collections.unmodifiableList(compositeUniqueConstraintModelList);
	}
	
	/**
	 * スキーマ名を取得する。
	 * 
	 * @return スキーマ名
	 */
	public String getSchemaName() {
		return schemaName;
	}
	
	/**
	 * スーパークラスの単純名を取得する。
	 * 
	 * @return スーパークラスの単純名
	 */
	public String getShortSuperclassName() {
		return shortSuperclassName;
	}
	
	/**
	 * テーブル名を取得する。
	 * 
	 * @return テーブル名
	 */
	public String getTableName() {
		return tableName;
	}
	
	/**
	 * 複合識別子を持つ場合{@code true}を取得する。
	 * 
	 * @return 複合識別子を持つ場合{@code true}
	 */
	public boolean hasCompositeId() {
		return compositeId;
	}
	
	/**
	 * エンティティクラスでアクセサを使用する場合 {@code true}を取得する。
	 * 
	 * @return エンティティクラスでアクセサを使用する場合 {@code true}
	 */
	public boolean isUseAccessor() {
		return useAccessor;
	}
	
	/**
	 * コメントを使用する場合 {@code true}を取得する。
	 * 
	 * @return コメントを使用する場合 {@code true}
	 */
	public boolean isUseComment() {
		return useComment;
	}
	
	/**
	 * カタログ名を設定する。
	 * 
	 * @param catalogName カタログ名
	 */
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	
	/**
	 * コメントを設定する。
	 * 
	 * @param comment コメント
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	/**
	 * 複合識別子を持つ場合{@code true}を設定する。
	 * 
	 * @param compositeId 複合識別子を持つ場合{@code true}
	 */
	public void setCompositeId(boolean compositeId) {
		this.compositeId = compositeId;
	}
	
	/**
	 * スキーマ名を設定する。
	 * 
	 * @param schemaName スキーマ名
	 */
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
	
	/**
	 * スーパークラスの単純名を設定する。
	 * 
	 * @param shortSuperclassName スーパークラスの単純名
	 */
	public void setShortSuperclassName(String shortSuperclassName) {
		this.shortSuperclassName = shortSuperclassName;
	}
	
	/**
	 * テーブル名を設定する。
	 * 
	 * @param tableName テーブル名
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	/**
	 * エンティティクラスでアクセサを使用する場合 {@code true}を設定する。
	 * 
	 * @param useAccessor エンティティクラスでアクセサを使用する場合 {@code true}
	 */
	public void setUseAccessor(boolean useAccessor) {
		this.useAccessor = useAccessor;
	}
	
	/**
	 * コメントを使用する場合 {@code true}を設定する。
	 * 
	 * @param useComment コメントを使用する場合 {@code true}
	 */
	public void setUseComment(boolean useComment) {
		this.useComment = useComment;
	}
	
}
