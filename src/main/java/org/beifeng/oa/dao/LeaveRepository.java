package org.beifeng.oa.dao;

import java.util.List;

import org.beifeng.oa.entity.Leave;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LeaveRepository extends PagingAndSortingRepository<Leave, String>{

	public List<Leave> findByLeaveuserId(String userId);
}
