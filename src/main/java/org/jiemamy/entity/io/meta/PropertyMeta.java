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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.jiemamy.entity.io.meta.generator.IdentityIdGeneratorMeta;
import org.jiemamy.entity.io.meta.generator.SequenceIdGeneratorMeta;
import org.jiemamy.entity.io.meta.generator.TableIdGeneratorMeta;
import org.jiemamy.utils.CollectionsUtil;

/**
 * JPA用のプロパティメタデータクラス。
 * 
 * @author j5ik2o
 */
public class PropertyMeta {
	
	/** 名前 */
	private String name;
	
	/** プロパティクラス */
	private Class<?> propertyClass;
	
	/** フィールド */
	private Field field;
	
	/** IDフラグ */
	private boolean id;
	
	/** カラムメタデータ */
	private ColumnMeta columnMeta;
	
	/** IDを自動生成する方法 */
	private GenerationType generationType;
	
	/** フェッチタイプ */
	private FetchType fetchType;
	
	/** 時制の種別 */
	private TemporalType temporalType;
	
	/** enumの種別 */
	private EnumType enumType;
	
	/** バージョンフラグ */
	private boolean version;
	
	/** 一時的フラグ*/
	private boolean trnsient;
	
	/** <code>LOB</code>フラグ */
	private boolean lob;
	
	/** 結合カラムメタデータのリスト */
	private List<JoinColumnMeta> joinColumnMetaList = new ArrayList<JoinColumnMeta>();
	
	/** 関連タイプ */
	private RelationshipType relationshipType;
	
	/** 関連の所有者側のプロパティ名 */
	private String mappedBy;
	
	/** 関連クラス */
	private Class<?> relationshipClass;
	
	/** {@link SequenceIdGeneratorMeta} */
	private SequenceIdGeneratorMeta sequenceIdGenerator;
	
	/** {@link IdentityIdGeneratorMeta} */
	private IdentityIdGeneratorMeta identityIdGenerator;
	
	/** {@link TableIdGeneratorMeta} */
	private TableIdGeneratorMeta tableIdGenerator;
	
	/** 追加情報 */
	private Map<String, Object> additionalInfo = CollectionsUtil.newHashMap();
	

	/**
	 * 追加情報を追加する。
	 * 
	 * @param <T> 値の型
	 * @param key 追加情報のキー
	 * @param value 値
	 */
	public <T>void addAdditionalInfo(String key, T value) {
		additionalInfo.put(key, value);
	}
	
	/**
	 * 結合カラムメタデータを追加する。
	 * 
	 * @param joinColumnMeta 結合カラムメタデータ
	 */
	public void addJoinColumnMeta(JoinColumnMeta joinColumnMeta) {
		joinColumnMetaList.add(joinColumnMeta);
	}
	
	/**
	 * 追加情報を取得する。
	 * 
	 * @param <T> 値の型
	 * @param key キー
	 * @return 値
	 */
	@SuppressWarnings("unchecked")
	public <T>T getAdditionalInfo(String key) {
		return (T) additionalInfo.get(key);
	}
	
	/**
	 * カラムメタを取得する。
	 * 
	 * @return {@link ColumnMeta}
	 */
	public ColumnMeta getColumnMeta() {
		return columnMeta;
	}
	
	/**
	 * {@link EnumType}を取得する。
	 * 
	 * @return {@link EnumType}
	 */
	public EnumType getEnumType() {
		return enumType;
	}
	
	/**
	 * {@link FetchType}を取得する。
	 * 
	 * @return {@link FetchType}
	 */
	public FetchType getFetchType() {
		return fetchType;
	}
	
	/**
	 * フィールドを取得する。
	 * 
	 * @return {@link Field}
	 */
	public Field getField() {
		return field;
	}
	
	/**
	 * {@link GenerationType}を取得する。
	 * 
	 * @return {@link GenerationType}
	 */
	public GenerationType getGenerationType() {
		return generationType;
	}
	
	/**
	 * {@link IdentityIdGeneratorMeta}を取得する。
	 * 
	 * @return {@link IdentityIdGeneratorMeta}
	 */
	public IdentityIdGeneratorMeta getIdentityIdGenerator() {
		return identityIdGenerator;
	}
	
	/**
	 * {@JoinColumnMeta}のリストを取得する。
	 * 
	 * @return {@JoinColumnMeta}のリスト
	 */
	public List<JoinColumnMeta> getJoinColumnMetaList() {
		return joinColumnMetaList;
	}
	
	/**
	 * mappedBy属性を取得する。
	 * 
	 * @return mappedBy属性
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
	 * プロパティクラスを取得する。
	 * 
	 * @return プロパティクラス
	 */
	public Class<?> getPropertyClass() {
		return propertyClass;
	}
	
	/**
	 * 関連クラスを取得する。
	 * 
	 * @return 関連クラス
	 */
	public Class<?> getRelationshipClass() {
		return relationshipClass;
	}
	
	/**
	 * {@link RelationshipType}を取得する。
	 * 
	 * @return {@link RelationshipType}
	 */
	public RelationshipType getRelationshipType() {
		return relationshipType;
	}
	
	/**
	 * {@link SequenceIdGeneratorMeta}を取得する。
	 * 
	 * @return {@link SequenceIdGeneratorMeta}
	 */
	public SequenceIdGeneratorMeta getSequenceIdGenerator() {
		return sequenceIdGenerator;
	}
	
	/**
	 * {@link TableIdGeneratorMeta}を取得する。
	 * 
	 * @return {@link TableIdGeneratorMeta}
	 */
	public TableIdGeneratorMeta getTableIdGenerator() {
		return tableIdGenerator;
	}
	
