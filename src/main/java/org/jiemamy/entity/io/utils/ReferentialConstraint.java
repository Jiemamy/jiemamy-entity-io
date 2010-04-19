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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 参照整合性制約。
 * <p>
 * S2JDBC-Genなど、スキーマ生成のツールにより参照されるアノテーションです。 S2JDBCの実行時には利用されません。
 * </p>
 * 
 * @author j5ik2o
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( {
	ElementType.FIELD
})
public @interface ReferentialConstraint {
	
	/** 制約を有効にする場合{@code true}、しない場合{@code false} */
	boolean enable() default true;
	
	/** 削除規則 */
	ReferentialActionType onDelete() default ReferentialActionType.NO_ACTION;
	
	/** 更新規則 */
	ReferentialActionType onUpdate() default ReferentialActionType.NO_ACTION;
}
