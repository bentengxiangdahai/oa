package org.beifeng.oa.service;

import java.util.Iterator;
import java.util.List;

import org.beifeng.oa.entity.FunctionTable;
import org.beifeng.oa.entity.PermissionFunction;

public interface FunctionTableService {

	public void addFunction(FunctionTable func);
	
	public List<FunctionTable> queryByParentId(String parentId);
	
	public void deleteFunc(FunctionTable func);
	
	public FunctionTable queryById(Integer functionId);
	
	public void updateFunc(FunctionTable func);
	
	public Iterator<FunctionTable> queryAll();
	
	public void addfunctiontogroup(PermissionFunction pf);
	
	public void updatefunctiontogroup(PermissionFunction pf);
	
	public PermissionFunction queryByGroupId(String groupId);
	
	public List<PermissionFunction> queryByGroupIdIn(List<String> groupidlist);
	
	public List<FunctionTable> queryByfunctionIdIn(List<Integer> funcidlist);
}
