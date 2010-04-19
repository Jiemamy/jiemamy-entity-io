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
package org.jiemamy.entity.io.utils;

import javax.persistence.Column;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.jiemamy.utils.ReflectionUtil;

/**
 * アノテーションに関するユーティリティクラスです。
 * 
 * @author j5ik2o
 */
public class AnnotationUtil {
	
	/** デフォルトの{@link Table}を取得するためのクラス */
	@Table
	protected static class TableAnnotated {
	}
	

	/** デフォルトの{@link Table} */
	protected static final Table DEFAULT_TABLE = TableAnnotated.class.getAnnotation(Table.class);
	
	/** デフォルトの{@link Column} */
	@Column(length = 255, precision = 19, scale = 2)
	protected static final Column DEFAULT_COLUMN =
			ReflectionUtil.getDeclaredFieldNoException(AnnotationUtil.class, "DEFAULT_COLUMN").getAnnotation(
					Column.class);
	
	/** デフォルトの{@link SequenceGenerator} */
	@SequenceGenerator(name = "default")
	protected static final SequenceGenerator DEFAULT_SEQUENCE_GENERATOR =
			ReflectionUtil.getDeclaredFieldNoException(AnnotationUtil.class, "DEFAULT_SEQUENCE_GENERATOR")
				.getAnnotation(SequenceGenerator.class);
	
	/** デフォルトの{@link TableGenerator} */
	@TableGenerator(name = "default")
	protected static final TableGenerator DEFAULT_TABLE_GENERATOR =
			ReflectionUtil.getDeclaredFieldNoException(AnnotationUtil.class, "DEFAULT_TABLE_GENERATOR").getAnnotation(
					TableGenerator.class);
	
	/** デフォルトの{@link ReferentialConstraint} */
	@ReferentialConstraint
	protected static final ReferentialConstraint DEFAULT_REFERENTIAL_CONSTRAINT =
			ReflectionUtil.getDeclaredFieldNoException(AnnotationUtil.class, "DEFAULT_REFERENTIAL_CONSTRAINT")
				.getAnnotation(ReferentialConstraint.class);
	

	/**
	 * デフォルトの{@link Column}を取得する。
	 * 
	 * @return デフォルトの{@link Column}
	 */
	public static Column getDefaultColumn() {
		return DEFAULT_COLUMN;
	}
	
	/**
	 * デフォルトの{@link ReferentialConstraint}を取得する。
	 * 
	 * @return デフォルトの{@link ReferentialConstraint}
	 */
	public static ReferentialConstraint getDefaultReferentialConstraint() {
		return DEFAULT_REFERENTIAL_CONSTRAINT;
	}
	
	/**
	 * デフォルトの{@link SequenceGenerator}を取得する。
	 * 
	 * @return デフォルトの{@link SequenceGenerator}
	 */
	public static SequenceGenerator getDefaultSequenceGenerator() {
		return DEFAULT_SEQUENCE_GENERATOR;
	}
	
	/**
	 * デフォルトの{@link Table}を取得する。
	 * 
	 * @return デフォルトの{@link Table}
	 */
	public static Table getDefaultTable() {
		return DEFAULT_TABLE;
	}
	
	/**
	 * デフォルトの{@link TableGenerator}を取得する。
	 * 
	 * @return デフォルトの{@link TableGenerator}
	 */
	public static TableGenerator getDefaultTableGenerator() {
		return DEFAULT_TABLE_GENERATOR;
	}
	
	/**
	 * 
	 */
	protected AnnotationUtil() {
	}
}
