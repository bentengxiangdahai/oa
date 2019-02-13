package org.beifeng.oa.service.impl;

import java.util.List;

import org.beifeng.oa.dao.LeaveRepository;
import org.beifeng.oa.entity.Leave;
import org.beifeng.oa.service.LeaveService;
import org.beifeng.oa.utils.KeyProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("leaveService")
@Transactional(propagation=Propagation.REQUIRED)
public class LeaveServiceImpl implements LeaveService{

	@Autowired
	private LeaveRepository leaveRepository;
	
	@Override
	public void addLeave(Leave leave) {
		leave.setLeaveId(KeyProvider.getPrimaryKey());
		leaveRepository.save(leave);
	}

	public List<Leave> queryByUserId(String userId){
		return leaveRepository.findByLeaveuserId(userId);
	}
	
	public Leave queryByLeaveId(String leaveId){
		return leaveRepository.findOne(leaveId);
	}
	
	public void updateLeave(Leave leave){
		leaveRepository.save(leave);
	}
}
