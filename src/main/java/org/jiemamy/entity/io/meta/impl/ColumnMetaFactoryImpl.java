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
package org.jiemamy.entity.io.meta.impl;

import java.lang.reflect.Field;

import javax.persistence.Column;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import org.jiemamy.entity.io.meta.ColumnMeta;
import org.jiemamy.entity.io.meta.ColumnMetaFactory;
import org.jiemamy.entity.io.meta.EntityMeta;
import org.jiemamy.entity.io.meta.PropertyMeta;
import org.jiemamy.utils.JmStringUtil;

/**
 * {@link ColumnMetaFactory}の実装クラス。
 * 
 * @author j5ik2o
 */
public class ColumnMetaFactoryImpl implements ColumnMetaFactory {
	
	public ColumnMeta createColumnMeta(Field field, EntityMeta entityMeta, PropertyMeta propertyMeta) {
		Validate.notNull(field);
		Validate.notNull(entityMeta);
		Validate.notNull(propertyMeta);
		ColumnMeta columnMeta = new ColumnMeta();
		String defaultName = fromPropertyNameToColumnName(propertyMeta.getName());
		Column column = field.getAnnotation(Column.class);
		if (column != null) {
			String name = column.name();
			if (StringUtils.isEmpty(name)) {
				name = defaultName;
			}
			columnMeta.setName(name);
			columnMeta.setInsertable(column.insertable());
			columnMeta.setUpdatable(column.updatable());
			columnMeta.setNullable(column.nullable());
			columnMeta.setUnique(column.unique());
			columnMeta.setLength(column.length() > 0 ? column.length() : null);
			columnMeta.setPrecision(column.precision() > 0 ? column.precision() : null);
		} else {
			columnMeta.setName(defaultName);
		}
		return columnMeta;
	}
	
	private String fromPropertyNameToColumnName(String name) {
		return JmStringUtil.toSQLName(name);
	}
	
}
