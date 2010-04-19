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

/**
 * 参照動作の列挙型。
 * 
 * @author j5ik2o
 */
public enum ReferentialActionType {
	/** 波及 */
	CASCADE,

	/** NULL値の設定 */
	SET_NULL,

	/** デフォルト値の設定 */
	SET_DEFAULT,

	/** 制限 */
	RESTRICT,

	/** 動作なし */
	NO_ACTION
}
