package org.beifeng.oa.dao;


import org.beifeng.oa.entity.TodoDefine;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TodoDefineRepository extends PagingAndSortingRepository<TodoDefine, String> {

	public TodoDefine findByBusiness(String business);
	
}
