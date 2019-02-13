package org.beifeng.oa.service;

import org.beifeng.oa.entity.TodoIns;

public interface TodoInsService {

	public TodoIns findBybusinesskey(String businesskey);
	
	public void add(TodoIns todoins);
}
