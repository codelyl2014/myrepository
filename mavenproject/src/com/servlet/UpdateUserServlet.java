package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.UserDao;
import com.model.User;

public class UpdateUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		UserDao userDao = (UserDao) ctx.getBean("userDao");
		User user = new User();
		int id = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");
		String displayname = request.getParameter("displayname");
		int position = Integer.parseInt(request.getParameter("position"));
		String positionname = request.getParameter("positionname");
		int active = Integer.parseInt(request.getParameter("active"));
		user.setId(id);
		user.setUser_name(username);
		user.setDisplay_name(displayname);
		user.setPosition(position);
		user.setPosition_name(positionname);
		user.setActive(active);
		userDao.updateUser(user);
		response.sendRedirect("manageuser.jsp");
	}

}
