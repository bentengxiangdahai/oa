package org.beifeng.oa.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.beifeng.oa.entity.Department;
import org.beifeng.oa.entity.User;
import org.beifeng.oa.entity.UserDepartmentMapping;
import org.beifeng.oa.entity.UserPermission;
import org.beifeng.oa.service.DepartmentService;
import org.beifeng.oa.service.PermissionGroupService;
import org.beifeng.oa.service.UserDepartmentMappingService;
import org.beifeng.oa.service.UserService;
import org.beifeng.oa.utils.StringUtils;
import org.beifeng.oa.vo.TreeNode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ComponentsController extends BaseController{

	@Resource(name="departmentService")
	private DepartmentService departmentService;
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="userdepartmentService")
	private UserDepartmentMappingService userdepartmentService;
	
	@Resource(name="permissiongroupService")
	private PermissionGroupService permissiongroupService;
	
	@RequestMapping("/getusercomponent")
	public String getusercomponent(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		inittree(model,request);
		return "redirect:components/getuser.jsp";
	}
	
	@RequestMapping("/getusercomponenttorole")
	public String getusercomponenttorole(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		String roleId=request.getParameter("roleId");
		HttpSession session=request.getSession();
		session.setAttribute("roleId", roleId);
		inittree(model,request);
		return "redirect:components/getusertorole.jsp";
	}
	
	@RequestMapping("/getusercomponenttogroup")
	public String getusercomponenttogroup(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		String groupId=request.getParameter("groupId");
		HttpSession session=request.getSession();
		session.setAttribute("groupId", groupId);
		inittree(model,request);
		List<UserPermission> uplist=permissiongroupService.queryByGroupId(groupId);
		List<UserDepartmentMapping> udlist=new ArrayList<UserDepartmentMapping>();
		for(UserPermission up:uplist){
			UserDepartmentMapping ud=userdepartmentService.queryByOtheridandType(up.getUserId(), "user");
			udlist.add(ud);
		}
		session.setAttribute("udlist", udlist);
		return "redirect:components/getusertogroup.jsp";
	}
	
	public void inittree(ModelMap model,HttpServletRequest request){
		String selectmodel=request.getParameter("selectmodel");
		if(StringUtils.isEmpty(selectmodel) || selectmodel.equals("checkbox")){
			selectmodel="mytree2.config.useCheckbox = true;";
		}else{
			selectmodel="mytree.config.useRadio = true;";
		}
		String orga=request.getParameter("orga");
		Iterator<Department> departit=departmentService.queryAll();
		TreeNode tree=new TreeNode();
		tree.setId(1);
		tree.setParentid(-1);
		tree.setName("根部门");
		List<TreeNode> list=new ArrayList<TreeNode>();
		list.add(tree);
		inittreedata(list, "root",1);
		HttpSession session=request.getSession();
		session.setAttribute("treedata", list);
		session.setAttribute("selectmodel", selectmodel);
	}
	
	private List<TreeNode> inittreedata(List<TreeNode> list,String departmentId,int id){
		List<User> ulist=userService.queryByDepartment(departmentId);
		for(User u:ulist){
			UserDepartmentMapping mapping=userdepartmentService.queryByOtheridandType(u.getUserId(),"user");
			if(mapping!=null){
				TreeNode tree=new TreeNode();
				tree.setId(mapping.getId());
				tree.setParentid(id);
				tree.setName(u.getUsername());
				list.add(tree);
			}
		}
		List<Department> dlist=departmentService.queryByParentId(departmentId);
		for(Department d:dlist){
			UserDepartmentMapping mapping=userdepartmentService.queryByOtheridandType(d.getDepartmentId(),"department");
			if(mapping!=null){
				TreeNode tree=new TreeNode();
				tree.setId(mapping.getId());
				tree.setParentid(id);
				tree.setName(d.getDepartmentname());
				list.add(tree);
				inittreedata(list, d.getDepartmentId(),mapping.getId());
			}
		}
		return list;
	}
}
