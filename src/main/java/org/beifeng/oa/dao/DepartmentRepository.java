package org.beifeng.oa.dao;

import java.util.List;

import org.beifeng.oa.entity.Department;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DepartmentRepository extends PagingAndSortingRepository<Department, String>{

	public List<Department> findByParentId(String parentId);
}
