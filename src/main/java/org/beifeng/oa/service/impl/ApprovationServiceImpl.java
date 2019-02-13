package org.beifeng.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.beifeng.oa.dao.ApprovationRepository;
import org.beifeng.oa.entity.Approvation;
import org.beifeng.oa.service.ApprovationService;
import org.beifeng.oa.utils.KeyProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("approvationService")
@Transactional(propagation=Propagation.REQUIRED)
public class ApprovationServiceImpl implements ApprovationService{

	@Autowired
	private ApprovationRepository approvationRepository;
	
	@Override
	public List<Approvation> findbyBusinesskey(String businesskey) {
		// TODO Auto-generated method stub
		return approvationRepository.findByBusinesskey(businesskey);
	}

	public void addApprovation(Approvation approvation){
		approvation.setApprovationId(KeyProvider.getPrimaryKey());
		approvation.setApprovationtime(new Date());
		approvationRepository.save(approvation);
	}
}
