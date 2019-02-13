package org.beifeng.oa.dao;

import java.util.List;

import org.beifeng.oa.entity.PermissionGroup;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PermissionGroupRepository extends PagingAndSortingRepository<PermissionGroup, String>{

	public List<PermissionGroup> findByGroupnameLike(String groupname);
}
