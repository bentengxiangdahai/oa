package org.beifeng.oa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="approvation")
public class Approvation implements Serializable{

	@Id
	private String approvationId;
	private String approvation;
	private String approvationuserId;
	private String approvationusername;
	private Date approvationtime;
	private String businesskey;
	public String getApprovationId() {
		return approvationId;
	}
	public void setApprovationId(String approvationId) {
		this.approvationId = approvationId;
	}
	public String getApprovation() {
		return approvation;
	}
	public void setApprovation(String approvation) {
		this.approvation = approvation;
	}
	public String getApprovationuserId() {
		return approvationuserId;
	}
	public void setApprovationuserId(String approvationuserId) {
		this.approvationuserId = approvationuserId;
	}
	public String getApprovationusername() {
		return approvationusername;
	}
	public void setApprovationusername(String approvationusername) {
		this.approvationusername = approvationusername;
	}
	public Date getApprovationtime() {
		return approvationtime;
	}
	public void setApprovationtime(Date approvationtime) {
		this.approvationtime = approvationtime;
	}
	public String getBusinesskey() {
		return businesskey;
	}
	public void setBusinesskey(String businesskey) {
		this.businesskey = businesskey;
	}
}
