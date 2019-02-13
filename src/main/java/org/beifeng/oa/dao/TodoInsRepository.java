package org.beifeng.oa.dao;

import org.beifeng.oa.entity.TodoIns;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TodoInsRepository extends PagingAndSortingRepository<TodoIns, String>{

	public TodoIns findBybusinesskey(String businesskey);
}
