package org.beifeng.oa.service;

import java.util.Iterator;
import java.util.List;

import org.beifeng.oa.entity.Department;

public interface DepartmentService {

	public void addDepartment(Department depart);
	public List<Department> queryByParentId(String parentId);
	public void deleteDepartment(Department depart);
	public Iterator<Department> queryAll();
}
