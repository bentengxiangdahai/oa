package org.beifeng.oa.service;

import java.util.List;

import org.beifeng.oa.entity.DataDictionary;

public interface DataDictionaryService {

	public List<DataDictionary> querybyDataType(String datatype);
}
