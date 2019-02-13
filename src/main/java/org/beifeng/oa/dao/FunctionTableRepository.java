package org.beifeng.oa.dao;

import java.util.List;

import org.beifeng.oa.entity.FunctionTable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FunctionTableRepository extends PagingAndSortingRepository<FunctionTable, Integer>{

	public List<FunctionTable> findByParentId(Integer parentId);
	
	public List<FunctionTable> findByfunctionIdIn(List<Integer> funcidlist);
}
