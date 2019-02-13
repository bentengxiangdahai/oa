package org.beifeng.oa.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.beifeng.oa.entity.Leave;
import org.beifeng.oa.entity.TodoDefine;
import org.beifeng.oa.entity.TodoIns;
import org.beifeng.oa.entity.User;
import org.beifeng.oa.service.LeaveService;
import org.beifeng.oa.service.WorkFlowService;
import org.beifeng.oa.vo.TodoPage;
import org.beifeng.oa.vo.TodoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TodoController extends BaseController{

	@Resource(name="workflowService")
	private WorkFlowService workflowService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Resource(name="leaveService")
	private LeaveService leaveService;
	
	@Autowired
	private RepositoryService repositoryService;
	
	@RequestMapping("/todolist")
	public String todolist(ModelMap model,HttpServletRequest request){
		User user=getUser(request);
		String page=request.getParameter("currentPage");
		int currentPage=0;
		if(page!=null && !page.equals("")){
			currentPage=Integer.parseInt(page);
		}
		int pagecount=5;
		String pc=request.getParameter("pagecount");
		if(pc!=null && !pc.equals("")){
			pagecount=Integer.parseInt(pc);
		}
		TodoPage todopage=workflowService.querytodoList(user.getUserId(),currentPage,pagecount);
		model.put("todopage", todopage);
		return "todo/todolist";
	}
	
	@RequestMapping("/claim")
	public String claim(HttpServletRequest request){
		String taskId=request.getParameter("taskId");
		String currentpage=request.getParameter("currentpage");
		workflowService.claim(taskId, getUser(request).getUserId());
		return "redirect:todolist?currentPage="+currentpage;
	}
	
	@RequestMapping("/detail")
	public String detail(HttpServletRequest request){
		String taskId=request.getParameter("taskId");
		Task task=workflowService.getTask(taskId);
		TodoIns todoins=workflowService.getTodoIns(task);
		ExecutionEntity execution=workflowService.getExxcution(task);
		String activitiId=execution.getActivityId();
		String url=todoins.getBusiness()+"/"+activitiId+"?taskId="+taskId+"&activitiId="+activitiId+"&businessKey="+todoins.getBusinesskey()+"&business="+todoins.getBusiness();
		return "redirect:"+url;
	}
}
