package org.beifeng.oa.dao;

import java.util.List;

import org.beifeng.oa.entity.UserDepartmentMapping;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDepartmentMappingRepository extends PagingAndSortingRepository<UserDepartmentMapping, Integer>{

	public UserDepartmentMapping findByOtheridAndType(String otherid,String type);
	
	public List<UserDepartmentMapping> findByTypeAndIdIn(String type,Integer [] ids);
}
