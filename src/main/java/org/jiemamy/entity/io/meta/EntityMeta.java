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

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.jiemamy.utils.ArrayMap;
import org.jiemamy.utils.CollectionsUtil;
import org.jiemamy.utils.PropertyNotFoundException;

/**
 * JPA用のエンティティメタデータクラス。
 * 
 * @author j5ik2o
 */
public class EntityMeta {
	
	/** エンティティクラス */
	private Class<?> entityClass;
	
	/**  エンティティ名 */
	private String name;
	
	/** テーブルメタデータ */
	private TableMeta tableMeta;
	
	/** IDプロパティメタデータのリスト */
	private List<PropertyMeta> idPropertyMetas = CollectionsUtil.newArrayList();
	
	/** プロパティ名に関連するプロパティメタデータのマップ */
	private ArrayMap<String, PropertyMeta> propertyMetas = new ArrayMap<String, PropertyMeta>();
	
	/** バージョンのプロパティメタデータ */
	private PropertyMeta versionPropertyMeta;
	
	/** カラム名に関連するプロパティメタデータのマップ */
	private ArrayMap<String, PropertyMeta> columnPropertyMetas = new ArrayMap<String, PropertyMeta>();
	
	/** mappedBy属性に関連するプロパティメタデータのマップ */
	private Map<String, Map<String, PropertyMeta>> mappedByPropertyMetas = CollectionsUtil.newHashMap();
	
	/** 追加情報 */
	private Map<String, Object> additionalInfo = CollectionsUtil.newHashMap();
	

	/**
	 * 追加情報を追加する。
	 * 
	 * @param <T> 値の型
	 * @param key キー
	 * @param value 値
	 */
	public <T>void addAdditionalInfo(String key, T value) {
		additionalInfo.put(key, value);
	}
	
