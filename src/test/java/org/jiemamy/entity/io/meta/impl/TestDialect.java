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
package org.jiemamy.entity.io.meta.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.GenerationType;

import org.jiemamy.JiemamyFactory;
import org.jiemamy.dialect.Dialect;
import org.jiemamy.entity.io.gen.dialect.GenDialect;
import org.jiemamy.entity.io.utils.CaseInsensitiveMap;
import org.jiemamy.entity.io.utils.ColumnUtil;

/**
 * TODO for kato
 * 
 * @author kato
 */
public class TestDialect implements GenDialect {
	
	/**
	 * 標準の{@link ColumnType}の実装クラスです。
	 * 
	 * @author j5ik2o
	 */
	public static class StandardColumnType implements ColumnType {
		
		private static final StandardColumnType BIGINT = new StandardColumnType("bigint", Long.class);
		
		private static final StandardColumnType BINARY = new StandardColumnType("binary", byte[].class);
		
		private static final StandardColumnType BIT = new StandardColumnType("bit", Boolean.class);
		
		private static final StandardColumnType BLOB = new StandardColumnType("blob", byte[].class, true);
		
		private static final StandardColumnType BOOLEAN = new StandardColumnType("boolean", Boolean.class);
		
		private static final StandardColumnType CHAR = new StandardColumnType("char($l)", String.class);
		
		private static final StandardColumnType CLOB = new StandardColumnType("clob", String.class, true);
		
		private static final StandardColumnType DATE = new StandardColumnType("date", java.sql.Date.class);
		
		private static final StandardColumnType DECIMAL = new StandardColumnType("decimal", BigDecimal.class);
		
		private static final StandardColumnType DOUBLE = new StandardColumnType("double", Double.class);
		
		private static final StandardColumnType FLOAT = new StandardColumnType("float", Float.class);
		
		private static final StandardColumnType INTEGER = new StandardColumnType("integer", Integer.class);
		
		private static final StandardColumnType LONGVARBINARY = new StandardColumnType("longvarbinary", byte[].class);
		
		private static final StandardColumnType LONGVARCHAR = new StandardColumnType("longvarchar", String.class);
		
		private static final StandardColumnType NUMERIC = new StandardColumnType("numeric", BigDecimal.class);
		
		private static final StandardColumnType REAL = new StandardColumnType("real", Float.class);
		
		private static final StandardColumnType SMALLINT = new StandardColumnType("smallint", Short.class);
		
		private static final StandardColumnType TIME = new StandardColumnType("time", java.sql.Time.class);
		
		private static final StandardColumnType TIMESTAMP = new StandardColumnType("timestamp", Timestamp.class);
		
		private static final StandardColumnType TINYINT = new StandardColumnType("tinyint", Short.class);
		
		private static final StandardColumnType VARBINARY = new StandardColumnType("varbinary($l)", byte[].class);
		
		private static final StandardColumnType VARCHAR = new StandardColumnType("varchar($l)", String.class);
		
		/** カラム定義 */
		protected String dataType;
		
		/** 属性のクラス */
		protected Class<?> attributeClass;
		
		/** LOBの場合{@code true} */
		protected boolean lob;
		

		/**
		 * インスタンスを構築する。
		 * 
		 * @param dataType データ型
		 * @param attributeClass 属性のクラス
		 */
		protected StandardColumnType(String dataType, Class<?> attributeClass) {
			this(dataType, attributeClass, false);
		}
		
		/**
		 * インスタンスを構築する。
		 * 
		 * @param dataType カラム定義
		 * @param attributeClass 属性のクラス
		 * @param lob LOBの場合{@code true}
		 */
		protected StandardColumnType(String dataType, Class<?> attributeClass, boolean lob) {
			this.dataType = dataType;
			this.attributeClass = attributeClass;
			this.lob = lob;
		}
		
		public Class<?> getAttributeClass(int length, int precision, int scale) {
			return attributeClass;
		}
		
		public String getColumnDefinition(int length, int precision, int scale, String defaultValue) {
			String completeDataType = ColumnUtil.formatDataType(dataType, length, precision, scale);
			return getColumnDefinitionInternal(completeDataType, defaultValue);
		}
		
		/**
		 * カラム定義を取得する。
		 * 
		 * @param completeDataType 完全なデータ型
		 * @param defaultValue デフォルト値、存在しない場合は{@code null}
		 * @return カラム定義
		 */
		protected String getColumnDefinitionInternal(String completeDataType, String defaultValue) {
			if (defaultValue == null) {
				return completeDataType;
			}
			return completeDataType + " default " + defaultValue;
		}
		
		public boolean isLob() {
			return lob;
		}
		
	}
	

	private Dialect dialect;
	
	private JiemamyFactory factory;
	
	/** カラムの型名をキー、{@link GenDialect.ColumnType}を値とするマップ */
	@SuppressWarnings("unchecked")
	protected Map<Object, ColumnType> columnTypeMap = new CaseInsensitiveMap();
	
