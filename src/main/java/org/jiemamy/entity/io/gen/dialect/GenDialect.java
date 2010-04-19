/*
 * Copyright 2007-2009 Jiemamy Project and the Others.
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
package org.jiemamy.entity.io.gen.dialect;

import javax.persistence.GenerationType;

/**
 * RDBMSごとの方言を扱うインタフェース。
 * 
 * @author j5ik2o
 */
public interface GenDialect {
	
	/**
	 * カラム型。
	 * <p>
	 * データベースのメタデータが返すカラムの型名に対応する。
	 * <p>
	 * <p>
	 * データベースのメタデータからJavaコードを生成する場合に使用できます。
	 * </p>
	 * 
	 * @author j5ik2o
	 */
	interface ColumnType {
		
		/**
		 * 属性のクラスを取得する。
		 * 
		 * @param length 長さ
		 * @param precision 精度
		 * @param scale スケール
		 * @return 属性のクラス
		 */
		Class<?> getAttributeClass(int length, int precision, int scale);
		
		/**
		 * カラム定義を取得する。
		 * 
		 * @param length 長さ
		 * @param precision 精度
		 * @param scale スケール
		 * @param defaultValue デフォルト値、存在しない場合は{@code null}
		 * @return カラム定義
		 */
		String getColumnDefinition(int length, int precision, int scale, String defaultValue);
		
		/**
		 * LOBの場合{@code true}
		 * 
		 * @return LOBの場合{@code true}
		 */
		boolean isLob();
		
	}
	

	/**
	 * SQLブロックのコンテキスト。
	 * 
	 * @author j5ik2o
	 */
//	interface SqlBlockContext {
//		
//		/**
//		 * SQLのキーワードを追加する。
//		 * 
//		 * @param keyword SQLのキーワード
//		 */
//		void addKeyword(String keyword);
//		
//		/**
//		 * SQLブロックの内側と判定できる場合{@code true}
//		 * 
//		 * @return SQLブロックの内側と判定できる場合{@code true}
//		 */
//		boolean isInSqlBlock();
//	}
	/**
	 * SQLブロックのコンテキストを作成する。
	 * 
	 * @return SQLブロックのコンテキスト
	 */
	//SqlBlockContext createSqlBlockContext();
	/**
	 * 終了を表すクォートを取得する。
	 * 
	 * @return 終了を表すクォート
	 */
	//String getCloseQuote();
	/**
	 * カラムのコメントをデータベースから直接取得しマップに詰めて取得する。
	 * <p>
	 * {@link #isJdbcCommentAvailable()}が{@code true}を返す場合に利用できます。
	 * </p>
	 * <p>
	 * 戻り値のマップのキーは大文字小文字を気にしません。 カラム名に対応するコメントが存在しない、値は{@code null}になります。
	 * </p>
	 * 
	 * @param connection コネクション
	 * @param catalogName カタログ名
	 * @param schemaName スキーマ名
	 * @param tableName テーブル名
	 * @return 大文字小文字を気にしないカラム名をキー、カラムのコメントを値とするマップ
	 * @throws SQLException SQL例外が発生した場合
	 */
//	Map<String, String> getColumnCommentMap(Connection connection, String catalogName, String schemaName,
//			String tableName) throws SQLException;
	/**
	 * カラム型を取得する。
	 * 
	 * @param columnTypeName カラムの型名
	 * @param sqlType JDBCのSQL型
	 * @return カラム型、サポートされていないカラムの型名の場合{@code null}
	 */
	ColumnType getColumnType(String columnTypeName, int sqlType);
	
	/**
	 * デフォルトの{@link GenerationType}を取得する。
	 * 
	 * @return デフォルトの{@link GenerationType}
	 */
	GenerationType getDefaultGenerationType();
	
