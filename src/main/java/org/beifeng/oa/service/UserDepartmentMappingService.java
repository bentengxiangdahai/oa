package org.beifeng.oa.service;

import java.util.List;

import org.beifeng.oa.entity.UserDepartmentMapping;

public interface UserDepartmentMappingService {

	public void save(UserDepartmentMapping mapping);
	public UserDepartmentMapping queryByOtheridandType(String otherid,String type);
	public List<UserDepartmentMapping> queryListByTypeAndIdIn(String type,Integer [] ids);
}
