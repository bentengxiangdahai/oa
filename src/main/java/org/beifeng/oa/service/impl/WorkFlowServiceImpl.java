package org.beifeng.oa.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.beifeng.oa.dao.DBControll;
import org.beifeng.oa.entity.TodoDefine;
import org.beifeng.oa.entity.TodoIns;
import org.beifeng.oa.service.TodoDefineService;
import org.beifeng.oa.service.TodoInsService;
import org.beifeng.oa.service.WorkFlowService;
import org.beifeng.oa.vo.TodoPage;
import org.beifeng.oa.vo.TodoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service("workflowService")
@Transactional(propagation=Propagation.REQUIRED)
public class WorkFlowServiceImpl implements WorkFlowService{

	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private IdentityService identityService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private TodoInsService todoinsService;
	
	@Autowired
	private TodoDefineService tododefineService;
	
	@Override
	public ProcessInstance startProcess(String userId,String key, String businesskey, Map map) {
		identityService.setAuthenticatedUserId(userId);
		return runtimeService.startProcessInstanceByKey(key, businesskey, map);
	}

	public TodoPage querytodoList(String userId,int currentpage,int pagecount){
		TodoPage todopage=new TodoPage();
		//取得一个人（当前用户）未签收的任务
		List<Task> taskCandidates=taskService.createTaskQuery().processDefinitionKey("leave").taskCandidateUser(userId).orderByTaskPriority().desc().list();
		//取得一个人（当前用户）的待办任务（已签收）
		List<Task> taskAssigness=taskService.createTaskQuery().processDefinitionKey("leave").taskAssignee(userId).orderByTaskPriority().desc().list();
		List<Task> tasklist=new ArrayList<Task>();
		tasklist.addAll(taskCandidates);
		tasklist.addAll(taskAssigness);
		int totalcount=tasklist.size();
		todopage.setTotalcount(totalcount);
		int totalpage=0;
		boolean hasnext=false;
		boolean haslast=false;
		if(totalcount%pagecount==0){
			totalpage=totalcount/pagecount;
		}else{
			totalpage=totalcount/pagecount+1;
		}
		if(totalpage>(currentpage+1)){
			hasnext=true;
		}
		if(currentpage>0){
			haslast=true;
		}
		todopage.setHaslast(haslast);
		todopage.setHasnext(hasnext);
		todopage.setCurrentpage(currentpage);
		todopage.setTotalpage(totalpage);
		List<TodoVO> list=new ArrayList<TodoVO>();
		int start=currentpage*pagecount;
		int end=0;
		if((currentpage+1)<totalpage){
			end=(currentpage+1)*pagecount;
		}else{
			end=start+(totalcount%pagecount);
		}
		if(totalpage==1){
			end=tasklist.size();
		}
		for(int i=start;i<end;i++){
			Task task=tasklist.get(i);
			String processinsId=task.getProcessInstanceId();
			String processdifineId=task.getProcessDefinitionId();
			ProcessInstance pi=runtimeService.createProcessInstanceQuery().processInstanceId(processinsId).singleResult();
			ProcessDefinition pd=repositoryService.createProcessDefinitionQuery().processDefinitionId(processdifineId).singleResult();
			String businesskey=pi.getBusinessKey();
			TodoIns todoins=todoinsService.findBybusinesskey(businesskey);
			String business=todoins.getBusiness();
			TodoDefine tododefine=tododefineService.findByBusiness(business);
			String sql="select "+tododefine.getColumn1()+","+tododefine.getColumn2()+" from "+tododefine.getTablename()+" where "+tododefine.getBusinesskeyname()+"='"+businesskey+"'";
			DBControll db=new DBControll();
			ResultSet rs=db.getData(sql);
			TodoVO vo=new TodoVO();
			try {
				while(rs.next()){
					String column1=rs.getString(1);
					String column2=rs.getString(2);
					String businessvalue=tododefine.getBusinessvalue();
					vo.setColumn1(column1);
					vo.setColumn2(column2);
					vo.setBusinessvalue(businessvalue);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				db.close();
			}
			vo.setProcessdefine(pd);
			vo.setProcessins(pi);
			vo.setTask(task);
			vo.setProcessinsId(processinsId);
			list.add(vo);
		}
		todopage.setContext(list);
		return todopage;
	}
	
	public void claim(String taskId,String userId){
		taskService.claim(taskId, userId);
	}
	
	public Task getTask(String taskId){
		return taskService.createTaskQuery().taskId(taskId).singleResult();
	}
	
	public TodoDefine getTodoDefine(Task task){
		String processinsId=task.getProcessInstanceId();
		ProcessInstance pi=runtimeService.createProcessInstanceQuery().processInstanceId(processinsId).singleResult();
		String businesskey=pi.getBusinessKey();
		TodoIns todoins=todoinsService.findBybusinesskey(businesskey);
		TodoDefine tododefine=tododefineService.findByBusiness(todoins.getBusiness());
		return tododefine;
	}
	
	public ExecutionEntity getExxcution(Task task){
		return (ExecutionEntity) runtimeService.createExecutionQuery().executionId(task.getExecutionId()).processInstanceId(task.getProcessInstanceId()).singleResult();
	}
	
	public TodoIns getTodoIns(Task task){
		String processinsId=task.getProcessInstanceId();
		ProcessInstance pi=runtimeService.createProcessInstanceQuery().processInstanceId(processinsId).singleResult();
		String businesskey=pi.getBusinessKey();
		TodoIns todoins=todoinsService.findBybusinesskey(businesskey);
		return todoins;
	}
	
	public void complete(String taskId,Map map){
		taskService.complete(taskId, map);
	}
}
