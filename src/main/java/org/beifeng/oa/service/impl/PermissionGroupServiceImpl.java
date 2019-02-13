package org.beifeng.oa.service.impl;

import java.util.List;

import org.beifeng.oa.dao.PermissionGroupRepository;
import org.beifeng.oa.dao.UserPermissionRepository;
import org.beifeng.oa.entity.PermissionGroup;
import org.beifeng.oa.entity.UserPermission;
import org.beifeng.oa.service.PermissionGroupService;
import org.beifeng.oa.utils.KeyProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("permissiongroupService")
@Transactional(propagation=Propagation.REQUIRED)
public class PermissionGroupServiceImpl implements PermissionGroupService{

	@Autowired
	private PermissionGroupRepository permissiongroupRepository;
	
	@Autowired
	private UserPermissionRepository userpermissionRepository;
	
	@Override
	public void addGroup(PermissionGroup group) {
		// TODO Auto-generated method stub
		group.setGroupId(KeyProvider.getPrimaryKey());
		permissiongroupRepository.save(group);
	}

	@Override
	public Iterable<PermissionGroup> queryallGroup() {
		// TODO Auto-generated method stub
		return permissiongroupRepository.findAll();
	}

	public List<PermissionGroup> queryBygroupname(String groupname){
		return permissiongroupRepository.findByGroupnameLike(groupname);
	}
	
	public void addUsertoGroup(UserPermission up){
		up.setUpId(KeyProvider.getPrimaryKey());
		userpermissionRepository.save(up);
	}
	
	public void deleteUsertoGroupByGroupId(String groupId){
		List<UserPermission> list=userpermissionRepository.findByGroupId(groupId);
		for(UserPermission u:list){
			userpermissionRepository.delete(u);
		}
	}
	
	public List<UserPermission> queryByGroupId(String groupId){
		return userpermissionRepository.findByGroupId(groupId);
	}
	
	public List<UserPermission> queryByUserId(String userId){
		return userpermissionRepository.findByUserId(userId);
	}
}
