/*
 * Copyright 2007-2009 Jiemamy Project and the Others.
 * Created on Apr 6, 2009
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

import javax.persistence.SequenceGenerator;

import org.jiemamy.entity.io.meta.EntityMeta;
import org.jiemamy.entity.io.meta.PropertyMeta;

/**
 * {@link SequenceGenerator}のメタデータを保持するクラス。
 * 
 * @author j5ik2o
 */
public class SequenceIdGeneratorMeta extends AbstractPreAllocateIdGeneratorMeta {
	
	private String sequenceName;
	

	/**
	 * インスタンスを生成する。
	 * 
	 * @param entityMeta {@link EntityMeta}
	 * @param propertyMeta {@link PropertyMeta}
	 * @param sequenceGenerator {@link SequenceGenerator}
	 */
	public SequenceIdGeneratorMeta(EntityMeta entityMeta, PropertyMeta propertyMeta, SequenceGenerator sequenceGenerator) {
		super(entityMeta, propertyMeta, sequenceGenerator.name(), sequenceGenerator.initialValue(), sequenceGenerator
			.allocationSize());
	}
	
	/**
	 * シーケンス名を取得する。
	 * 
	 * @return シーケンス名
	 */
	public String getSequenceName() {
		return sequenceName;
	}
}
