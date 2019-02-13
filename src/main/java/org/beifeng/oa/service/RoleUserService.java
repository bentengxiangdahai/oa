package org.beifeng.oa.service;

import org.beifeng.oa.entity.RoleUser;
import org.springframework.data.domain.Page;

public interface RoleUserService {

	public void add(RoleUser ru);
	
	public Page<RoleUser> queryuserbyRole(String roleId,int nowpage,int count);
}
