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
package org.jiemamy.entity.io.gen.model;

import org.jiemamy.entity.io.gen.desc.AssociationDesc;

/**
 * {@link AssociationModel}のファクトリインターフェイス。
 * 
 * @author j5ik2o
 */
public interface AssociationModelFactory {
	
	/**
	 * 関連モデルを取得する。
	 * 
	 * @param associationDesc 関連記述
	 * @return 関連モデル
	 */
	AssociationModel getAssociationModel(AssociationDesc associationDesc);
}
