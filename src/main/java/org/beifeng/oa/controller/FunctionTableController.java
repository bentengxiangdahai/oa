package org.beifeng.oa.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.beifeng.oa.entity.Department;
import org.beifeng.oa.entity.FunctionTable;
import org.beifeng.oa.entity.PermissionFunction;
import org.beifeng.oa.service.FunctionTableService;
import org.beifeng.oa.vo.TreeNode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FunctionTableController extends BaseController{

	@Resource(name="functiontableService")
	private FunctionTableService functiontableService;
	
	@RequestMapping("/tofunc")
	public String tofunc(ModelMap model,HttpServletRequest request){
		List<FunctionTable> list=functiontableService.queryByParentId("0");
		model.put("list", list);
		return "permission/func";
	}
	
	@RequestMapping("/queryfunclist")
	public void queryfunclist(HttpServletRequest request,HttpServletResponse response){
		String parentId=request.getParameter("parentId");
		List<FunctionTable> list=functiontableService.queryByParentId(parentId);
		StringBuffer sb=new StringBuffer();
		for(FunctionTable d:list){
			sb.append("<li id='"+d.getFunctionId()+"'><span class='text'><a href='queryuser.do?departmentId="+d.getFunctionId()+"' target='frmrightChild'>"+d.getFunctionname()+"</a></span>");
			sb.append("<ul class='ajax'><li>{url:queryfunclist?parentId="+d.getFunctionId()+"}</li></ul>");
			sb.append("</li>");
		}
		writetoPage(response, sb.toString());
	}
	
	@RequestMapping("/toaddfunc")
	public String toaddfunc(ModelMap model,HttpServletRequest request){
		String parentId=request.getParameter("parentId");
		model.put("parentId", parentId);
		return "permission/addfunc";
	}
	
	@RequestMapping("/addfunc")
	public void addfunc(HttpServletRequest request,HttpServletResponse response){
		FunctionTable func=new FunctionTable();
		func.setFunctionname(request.getParameter("functionname"));
		func.setUrl(request.getParameter("url"));
		func.setParentId(Integer.parseInt(request.getParameter("parentId")));
		functiontableService.addFunction(func);
		writetoPage(response, "ok");
	}
	
	@RequestMapping("/deletefunction")
	public void deletefunction(HttpServletRequest request,HttpServletResponse response){
		String deletefunctionId=request.getParameter("deletefunctionId");
		List<FunctionTable> list=functiontableService.queryByParentId(deletefunctionId);
		if(list.size()>0){
			String s="<script type='text/javascript'>alert('该功能点有子节点，不能删除！')</script>";
			return;
		}else{
			FunctionTable func=new FunctionTable();
			func.setFunctionId(Integer.parseInt(deletefunctionId));
			functiontableService.deleteFunc(func);
		}
	}
	
	@RequestMapping("/toupdatefunc")
	public String toupdatefunc(ModelMap model,HttpServletRequest request){
		String functionId=request.getParameter("functionId");
		FunctionTable func=functiontableService.queryById(Integer.parseInt(functionId));
		model.put("func", func);
		return "permission/updatefunc";
	}
	
	@RequestMapping("/updatefunc")
	public String updatefunc(HttpServletRequest request){
		FunctionTable func=new FunctionTable();
		func.setFunctionname(request.getParameter("functionname"));
		func.setUrl(request.getParameter("url"));
		func.setParentId(Integer.parseInt(request.getParameter("parentId")));
		func.setFunctionId(Integer.parseInt(request.getParameter("functionId")));
		functiontableService.updateFunc(func);
		return "redirect:/tofunc";
	}
	
	@RequestMapping("/getfunctiontogroup")
	public String getfunctiontogroup(HttpServletRequest request){
		HttpSession session=request.getSession();
		String groupId=request.getParameter("groupId");
		session.setAttribute("groupId", groupId);
		initfunctree(session);
		return "redirect:components/functiontogroup.jsp";
	}
	
	private void initfunctree(HttpSession session){
		Iterator<FunctionTable> list=functiontableService.queryAll();
		TreeNode node=new TreeNode();
		node.setId(0);
		node.setParentid(-1);
		node.setName("功能点");
		List<TreeNode> treelist=new ArrayList<TreeNode>();
		treelist.add(node);
		initfunctiontreedata(treelist, 0);
		session.setAttribute("treedata", treelist);
	}
	
	private void initfunctiontreedata(List<TreeNode> treelist,int parentId){
		List<FunctionTable> list=functiontableService.queryByParentId(parentId+"");
		for(FunctionTable func:list){
			TreeNode node=new TreeNode();
			node.setId(func.getFunctionId());
			node.setParentid(parentId);
			node.setName(func.getFunctionname());
			node.setUrl(func.getUrl());
			treelist.add(node);
			initfunctiontreedata(treelist, func.getFunctionId());
		}
	}
	
	@RequestMapping("/addfunctiontogroup")
	public void addfunctiontogroup(HttpServletRequest request,HttpServletResponse response){
		String funcs=request.getParameter("funcs");
		funcs=funcs.substring(1,funcs.length());
		String groupId=request.getParameter("groupId");
		PermissionFunction pf=functiontableService.queryByGroupId(groupId);
		if(pf!=null){
			pf.setFunctionId(funcs);
			functiontableService.updatefunctiontogroup(pf);
		}else{
			pf=new PermissionFunction();
			pf.setGroupId(groupId);
			pf.setFunctionId(funcs);
			functiontableService.addfunctiontogroup(pf);
		}
		writetoPage(response, "ok");
	}
}
