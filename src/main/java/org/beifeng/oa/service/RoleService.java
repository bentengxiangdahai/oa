package org.beifeng.oa.service;

import java.util.List;

import org.beifeng.oa.entity.Role;

public interface RoleService {

	public void addRole(Role role);
	public List<Role> queryListByParentId(String parentId);
	public void deleteRole(Role role);
}
