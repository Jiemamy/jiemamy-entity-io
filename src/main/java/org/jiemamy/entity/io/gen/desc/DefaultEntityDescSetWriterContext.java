/*
 * Copyright 2007-2009 Jiemamy Project and the Others.
 * Created on Apr 14, 2009
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
 * {@link EntityDescSetWriterContext}のデフォルト実装。
 * 
 * @author j5ik2o
 */
public class DefaultEntityDescSetWriterContext implements EntityDescSetWriterContext {
	
	private Integer allocationSize;
	
	private GenerationType idGenerationType;
	
	private Integer initialValue;
	
	private File javaSrcFileDestDir;
	
	private String entityPackageName;
	
	private String javaSrcFileEncoding;
	
	private boolean overwrite;
	
	private String rootPackageName;
	
	private boolean applyDbCommentToJava;
	
	private boolean useTemporalType;
	
	private boolean useAccessor;
	
	private boolean showTableName;
	
	private String schemaName;
	
	private Pattern tableNamePattern;
	
	private String templateFileEncoding;
	
	private File templateFilePrimaryDir;
	
	private String templateName;
	
	private Pattern versionColumnNamePattern;
	
	private boolean showCatalogName;
	
	private boolean showColumnDefinition;
	
	private Pattern ignoreTableNamePattern;
	
	private boolean showColumnName;
	
	private boolean showJoinColumn;
	
	private boolean showSchemaName;
	
	private Class<?> entitySuperClass;
	
	private GenDialect dialect;
	
	private PersistenceConvention persistenceConvention;
	
	private File pluralFormFile;
	
	private DbTableMetaReader dbTableMetaReader;
	

	public Integer getAllocationSize() {
		return allocationSize;
	}
	
	public DbTableMetaReader getDbTableMetaReader() {
		return dbTableMetaReader;
	}
	
	public GenDialect getDialect() {
		return dialect;
	}
	
	public String getEntityPackageName() {
		return entityPackageName;
	}
	
	public Class<?> getEntitySuperClass() {
		return entitySuperClass;
	}
	
	public GenerationType getIdGenerationType() {
		return idGenerationType;
	}
	
	public Pattern getIgnoreTableNamePattern() {
		return ignoreTableNamePattern;
	}
	
	public Integer getInitialValue() {
		return initialValue;
	}
	
	public File getJavaSrcFileDestDir() {
		return javaSrcFileDestDir;
	}
	
	public String getJavaSrcFileEncoding() {
		return javaSrcFileEncoding;
	}
	
	public PersistenceConvention getPersistenceConvention() {
		return persistenceConvention;
	}
	
	public File getPluralFormFile() {
		return pluralFormFile;
	}
	
	public String getRootPackageName() {
		return rootPackageName;
	}
	
	public String getSchemaName() {
		return schemaName;
	}
	
	public Pattern getTableNamePattern() {
		return tableNamePattern;
	}
	
	public String getTemplateFileEncoding() {
		return templateFileEncoding;
	}
	
	public File getTemplateFilePrimaryDir() {
		return templateFilePrimaryDir;
	}
	
	public String getTemplateName() {
		return templateName;
	}
	
	public Pattern getVersionColumnNamePattern() {
		return versionColumnNamePattern;
	}
	
	public boolean isApplyDbCommentToJava() {
		return applyDbCommentToJava;
	}
	
	public boolean isOverwrite() {
		return overwrite;
	}
	
	public boolean isShowCatalogName() {
		return showCatalogName;
	}
	
	public boolean isShowColumnDefinition() {
		return showColumnDefinition;
	}
	
	public boolean isShowColumnName() {
		return showColumnName;
	}
	
	public boolean isShowJoinColumn() {
		return showJoinColumn;
	}
	
	public boolean isShowSchemaName() {
		return showSchemaName;
	}
	
	public boolean isShowTableName() {
		return showTableName;
	}
	
	public boolean isUseAccessor() {
		return useAccessor;
	}
	
	public boolean isUseTemporalType() {
		return useTemporalType;
	}
	
