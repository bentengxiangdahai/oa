package org.beifeng.oa.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.beifeng.oa.entity.PermissionGroup;
import org.beifeng.oa.entity.UserDepartmentMapping;
import org.beifeng.oa.entity.UserPermission;
import org.beifeng.oa.service.PermissionGroupService;
import org.beifeng.oa.service.UserDepartmentMappingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PermissionGroupController extends BaseController{

	@Resource(name="permissiongroupService")
	public PermissionGroupService permissiongroupService;
	
	@Resource(name="userdepartmentService")
	private UserDepartmentMappingService userdepartmentService;
	
	@RequestMapping("/togroup")
	public String togroup(ModelMap model,HttpServletRequest request){
		Iterable<PermissionGroup> iter=permissiongroupService.queryallGroup();
		List<PermissionGroup> list=new ArrayList<PermissionGroup>();
		Iterator<PermissionGroup> it=iter.iterator();
		while(it.hasNext()){
			list.add(it.next());
		}
		model.put("list", list);
		return "permission/group";
	}
	
	@RequestMapping("/toaddgroup")
	public String toaddgroup(HttpServletRequest request){
		return "permission/addgroup";
	}
	
	@RequestMapping("/addgroup")
	public String addgroup(HttpServletRequest request){
		PermissionGroup group=new PermissionGroup();
		group.setGroupname(request.getParameter("groupname"));
		permissiongroupService.addGroup(group);
		return "redirect:/togroup";
	}
	
	@RequestMapping("/querygroup")
	public String querygroup(ModelMap model,HttpServletRequest request){
		String querygroupname="%"+request.getParameter("querygroupname")+"%";
		List<PermissionGroup> list=permissiongroupService.queryBygroupname(querygroupname);
		model.put("list", list);
		return "permission/group";
	}
	
	@RequestMapping("/addusertogroup")
	public String addusertogroup(HttpServletRequest request){
		String groupId=request.getParameter("groupId");
		permissiongroupService.deleteUsertoGroupByGroupId(groupId);
		String [] users=request.getParameter("users").split(",");
		Integer [] ids=new Integer[users.length-1];
		for(int i=0;i<ids.length;i++){
			ids[i]=Integer.parseInt(users[i+1]);
		}
		List<UserDepartmentMapping> list=userdepartmentService.queryListByTypeAndIdIn("user", ids);
		for(UserDepartmentMapping ud:list){
			UserPermission up=new UserPermission();
			up.setGroupId(groupId);
			up.setUserId(ud.getOtherid());
			permissiongroupService.addUsertoGroup(up);
		}
		return "redirect:/togroup";
	}
}
