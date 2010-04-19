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
package org.jiemamy.entity.io.meta.generator;

import org.jiemamy.entity.io.meta.EntityMeta;
import org.jiemamy.entity.io.meta.PropertyMeta;

/**
 * 識別子を自動生成するIDジェネレータメタの抽象クラス。
 * 
 * @author j5ik2o
 */
public abstract class AbstractIdGeneratorMeta implements IdGeneratorMeta {
	
	/** エンティティメタデータ */
	private EntityMeta entityMeta;
	
	/** プロパティメタデータ */
	private PropertyMeta propertyMeta;
	

	/**
	 * インスタンスを構築する。
	 * 
	 * @param entityMeta エンティティメタデータ
	 * @param propertyMeta プロパティメタデータ
	 */
	public AbstractIdGeneratorMeta(final EntityMeta entityMeta, final PropertyMeta propertyMeta) {
		super();
		this.entityMeta = entityMeta;
		this.propertyMeta = propertyMeta;
	}
	
	/**
	 * {@link EntityMeta}を取得する。
	 * 
	 * @return {@link EntityMeta}
	 */
	public EntityMeta getEntityMeta() {
		return entityMeta;
	}
	
	/**
	 * {@link PropertyMeta}を取得する。
	 * 
	 * @return {@link PropertyMeta}
	 */
	public PropertyMeta getPropertyMeta() {
		return propertyMeta;
	}
	
}
