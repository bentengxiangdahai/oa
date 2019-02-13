package org.beifeng.oa.service.impl;

import org.beifeng.oa.dao.TodoDefineRepository;
import org.beifeng.oa.entity.TodoDefine;
import org.beifeng.oa.service.TodoDefineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("tododefineService")
@Transactional(propagation=Propagation.REQUIRED)
public class TodoDefineServiceImpl implements TodoDefineService{

	@Autowired
	private TodoDefineRepository tododefineRepository;
	
	@Override
	public TodoDefine findByBusiness(String business) {
		// TODO Auto-generated method stub
		return tododefineRepository.findByBusiness(business);
	}
}