	/**
	 * 識別子の割り当てサイズを設定する。
	 * 
	 * @param allocationSize 識別子の割り当てサイズ
	 */
	public void setAllocationSize(Integer allocationSize) {
		this.allocationSize = allocationSize;
	}
	
	/**
	 * DB上のコメントをエンティティに適用するかどうかのフラグを設定する。
	 * 
	 * @param applyDbCommentToJava trueの場合適用する
	 */
	public void setApplyDbCommentToJava(boolean applyDbCommentToJava) {
		this.applyDbCommentToJava = applyDbCommentToJava;
	}
	
	/**
	 * {@link DbTableMetaReader}を設定する。
	 * 
	 * @param dbTableMetaReader {@link DbTableMetaReader}
	 */
	public void setDbTableMetaReader(DbTableMetaReader dbTableMetaReader) {
		this.dbTableMetaReader = dbTableMetaReader;
	}
	
	/**
	 * {@link GenDialect}を設定する。
	 * 
	 * @param dialect {@link GenDialect}
	 */
	public void setDialect(GenDialect dialect) {
		this.dialect = dialect;
	}
	
	/**
	 * エンティティクラスのパッケージ名を設定する。
	 * 
	 * @param entityPackageName エンティティクラスのパッケージ名
	 */
	public void setEntityPackageName(String entityPackageName) {
		this.entityPackageName = entityPackageName;
	}
	
	/**
	 * エンティティクラスのスーパークラスを設定する。
	 * 
	 * @param entitySuperClass エンティティクラスのスーパークラス
	 */
	public void setEntitySuperClass(Class<?> entitySuperClass) {
		this.entitySuperClass = entitySuperClass;
	}
	
	/**
	 * 識別子を生成する方法を設定する。
	 * 
	 * @param idGenerationType {@link GenerationType}
	 */
	public void setIdGenerationType(GenerationType idGenerationType) {
		this.idGenerationType = idGenerationType;
	}
	
	/**
	 * 対象としないテーブル名の正規表現を設定する。
	 * 
	 * @param ignoreTableNamePattern 対象としないテーブル名の正規表現
	 */
	public void setIgnoreTableNamePattern(Pattern ignoreTableNamePattern) {
		this.ignoreTableNamePattern = ignoreTableNamePattern;
	}
	
	/**
	 * 識別子の初期値を設定する。
	 * 
	 * @param initialValue 識別子の初期値
	 */
	public void setInitialValue(Integer initialValue) {
		this.initialValue = initialValue;
	}
	
	/**
	 * Javaソースファイルの出力先ディレクトリを設定する。
	 * 
	 * @param javaSrcFileDestDir Javaソースファイルの出力先ディレクトリ
	 */
	public void setJavaSrcFileDestDir(File javaSrcFileDestDir) {
		this.javaSrcFileDestDir = javaSrcFileDestDir;
	}
	
	/**
	 * Javaファイルのエンコーディングを設定する。
	 * 
	 * @param javaSrcFileEncoding Javaファイルのエンコーディング
	 */
	public void setJavaSrcFileEncoding(String javaSrcFileEncoding) {
		this.javaSrcFileEncoding = javaSrcFileEncoding;
	}
	
	/**
	 * 上書きフラグを設定する。
	 * 
	 * @param overwrite 上書きする場合は{@code true}、しない場合は{@code false}
	 */
	public void setOverwrite(boolean overwrite) {
		this.overwrite = overwrite;
	}
	
	/**
	 * {@link PersistenceConvention}を設定する。
	 * 
	 * @param persistenceConvention {@link PersistenceConvention}
	 */
	public void setPersistenceConvention(PersistenceConvention persistenceConvention) {
		this.persistenceConvention = persistenceConvention;
	}
	
	/**
	 * OneToManyアノテーションの関連プロパティ名を複数形で作成する際に使用される辞書ファイルを設定する。
	 * 
	 * @param pluralFormFile 辞書ファイル
	 */
	public void setPluralFormFile(File pluralFormFile) {
		this.pluralFormFile = pluralFormFile;
	}
	
	/**
	 * ルートパッケージ名を設定する。
	 * 
	 * @param rootPackageName ルートパッケージ名
	 */
	public void setRootPackageName(String rootPackageName) {
		this.rootPackageName = rootPackageName;
	}
	
