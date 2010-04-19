/*
 * Copyright 2007-2009 Jiemamy Project and the Others.
 * Created on Apr 13, 2009
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

import java.io.File;
import java.util.regex.Pattern;

import javax.persistence.GenerationType;

import org.jiemamy.entity.io.convensition.PersistenceConvention;
import org.jiemamy.entity.io.gen.dialect.GenDialect;
import org.jiemamy.entity.io.gen.meta.DbTableMetaReader;

/**
 * {@link EntityDescSetWriter}のコンテキストインターフェイス。
 * 
 * @author j5ik2o
 */
public interface EntityDescSetWriterContext {
	
	/**
	 * 識別子の割り当てサイズを取得する。
	 * 
	 * @return 識別子の割り当てサイズ
	 */
	Integer getAllocationSize();
	
	/**
	 * {@link DbTableMetaReader}を取得する。
	 * 
	 * @return {@link DbTableMetaReader}
	 */
	DbTableMetaReader getDbTableMetaReader();
	
	/**
	 * {@link GenDialect}を取得する。
	 * 
	 * @return {@link GenDialect}
	 */
	GenDialect getDialect();
	
	/**
	 * エンティティクラスのパッケージ名を取得する。
	 * 
	 * @return エンティティクラス
	 */
	String getEntityPackageName();
	
	/**
	 * エンティティクラスのスーパークラスを取得する。
	 * 
	 * @return エンティティクラス
	 */
	Class<?> getEntitySuperClass();
	
	/**
	 * 識別子を生成する方法を取得する。
	 * 
	 * @return {@link GenerationType}
	 */
	GenerationType getIdGenerationType();
	
	/**
	 * 対象としないテーブル名の正規表現を取得する。
	 * 
	 * @return {@link Pattern}
	 */
	Pattern getIgnoreTableNamePattern();
	
	/**
	 * 識別子の初期値を取得する。
	 * 
	 * @return 識別子の初期値
	 */
	Integer getInitialValue();
	
	/**
	 * Javaソースファイルの出力先ディレクトリを取得する。
	 * 
	 * @return Javaソースファイルの出力先ディレクトリ
	 */
	File getJavaSrcFileDestDir();
	
	/**
	 * Javaファイルのエンコーディングを取得する。
	 * 
	 * @return Javaファイルのエンコーディング
	 */
	String getJavaSrcFileEncoding();
	
	/**
	 * {@link PersistenceConvention}を取得する。
	 * 
	 * @return {@link PersistenceConvention}
	 */
	PersistenceConvention getPersistenceConvention();
	
	/**
	 * OneToManyアノテーションの関連プロパティ名を複数形で作成する際に使用される辞書ファイルを取得する。
	 * 
	 * @return 辞書ファイル
	 */
	File getPluralFormFile();
	
	/**
	 * ルートパッケージ名を取得する。
	 * 
	 * @return パッケージ名
	 */
	String getRootPackageName();
	
	/**
	 * スキーマ名を取得する。
	 * 
	 * @return スキーマ名
	 */
	String getSchemaName();
	
	/**
	 * 対象とするテーブル名の正規表現を取得する。
	 * 
	 * @return {@link Pattern}
	 */
	Pattern getTableNamePattern();
	
	/**
	 * テンプレートファイルのエンコーディングを取得する。
	 * 
	 * @return テンプレートファイルのエンコーディング
	 */
	String getTemplateFileEncoding();
	
	/**
	 * テンプレートファイルを検索する際の優先ディレクトリを取得する。
	 * 
	 * @return テンプレートファイルを検索する際の優先ディレクトリ
	 */
	File getTemplateFilePrimaryDir();
	
	/**
	 * テンプレート名を取得する。
	 * 
	 * @return テンプレート名
	 */
	String getTemplateName();
	
	/**
	 * エンティティのプロパティに@Versionを付与するカラム名の正規表現を取得する。
	 * 
	 * @return {@link Pattern}
	 */
	Pattern getVersionColumnNamePattern();
	
	/**
	 * DB上のコメントをエンティティに適用するかどうかのフラグを取得する。
	 * 
	 * @return trueの場合適用する
	 */
	boolean isApplyDbCommentToJava();
	
	/**
	 * 上書きフラグを取得する。
	 * 
	 * @return 上書きする場合は{@code true}、しない場合は{@code false}
	 */
	boolean isOverwrite();
	
	/**
	 * Tableアノテーションのcatalog属性にカタログ名を明記するかどうかのフラグを取得する。
	 * 
	 * @return trueの場合明記する
	 */
	boolean isShowCatalogName();
	
	/**
	 * ColumnアノテーションのcolumnDefinition属性にカラム定義を明記するかどうかのフラグを取得する。
	 * 
	 * @return trueの場合明記する
	 */
	boolean isShowColumnDefinition();
	
	/**
	 * Columnアノテーションのname属性にカラム名を明記するかどうかのフラグを取得する。
	 * 
	 * @return trueの場合明記する
	 */
	boolean isShowColumnName();
	
	/**
	 * JoinColumnアノテーションを明記するかどうかのフラグを取得する。
	 * 
	 * @return trueの場合明記する
	 */
	boolean isShowJoinColumn();
	
	/**
	 * Tableアノテーションのschema属性にスキーマ名を明記するかどうかのフラグを取得する。
	 * 
	 * @return trueの場合明記する
	 */
	boolean isShowSchemaName();
	
	/**
	 * Tableアノテーションのname属性にテーブル名を明記するかどうかのフラグを取得する。
	 * 
	 * @return trueの場合明記する
	 */
	boolean isShowTableName();
	
	/**
	 * 生成するエンティティのアクセサメソッドを付与するかどうかのフラグを取得する。
	 * 
	 * @return trueの場合
	 */
	boolean isUseAccessor();
	
	/**
	 * 日付型カラムに対応するプロパティの型を@Temporalつきのjava.util.Dateとする。
	 * 
	 * @return trueの場合@Temporalつきのjava.util.Dateにする。
	 */
	boolean isUseTemporalType();
	
}
