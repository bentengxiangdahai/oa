package org.beifeng.oa.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.beifeng.oa.entity.Department;
import org.beifeng.oa.entity.Role;
import org.beifeng.oa.entity.RoleUser;
import org.beifeng.oa.entity.UserDepartmentMapping;
import org.beifeng.oa.service.RoleService;
import org.beifeng.oa.service.RoleUserService;
import org.beifeng.oa.service.UserDepartmentMappingService;
import org.beifeng.oa.utils.KeyProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RoleController extends BaseController{

	@Resource(name="roleService")
	private RoleService roleService;
	
	@Resource(name="roleuserService")
	private RoleUserService roleuserService;
	
	@Autowired
	private IdentityService identityService;
	
	@Resource(name="userdepartmentService")
	private UserDepartmentMappingService userdepartmentService;
	
	@RequestMapping("/torole")
	public String torole(ModelMap model,HttpServletRequest request){
		List<Role> list=roleService.queryListByParentId("root");
		model.put("list", list);
		return "organiza/role";
	}
	
	@RequestMapping("/toaddrole")
	public String toaddrole(ModelMap model,HttpServletRequest request){
		String parentId=request.getParameter("parentId");
		model.put("parentId", parentId);
		return "organiza/addrole";
	}
	
	@RequestMapping("/addrole")
	public String addrole(HttpServletRequest request){
		Role role=new Role();
		String roleId=KeyProvider.getPrimaryKey();
		role.setParentId(request.getParameter("parentId"));
		role.setRolename(request.getParameter("rolename"));
		role.setRoleId(roleId);
		roleService.addRole(role);
		Group g=identityService.newGroup(roleId);
		g.setName(role.getRolename());
		identityService.saveGroup(g);
		return "redirect:torole";
	}
	
	@RequestMapping("/queryrolelist")
	public void queryrolelist(HttpServletRequest request,HttpServletResponse response){
		String parentId=request.getParameter("parentId");
		List<Role> list=roleService.queryListByParentId(parentId);
		StringBuffer sb=new StringBuffer();
		for(Role d:list){
			sb.append("<li id='"+d.getRoleId()+"'><span class='text'>"+d.getRolename()+"</span>");
			sb.append("<ul class='ajax'><li>{url:querydepartlist?parentId="+d.getRoleId()+"}</li></ul>");
			sb.append("</li>");
		}
		writetoPage(response, sb.toString());
	}
	
	@RequestMapping("/deleterole")
	public void deleterole(HttpServletRequest request,HttpServletResponse response){
		String roleId=request.getParameter("roleId");
		List<Role> list=roleService.queryListByParentId(roleId);
		if(list.size()>0){
			writetoPage(response, "haveson");
			return;
		}else{
			//省略了该角色下是否有人的判断
			if(true){
				Role role=new Role();
				role.setRoleId(roleId);
				roleService.deleteRole(role);
				writetoPage(response, "ok");
				identityService.deleteGroup(roleId);
			}
		}
	}
	
	@RequestMapping("/addusertorole")
	public void addusertorole(HttpServletRequest request,HttpServletResponse response){
		String users=request.getParameter("users");
		String roleId=request.getParameter("roleId");
		users=users.substring(1,users.length());
		String [] ss=users.split(",");
		Integer [] ids=new Integer[ss.length];
		for(int i=0;i<ss.length;i++){
			ids[i]=Integer.parseInt(ss[i]);
		}
		List<UserDepartmentMapping> list=userdepartmentService.queryListByTypeAndIdIn("user", ids);
		for(UserDepartmentMapping ud:list){
			RoleUser ru=new RoleUser();
			ru.setRuId(KeyProvider.getPrimaryKey());
			ru.setUserId(ud.getOtherid());
			ru.setRoleId(roleId);
			roleuserService.add(ru);
			identityService.createMembership(ud.getOtherid(), roleId);
		}
		writetoPage(response, "ok");
	}
}
