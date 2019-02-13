package org.beifeng.oa.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Entity;

@Entity
@Table(name="permissionfunction")
public class PermissionFunction implements Serializable{

	@Id
	private String pfId;
	private String groupId;
	private String functionId;
	public String getPfId() {
		return pfId;
	}
	public void setPfId(String pfId) {
		this.pfId = pfId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getFunctionId() {
		return functionId;
	}
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}
}
