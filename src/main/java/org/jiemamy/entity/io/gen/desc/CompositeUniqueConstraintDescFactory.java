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
package org.jiemamy.entity.io.gen.desc;

import org.jiemamy.entity.io.gen.meta.DbUniqueKeyMeta;

/**
 * {@link CompositeUniqueConstraintDesc}のファクトリインターフェイス。
 * 
 * @author j5ik2o
 */
public interface CompositeUniqueConstraintDescFactory {
	
	/**
	 * 一意制約記述を取得する。
	 * 
	 * @param uniqueKeyMeta 一意キーメタデータ
	 * @return 存在する場合は一意制約記述、存在しない場合は{@code null}
	 */
	CompositeUniqueConstraintDesc getCompositeUniqueConstraintDesc(DbUniqueKeyMeta uniqueKeyMeta);
}
