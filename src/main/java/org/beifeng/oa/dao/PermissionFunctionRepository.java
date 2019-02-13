package org.beifeng.oa.dao;

import java.util.List;

import org.beifeng.oa.entity.PermissionFunction;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PermissionFunctionRepository extends PagingAndSortingRepository<PermissionFunction, String>{

	public List<PermissionFunction> findByGroupId(String groupId);
	
	public List<PermissionFunction> findByGroupIdIn(List<String> groupidlist);
}