	/**
	 * {@link TemporalType}を取得する。
	 * 
	 * @return {@link TemporalType}
	 */
	public TemporalType getTemporalType() {
		return temporalType;
	}
	
	/**
	 * 識別子を自動生成するIDジェネレータを設定する。
	 * 
	 * @return 識別子を自動生成するIDジェネレータ
	 */
	public boolean hasIdGenerator() {
		return generationType != null;
	}
	
	/**
	 * IDフラグを取得する。
	 * 
	 * @return trueの場合はID
	 */
	public boolean isId() {
		return id;
	}
	
	/**
	 * LOBフラグを取得する。
	 * 
	 * @return LOBフラグ
	 */
	public boolean isLob() {
		return lob;
	}
	
	/**
	 * 関連かどうかを取得する。
	 * 
	 * @return 関連かどうか
	 */
	public boolean isRelationship() {
		return relationshipType != null;
	}
	
	/**
	 * <code>transient</code>フラグを取得する。
	 * 
	 * @return <code>transient</code>
	 */
	public boolean isTransient() {
		return trnsient;
	}
	
	/**
	 * バージョンフラグを取得する。
	 * 
	 * @return バージョンフラグ
	 */
	public boolean isVersion() {
		return version;
	}
	
	/**
	 * カラムメタを設定する。
	 * 
	 * @param columnMeta {@link ColumnMeta}
	 */
	public void setColumnMeta(final ColumnMeta columnMeta) {
		this.columnMeta = columnMeta;
	}
	
	/**
	 * {@link EnumType}を設定する。
	 * 
	 * @param enumType {@link EnumType}
	 */
	public void setEnumType(EnumType enumType) {
		this.enumType = enumType;
	}
	
	/**
	 * {@link FetchType}を設定する。
	 * 
	 * @param fetchType {@link FetchType}
	 */
	public void setFetchType(FetchType fetchType) {
		this.fetchType = fetchType;
	}
	
	/**
	 * フィールドを設定する。
	 * 
	 * @param field {@link Field}
	 */
	public void setField(Field field) {
		this.field = field;
	}
	
	/**
	 * {@link GenerationType}を設定する。
	 * 
	 * @param generationType {@link GenerationType}
	 */
	public void setGenerationType(GenerationType generationType) {
		this.generationType = generationType;
	}
	
	/**
	 * IDフラグを設定する。
	 * 
	 * @param id IDフラグ
	 */
	public void setId(boolean id) {
		this.id = id;
	}
	
	/**
	 * {@link IdentityIdGeneratorMeta}を設定する。
	 * 
	 * @param identityIdGenerator {@link IdentityIdGeneratorMeta}
	 */
	public void setIdentityIdGenerator(IdentityIdGeneratorMeta identityIdGenerator) {
		this.identityIdGenerator = identityIdGenerator;
	}
	
	/**
	 * ジョインカラムメタのリストを設定する。
	 * 
	 * @param joinColumnMetaList ジョインカラムメタのリスト
	 */
	public void setJoinColumnMetaList(List<JoinColumnMeta> joinColumnMetaList) {
		this.joinColumnMetaList = joinColumnMetaList;
	}
	
	/**
	 * LOBフラグを設定する。
	 * 
	 * @param lob LOBフラグ
	 */
	public void setLob(boolean lob) {
		this.lob = lob;
	}
	
	/**
	 * mappedBy属性を設定する。
	 * 
	 * @param mappedBy mappedBy属性
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
	 * プロパティクラスを設定する。
	 * 
	 * @param propertyClass プロパティクラス
	 */
	public void setPropertyClass(Class<?> propertyClass) {
		this.propertyClass = propertyClass;
	}
	
	/**
	 * 関連クラスを設定する。
	 * 
	 * @param relationshipClass 関連クラス
	 */
	public void setRelationshipClass(Class<?> relationshipClass) {
		this.relationshipClass = relationshipClass;
	}
	
	/**
	 * {@link RelationshipType}を設定する。
	 * 
	 * @param relationshipType {@link RelationshipType}
	 */
	public void setRelationshipType(RelationshipType relationshipType) {
		this.relationshipType = relationshipType;
	}
	
	/**
	 * {@link SequenceIdGeneratorMeta}を設定する。
	 * 
	 * @param sequenceIdGenerator {@link SequenceIdGeneratorMeta}
	 */
	public void setSequenceIdGenerator(SequenceIdGeneratorMeta sequenceIdGenerator) {
		this.sequenceIdGenerator = sequenceIdGenerator;
	}
	
	/**
	 * {@link TableIdGeneratorMeta}を設定する。
	 * 
	 * @param tableIdGenerator {@link TableIdGeneratorMeta}
	 */
	public void setTableIdGenerator(TableIdGeneratorMeta tableIdGenerator) {
		this.tableIdGenerator = tableIdGenerator;
	}
	
	/**
	 * {@link TemporalType}を設定する。
	 * 
	 * @param temporalType {@link TemporalType}
	 */
	public void setTemporalType(TemporalType temporalType) {
		this.temporalType = temporalType;
	}
	
	/**
	 * <code>transient</code>フラグを設定する。
	 * 
	 * @param trnsient <code>transient</code>フラグ
	 */
	public void setTransient(boolean trnsient) {
		this.trnsient = trnsient;
	}
	
	/**
	 * バージョンフラグを設定する。
	 * 
	 * @param version バージョンフラグ
	 */
	public void setVersion(boolean version) {
		this.version = version;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
