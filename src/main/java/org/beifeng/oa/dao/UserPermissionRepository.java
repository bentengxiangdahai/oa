package org.beifeng.oa.dao;

import java.util.List;

import org.beifeng.oa.entity.UserPermission;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserPermissionRepository extends PagingAndSortingRepository<UserPermission, String>{

	public List<UserPermission> findByGroupId(String groupId);
	
	public List<UserPermission> findByUserId(String userId);
}
