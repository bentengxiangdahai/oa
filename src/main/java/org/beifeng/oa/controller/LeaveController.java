package org.beifeng.oa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.beifeng.oa.entity.Approvation;
import org.beifeng.oa.entity.DataDictionary;
import org.beifeng.oa.entity.Leave;
import org.beifeng.oa.entity.TodoIns;
import org.beifeng.oa.entity.User;
import org.beifeng.oa.service.ApprovationService;
import org.beifeng.oa.service.DataDictionaryService;
import org.beifeng.oa.service.LeaveService;
import org.beifeng.oa.service.TodoInsService;
import org.beifeng.oa.service.WorkFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LeaveController extends BaseController{

	@Resource(name="leaveService")
	private LeaveService leaveService;
	
	@Resource(name="datadictionaryService")
	private DataDictionaryService datadictionaryService;
	
	@Resource(name="workflowService")
	private WorkFlowService workflowService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private IdentityService identityService;
	
	@Resource(name="todoinsService")
	private TodoInsService todoinsService;
	
	@Resource(name="approvationService")
	private ApprovationService approvationService;
	
	@RequestMapping("/leavelist")
	public String leavelist(ModelMap model,HttpServletRequest request){
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("USER");
		List<Leave> list=leaveService.queryByUserId(user.getUserId());
		model.put("list", list);
		return "leave/leavelist";
	}
	
	@RequestMapping("/tostartleave")
	public String tostartleave(ModelMap model){
		List<DataDictionary> leavetype=datadictionaryService.querybyDataType("leave");
		model.put("leavetype", leavetype);
		return "leave/start";
	}
	
	@RequestMapping("/startleave")
	public String startleave(HttpServletRequest request){
		User user=getUser(request);
		Leave leave=new Leave();
		leave.setLeaveday(request.getParameter("leaveday"));
		leave.setLeaveendtime(request.getParameter("leaveendtime"));
		leave.setLeavestarttime(request.getParameter("leavestarttime"));
		leave.setLeavetype(request.getParameter("leavetype"));
		leave.setReason(request.getParameter("reason"));
		leave.setLeaveuserId(user.getUserId());
		leave.setLeaveusername(user.getUsername());
		leaveService.addLeave(leave);
		TodoIns todoins=new TodoIns();
		todoins.setBusiness("leave");
		todoins.setBusinesskey(leave.getLeaveId());
		todoinsService.add(todoins);
		String businesskey=leave.getLeaveId();
		//设置流程的启动人
		//identityService.setAuthenticatedUserId(user.getUserId());
		Map map=new HashMap();
		ProcessInstance processins=workflowService.startProcess(user.getUserId(),"leave", businesskey, map);
		leave.setProcessins(processins);
		leave.setProcessinsId(processins.getId());
		return "redirect:leavelist";
	}
	
	@RequestMapping("/leave/departLeaderAduit")
	public String departLeaderAduit(HttpServletRequest request,ModelMap model){
		String taskId=request.getParameter("taskId");
		String activitiId=request.getParameter("activitiId");
		String businessKey=request.getParameter("businessKey");
		String business=request.getParameter("business");
		Leave leave=leaveService.queryByLeaveId(businessKey);
		List<Approvation> applist=approvationService.findbyBusinesskey(businessKey);
		model.put("leave", leave);
		model.put("taskId", taskId);
		model.put("activitiId", activitiId);
		model.put("applist", applist);
		return business+"/"+activitiId;
	}
	
	@RequestMapping("/leave/usertask2")
	public String usertask2(HttpServletRequest request,ModelMap model){
		String taskId=request.getParameter("taskId");
		String activitiId=request.getParameter("activitiId");
		String businessKey=request.getParameter("businessKey");
		String business=request.getParameter("business");
		Leave leave=leaveService.queryByLeaveId(businessKey);
		List<Approvation> applist=approvationService.findbyBusinesskey(businessKey);
		model.put("leave", leave);
		model.put("taskId", taskId);
		model.put("activitiId", activitiId);
		model.put("applist", applist);
		return business+"/"+activitiId;
	}
	
	@RequestMapping("/leave/hrLeaderAduit")
	public String hrLeaderAduit(HttpServletRequest request,ModelMap model){
		String taskId=request.getParameter("taskId");
		String activitiId=request.getParameter("activitiId");
		String businessKey=request.getParameter("businessKey");
		String business=request.getParameter("business");
		Leave leave=leaveService.queryByLeaveId(businessKey);
		List<Approvation> applist=approvationService.findbyBusinesskey(businessKey);
		model.put("leave", leave);
		model.put("taskId", taskId);
		model.put("activitiId", activitiId);
		model.put("applist", applist);
		return business+"/"+activitiId;
	}
	
	@RequestMapping("/leave/usertask3")
	public String usertask3(HttpServletRequest request,ModelMap model){
		String taskId=request.getParameter("taskId");
		String activitiId=request.getParameter("activitiId");
		String businessKey=request.getParameter("businessKey");
		String business=request.getParameter("business");
		Leave leave=leaveService.queryByLeaveId(businessKey);
		List<Approvation> applist=approvationService.findbyBusinesskey(businessKey);
		model.put("leave", leave);
		model.put("taskId", taskId);
		model.put("activitiId", activitiId);
		model.put("applist", applist);
		return business+"/"+activitiId;
	}
	
	@RequestMapping("/leave/completeleavedepart")
	public String completeleavedepart(HttpServletRequest request,HttpServletResponse response){
		String taskId=request.getParameter("taskId");
		String departLeader=request.getParameter("departLeader");
		boolean departLeaderPass=false;
		if(departLeader.equals("1")){
			departLeaderPass=true;
		}
		Map map=new HashMap();
		map.put("departLeaderPass", departLeaderPass);
		Approvation app=new Approvation();
		app.setApprovation(request.getParameter("approvation"));
		app.setBusinesskey(request.getParameter("leaveId"));
		User user=getUser(request);
		app.setApprovationuserId(user.getUserId());
		app.setApprovationusername(user.getUsername());
		approvationService.addApprovation(app);
		workflowService.complete(taskId, map);
		return "redirect:../todolist";
	}
	
	@RequestMapping("/leave/completeleaveusertask2")
	public String completeleaveusertask2(HttpServletRequest request){
		String taskId=request.getParameter("taskId");
		String leaveId=request.getParameter("leaveId");
		Leave leave=leaveService.queryByLeaveId(leaveId);
		leave.setLeaveday(request.getParameter("leaveday"));
		leave.setLeavestarttime(request.getParameter("leavestarttime"));
		leave.setLeavetype(request.getParameter("leavetype"));
		leave.setReason(request.getParameter("reason"));
		leaveService.updateLeave(leave);
		Map map=new HashMap();
		Approvation app=new Approvation();
		app.setApprovation(request.getParameter("approvation"));
		app.setBusinesskey(leaveId);
		User user=getUser(request);
		app.setApprovationuserId(user.getUserId());
		app.setApprovationusername(user.getUsername());
		approvationService.addApprovation(app);
		workflowService.complete(taskId, map);
		return "redirect:../todolist";
	}
	
	@RequestMapping("/leave/completeleavehr")
	public String completeleavehr(HttpServletRequest request){
		String taskId=request.getParameter("taskId");
		String hrLeader=request.getParameter("hrLeaderPass");
		boolean hrLeaderPass=false;
		if(hrLeader.equals("1")){
			hrLeaderPass=true;
		}
		Map map=new HashMap();
		map.put("hrLeaderPass", hrLeaderPass);
		Approvation app=new Approvation();
		app.setApprovation(request.getParameter("approvation"));
		app.setBusinesskey(request.getParameter("leaveId"));
		User user=getUser(request);
		app.setApprovationuserId(user.getUserId());
		app.setApprovationusername(user.getUsername());
		approvationService.addApprovation(app);
		workflowService.complete(taskId, map);
		return "redirect:../todolist";
	}
	
	@RequestMapping("/leave/completeleavexiaojia")
	public String completeleavexiaojia(HttpServletRequest request){
		String taskId=request.getParameter("taskId");
		Map map=new HashMap();
		map.put("need", false);
		Approvation app=new Approvation();
		app.setApprovation(request.getParameter("approvation"));
		app.setBusinesskey(request.getParameter("leaveId"));
		User user=getUser(request);
		app.setApprovationuserId(user.getUserId());
		app.setApprovationusername(user.getUsername());
		approvationService.addApprovation(app);
		workflowService.complete(taskId, map);
		return "redirect:../todolist";
	}
}
