package com.users.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class UsersFilter
 */
//@WebFilter("/UsersFilter")
public class UsersFilter implements Filter {

	private FilterConfig config;
	
	public void destroy() {
		config = null ;
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷admins是否登入過】
		Object usersVO = session.getAttribute("usersVO");
		if (usersVO == null) {
			session.setAttribute("location", req.getRequestURI());
//System.out.println("這裡是filter , req.getRequestURI() = " + req.getRequestURI()); 
			res.sendRedirect(req.getContextPath() + "/front-end/users/login.jsp");
			return;
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.config = config ;
	}

}
