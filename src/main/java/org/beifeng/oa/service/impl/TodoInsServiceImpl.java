package org.beifeng.oa.service.impl;

import org.beifeng.oa.dao.TodoInsRepository;
import org.beifeng.oa.entity.TodoIns;
import org.beifeng.oa.service.TodoInsService;
import org.beifeng.oa.utils.KeyProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("todoinsService")
@Transactional(propagation=Propagation.REQUIRED)
public class TodoInsServiceImpl implements TodoInsService{

	@Autowired
	private TodoInsRepository todoinsRepository;
	
	@Override
	public TodoIns findBybusinesskey(String businesskey) {
		// TODO Auto-generated method stub
		return todoinsRepository.findBybusinesskey(businesskey);
	}
	
	public void add(TodoIns todoins){
		todoins.setTodoinsid(KeyProvider.getPrimaryKey());
		todoinsRepository.save(todoins);
	}
}
