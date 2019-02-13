package org.beifeng.oa.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 流程部署的controller
 * */
@Controller
public class FlowDeploymentController extends BaseController{

	@Resource(name="repositoryService")
	private RepositoryService repositoryService;
	
	@RequestMapping("/toupload")
	public String touplod(){
		return "deployment/upload";
	}
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public String upload(@RequestParam("uploadfile")MultipartFile file,@RequestParam("uploaduser")String username,HttpServletRequest request,ModelMap model) throws Exception{
		System.out.println(username);
		if(!file.isEmpty()){
			String realpath=request.getSession().getServletContext().getRealPath("/uploadfile");
			byte [] data=file.getBytes();
			File out=new File(realpath,file.getOriginalFilename());
			//文件的生成
			FileUtils.writeByteArrayToFile(out, data);
			if(FilenameUtils.getExtension(file.getOriginalFilename()).equals("zip")||FilenameUtils.getExtension(file.getOriginalFilename()).equals("bar")){
				ZipInputStream zip=new ZipInputStream(file.getInputStream());
				//部署
				repositoryService.createDeployment().addZipInputStream(zip).deploy();
			}
		}
		return "redirect:processlist";
	}
	
	@RequestMapping("/processlist")
	public String processlist(ModelMap model,HttpServletRequest request){
		List<ProcessDefinition> processlist=repositoryService.createProcessDefinitionQuery().list();
		model.put("processlist", processlist);
		return "deployment/processlist";
	}
	
	@RequestMapping("/lookprocessdefine")
	public void loadResourceByDeployment(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String deploymentId=request.getParameter("deploymentId");
		String resourceName=request.getParameter("resourceName");
		InputStream is=repositoryService.getResourceAsStream(deploymentId, resourceName);
		byte [] data=IOUtils.toByteArray(is);
		ServletOutputStream os=(ServletOutputStream)response.getOutputStream();
		os.write(data, 0, data.length);
		os.flush();
		os.close();
	}
}
