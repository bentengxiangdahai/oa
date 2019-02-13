package org.beifeng.oa.service.impl;

import java.util.List;

import org.beifeng.oa.dao.UserRepository;
import org.beifeng.oa.entity.User;
import org.beifeng.oa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional(propagation=Propagation.REQUIRED)
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void addUser(User user) {
		userRepository.save(user);
	}
	
	public User login(String email,String password){
		return userRepository.findByEmailAndPassword(email, password);
	}
	
	public List<User> queryByDepartment(String departmentId){
		return userRepository.findByDepartmentId(departmentId);
	}
	
	public Long totalCount(String departmentId){
		return userRepository.findcountByDepartmentId(departmentId);
	}
	
	public Page<User> queryPageforDepartment(String departmentId,int nowpage,int size){
		Order order=new Order(Direction.ASC, "userId");
		Sort sort=new Sort(order);
		PageRequest pr=new PageRequest(nowpage, size, sort);
		return userRepository.findByDepartmentId(departmentId, pr);
	}
	
	public User queryUser(String userId){
		return userRepository.findOne(userId);
	}
	
	public void updateUser(User user){
		userRepository.save(user);
	}
	
	public List<User> findByuserIdIn(String [] userids){
		return userRepository.findByuserIdIn(userids);
	}
}
