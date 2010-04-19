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
package org.jiemamy.entity.io.gen.desc;

import javax.persistence.GenerationType;
import javax.persistence.TemporalType;

/**
 * エンティティの属性記述インターフェイス。
 * 
 * @author j5ik2o
 */
public class AttributeDesc {
	
	/** 名前 */
	protected String name;
	
	/** 属性のクラス */
	protected Class<?> attributeClass;
	
	/** 識別子の場合{@code true} */
	protected boolean id;
	
	/** 識別子の生成方法を示す列挙型、生成しない場合{@code null} */
	protected GenerationType generationType;
	
	/** 識別子の初期値 */
	protected int initialValue;
	
	/** 識別子の割り当てサイズ */
	protected int allocationSize;
	
	/** バージョンの場合{@code true} */
	protected boolean version;
	
	/** 一時的の場合{@code true} */
	protected boolean trnsient;
	
	/** {@code LOB}の場合{@code true} */
	protected boolean lob;
	
	/** カラムの名前 */
	protected String columnName;
	
	/** 長さ */
	protected int length;
	
	/** 精度 */
	protected int precision;
	
	/** スケール */
	protected int scale;
	
	/** NULL可能の場合{@code true} */
	protected boolean nullable;
	
	/** 一意の場合{@code true} */
	protected boolean unique;
	
	/** カラム定義 */
	protected String columnDefinition;
	
	/** カラムの型名 */
	protected String columnTypeName;
	
	/** サポートされていないカラム型の場合{@code true} */
	protected boolean unsupportedColumnType;
	
	/** コメント */
	protected String comment;
	

	/**
	 * インスタンスを生成する。
	 */
	public AttributeDesc() {
	}
	
	/**
	 * 識別子の割り当てサイズを取得する。
	 * 
	 * @return 識別子の割り当てサイズ
	 */
	public int getAllocationSize() {
		return allocationSize;
	}
	
	/**
	 * 属性のクラスを取得する。
	 * 
	 * @return 属性のクラス
	 */
	public Class<?> getAttributeClass() {
		return attributeClass;
	}
	
	/**
	 * カラム定義を取得する。
	 * 
	 * @return カラム定義
	 */
	public String getColumnDefinition() {
		return columnDefinition;
	}
	
	/**
	 * カラムの名前を取得する。
	 * 
	 * @return カラムの名前
	 */
	public String getColumnName() {
		return columnName;
	}
	
