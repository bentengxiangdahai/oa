package org.beifeng.oa.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.beifeng.oa.entity.User;

public class BaseController {

	public void writetoPage(HttpServletResponse response,String s){
		try{
			response.setCharacterEncoding("UTF-8");
			PrintWriter out=response.getWriter();
			out.print(s);
			out.flush();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public User getUser(HttpServletRequest request){
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("USER");
		return user;
	}
}
