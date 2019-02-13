package org.beifeng.oa.service;

import java.util.List;

import org.beifeng.oa.entity.User;
import org.springframework.data.domain.Page;

public interface UserService {

	public void addUser(User user);
	
	public User login(String email,String password);
	
	public List<User> queryByDepartment(String departmentId);
	
	public Long totalCount(String departmentId);
	
	public Page<User> queryPageforDepartment(String departmentId,int nowpage,int count);
	
	public User queryUser(String userId);
	
	public void updateUser(User user);
	
	public List<User> findByuserIdIn(String [] userids);
}
