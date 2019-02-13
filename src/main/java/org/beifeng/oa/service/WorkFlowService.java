package org.beifeng.oa.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.beifeng.oa.entity.TodoDefine;
import org.beifeng.oa.entity.TodoIns;
import org.beifeng.oa.vo.TodoPage;
import org.beifeng.oa.vo.TodoVO;

public interface WorkFlowService {

	public ProcessInstance startProcess(String userId,String key,String businesskey,Map map);
	
	public TodoPage querytodoList(String userId,int currentpage,int pagecount);
	
	public void claim(String taskId,String userId);
	
	public Task getTask(String taskId);
	
	public TodoDefine getTodoDefine(Task task);
	
	public ExecutionEntity getExxcution(Task task);
	
	public TodoIns getTodoIns(Task task);
	
	public void complete(String taskId,Map map);
}
