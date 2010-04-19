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
 * INSERT前に識別子を自動生成するIDジェネレータメタの抽象クラスです。
 * 
 * @author j5ik2o
 */
public abstract class AbstractPreAllocateIdGeneratorMeta extends AbstractIdGeneratorMeta {
	
	/** 割り当てサイズ */
	private long allocationSize;
	
	/** 名前 */
	private String name;
	
	/** 初期値 */
	private long initialValue;
	

	/**
	 * インスタンスを構築する。
	 * 
	 * @param entityMeta エンティティメタデータ
	 * @param propertyMeta プロパティメタデータ
	 * @param name ジェネレータ名
	 * @param initialValue 初期値
	 * @param allocationSize 割り当てサイズ
	 */
	public AbstractPreAllocateIdGeneratorMeta(final EntityMeta entityMeta, final PropertyMeta propertyMeta,
			String name, long initialValue, long allocationSize) {
		super(entityMeta, propertyMeta);
		this.name = name;
		this.initialValue = initialValue;
		this.allocationSize = allocationSize;
	}
	
	/**
	 * 割り当てサイズを取得する。
	 * 
	 * @return 割り当てサイズ
	 */
	public long getAllocationSize() {
		return allocationSize;
	}
	
	/**
	 * シーケンスの初期値を取得する。
	 * 
	 * @return シーケンスの初期値
	 */
	public long getInitialValue() {
		return initialValue;
	}
	
	/**
	 * ジェネレータ名を取得する。
	 * 
	 * @return ジェネレータ名
	 */
	public String getName() {
		return name;
	}
	
}
