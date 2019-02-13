package org.beifeng.oa.service.impl;

import java.util.List;

import org.beifeng.oa.dao.DataDictionaryRepository;
import org.beifeng.oa.entity.DataDictionary;
import org.beifeng.oa.service.DataDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("datadictionaryService")
@Transactional(propagation=Propagation.REQUIRED)
public class DataDictionaryServiceImpl implements DataDictionaryService{

	@Autowired
	private DataDictionaryRepository datadictionaryRepository;
	
	@Override
	public List<DataDictionary> querybyDataType(String datatype) {
		// TODO Auto-generated method stub
		return datadictionaryRepository.findByDatatype(datatype);
	}
}
