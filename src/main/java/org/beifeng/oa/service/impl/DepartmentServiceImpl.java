package org.beifeng.oa.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.beifeng.oa.dao.DepartmentRepository;
import org.beifeng.oa.entity.Department;
import org.beifeng.oa.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("departmentService")
@Transactional(propagation=Propagation.REQUIRED)
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Override
	public void addDepartment(Department depart) {
		departmentRepository.save(depart);
	}
	
	@Override
	public List<Department> queryByParentId(String parentId) {
		// TODO Auto-generated method stub
		return departmentRepository.findByParentId(parentId);
	}
	
	public void deleteDepartment(Department depart){
		departmentRepository.delete(depart);
	}
	
	public Iterator<Department> queryAll(){
		Iterable<Department> it= departmentRepository.findAll();
		return it.iterator();
	}
}
