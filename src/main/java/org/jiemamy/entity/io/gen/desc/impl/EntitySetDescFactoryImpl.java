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
package org.jiemamy.entity.io.gen.desc.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.GenerationType;

import org.apache.commons.lang.Validate;

import org.jiemamy.entity.io.convensition.PersistenceConvention;
import org.jiemamy.entity.io.gen.desc.AttributeDescFactory;
import org.jiemamy.entity.io.gen.desc.CompositeUniqueConstraintDescFactory;
import org.jiemamy.entity.io.gen.desc.EntityDesc;
import org.jiemamy.entity.io.gen.desc.EntityDescFactory;
import org.jiemamy.entity.io.gen.desc.EntitySetDesc;
import org.jiemamy.entity.io.gen.desc.EntitySetDescFactory;
import org.jiemamy.entity.io.gen.dialect.GenDialect;
import org.jiemamy.entity.io.gen.meta.DbForeignKeyMeta;
import org.jiemamy.entity.io.gen.meta.DbTableMeta;
import org.jiemamy.entity.io.gen.meta.DbTableMetaReader;

/**
 * {@link EntitySetDescFactory}の実装クラスです。
 * 
 * @author j5ik2o
 */
public class EntitySetDescFactoryImpl implements EntitySetDescFactory {
	
	/** テーブルメタデータのリーダ */
	protected DbTableMetaReader dbTableMetaReader;
	
	/** バージョンカラム名のパターン */
	protected Pattern versionColumnNamePattern;
	
	/** 単語を複数系に変換するための辞書ファイル、使用しない場合は{@code null} */
	protected File pluralFormFile;
	
	/** エンティティの識別子の生成方法を示す列挙型 、生成しない場合は{@code null} */
	protected GenerationType generationType;
	
	/** エンティティの識別子の初期値、指定しない場合は{@code null} */
	protected Integer initialValue;
	
	/** エンティティの識別子の割り当てサイズ、指定しない場合は{@code null} */
	protected Integer allocationSize;
	
	/** エンティティ記述のファクトリ */
	protected EntityDescFactory entityDescFactory;
	
	/** 方言 */
	protected GenDialect dialect;
	
	private PersistenceConvention persistenceConvention;
	

	/**
	 * インスタンスを構築する。
	 * 
	 * @param dbTableMetaReader テーブルメタデータのリーダ
	 * @param persistenceConvention 永続化層の命名規約
	 * @param dialect SQL方言
	 * @param versionColumnNamePattern バージョンカラム名のパターン
	 * @param pluralFormFile 単語を複数系に変換するための辞書ファイル、使用しない場合は{@code null}
	 * @param generationType エンティティの識別子の生成方法を示す列挙型 、生成しない場合は{@code null}
	 * @param initialValue エンティティの識別子の初期値、指定しない場合は{@code null}
	 * @param allocationSize エンティティの識別子の割り当てサイズ、指定しない場合は{@code null}
	 * @throws IdentityNotSupportedException IDENTITYがサポートされていない場合
	 */
	public EntitySetDescFactoryImpl(DbTableMetaReader dbTableMetaReader, PersistenceConvention persistenceConvention,
			GenDialect dialect, Pattern versionColumnNamePattern, File pluralFormFile, GenerationType generationType,
			Integer initialValue, Integer allocationSize) throws IdentityNotSupportedException {
		Validate.notNull(dbTableMetaReader);
		Validate.notNull(persistenceConvention);
		Validate.notNull(dialect);
		Validate.notNull(versionColumnNamePattern);
		this.dbTableMetaReader = dbTableMetaReader;
		this.persistenceConvention = persistenceConvention;
		this.dialect = dialect;
		this.versionColumnNamePattern = versionColumnNamePattern;
		this.pluralFormFile = pluralFormFile;
		this.generationType = generationType;
		this.initialValue = initialValue;
		this.allocationSize = allocationSize;
		entityDescFactory = createEntityDescFactory();
	}
	
	/**
	 * 関連のリゾルバを作成する。
	 * 
	 * @param entitySetDesc エンティティ集合記述
	 * @param pluralFormDictinary 単語を複数形に変換するための辞書
	 * @return 関連のリゾルバ
	 */
	protected AssociationResolver createAssociationResolver(EntitySetDesc entitySetDesc,
			PluralFormDictinary pluralFormDictinary) {
		return new AssociationResolver(entitySetDesc, pluralFormDictinary);
	}
	
	/**
	 * {@link EntityDescFactory}の実装を作成する。
	 * 
	 * @return {@link EntityDescFactory}の実装
	 * @throws IdentityNotSupportedException IDENTITYがサポートされていない場合
	 */
	protected EntityDescFactory createEntityDescFactory() throws IdentityNotSupportedException {
		AttributeDescFactory attributeDescFactory =
				new AttributeDescFactoryImpl(persistenceConvention, dialect, versionColumnNamePattern, generationType,
						initialValue, allocationSize);
		CompositeUniqueConstraintDescFactory compositeUniqueConstraintDescFactory =
				new CompositeUniqueConstraintDescFactoryImpl();
		return new EntityDescFactoryImpl(persistenceConvention, attributeDescFactory,
				compositeUniqueConstraintDescFactory);
	}
	
	/**
	 * 単語を複数形に変換するための辞書を作成する。
	 * 
	 * @return 単語を複数形に変換するための辞書
	 * @throws IOException 入出力が失敗した場合
	 */
	protected PluralFormDictinary createPluralFormDictinary() throws IOException {
		if (pluralFormFile != null) {
			LinkedHashMap<String, String> map = loadPluralFormFile();
			return new PluralFormDictinary(map);
		}
		return new PluralFormDictinary();
	}
	
	public EntitySetDesc getEntitySetDesc() throws IOException {
		EntitySetDesc entitySetDesc = new EntitySetDesc();
		List<DbTableMeta> dbTableMetaList = dbTableMetaReader.read();
		for (DbTableMeta tableMeta : dbTableMetaList) {
			EntityDesc entityDesc = entityDescFactory.getEntityDesc(tableMeta);
			entitySetDesc.addEntityDesc(entityDesc);
		}
		
		PluralFormDictinary pluralFormDictinary = createPluralFormDictinary();
		AssociationResolver associationResolver = createAssociationResolver(entitySetDesc, pluralFormDictinary);
		for (DbTableMeta tableMeta : dbTableMetaList) {
			for (DbForeignKeyMeta fkMeta : tableMeta.getForeignKeyMetaList()) {
				associationResolver.resolve(tableMeta, fkMeta);
			}
		}
		return entitySetDesc;
	}
	
	/**
	 * 辞書ファイルをロードし結果を{@link LinkedHashMap}として取得する。
	 * 
	 * @return 正規表現をキー、置換文字列を値とするマップ
	 * @throws IOException 入出力が失敗した場合
	 */
	public LinkedHashMap<String, String> loadPluralFormFile() throws IOException {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(pluralFormFile), "UTF-8"));
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.length() == 0) {
					continue;
				}
				char firstChar = line.charAt(0);
				if (firstChar == '#' || firstChar == '!') {
					continue;
				}
				int pos = line.indexOf('=');
				if (pos < 0) {
					continue;
				}
				String key = line.substring(0, pos);
				String value = line.substring(pos + 1, line.length());
				map.put(key, value);
			}
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		return map;
	}
	
}
