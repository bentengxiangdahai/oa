package org.beifeng.oa.service.impl;

import java.util.Iterator;
import java.util.List;

import org.beifeng.oa.dao.FunctionTableRepository;
import org.beifeng.oa.dao.PermissionFunctionRepository;
import org.beifeng.oa.entity.FunctionTable;
import org.beifeng.oa.entity.PermissionFunction;
import org.beifeng.oa.service.FunctionTableService;
import org.beifeng.oa.utils.KeyProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("functiontableService")
@Transactional(propagation=Propagation.REQUIRED)
public class FunctionTableServiceImpl implements FunctionTableService{

	@Autowired
	private FunctionTableRepository functiontableRepository;
	
	@Autowired
	private PermissionFunctionRepository permissionfunctionRepository;
	
	@Override
	public void addFunction(FunctionTable func) {
		// TODO Auto-generated method stub
		functiontableRepository.save(func);
	}

	@Override
	public List<FunctionTable> queryByParentId(String parentId) {
		// TODO Auto-generated method stub
		return functiontableRepository.findByParentId(Integer.parseInt(parentId));
	}

	public void deleteFunc(FunctionTable func){
		functiontableRepository.delete(func);
	}
	
	public FunctionTable queryById(Integer functionId){
		return functiontableRepository.findOne(functionId);
	}
	
	public void updateFunc(FunctionTable func){
		functiontableRepository.save(func);
	}
	
	public Iterator<FunctionTable> queryAll(){
		return functiontableRepository.findAll().iterator();
	}

	@Override
	public void addfunctiontogroup(PermissionFunction pf) {
		pf.setPfId(KeyProvider.getPrimaryKey());
		permissionfunctionRepository.save(pf);
	}

	@Override
	public void updatefunctiontogroup(PermissionFunction pf) {
		permissionfunctionRepository.save(pf);
	}

	@Override
	public PermissionFunction queryByGroupId(String groupId) {
		PermissionFunction pf=null;
		List<PermissionFunction> list=permissionfunctionRepository.findByGroupId(groupId);
		if(list!=null && list.size()>0){
			pf=list.get(0);
		}
		return pf;
	}
	
	public List<PermissionFunction> queryByGroupIdIn(List<String> groupidlist){
		return permissionfunctionRepository.findByGroupIdIn(groupidlist);
	}
	
	public List<FunctionTable> queryByfunctionIdIn(List<Integer> funcidlist){
		return functiontableRepository.findByfunctionIdIn(funcidlist);
	}
}
