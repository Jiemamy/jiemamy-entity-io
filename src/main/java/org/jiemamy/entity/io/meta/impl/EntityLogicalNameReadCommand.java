/*
 * Copyright 2007-2009 Jiemamy Project and the Others.
 * Created on Apr 10, 2009
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

import java.util.List;

import org.jiemamy.entity.io.annotation.ColumnName;
import org.jiemamy.entity.io.annotation.TableName;
import org.jiemamy.entity.io.meta.EntityMeta;
import org.jiemamy.entity.io.meta.EntityMetaCommand;
import org.jiemamy.entity.io.meta.PropertyMeta;

/**
 * 論理名を読み取って{@link EntityMeta}に設定するコマンドクラス。
 * 
 * @author j5ik2o
 */
public class EntityLogicalNameReadCommand implements EntityMetaCommand {
	
	public void execute(List<EntityMeta> entityMetaList) {
		for (EntityMeta entityMeta : entityMetaList) {
			TableName tn = entityMeta.getEntityClass().getAnnotation(TableName.class);
			if (tn != null) {
				String tableLogicalName = tn.logical();
				entityMeta.getTableMeta().setLogicalName(tableLogicalName);
			}
			for (PropertyMeta propertyMeta : entityMeta.getAllColumnPropertyMeta()) {
				ColumnName cn = propertyMeta.getField().getAnnotation(ColumnName.class);
				if (cn == null) {
					continue;
				}
				String columnLogicalName = cn.logical();
				propertyMeta.getColumnMeta().setLogicalName(columnLogicalName);
			}
		}
	}
}