	/**
	 * デフォルトのスキーマ名を取得する。
	 * 
	 * @param userName ユーザー名
	 * @return スキーマ名
	 */
	//String getDefaultSchemaName(String userName);
	/**
	 * 外部キーを削除する構文を取得する。
	 * 
	 * @return 外部キーを削除する構文
	 */
	//String getDropForeignKeySyntax();
	/**
	 * 一意キーを削除する構文を取得する。
	 * 
	 * @return 外部キーを削除する構文
	 */
	//String getDropUniqueKeySyntax();
	/**
	 * IDENTITYカラムの定義を取得する。
	 * 
	 * @return IDENTITYカラムの定義
	 */
	//String getIdentityColumnDefinition();
	/**
	 * IDENTITYカラムに対するinsertを無効化するステートメントを取得する。
	 * 
	 * @param tableName テーブル名
	 * @return IDENTITYカラムに対するinsertを無効化するステートメント
	 */
	//String getIdentityInsertDisableStatement(String tableName);
	/**
	 * IDENTITYカラムに対するinsertを有効化するステートメントを取得する。
	 * 
	 * @param tableName テーブル名
	 * @return IDENTITYカラムに対するinsertを有効化するステートメント
	 */
	//String getIdentityInsertEnableStatement(String tableName);
	/**
	 * 名前を取得する。
	 * 
	 * @return 名前
	 */
	String getName();
	
	/**
	 * 開始を表すクォートを取得する。
	 * 
	 * @return 開始を表すクォート
	 */
	//String getOpenQuote();
	/**
	 * シーケンス定義の断片を取得する。
	 * <p>
	 * この断片は create sequence 以降に続きます。
	 * </p>
	 * 
	 * @param dataType データタイプ
	 * @param initialValue 初期値
	 * @param allocationSize 割り当てサイズ
	 * @return シーケンス定義の断片
	 */
	//String getSequenceDefinitionFragment(String dataType, long initialValue, int allocationSize);
	/**
	 * シーケンスの値を取得するSQLを取得する。
	 * 
	 * @param sequenceName シーケンス名
	 * @param allocationSize 割り当てサイズ
	 * @return シーケンスの値を取得するSQL
	 */
	//String getSequenceNextValString(String sequenceName, int allocationSize);
	/**
	 * SQLブロックの区切り文字を取得する。
	 * 
	 * @return SQLブロックの区切り文字、SQLブロックの区切り文字蛾存在しない場合{@code null}
	 */
	//String getSqlBlockDelimiter();
	/**
	 * SQL型を取得する。
	 * 
	 * @param sqlType JDBCのSQL型
	 * @return SQL型
	 * @throws UnsupportedSqlTypeException サポートされていないJDBCのSQL型が渡された場合
	 */
	//SqlType getSqlType(int sqlType) throws UnsupportedSqlTypeException;
	/**
	 * SQL型を取得する。
	 * 
	 * @param valueTypeProvider
	 *            {@link ValueType}の提供者
	 * @param propertyMeta
	 *            プロパティメタデータ
	 * @return SQL型
	 * @throws UnsupportedSqlTypeException 
	 * @throws UnsupportedSqlTypeRuntimeException
	 *             サポートされていないJDBCのSQL型が渡された場合
	 */
//	SqlType getSqlType(ValueTypeProvider valueTypeProvider, PropertyMeta propertyMeta)
//			throws UnsupportedSqlTypeException;
	/**
	 * テーブルのコメントをデータベースから直接取得する。
	 * <p>
	 * {@link #isJdbcCommentAvailable()}が{@code true}を返す場合に利用できます。
	 * </p>
	 * 
	 * @param connection コネクション
	 * @param catalogName カタログ名
	 * @param schemaName スキーマ名
	 * @param tableName テーブル名
	 * @return テーブルのコメント、存在しない場合{@code null}
	 * @throws SQLException SQL例外が発生した場合
	 */
	//String getTableComment(Connection connection, String catalogName, String schemaName, String tableName)
	//		throws SQLException;
	/**
	 * 列の値が自動的に増分される場合{@code true}を取得する。
	 * 
	 * @param connection コネクション
	 * @param catalogName カタログ名
	 * @param schemaName スキーマ名
	 * @param tableName テーブル名
	 * @param columnName カラム名
	 * @return 列が自動的に増分される場合{@code true}、そうでない場合{@code false}
	 * @throws SQLException SQL例外が発生した場合
	 */
//	boolean isAutoIncrement(Connection connection, String catalogName, String schemaName, String tableName,
//			String columnName) throws SQLException;
	/**
	 * カラムが存在しない例外を表す場合{@code true}を取得する。
	 * 
	 * @param throwable 何らかの例外
	 * @return カラムが存在しない例外を表す場合{@code true}
	 */
	//boolean isColumnNotFound(Throwable throwable);
	/**
	 * JDBCのコメント取得機能が利用できる場合{@code true}を取得する。
	 * <p>
	 * JDBCのコメント取得機能が利用できるとは、次のメソッドでREMARKSカラムの値が取得できることを意味する。
	 * </p>
	 * <ul>
	 * <li>{@link DatabaseMetaData#getTables(String, String, String, String[])}</li>
	 * <li>{@link DatabaseMetaData#getColumns(String, String, String, String)}</li>
	 * </ul>
	 * 
	 * @return JDBCのコメント取得機能が利用できる場合{@code true}
	 */
	//boolean isJdbcCommentAvailable();
	/**
	 * シーケンスが存在しない例外を表す場合{@code true}を取得する。
	 * 
	 * @param throwable 何らかの例外
	 * @return シーケンスが存在しない例外を表す場合{@code true}
	 */
	//boolean isSequenceNotFound(Throwable throwable);
	/**
	 * テーブルが存在しない例外を表す場合{@code true}を取得する。
	 * 
	 * @param throwable 何らかの例外
	 * @return テーブルが存在しない例外を表す場合{@code true}
	 */
	//boolean isTableNotFound(Throwable throwable);
	/**
	 * クォートで囲みます。
	 * 
	 * @param value 値
	 * @return クォートで囲まれた値
	 */
	//String quote(String value);
	/**
	 * CREATE TABLEでコメントをサポートする場合{@code true}を取得する。
	 * 
	 * @return コメントをサポートする場合{@code true}
	 */
	//boolean supportsCommentInCreateTable();
	/**
	 * COMMENT ONをサポートする場合{@code true}を取得する。
	 * 
	 * @return COMMENT ONをサポートする場合{@code true}
	 */
	//boolean supportsCommentOn();
	/**
	 * {@link DatabaseMetaData#getIndexInfo(String, String, String, boolean, boolean)}
	 * をサポートする場合{@code true}を取得する。
	 * 
	 * @param catalogName カタログ名
	 * @param schemaName スキーマ名
	 * @param tableName テーブル名
	 * @return {@link DatabaseMetaData#getIndexInfo(String, String, String, boolean, boolean)}
	 *         をサポートする場合{@code true}
	 */
	//boolean supportsGetIndexInfo(String catalogName, String schemaName, String tableName);
	/**
	 * IDENTITYカラムをサポートしている場合{@code true}を取得する。
	 * 
	 * @return IDENTITYカラムをサポートしている場合{@code true}
	 */
	boolean supportsIdentity();
	
