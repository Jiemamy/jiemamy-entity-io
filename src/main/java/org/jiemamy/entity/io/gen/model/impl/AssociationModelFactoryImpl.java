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

import javax.persistence.JoinColumn;

import org.jiemamy.entity.io.gen.desc.AssociationDesc;
import org.jiemamy.entity.io.gen.desc.AssociationType;
import org.jiemamy.entity.io.gen.model.AssociationModel;
import org.jiemamy.entity.io.gen.model.AssociationModelFactory;
import org.jiemamy.entity.io.gen.model.JoinColumnModel;
import org.jiemamy.entity.io.gen.model.JoinColumnsModel;

/**
 * {@link AssociationModelFactory}の実装クラス。
 * 
 * @author j5ik2o
 */
public class AssociationModelFactoryImpl implements AssociationModelFactory {
	
	/** {@link JoinColumn}を表示する場合{@code true} */
	protected boolean showJoinColumn;
	

	/**
	 * インスタンスを構築する。
	 * 
	 * @param showJoinColumn {@link JoinColumn}を表示する場合{@code true}
	 */
	public AssociationModelFactoryImpl(boolean showJoinColumn) {
		this.showJoinColumn = showJoinColumn;
	}
	
	/**
	 * 結合カラムモデルを処理する。
	 * 
	 * @param associationModel 関連モデル
	 * @param associationDesc 関連記述
	 */
	protected void doJoinColumnModel(AssociationModel associationModel, AssociationDesc associationDesc) {
		String propertyName = associationDesc.getName();
		String columnName = associationDesc.getColumnNameList().get(0);
		String referencedColumnName = associationDesc.getReferencedColumnNameList().get(0);
		if (showJoinColumn || !matchesDefaultMappingRule(propertyName, columnName, referencedColumnName)) {
			JoinColumnModel joinColumnModel = new JoinColumnModel();
			joinColumnModel.setName(columnName);
			joinColumnModel.setReferencedColumnName(referencedColumnName);
			associationModel.setJoinColumnModel(joinColumnModel);
		}
	}
	
	/**
	 * 複合結合カラムモデルを処理する。
	 * 
	 * @param associationModel 関連モデル
	 * @param associationDesc 関連記述
	 */
	protected void doJoinColumnsModel(AssociationModel associationModel, AssociationDesc associationDesc) {
		JoinColumnsModel joinColumnsModel = new JoinColumnsModel();
		int i = 0;
		for (String name : associationDesc.getColumnNameList()) {
			String referencedColumnName = associationDesc.getReferencedColumnNameList().get(i);
			JoinColumnModel joinColumnModel = new JoinColumnModel();
			joinColumnModel.setName(name);
			joinColumnModel.setReferencedColumnName(referencedColumnName);
			joinColumnsModel.addJoinColumnModel(joinColumnModel);
			i++;
		}
		associationModel.setJoinColumnsModel(joinColumnsModel);
	}
	
	public AssociationModel getAssociationModel(AssociationDesc associationDesc) {
		AssociationModel model = new AssociationModel();
		model.setName(associationDesc.getName());
		if (associationDesc.getAssociationType() == AssociationType.ONE_TO_MANY) {
			String entityName = associationDesc.getReferencedEntityDesc().getName();
			model.setShortClassName("List<" + entityName + ">");
		} else {
			model.setShortClassName(associationDesc.getReferencedEntityDesc().getName());
		}
		model.setAssociationType(associationDesc.getAssociationType());
		model.setMappedBy(associationDesc.getMappedBy());
		if (associationDesc.getColumnNameList().size() == 1) {
			doJoinColumnModel(model, associationDesc);
		} else if (associationDesc.getColumnNameList().size() > 1) {
			doJoinColumnsModel(model, associationDesc);
		}
		return model;
	}
	
	/**
	 * 結合カラムのデフォルトのマッピングルールに合致する場合{@code true}を取得する。
	 * 
	 * @param propertyName プロパティ名
	 * @param columnName 参照する側のカラム名
	 * @param referencedColumnName 参照される側のカラム名
	 * @return 結合カラムの命名規約に合致する場合{@code true}
	 */
	protected boolean matchesDefaultMappingRule(String propertyName, String columnName, String referencedColumnName) {
		return columnName.equalsIgnoreCase(propertyName + "_" + referencedColumnName);
	}
	
}
