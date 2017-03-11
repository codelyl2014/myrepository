package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {
	public void destroy() {

	}
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String currentURI = request.getRequestURI();
//		System.out.println(currentURI);	
		String ctxPath = request.getContextPath();
//		System.out.println(ctxPath);	
		String targetURL = currentURI.substring(ctxPath.length());
//		System.out.println(targetURL);
		HttpSession session = request.getSession(false);
		if (!"/".equals(targetURL)||!"/login.jsp".equals(targetURL)) {
			System.out.println(targetURL);
			if (session == null || session.getAttribute("username") == null) {
				// *用户登录以后需手动添加session
				response.sendRedirect("login.jsp");
				// 如果session为空表示用户没有登录就重定向到login.jsp页面
				return;
			} else {
				filterChain.doFilter(request, response);
				return;
			}
		} else {
			filterChain.doFilter(request, response);
			return;
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}
}