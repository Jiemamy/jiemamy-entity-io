/*
 * Copyright 2007-2009 Jiemamy Project and the Others.
 * Created on Apr 5, 2009
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

import java.lang.reflect.Field;

/**
 * {@link PropertyMeta}のファクトリインターフェイス。
 * 
 * @author	j5ik2o
 */
public interface PropertyMetaFactory {
	
	/**
	 * {@link PropertyMeta}を生成する。
	 * 
	 * @param entityMeta {@link EntityMeta}
	 * @param field 対象となるフィールド
	 * @return {@link PropertyMeta}
	 * @throws CannotCreatePropertyException プロパティを生成できなかった場合
	 */
	PropertyMeta createPropertyMeta(EntityMeta entityMeta, Field field) throws CannotCreatePropertyException;
	
}
