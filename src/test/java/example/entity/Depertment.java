/*
 * Copyright 2007-2009 Jiemamy Project and the Others.
 * Created on Apr 8, 2009
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
package example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

import org.jiemamy.entity.io.annotation.ColumnName;
import org.jiemamy.entity.io.annotation.TableName;

/**
 * 部署テーブル。
 * 
 * @author j5ik2o
 */
@Entity
@TableName(logical = "部署")
public class Depertment {
	
	@Id
	@ColumnName(logical = "部署ID")
	private Long depertmentId;
	
	@Column(nullable = false)
	@ColumnName(logical = "部署名")
	private String depertmentName;
	
	@Version
	@Column(nullable = false)
	@ColumnName(logical = "バージョン")
	private Long version;
	

	/**
	 * 部署IDを取得する。
	 * 
	 * @return 部署ID
	 */
	public Long getDepertmentId() {
		return depertmentId;
	}
	
	/**
	 * 部署名を取得する。
	 * 
	 * @return 部署名
	 */
	public String getDepertmentName() {
		return depertmentName;
	}
	
	/**
	 * バージョンを取得する。
	 * 
	 * @return　バージョン
	 */
	public Long getVersion() {
		return version;
	}
	
	/**
	 * 部署IDを設定する。
	 * 
	 * @param depertmentId 部署ID
	 */
	public void setDepertmentId(Long depertmentId) {
		this.depertmentId = depertmentId;
	}
	
	/**
	 * 部署名を設定する。
	 * 
	 * @param depertmentName 部署名
	 */
	public void setDepertmentName(String depertmentName) {
		this.depertmentName = depertmentName;
	}
	
	/**
	 * バージョンを設定する。
	 * 
	 * @param version バージョン
	 */
	public void setVersion(Long version) {
		this.version = version;
	}
	
}
