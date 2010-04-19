/*
 * Copyright 2007-2009 Jiemamy Project and the Others.
 * Created on Apr 2, 2009
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.jiemamy.entity.io.annotation.ColumnName;
import org.jiemamy.entity.io.annotation.TableName;

/**
 * 従業員
 * 
 * @author j5ik2o
 */
@Entity
@Table(name = "EMPLOYEE")
@TableName(logical = "従業員")
public class Employee {
	
	@Id
	@ColumnName(logical = "従業員ID")
	private Long employeeId;
	
	@Column(nullable = false)
	@ColumnName(logical = "従業員名")
	private String employeeName;
	
	@Column(nullable = false)
	@ColumnName(logical = "部署ID")
	private Long depertmentId;
	
	@Version
	@Column(nullable = false)
	@ColumnName(logical = "バージョン")
	private Long version;
	
	@ManyToOne
	@JoinColumn(name = "DEPERTMENT_ID", referencedColumnName = "DEPERTMENT_ID")
	private Depertment depertment;
	

	/**
	 * 部署を取得する。
	 * 
	 * @return 部署
	 */
	public Depertment getDepertment() {
		return depertment;
	}
	
	/**
	 * 部署IDを取得する。
	 * 
	 * @return 部署ID
	 */
	public Long getDepertmentId() {
		return depertmentId;
	}
	
	/**
	 * 従業員IDを取得する。
	 * 
	 * @return 従業員ID
	 */
	public Long getEmployeeId() {
		return employeeId;
	}
	
	/**
	 * 従業員名を取得する。
	 * 
	 * @return 従業員名
	 */
	public String getEmployeeName() {
		return employeeName;
	}
	
	/**
	 * バージョンを取得する。
	 * 
	 * @return バージョン
	 */
	public Long getVersion() {
		return version;
	}
	
	/**
	 * 部署を設定する。
	 * 
	 * @param depertment 部署
	 */
	public void setDepertment(Depertment depertment) {
		this.depertment = depertment;
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
	 * 従業員IDを設定する。
	 * 
	 * @param employeeId 従業員ID
	 */
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	
	/**
	 * 従業員名を設定する。
	 * 
	 * @param employeeName 従業員名
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
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
