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

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.TemporalType;

import org.jiemamy.entity.io.convensition.PersistenceConvention;
import org.jiemamy.entity.io.gen.desc.AttributeDesc;
import org.jiemamy.entity.io.gen.model.AttributeModel;
import org.jiemamy.entity.io.gen.model.AttributeModelFactory;
import org.jiemamy.utils.ClassUtil;

/**
 * {@link AttributeModelFactory}の実装クラス。
 * 
 * @author j5ik2o
 */
public class AttributeModelFactoryImpl implements AttributeModelFactory {
	
	/** カラム名を表示する場合{@code true} */
	protected boolean showColumnName;
	
	/** カラム定義を表示する場合{@code true} */
	protected boolean showColumnDefinition;
	
	/** {@link TemporalType}を使用する場合{@code true} */
	protected boolean useTemporalType;
	
	/** 永続化層の命名規約 */
	protected PersistenceConvention persistenceConvention;
	

	/**
	 * インスタンスを生成する。
	 * 
	 * @param persistenceConvention 永続化層の命名規約
	 * @param showColumnName カラム名を表示する場合{@code true}
	 * @param showColumnDefinition カラム定義を表示する場合{@code true}
	 * @param useTemporalType {@link TemporalType}を使用する場合{@code true}
	 */
	public AttributeModelFactoryImpl(PersistenceConvention persistenceConvention, boolean showColumnName,
			boolean showColumnDefinition, boolean useTemporalType) {
		this.persistenceConvention = persistenceConvention;
		this.showColumnName = showColumnName;
		this.showColumnDefinition = showColumnDefinition;
		this.useTemporalType = useTemporalType;
	}
	
	/**
	 * 属性のクラスを処理する。
	 * 
	 * @param attributeModel 属性モデル
	 * @param attributeDesc 属性記述
	 */
	protected void doAttributeClass(AttributeModel attributeModel, AttributeDesc attributeDesc) {
		if (useTemporalType && attributeDesc.isTemporal()) {
			attributeModel.setTemporalType(attributeDesc.getTemporalType());
			attributeModel.setAttributeClass(Date.class);
		} else {
			attributeModel.setAttributeClass(attributeDesc.getAttributeClass());
		}
	}
	
	/**
	 * カラム定義を処理する。
	 * 
	 * @param attributeModel 属性モデル
	 * @param attributeDesc 属性記述
	 */
	protected void doColumnDefinition(AttributeModel attributeModel, AttributeDesc attributeDesc) {
		attributeModel.setColumnDefinition(attributeDesc.getColumnDefinition());
		attributeModel.setUnsupportedColumnType(attributeDesc.isUnsupportedColumnType());
	}
	
	/**
	 * カラム名を処理する。
	 * 
	 * @param attributeModel 属性モデル
	 * @param attributeDesc 属性記述
	 */
	protected void doColumnName(AttributeModel attributeModel, AttributeDesc attributeDesc) {
		String realColumnName = attributeDesc.getColumnName();
		String convertedColumnName = persistenceConvention.fromPropertyNameToColumnName(attributeModel.getName());
		if (showColumnName || !realColumnName.equalsIgnoreCase(convertedColumnName)) {
			attributeModel.setColumnName(realColumnName);
		}
	}
	
	/**
	 * 長さを処理する。
	 * 
	 * @param attributeModel 属性モデル
	 * @param attributeDesc 属性記述
	 */
	protected void doLength(AttributeModel attributeModel, AttributeDesc attributeDesc) {
		if (attributeDesc.getLength() < 1) {
			return;
		}
		Class<?> clazz = ClassUtil.getWrapperClassIfPrimitive(attributeDesc.getAttributeClass());
		if (!Number.class.isAssignableFrom(clazz) && !Date.class.isAssignableFrom(clazz)
				&& !Calendar.class.isAssignableFrom(clazz)) {
			attributeModel.setLength(attributeDesc.getLength());
		}
	}
	
	/**
	 * 精度を処理する。
	 * 
	 * @param attributeModel 属性モデル
	 * @param attributeDesc 属性記述
	 */
	protected void doPrecision(AttributeModel attributeModel, AttributeDesc attributeDesc) {
		if (attributeDesc.getPrecision() < 1) {
			return;
		}
		Class<?> clazz = ClassUtil.getWrapperClassIfPrimitive(attributeDesc.getAttributeClass());
		if (Number.class.isAssignableFrom(clazz)) {
			attributeModel.setPrecision(attributeDesc.getPrecision());
		}
	}
	
	/**
	 * スケールを処理する。
	 * 
	 * @param attributeModel 属性モデル
	 * @param attributeDesc 属性記述
	 */
	protected void doScale(AttributeModel attributeModel, AttributeDesc attributeDesc) {
		if (attributeDesc.getScale() < 1) {
			return;
		}
		Class<?> clazz = ClassUtil.getWrapperClassIfPrimitive(attributeDesc.getAttributeClass());
		if (clazz == BigDecimal.class || clazz == Float.class || clazz == Double.class) {
			attributeModel.setScale(attributeDesc.getScale());
		}
	}
	
	public AttributeModel getAttributeModel(AttributeDesc attributeDesc) {
		AttributeModel attributeModel = new AttributeModel();
		attributeModel.setName(attributeDesc.getName());
		attributeModel.setId(attributeDesc.isId());
		attributeModel.setGenerationType(attributeDesc.getGenerationType());
		attributeModel.setInitialValue(attributeDesc.getInitialValue());
		attributeModel.setAllocationSize(attributeDesc.getAllocationSize());
		attributeModel.setLob(attributeDesc.isLob());
		attributeModel.setTransient(attributeDesc.isTransient());
		attributeModel.setVersion(attributeDesc.isVersion());
		attributeModel.setNullable(attributeDesc.isNullable());
		attributeModel.setUnique(attributeDesc.isUnique());
		attributeModel.setColumnTypeName(attributeDesc.getColumnTypeName());
		attributeModel.setComment(attributeDesc.getComment());
		doAttributeClass(attributeModel, attributeDesc);
		doColumnName(attributeModel, attributeDesc);
		if (showColumnDefinition) {
			doColumnDefinition(attributeModel, attributeDesc);
		} else {
			doLength(attributeModel, attributeDesc);
			doPrecision(attributeModel, attributeDesc);
			doScale(attributeModel, attributeDesc);
		}
		return attributeModel;
	}
}
