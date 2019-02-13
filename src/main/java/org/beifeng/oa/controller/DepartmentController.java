package org.beifeng.oa.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.beifeng.oa.entity.Department;
import org.beifeng.oa.entity.UserDepartmentMapping;
import org.beifeng.oa.service.DepartmentService;
import org.beifeng.oa.service.UserDepartmentMappingService;
import org.beifeng.oa.utils.KeyProvider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DepartmentController extends BaseController{

	@Resource(name="departmentService")
	private DepartmentService departmentService;
	
	@Resource(name="userdepartmentService")
	private UserDepartmentMappingService userdepartmentService;
	
	@RequestMapping("/todepartment")
	public String todepartment(ModelMap model,HttpServletRequest request){
		List<Department> list=departmentService.queryByParentId("root");
		model.put("list", list);
		return "organiza/department";
	}
	
	@RequestMapping("/querydepartlist")
	public void querydepartlist(HttpServletRequest request,HttpServletResponse response){
		String parentId=request.getParameter("parentId");
		List<Department> list=departmentService.queryByParentId(parentId);
		StringBuffer sb=new StringBuffer();
		for(Department d:list){
			sb.append("<li id='"+d.getDepartmentId()+"'><span class='text'><a href='queryuser.do?departmentId="+d.getDepartmentId()+"' target='frmrightChild'>"+d.getDepartmentname()+"</a></span>");
			sb.append("<ul class='ajax'><li>{url:querydepartlist?parentId="+d.getDepartmentId()+"}</li></ul>");
			sb.append("</li>");
		}
		writetoPage(response, sb.toString());
	}
	
	@RequestMapping("/toadddepartment")
	public String adddepartment(ModelMap model,HttpServletRequest request){
		String parentId=request.getParameter("parentId");
		model.put("parentId", parentId);
		return "organiza/adddepartment";
	}
	
	@RequestMapping("/adddepartment")
	public String adddepartment(HttpServletRequest request){
		Department depart=new Department();
		String departmentname=request.getParameter("departmentname");
		depart.setDepartmentname(departmentname);
		depart.setDepartmenttel(request.getParameter("departmenttel"));
		depart.setParentId(request.getParameter("parentId"));
		depart.setDepartmentId(KeyProvider.getPrimaryKey());
		departmentService.addDepartment(depart);
		UserDepartmentMapping mapping=new UserDepartmentMapping();
		mapping.setOtherid(depart.getDepartmentId());
		mapping.setType("department");
		userdepartmentService.save(mapping);
		return "redirect:todepartment";
	}
	
	@RequestMapping("/deleteDepartment")
	public String deleteDepartment(HttpServletRequest request){
		Department depart=new Department();
		depart.setDepartmentId(request.getParameter("deletedepartmentId"));
		departmentService.deleteDepartment(depart);
		return "redirect:todepartment";
	}
}
