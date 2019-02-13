package org.beifeng.oa.dao;

import java.util.List;

import org.beifeng.oa.entity.DataDictionary;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DataDictionaryRepository extends PagingAndSortingRepository<DataDictionary, String>{

	public List<DataDictionary> findByDatatype(String datatype);
}
