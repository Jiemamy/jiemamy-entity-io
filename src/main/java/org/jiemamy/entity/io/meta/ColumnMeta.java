/*
 * Copyright 2007-2009 Jiemamy Project and the Others.
 * Created on Apr 3, 2009
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
package org.jiemamy.entity.io.meta;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * カラム用のメタデータです。
 * 
 * @author j5ik2o
 */
public class ColumnMeta {
	
	/** 名前 */
	private String name;
	
	private String logicalName;
	
	/** 挿入可能フラグ */
	private boolean insertable = true;
	
	/** 更新可能フラグ */
	private boolean updatable = true;
	
	/** NULL許可制約 */
	private boolean nullable = true;
	
	/** ユニーク制約 */
	private boolean unique = false;
	
	private Integer length;
	
	private Integer precision;
	

	/**
	 * カラムのサイズを取得する。
	 * 
	 * @return カラムのサイズ
	 */
	public Integer getLength() {
		return length;
	}
	
	/**
	 * 論理名を取得する。
	 * 
	 * @return 論理名
	 */
	public String getLogicalName() {
		return logicalName;
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
	 * 精度を取得する。
	 * 
	 * @return 精度
	 */
	public Integer getPrecision() {
		return precision;
	}
	
	/**
	 * 挿入可能フラグを取得する。
	 * 
	 * @return trueの場合は挿入可能
	 */
	public boolean isInsertable() {
		return insertable;
	}
	
	/**
	 * NULL許可制約を取得する。
	 * 
	 * @return NULL許可制約
	 */
	public boolean isNullable() {
		return nullable;
	}
	
	/**
	 * ユニーク制約を取得する。
	 * 
	 * @return ユニーク制約
	 */
	public boolean isUnique() {
		return unique;
	}
	
	/**
	 * 更新可能フラグを取得する。
	 * 
	 * @return trueの場合は更新可能
	 */
	public boolean isUpdatable() {
		return updatable;
	}
	
	/**
	 * 挿入可能フラグを設定する。
	 * 
	 * @param insertable 挿入可能フラグ
	 */
	public void setInsertable(boolean insertable) {
		this.insertable = insertable;
	}
	
	/**
	 * カラムのサイズを設定する。
	 * 
	 * @param length カラムのサイズ
	 */
	public void setLength(Integer length) {
		this.length = length;
	}
	
	/**
	 * 論理名を設定する。
	 * 
	 * @param logicalName 論理名
	 */
	public void setLogicalName(String logicalName) {
		this.logicalName = logicalName;
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
	 * NULL許可制約を設定する。
	 * 
	 * @param nullable NULL許可制約
	 */
	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}
	
	/**
	 * 精度を設定する。
	 * 
	 * @param precision 精度
	 */
	public void setPrecision(Integer precision) {
		this.precision = precision;
	}
	
	/**
	 * ユニーク制約を設定する。
	 * 
	 * @param unique ユニーク制約
	 */
	public void setUnique(boolean unique) {
		this.unique = unique;
	}
	
	/**
	 * 更新可能フラグを設定する。
	 * 
	 * @param updatable 更新可能フラグ
	 */
	public void setUpdatable(boolean updatable) {
		this.updatable = updatable;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
