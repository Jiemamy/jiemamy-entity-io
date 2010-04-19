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

import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import org.jiemamy.entity.io.meta.EntityMeta;
import org.jiemamy.entity.io.meta.TableMeta;
import org.jiemamy.entity.io.meta.TableMetaFactory;
import org.jiemamy.utils.JmStringUtil;

/**
 * {@link TableMetaFactory}の実装クラス。
 * 
 * @author j5ik2o
 */
public class TableMetaFactoryImpl implements TableMetaFactory {
	
	public TableMeta createTableMeta(EntityMeta entityMeta) {
		Validate.notNull(entityMeta);
		TableMeta tableMeta = new TableMeta();
		String defaultName = fromEntityNameToTableName(entityMeta.getName());
		Table table = entityMeta.getEntityClass().getAnnotation(Table.class);
		if (table != null) {
			String name = table.name();
			if (StringUtils.isEmpty(name)) {
				name = defaultName;
			}
			tableMeta.setName(name);
			String catalog = table.catalog();
			if (StringUtils.isEmpty(catalog) == false) {
				tableMeta.setCatalog(catalog);
			}
			String schema = table.schema();
			if (StringUtils.isEmpty(schema) == false) {
				tableMeta.setSchema(schema);
			}
		} else {
			tableMeta.setName(defaultName);
		}
		return tableMeta;
	}
	
	private String fromEntityNameToTableName(String entityName) {
		Validate.notNull(entityName);
		return JmStringUtil.toSQLName(entityName);
	}
}
