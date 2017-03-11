package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JiraServlet extends HttpServlet {

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
		Statement st = null;
		Statement st1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		Connection conn = null;
		StringBuffer resultJson = new StringBuffer();
//		System.out.print("xxxixixix");
		try {
			conn = JiraConn.getConn();
			st = conn.createStatement();
			st1 = conn.createStatement();
			String sql = "  select ID,pname  from project order by ID desc ";
			rs = st.executeQuery(sql);
			String SQL = "  select count(*) as sum  from project  ";
			rs1 = st1.executeQuery(SQL);
			rs1.next();
			int count = rs1.getInt("sum");
			int i = 0;
			// resultJson.append("{"+"\"project\""+":[");
			resultJson.append("[");
			while (rs.next()) {
				resultJson.append("{");
				for (int j = 0; j < rs.getMetaData().getColumnCount(); j++) {
					resultJson.append("\"");
					String cn = rs.getString(j + 1);
					resultJson.append(rs.getMetaData().getColumnName(j + 1)
							+ "\":" + "\"" + cn + "\"");
					if (j < rs.getMetaData().getColumnCount() - 1)
						resultJson.append(",");
				}
				if (i == (count - 1))
					resultJson.append("}");
				else
					resultJson.append("},");
				i++;
			}
			// resultJson.append("]}");
			resultJson.append("]");
			String json = resultJson.toString();
			System.out.println(resultJson);
			PrintWriter pw = response.getWriter();
			pw.print(json);
			pw.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JiraConn.close(conn, st, rs);
		}
	}

}
