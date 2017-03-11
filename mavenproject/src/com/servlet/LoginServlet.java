package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.jms.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet implements Servlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=gb2312");
		request.setCharacterEncoding("gb2312");
		String result = "";
		String username = request.getParameter("username");
		if (username == "" || username == null || username.length() > 20) {
			result = "请输入用户名（不超过20字符）！";
			request.getSession().setAttribute("error", result);
			response.sendRedirect("login.jsp");
		}
		String password = request.getParameter("password");
		if (password == "" || password == null || password.length() > 20) {
			result = "请输入密码（不超过20字符）！";
			request.getSession().setAttribute("error", result);
			response.sendRedirect("login.jsp");
		}

		Statement st = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DBConn.getConn();
			st = conn.createStatement();
			String sql = "select * from userInfo where username='" + username
					+ "' and password = '" + password + "'";
			rs = st.executeQuery(sql);
			if (rs.next())
			{
				request.getSession().setAttribute("username", username);
				response.sendRedirect("home.jsp");
			} else {
				request.getSession().setAttribute("error", "用户名或密码错误！");
				response.sendRedirect("fail.jsp");
			}
		} catch (SQLException e) {
			response.sendRedirect("fail.jsp");
			e.printStackTrace();
		} finally {
			DBConn.close(conn, st, rs);
		}

	}
}
