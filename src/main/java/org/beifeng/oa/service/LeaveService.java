package org.beifeng.oa.service;

import java.util.List;

import org.beifeng.oa.entity.Leave;

public interface LeaveService {

	public void addLeave(Leave leave);
	
	public List<Leave> queryByUserId(String userId);
	
	public Leave queryByLeaveId(String leaveId);
	
	public void updateLeave(Leave leave);
}