	/**
	 * スキーマ名を設定する。
	 * 
	 * @param schemaName スキーマ名
	 */
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
	
	/**
	 * Tableアノテーションのcatalog属性にカタログ名を明記するかどうかのフラグを設定する。
	 * 
	 * @param showCatalogName trueの場合明記する
	 */
	public void setShowCatalogName(boolean showCatalogName) {
		this.showCatalogName = showCatalogName;
	}
	
	/**
	 * ColumnアノテーションのcolumnDefinition属性にカラム定義を明記するかどうかのフラグを設定する。
	 * 
	 * @param showColumnDefinition trueの場合明記する
	 */
	public void setShowColumnDefinition(boolean showColumnDefinition) {
		this.showColumnDefinition = showColumnDefinition;
	}
	
	/**
	 * Columnアノテーションのname属性にカラム名を明記するかどうかのフラグを設定する。
	 * 
	 * @param showColumnName trueの場合明記する
	 */
	public void setShowColumnName(boolean showColumnName) {
		this.showColumnName = showColumnName;
	}
	
	/**
	 * JoinColumnアノテーションを明記するかどうかのフラグを設定する。
	 * 
	 * @param showJoinColumn trueの場合明記する
	 */
	public void setShowJoinColumn(boolean showJoinColumn) {
		this.showJoinColumn = showJoinColumn;
	}
	
	/**
	 * Tableアノテーションのschema属性にスキーマ名を明記するかどうかのフラグを設定する。
	 * 
	 * @param showSchemaName trueの場合明記する
	 */
	public void setShowSchemaName(boolean showSchemaName) {
		this.showSchemaName = showSchemaName;
	}
	
	/**
	 * Tableアノテーションのname属性にテーブル名を明記するかどうかのフラグを設定する。
	 * 
	 * @param showTableName trueの場合明記する
	 */
	public void setShowTableName(boolean showTableName) {
		this.showTableName = showTableName;
	}
	
	/**
	 * 対象とするテーブル名の正規表現を設定する。
	 * 
	 * @param tableNamePattern 対象とするテーブル名の正規表現
	 */
	public void setTableNamePattern(Pattern tableNamePattern) {
		this.tableNamePattern = tableNamePattern;
	}
	
	/**
	 * テンプレートファイルのエンコーディングを設定する。
	 * 
	 * @param templateFileEncoding テンプレートファイルのエンコーディング
	 */
	public void setTemplateFileEncoding(String templateFileEncoding) {
		this.templateFileEncoding = templateFileEncoding;
	}
	
	/**
	 * テンプレートファイルを検索する際の優先ディレクトリを設定する。
	 * 
	 * @param templateFilePrimaryDir テンプレートファイルを検索する際の優先ディレクトリ
	 */
	public void setTemplateFilePrimaryDir(File templateFilePrimaryDir) {
		this.templateFilePrimaryDir = templateFilePrimaryDir;
	}
	
	/**
	 * テンプレート名を設定する。
	 * 
	 * @param templateName テンプレート名
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	
	/**
	 * 生成するエンティティのアクセサメソッドを付与するかどうかのフラグを設定する。
	 * 
	 * @param useAccessor trueの場合付与する
	 */
	public void setUseAccessor(boolean useAccessor) {
		this.useAccessor = useAccessor;
	}
	
	/**
	 * 日付型カラムに対応するプロパティの型を@Temporalつきのjava.util.Dateとする。
	 * 
	 * @param useTemporalType trueの場合@Temporalつきのjava.util.Dateにする。
	 */
	public void setUseTemporalType(boolean useTemporalType) {
		this.useTemporalType = useTemporalType;
	}
	
	/**
	 * エンティティのプロパティに@Versionを付与するカラム名の正規表現を設定する。
	 * 
	 * @param versionColumnNamePattern {@link Pattern}
	 */
	public void setVersionColumnNamePattern(Pattern versionColumnNamePattern) {
		this.versionColumnNamePattern = versionColumnNamePattern;
	}
	
}