	/**
	 * カラムの型名を取得する。
	 * 
	 * @return カラムの型名
	 */
	public String getColumnTypeName() {
		return columnTypeName;
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
	 * 識別子の生成方法を示す列挙型を取得する。
	 * 
	 * @return 識別子の生成方法を示す列挙型、生成しない場合{@code null}
	 */
	public GenerationType getGenerationType() {
		return generationType;
	}
	
	/**
	 * 識別子の初期値を取得する。
	 * 
	 * @return 識別子の初期値
	 */
	public int getInitialValue() {
		return initialValue;
	}
	
	/**
	 * 長さを取得する。
	 * 
	 * @return 長さ
	 */
	public int getLength() {
		return length;
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
	public int getPrecision() {
		return precision;
	}
	
	/**
	 * スケールを取得する。
	 * 
	 * @return スケール
	 */
	public int getScale() {
		return scale;
	}
	
	/**
	 * 時制の種別を取得する。
	 * 
	 * @return 時制の種別、対応する種別がない場合{@code null}
	 */
	public TemporalType getTemporalType() {
		if (attributeClass == java.sql.Date.class) {
			return TemporalType.DATE;
		}
		if (attributeClass == java.sql.Time.class) {
			return TemporalType.TIME;
		}
		if (attributeClass == java.sql.Timestamp.class) {
			return TemporalType.TIMESTAMP;
		}
		return null;
	}
	
	/**
	 * 識別子の場合{@code true}を取得する。
	 * 
	 * @return 識別子の場合{@code true}、そうでなければ{@code false}
	 */
	public boolean isId() {
		return id;
	}
	
	/**
	 * {@code LOB}の場合{@code true}を取得する。
	 * 
	 * @return {@code LOB}の場合{@code true}、そうでなければ{@code false}
	 */
	public boolean isLob() {
		return lob;
	}
	
	/**
	 * NULL可能の場合{@code true}を取得する。
	 * 
	 * @return NULL可能の場合{@code true}、そうでない場合{@code false}
	 */
	public boolean isNullable() {
		return nullable;
	}
	
	/**
	 * 時制を表す場合{@code true}を取得する。
	 * 
	 * @return 時制を表す場合{@code true}
	 */
	public boolean isTemporal() {
		return getTemporalType() != null;
	}
	
	/**
	 * 一時的の場合{@code true}を取得する。
	 * 
	 * @return 一時的の場合{@code true}、そうでなければ{@code false}
	 */
	public boolean isTransient() {
		return trnsient;
	}
	
	/**
	 * 一意の場合{@code true}、そうでない場合{@code false}を取得する。
	 * 
	 * @return 一意の場合{@code true}、そうでない場合{@code false}
	 */
	public boolean isUnique() {
		return unique;
	}
	
	/**
	 * サポートされていないカラム型の場合{@code true}を取得する。
	 * 
	 * @return サポートされていないカラム型の場合{@code true}、そうでない場合{@code false}
	 */
	public boolean isUnsupportedColumnType() {
		return unsupportedColumnType;
	}
	
	/**
	 * バージョンの場合{@code true}を取得する。
	 * 
	 * @return バージョンの場合{@code true}、そうでなければ{@code false}
	 */
	public boolean isVersion() {
		return version;
	}
	
	/**
	 * 識別子の割り当てサイズを設定する。
	 * 
	 * @param allocationSize 識別子の割り当てサイズ
	 */
	public void setAllocationSize(int allocationSize) {
		this.allocationSize = allocationSize;
	}
	
	/**
	 * 属性のクラスを設定する。
	 * 
	 * @param attributeClass 属性のクラス
	 */
	public void setAttributeClass(Class<?> attributeClass) {
		this.attributeClass = attributeClass;
	}
	
	/**
	 * カラム定義を設定する。
	 * 
	 * @param columnDefinition カラム定義
	 */
	public void setColumnDefinition(String columnDefinition) {
		this.columnDefinition = columnDefinition;
	}
	
	/**
	 * カラムの名前を設定する。
	 * 
	 * @param columnName カラムの名前
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
	/**
	 * カラムの型名を設定する。
	 * 
	 * @param columnTypeName カラムの型名
	 */
	public void setColumnTypeName(String columnTypeName) {
		this.columnTypeName = columnTypeName;
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
	 * 識別子の生成方法を示す列挙型を設定する。
	 * 
	 * @param generationType 識別子の生成方法を示す列挙型、生成しない場合{@code null}
	 */
	public void setGenerationType(GenerationType generationType) {
		this.generationType = generationType;
	}
	
	/**
	 * 識別子の場合{@code true}を設定する。
	 * 
	 * @param id 識別子の場合{@code true}
	 */
	public void setId(boolean id) {
		this.id = id;
	}
	
	/**
	 * 識別子の初期値を設定する。
	 * 
	 * @param initialValue 識別子の初期値
	 */
	public void setInitialValue(int initialValue) {
		this.initialValue = initialValue;
	}
	
	/**
	 * 長さを設定する。
	 * 
	 * @param length 長さ
	 */
	public void setLength(int length) {
		this.length = length;
	}
	
	/**
	 * {@code LOB}の場合{@code true}を設定する。
	 * 
	 * @param lob {@code LOB}の場合{@code true}
	 */
	public void setLob(boolean lob) {
		this.lob = lob;
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
	 * NULL可能の場合{@code true}を設定する。
	 * 
	 * @param nullable NULL可能の場合{@code true}
	 */
	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}
	
	/**
	 * 精度を設定する。
	 * 
	 * @param precision	精度
	 */
	public void setPrecision(int precision) {
		this.precision = precision;
	}
	
	/**
	 * スケールを設定する。
	 * 
	 * @param scale スケール
	 */
	public void setScale(int scale) {
		this.scale = scale;
	}
	
	/**
	 * 一時的の場合{@code true}を設定する。
	 * 
	 * @param trnsient 一時的の場合{@code true}
	 */
	public void setTransient(boolean trnsient) {
		this.trnsient = trnsient;
	}
	
	/**
	 * 一意の場合{@code true}、そうでない場合{@code false}を設定する。
	 * 
	 * @param unique 一意の場合{@code true}
	 */
	public void setUnique(boolean unique) {
		this.unique = unique;
	}
	
	/**
	 * サポートされていないカラム型の場合{@code true}を設定する。
	 * 
	 * @param unsupportedColumnType サポートされていないカラム型の場合{@code true}、そうでない場合{@code false}
	 */
	public void setUnsupportedColumnType(boolean unsupportedColumnType) {
		this.unsupportedColumnType = unsupportedColumnType;
	}
	
	/**
	 * バージョンの場合{@code true}を設定する。
	 * 
	 * @param version バージョンの場合{@code true}
	 */
	public void setVersion(boolean version) {
		this.version = version;
	}
	
}
