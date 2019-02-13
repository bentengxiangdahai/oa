package org.beifeng.oa.service.impl;

import java.util.List;

import org.beifeng.oa.dao.UserDepartmentMappingRepository;
import org.beifeng.oa.entity.UserDepartmentMapping;
import org.beifeng.oa.service.UserDepartmentMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("userdepartmentService")
@Transactional(propagation=Propagation.REQUIRED)
public class UserDepartmentMappingServiceImpl implements UserDepartmentMappingService{

	@Autowired
	private UserDepartmentMappingRepository userdepartmentmappingRepository;
	
	@Override
	public void save(UserDepartmentMapping mapping) {
		userdepartmentmappingRepository.save(mapping);
	}

	@Override
	public UserDepartmentMapping queryByOtheridandType(String otherid,String type) {
		// TODO Auto-generated method stub
		return userdepartmentmappingRepository.findByOtheridAndType(otherid, type);
	}

	public List<UserDepartmentMapping> queryListByTypeAndIdIn(String type,Integer [] ids){
		return userdepartmentmappingRepository.findByTypeAndIdIn(type, ids);
	}
	
}
