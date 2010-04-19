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

import java.lang.annotation.Annotation;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * 関連タイプ。
 * 
 * @author j5ik2o
 */
public enum AssociationType {
	
	/**
	 * 多対一です。
	 */
	MANY_TO_ONE(ManyToOne.class),

	/**
	 * 一対多です。
	 */
	ONE_TO_MANY(OneToMany.class),

	/**
	 * 一対一です。
	 */
	ONE_TO_ONE(OneToOne.class);
	
	private final Class<? extends Annotation> annotation;
	

	AssociationType(Class<? extends Annotation> annotation) {
		this.annotation = annotation;
	}
	
	/**
	 * アノテーションを取得する。
	 * 
	 * @return アノテーション
	 */
	public Class<? extends Annotation> getAnnotation() {
		return annotation;
	}
}
