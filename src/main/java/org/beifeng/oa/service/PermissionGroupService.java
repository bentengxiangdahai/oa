package org.beifeng.oa.service;

import java.util.List;

import org.beifeng.oa.entity.PermissionGroup;
import org.beifeng.oa.entity.UserPermission;

public interface PermissionGroupService {

	public void addGroup(PermissionGroup group);
	
	public Iterable<PermissionGroup> queryallGroup();
	
	public List<PermissionGroup> queryBygroupname(String groupname);
	
	public void addUsertoGroup(UserPermission up);
	
	public void deleteUsertoGroupByGroupId(String groupId);
	
	public List<UserPermission> queryByGroupId(String groupId);
	
	public List<UserPermission> queryByUserId(String userId);
}
