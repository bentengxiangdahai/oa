package org.beifeng.oa.dao;

import java.util.List;

import org.beifeng.oa.entity.Approvation;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ApprovationRepository extends PagingAndSortingRepository<Approvation, String>{

	public List<Approvation> findByBusinesskey(String businesskey);
}