	/**
	 * {@link PropertyMeta}を追加する。
	 * 
	 * @param propertyMeta {@link PropertyMeta}
	 * @throws ColumnDuplicatedException カラムがだぶって生成された場合
	 * @throws PropertyDuplicatedException プロパティがだぶって生成された場合
	 */
	public void addPropertyMeta(PropertyMeta propertyMeta) throws ColumnDuplicatedException,
			PropertyDuplicatedException {
		if (propertyMetas.put(propertyMeta.getName(), propertyMeta) != null) {
			throw new PropertyDuplicatedException(name, propertyMeta.getName());
		}
		if (propertyMeta.isId()) {
			idPropertyMetas.add(propertyMeta);
		}
		if (propertyMeta.isVersion()) {
			versionPropertyMeta = propertyMeta;
		}
		if (propertyMeta.getMappedBy() != null) {
			Map<String, PropertyMeta> m = mappedByPropertyMetas.get(propertyMeta.getMappedBy());
			if (m == null) {
				m = CollectionsUtil.newHashMap();
				mappedByPropertyMetas.put(propertyMeta.getMappedBy(), m);
			}
			m.put(propertyMeta.getRelationshipClass().getName(), propertyMeta);
		}
		if (propertyMeta.getColumnMeta() != null) {
			String columnName = propertyMeta.getColumnMeta().getName();
			PropertyMeta pm2 = columnPropertyMetas.put(columnName, propertyMeta);
			if (pm2 != null) {
				throw new ColumnDuplicatedException(name, pm2.getName(), propertyMeta.getName(), columnName);
			}
		}
		
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
	 * カラムに結びつく全てのプロパティメタデータの{@link Iterable}を取得する。
	 * 
	 * @return カラムに結びつく全てのプロパティメタデータの{@link Iterable}
	 */
	public Iterable<PropertyMeta> getAllColumnPropertyMeta() {
		return new Iterable<PropertyMeta>() {
			
			public Iterator<PropertyMeta> iterator() {
				return new Iterator<PropertyMeta>() {
					
					private int i;
					

					public boolean hasNext() {
						return i < columnPropertyMetas.size();
					}
					
					public PropertyMeta next() {
						return columnPropertyMetas.get(i++);
					}
					
					public void remove() {
						throw new UnsupportedOperationException();
					}
				};
			}
		};
	}
	
	/**
	* 全てのプロパティメタデータの{@link Iterable}を取得する。
	* 
	* @return 全てのプロパティメタデータの{@link Iterable}
	*/
	public Iterable<PropertyMeta> getAllPropertyMeta() {
		return new Iterable<PropertyMeta>() {
			
			public Iterator<PropertyMeta> iterator() {
				return new Iterator<PropertyMeta>() {
					
					private int i;
					

					public boolean hasNext() {
						return i < propertyMetas.size();
					}
					
					public PropertyMeta next() {
						return propertyMetas.get(i++);
					}
					
					public void remove() {
						throw new UnsupportedOperationException();
					}
				};
			}
		};
	}
	
	/**
	 * カラムに結びつくプロパティメタデータを取得する。
	 * 
	 * @param index カラムインデックス
	 * @return プロパティメタデータ
	 */
	public PropertyMeta getColumnPropertyMeta(int index) {
		return columnPropertyMetas.get(index);
	}
	
	/**
	 * カラムに結びつくプロパティメタデータを取得する。
	 * 
	 * @param columnName カラム名
	 * @return プロパティメタデータ
	 * @throws EntityColumnNotFoundException カラム用のプロパティメタデータが見つからない場合
	 * 
	 */
	public PropertyMeta getColumnPropertyMeta(String columnName) throws EntityColumnNotFoundException {
		PropertyMeta meta = columnPropertyMetas.get(columnName);
		if (meta == null) {
			throw new EntityColumnNotFoundException(name, columnName);
		}
		return meta;
	}
	
	/**
	 * エンンティクラスを取得する。
	 * 
	 * @return エンティティクラス
	 */
	public Class<?> getEntityClass() {
		return entityClass;
	}
	
	/**
	 * IDプロパティメタのリストを取得する。
	 * 
	 * @return IDプロパティメタのリスト
	 */
	public List<PropertyMeta> getIdPropertyMetas() {
		return idPropertyMetas;
	}
	
	/**
	 * MappedByで注釈されているプロパティメタデータを取得する。
	 * 
	 * @param mappedBy 関連の所有者側のプロパティ名
	 * @param relationshipClass 関連クラス
	 * @return MappedByで注釈されているプロパティメタデータ
	 */
	public PropertyMeta getMappedByPropertyMeta(String mappedBy, Class<?> relationshipClass) {
		Map<String, PropertyMeta> m = mappedByPropertyMetas.get(mappedBy);
		if (m == null) {
			return null;
		}
		return m.get(relationshipClass.getName());
	}
	
	/**
	 * エンティティ名を取得する。
	 * 
	 * @return エンティティ名
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * プロパティメタデータを取得する。
	 * 
	 * @param index インデックス
	 * @return プロパティメタデータ
	 */
	public PropertyMeta getPropertyMeta(int index) {
		return propertyMetas.get(index);
	}
	
	/**
	* プロパティメタデータを取得する。
	* 
	* @param propertyName プロパティ名
	* @return {@link PropertyMeta}
	* @throws PropertyNotFoundException プロパティがみつからなかった場合
	*/
	public PropertyMeta getPropertyMeta(String propertyName) throws PropertyNotFoundException {
		PropertyMeta meta = propertyMetas.get(propertyName);
		if (meta == null) {
			throw new PropertyNotFoundException(name, propertyName);
		}
		return meta;
	}
	
	/**
	 * プロパティメタデータの数を取得する。
	 * 
	 * @return プロパティメタデータの数
	 */
	public int getPropertyMetaSize() {
		return propertyMetas.size();
	}
	
	/**
	 * テーブルメタデータを取得する。
	 * 
	 * @return {@link TableMeta}
	 */
	public TableMeta getTableMeta() {
		return tableMeta;
	}
	
	/**
	 * バージョン用プロパティメタデータを取得する。
	 * 
	 * @return バージョン用プロパティメタデータ
	 */
	public PropertyMeta getVersionPropertyMeta() {
		return versionPropertyMeta;
	}
	
	/**
	 * カラムに結びつくプロパティメタデータがあるかどうかを取得する。
	 * 
	 * @param columnName カラム名
	 * @return プロパティメタデータがあるかどうか
	 */
	public boolean hasColumnPropertyMeta(String columnName) {
		return columnPropertyMetas.containsKey(columnName);
	}
	
	/**
	 * プロパティメタデータの有無を取得する。
	 * 
	 * @param propertyName プロパティ名
	 * @return trueの場合は存在する
	 */
	public boolean hasPropertyMeta(String propertyName) {
		return propertyMetas.containsKey(propertyName);
	}
	
	/**
	 * バージョンを表すプロパティメタデータがあれば<code>true</code>を取得する。
	 * 
	 * @return バージョンを表すプロパティメタデータがあれば<code>true</code>
	 */
	public boolean hasVersionPropertyMeta() {
		return versionPropertyMeta != null;
	}
	
	/**
	 * エンティティクラスを設定する。
	 * 
	 * @param entityClass エンティティクラス
	 */
	public void setEntityClass(Class<?> entityClass) {
		this.entityClass = entityClass;
	}
	
	/**
	 * IDプロパティメタのリストを設定する。
	 * 
	 * @param idPropertyMetas IDプロパティメタのリスト
	 */
	public void setIdPropertyMetas(List<PropertyMeta> idPropertyMetas) {
		this.idPropertyMetas = idPropertyMetas;
	}
	
	/**
	 * エンティティ名を設定する。
	 * 
	 * @param name エンティティ名
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * プロパティメタデータを設定する。
	 * 
	 * @param propertyMetas プロパティメタ
	 */
	public void setPropertyMetaMap(ArrayMap<String, PropertyMeta> propertyMetas) {
		this.propertyMetas = propertyMetas;
	}
	
	/**
	 * テーブルメタデータを設定する。
	 * 
	 * @param tableMeta {@link TableMeta}
	 */
	public void setTableMeta(TableMeta tableMeta) {
		this.tableMeta = tableMeta;
	}
	
	/**
	 * バージョン用プロパティメタデータを設定する。
	 * 
	 * @param versionPropertyMeta バージョン用プロパティメタデータ
	 */
	public void setVersionPropertyMeta(PropertyMeta versionPropertyMeta) {
		this.versionPropertyMeta = versionPropertyMeta;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
