package org.beifeng.oa.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest srequest, ServletResponse sresponse,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest)srequest;
		HttpSession session=request.getSession();
		if(session.getAttribute("USER")==null){
			String uri=request.getRequestURI();
			if(uri.contains("login") || uri.contains("js")||uri.contains("css")||uri.contains("jpg")||uri.contains("gif")){
				chain.doFilter(srequest, sresponse);
			}else{
				HttpServletResponse response=(HttpServletResponse)sresponse;
				response.sendRedirect("login");
				return;
			}
		}else{
			chain.doFilter(srequest, sresponse);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	
}
