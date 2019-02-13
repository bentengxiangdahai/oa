package org.beifeng.oa.dao;

import java.util.List;

import org.beifeng.oa.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, String>{

	public User findByEmailAndPassword(String email,String password);
	
	public List<User> findByDepartmentId(String departmentId);
	
	@Query("select count(*) from User u where u.departmentId=?1")
	public Long findcountByDepartmentId(String departmentId);
	
	public Page<User> findByDepartmentId(String departmentId,Pageable page);
	
	public List<User> findByuserIdIn(String [] userids);
}
