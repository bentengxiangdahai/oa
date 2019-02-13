package org.beifeng.oa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="roleuser")
public class RoleUser implements Serializable{

	@Id
	private String ruId;
	private String userId;
	private String roleId;
	public String getRuId() {
		return ruId;
	}
	public void setRuId(String ruId) {
		this.ruId = ruId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
}
