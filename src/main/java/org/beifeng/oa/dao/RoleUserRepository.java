package org.beifeng.oa.dao;

import org.beifeng.oa.entity.RoleUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleUserRepository extends PagingAndSortingRepository<RoleUser, String>{

	public Page<RoleUser> findByRoleId(String roleId,Pageable p);
}
