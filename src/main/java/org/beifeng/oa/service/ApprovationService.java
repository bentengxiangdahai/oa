package org.beifeng.oa.service;

import java.util.List;

import org.beifeng.oa.entity.Approvation;

public interface ApprovationService {

	public List<Approvation> findbyBusinesskey(String businesskey);
	
	public void addApprovation(Approvation approvation);
}
