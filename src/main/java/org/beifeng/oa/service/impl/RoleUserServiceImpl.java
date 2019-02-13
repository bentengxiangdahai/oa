package org.beifeng.oa.service.impl;

import org.beifeng.oa.dao.RoleUserRepository;
import org.beifeng.oa.entity.RoleUser;
import org.beifeng.oa.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("roleuserService")
@Transactional(propagation=Propagation.REQUIRED)
public class RoleUserServiceImpl implements RoleUserService{

	@Autowired
	private RoleUserRepository roleuserRepository;
	
	@Override
	public void add(RoleUser ru) {
		// TODO Auto-generated method stub
		roleuserRepository.save(ru);
	}
	
	public Page<RoleUser> queryuserbyRole(String roleId,int nowpage,int count){
		PageRequest pr=new PageRequest(nowpage, count);
		return roleuserRepository.findByRoleId(roleId, pr);
	}
}