	/** カラムのSQL型をキー、{@link GenDialect.ColumnType}を値とするマップ。 */
	protected Map<Integer, ColumnType> fallbackColumnTypeMap = new HashMap<Integer, ColumnType>();
	

	/**
	 * インスタンスを生成する。
	 * 
	 * @param factory
	 * @param dialect
	 */
	public TestDialect(final JiemamyFactory factory, final Dialect dialect) {
		this.factory = factory;
		this.dialect = dialect;
		columnTypeMap.put("bigint", StandardColumnType.BIGINT);
		columnTypeMap.put("binary", StandardColumnType.BINARY);
		columnTypeMap.put("bit", StandardColumnType.BIT);
		columnTypeMap.put("blob", StandardColumnType.BLOB);
		columnTypeMap.put("boolean", StandardColumnType.BOOLEAN);
		columnTypeMap.put("char", StandardColumnType.CHAR);
		columnTypeMap.put("clob", StandardColumnType.CLOB);
		columnTypeMap.put("date", StandardColumnType.DATE);
		columnTypeMap.put("decimal", StandardColumnType.DECIMAL);
		columnTypeMap.put("double", StandardColumnType.DOUBLE);
		columnTypeMap.put("float", StandardColumnType.FLOAT);
		columnTypeMap.put("integer", StandardColumnType.INTEGER);
		columnTypeMap.put("longvarbinary", StandardColumnType.LONGVARBINARY);
		columnTypeMap.put("longvarchar", StandardColumnType.LONGVARCHAR);
		columnTypeMap.put("numeric", StandardColumnType.NUMERIC);
		columnTypeMap.put("real", StandardColumnType.REAL);
		columnTypeMap.put("smallint", StandardColumnType.SMALLINT);
		columnTypeMap.put("time", StandardColumnType.TIME);
		columnTypeMap.put("timestamp", StandardColumnType.TIMESTAMP);
		columnTypeMap.put("tinyint", StandardColumnType.TINYINT);
		columnTypeMap.put("varbinary", StandardColumnType.VARBINARY);
		columnTypeMap.put("varchar", StandardColumnType.VARCHAR);
		
		fallbackColumnTypeMap.put(Types.BIGINT, StandardColumnType.BIGINT);
		fallbackColumnTypeMap.put(Types.BINARY, StandardColumnType.BINARY);
		fallbackColumnTypeMap.put(Types.BIT, StandardColumnType.BIT);
		fallbackColumnTypeMap.put(Types.BLOB, StandardColumnType.BLOB);
		fallbackColumnTypeMap.put(Types.BOOLEAN, StandardColumnType.BOOLEAN);
		fallbackColumnTypeMap.put(Types.CHAR, StandardColumnType.CHAR);
		fallbackColumnTypeMap.put(Types.CLOB, StandardColumnType.CLOB);
		fallbackColumnTypeMap.put(Types.DATE, StandardColumnType.DATE);
		fallbackColumnTypeMap.put(Types.DECIMAL, StandardColumnType.DECIMAL);
		fallbackColumnTypeMap.put(Types.DOUBLE, StandardColumnType.DOUBLE);
		fallbackColumnTypeMap.put(Types.FLOAT, StandardColumnType.FLOAT);
		fallbackColumnTypeMap.put(Types.INTEGER, StandardColumnType.INTEGER);
		fallbackColumnTypeMap.put(Types.LONGVARBINARY, StandardColumnType.LONGVARBINARY);
		fallbackColumnTypeMap.put(Types.LONGVARCHAR, StandardColumnType.LONGVARCHAR);
		fallbackColumnTypeMap.put(Types.NUMERIC, StandardColumnType.NUMERIC);
		fallbackColumnTypeMap.put(Types.REAL, StandardColumnType.REAL);
		fallbackColumnTypeMap.put(Types.SMALLINT, StandardColumnType.SMALLINT);
		fallbackColumnTypeMap.put(Types.TIME, StandardColumnType.TIME);
		fallbackColumnTypeMap.put(Types.TIMESTAMP, StandardColumnType.TIMESTAMP);
		fallbackColumnTypeMap.put(Types.TINYINT, StandardColumnType.TINYINT);
		fallbackColumnTypeMap.put(Types.VARBINARY, StandardColumnType.VARBINARY);
		fallbackColumnTypeMap.put(Types.VARCHAR, StandardColumnType.VARCHAR);
	}
	
	public ColumnType getColumnType(String columnTypeName, int sqlType) {
		ColumnType columnType = columnTypeMap.get(columnTypeName);
		return columnType != null ? columnType : fallbackColumnTypeMap.get(sqlType);
	}
	
	public GenerationType getDefaultGenerationType() {
		return GenerationType.IDENTITY;
	}
	
	public String getName() {
		return "testDialect";
	}
	
	public boolean supportsIdentity() {
		return true;
	}
	
}
