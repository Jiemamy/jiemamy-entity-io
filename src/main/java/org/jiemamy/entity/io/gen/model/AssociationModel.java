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

import org.jiemamy.entity.io.gen.desc.AssociationType;

/**
 * 関連モデル。
 * 
 * @author j5ik2o
 */
public class AssociationModel {
	
	/** 名前 */
	protected String name;
	
	/** クラスの単純名 */
	protected String shortClassName;
	
	/** 関連タイプ */
	protected AssociationType associationType;
	
	/** 関連の所有者側のプロパティの名前 */
	protected String mappedBy;
	
	/** 結合カラムモデル */
	protected JoinColumnModel joinColumnModel;
	
	/** 複合結合カラムモデル */
	protected JoinColumnsModel joinColumnsModel;
	

	/**
	 * 関連タイプを取得する。
	 * 
	 * @return 関連タイプ
	 */
	public AssociationType getAssociationType() {
		return associationType;
	}
	
	/**
	 * 結合カラムモデルを取得する。
	 * 
	 * @return 結合カラムモデル
	 */
	public JoinColumnModel getJoinColumnModel() {
		return joinColumnModel;
	}
	
	/**
	 * 複合結合カラムモデルを取得する。
	 * 
	 * @return 複合結合カラムモデル
	 */
	public JoinColumnsModel getJoinColumnsModel() {
		return joinColumnsModel;
	}
	
	/**
	 * 関連の所有者側のプロパティの名前を取得する。
	 * 
	 * @return 関連の所有者側のプロパティの名前
	 */
	public String getMappedBy() {
		return mappedBy;
	}
	
	/**
	 * 名前を取得する。
	 * 
	 * @return 名前
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * クラスの単純名を取得する。
	 * 
	 * @return クラスの単純名
	 */
	public String getShortClassName() {
		return shortClassName;
	}
	
	/**
	 * 関連タイプを設定する。
	 * 
	 * @param associationType 関連タイプ
	 */
	public void setAssociationType(AssociationType associationType) {
		this.associationType = associationType;
	}
	
	/**
	 * 結合カラムモデルを設定する。
	 * 
	 * @param joinColumnModel 結合カラムモデル
	 * 
	 */
	public void setJoinColumnModel(JoinColumnModel joinColumnModel) {
		if (joinColumnsModel != null) {
			throw new IllegalStateException("joinColumnsModel");
		}
		this.joinColumnModel = joinColumnModel;
	}
	
	/**
	 * 複合結合カラムモデルを設定する。
	 * 
	 * @param joinColumnsModel 複合結合カラムモデル
	 */
	public void setJoinColumnsModel(JoinColumnsModel joinColumnsModel) {
		if (joinColumnModel != null) {
			throw new IllegalStateException("joinColumnModel");
		}
		this.joinColumnsModel = joinColumnsModel;
	}
	
	/**
	 * 関連の所有者側のプロパティの名前を設定する。
	 * 
	 * @param mappedBy 関連の所有者側のプロパティの名前
	 */
	public void setMappedBy(String mappedBy) {
		this.mappedBy = mappedBy;
	}
	
	/**
	 * 名前を設定する。
	 * 
	 * @param name 名前
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * クラスの単純名を設定する。
	 * 
	 * @param shortClassName クラスの単純名
	 */
	public void setShortClassName(String shortClassName) {
		this.shortClassName = shortClassName;
	}
	
}
