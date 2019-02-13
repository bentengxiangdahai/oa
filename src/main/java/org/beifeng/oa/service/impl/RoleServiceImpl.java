package org.beifeng.oa.service.impl;

import java.util.List;

import org.beifeng.oa.dao.RoleRepository;
import org.beifeng.oa.entity.Role;
import org.beifeng.oa.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("roleService")
@Transactional(propagation=Propagation.REQUIRED)
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public void addRole(Role role) {
		// TODO Auto-generated method stub
		roleRepository.save(role);
	}
	
	public List<Role> queryListByParentId(String parentId) {
		return roleRepository.findByParentId(parentId);
	};
	
	public void deleteRole(Role role){
		roleRepository.delete(role);
	}
}