	/**
	 * IDENTITYカラムに対するinsertをサポートしている場合{@code true}を取得する。
	 * 
	 * @return IDENTITYカラムに対するinsertをサポートしている場合{@code true}
	 */
	//boolean supportsIdentityInsert();
	/**
	 * IDENTITYカラムに対するinsertの有効/無効を制御するステートメントをサポートしている場合{@code true}を取得する。
	 * 
	 * @return IDENTITYカラムに対するinsertをサポートしている場合{@code true}
	 */
	//boolean supportsIdentityInsertControlStatement();
	/**
	 * NULLが可能な一意制約をサポートしている場合{@code true}を取得する。
	 * 
	 * @return NULLが可能な一意制約をサポートしている場合{@code true}
	 */
	//boolean supportsNullableUnique();
	/**
	 * 参照整合制約の削除規則をサポートする場合{@code true}を取得する。
	 * 
	 * @return 参照整合制約の削除規則をサポートする場合{@code true}
	 */
	//boolean supportsReferentialDeleteRule();
	/**
	 * 参照整合制約の更新規則をサポートする場合{@code true}を取得する。
	 * 
	 * @return 参照整合制約の更新規則をサポートする場合{@code true}
	 */
	//boolean supportsReferentialUpdateRule();
	/**
	 * シーケンスをサポートする場合{@code true}を取得する。
	 * 
	 * @return シーケンスをサポートする場合{@code true}、しない場合{@code false}
	 */
	//boolean supportsSequence();
	/**
	 * クォートを除去する。
	 * 
	 * @param value 値
	 * @return クォートが取り除かれた値
	 */
	//String unquote(String value);
}
