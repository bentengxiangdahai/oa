package org.beifeng.oa.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Entity;

@Entity
@Table(name="tododefine")
public class TodoDefine implements Serializable{

	@Id
	private String tododefineId;
	private String business;
	private String tablename;
	private String column1;
	private String column2;
	private String businesskeyname;
	private String businessvalue;
	public String getTododefineId() {
		return tododefineId;
	}
	public void setTododefineId(String tododefineId) {
		this.tododefineId = tododefineId;
	}
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business = business;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String getColumn1() {
		return column1;
	}
	public void setColumn1(String column1) {
		this.column1 = column1;
	}
	public String getColumn2() {
		return column2;
	}
	public void setColumn2(String column2) {
		this.column2 = column2;
	}
	public String getBusinesskeyname() {
		return businesskeyname;
	}
	public void setBusinesskeyname(String businesskeyname) {
		this.businesskeyname = businesskeyname;
	}
	public String getBusinessvalue() {
		return businessvalue;
	}
	public void setBusinessvalue(String businessvalue) {
		this.businessvalue = businessvalue;
	}
}
