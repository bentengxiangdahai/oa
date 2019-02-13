package org.beifeng.oa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="todoins")
public class TodoIns implements Serializable{

	@Id
	private String todoinsid;
	private String businesskey;
	private String business;
	public String getTodoinsid() {
		return todoinsid;
	}
	public void setTodoinsid(String todoinsid) {
		this.todoinsid = todoinsid;
	}
	public String getBusinesskey() {
		return businesskey;
	}
	public void setBusinesskey(String businesskey) {
		this.businesskey = businesskey;
	}
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business = business;
	}
}
