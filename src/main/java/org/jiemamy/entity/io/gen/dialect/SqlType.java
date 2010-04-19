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

import java.sql.Types;

import org.jiemamy.entity.io.utils.ColumnUtil;

/**
 * SQL型です。
 * <p>
 * JDBCのSQL型、つまり{@link Types}の定数に対応する。
 * </p>
 * 
 * @author j5ik2o
 */
public class SqlType {
	
	private String dataType;
	

	/**
	 * インスタンスを生成する。
	 * 
	 * @param dataType データタイプ名
	 */
	public SqlType(String dataType) {
		this.dataType = dataType;
	}
	
	/**
	 * データ型を取得する。
	 * 
	 * @param length 長さ
	 * @param precision 精度
	 * @param scale スケール
	 * @param identity IDENTITYカラムの場合{@code true}
	 * @return データ型
	 */
	public String getDataType(int length, int precision, int scale, boolean identity) {
		return ColumnUtil.formatDataType(dataType, length, precision, scale);
	}
}
