package org.beifeng.oa.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.IdentityService;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.beifeng.oa.activiti.entity.ActivitiUser;
import org.beifeng.oa.entity.DataDictionary;
import org.beifeng.oa.entity.FunctionTable;
import org.beifeng.oa.entity.PermissionFunction;
import org.beifeng.oa.entity.RoleUser;
import org.beifeng.oa.entity.User;
import org.beifeng.oa.entity.UserDepartmentMapping;
import org.beifeng.oa.entity.UserPermission;
import org.beifeng.oa.service.DataDictionaryService;
import org.beifeng.oa.service.FunctionTableService;
import org.beifeng.oa.service.PermissionGroupService;
import org.beifeng.oa.service.RoleUserService;
import org.beifeng.oa.service.UserDepartmentMappingService;
import org.beifeng.oa.service.UserService;
import org.beifeng.oa.utils.KeyProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController extends BaseController{

	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="datadictionaryService")
	private DataDictionaryService datadictionaryService;
	
	@Autowired
	private IdentityService identityService;
	
	@Resource(name="userdepartmentService")
	private UserDepartmentMappingService userdepartmentService;
	
	@Resource(name="roleuserService")
	private RoleUserService roleuserService;
	
	@Resource(name="permissiongroupService")
	private PermissionGroupService permissiongroupService;
	
	@Resource(name="functiontableService")
	private FunctionTableService functiontableService;
	
	@RequestMapping("/toadd")
	public String toadd(ModelMap model,HttpServletRequest request){
		String parentId=request.getParameter("parentId");
		model.put("departmentId", parentId);
		List<DataDictionary> list=datadictionaryService.querybyDataType("xueli");
		model.put("list", list);
		return "organiza/adduser";
	}
	
	@RequestMapping("/adduser")
	public String adduser(HttpServletRequest request){
		User user=new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword("8888");
		user.setEmail(request.getParameter("email"));
		user.setTel(request.getParameter("tel"));
		user.setBirthday(request.getParameter("birthday"));
		user.setDepartmentId(request.getParameter("departmentId"));
		user.setUsertype(Integer.parseInt(request.getParameter("usertype")));
		user.setXueli(request.getParameter("xueli"));
		user.setUserId(KeyProvider.getPrimaryKey());
		userService.addUser(user);
		UserDepartmentMapping mapping=new UserDepartmentMapping();
		mapping.setOtherid(user.getUserId());
		mapping.setType("user");
		userdepartmentService.save(mapping);
		UserEntity ue=new UserEntity(user.getUserId());
		ue.setEmail(user.getEmail());
		ue.setPassword(user.getPassword());
		ue.setFirstName(user.getUsername());
		ue.setLastName(user.getUsername());
		identityService.saveUser(ue);
		return "redirect:todepartment";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request){
		HttpSession session=request.getSession();
		if(session.getAttribute("USER")!=null){
			return "redirect:main";
		}else{
			return "login";
		}
	}
	
	@RequestMapping("/loginuser")
	public String loginuser(HttpServletRequest request){
		String result="";
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		User user=userService.login(email, password);
		if(user!=null){
			result="redirect:main";
			HttpSession session=request.getSession();
			session.setAttribute("USER", user);
		}else{
			result="login";
		}
		return result;
	}
	
	@RequestMapping("/main")
	public String main(HttpServletRequest request){
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("USER");
		List<UserPermission> uplist=permissiongroupService.queryByUserId(user.getUserId());
		List<String> groupidlist=new ArrayList<String>();
		for(UserPermission up:uplist){
			groupidlist.add(up.getGroupId());
		}
		List<PermissionFunction> pflist=functiontableService.queryByGroupIdIn(groupidlist);
		List<Integer> funcidlist=new ArrayList<Integer>();
		for(PermissionFunction pf:pflist){
			String [] funcids=pf.getFunctionId().split(",");
			for(int i=0;i<funcids.length;i++){
				funcidlist.add(Integer.parseInt(funcids[i]));
			}
		}
		List<FunctionTable> funclist=functiontableService.queryByfunctionIdIn(funcidlist);
		session.setAttribute("funclist", funclist);
		return "main";
	}
	
	@RequestMapping("/queryuser")
	public String queryuser(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		String departmentId=request.getParameter("departmentId");
		String nowpage=request.getParameter("nowpage");
		int currentPage=0;
		if(nowpage!=null && !nowpage.equals("")){
			currentPage=Integer.parseInt(nowpage);
		}
		Page<User> page=userService.queryPageforDepartment(departmentId, currentPage, 5);
		model.put("page", page);
		model.put("departmentId", departmentId);
		model.put("currentPage", currentPage);
		return "organiza/userlist";
	}
	
	@RequestMapping("/lookuser")
	public String lookuser(ModelMap model,HttpServletRequest request){
		String userId=request.getParameter("userId");
		User user=userService.queryUser(userId);
		model.put("user", user);
		return "organiza/userinfo";
	}
	
	@RequestMapping("/toupdateinfo")
	public String toupdateinfo(ModelMap model,HttpServletRequest request){
		List<DataDictionary> list=datadictionaryService.querybyDataType("xueli");
		model.put("list", list);
		return "organiza/updateuserinfo";
	}
	
	@RequestMapping("/updateuser")
	public void updateuser(HttpServletRequest request,HttpServletResponse response){
		String userId=request.getParameter("userId");
		String username=request.getParameter("username");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		User user=new User();
		user.setBirthday(request.getParameter("birthday"));
		user.setDepartmentId(request.getParameter("departmentId"));
		user.setEmail(email);
		user.setPassword(password);
		user.setTel(request.getParameter("tel"));
		user.setUserId(userId);
		user.setUsername(username);
		user.setUsertype(Integer.parseInt(request.getParameter("usertype")));
		user.setXueli(request.getParameter("xueli"));
		userService.updateUser(user);
		UserEntity ue=(UserEntity)identityService.createUserQuery().userId(userId).singleResult();
		ue.setFirstName(username);
		ue.setLastName(username);
		ue.setEmail(email);
		ue.setPassword(password);
		identityService.saveUser(ue);
		HttpSession session=request.getSession();
		session.setAttribute("USER", user);
		String s="<script type='text/javascript'>alert('用户信息修改成功！')</script>";
		writetoPage(response, s);
	}
	
	@RequestMapping("/queryuserbyrole")
	public String queryuserbyrole(ModelMap model,HttpServletRequest request){
		String roleId=request.getParameter("roleId");
		String nowpage=request.getParameter("nowpage");
		int currentPage=0;
		if(nowpage!=null && !nowpage.equals("")){
			currentPage=Integer.parseInt(nowpage);
		}
		Page<RoleUser> page=roleuserService.queryuserbyRole(roleId, currentPage, 5);
		List<RoleUser> rulist=page.getContent();
		String s="";
		for(RoleUser ru:rulist){
			s+=ru.getUserId()+",";
		}
		s=s.substring(0,s.length()-1);
		String [] ss=s.split(",");
		List<User> list=userService.findByuserIdIn(ss);
		model.put("page", page);
		model.put("list", list);
		model.put("roleId", roleId);
		model.put("currentPage", currentPage);
		return "organiza/userlistrole";
	}
}
