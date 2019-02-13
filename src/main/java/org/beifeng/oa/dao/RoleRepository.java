package org.beifeng.oa.dao;

import java.util.List;

import org.beifeng.oa.entity.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleRepository extends PagingAndSortingRepository<Role, String>{

	public List<Role> findByParentId(String parentId);
}
