package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.DbJiraData;
import com.model.DbJiraIssue;
import com.monthreport.ConnAll;
import com.monthreport.InsertAll;

public class CalculateTestTime extends HttpServlet implements Servlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String projectid = request.getParameter("department");
		String starttimeString = request.getParameter("startdate");
		String endtimeString = request.getParameter("enddate");
		String batch = starttimeString + "——" + endtimeString;
		InsertAll insertall = new InsertAll();
		ConnAll connall = new ConnAll();
		PrintWriter out = response.getWriter();
		CalculateMeasure calculatemeasure = new CalculateMeasure();
		String createddate = calculatemeasure.CreateDate();
		PrintWriter pw = response.getWriter();

		List<DbJiraIssue> jiraissuelist = connall.getmaintable(starttimeString,
				endtimeString, projectid, batch, createddate);
		if (jiraissuelist.size() == 0) {
			pw = response.getWriter();
			pw.print("[]");
			pw.close();
			// out.println("<script>alert('查不到数据！');history.back();</script>");
		} else {
			// jira项目总工时
			List<DbJiraData> jiradatalist = connall.getJiraData(
					starttimeString, endtimeString, projectid, batch,
					createddate);
			insertall.getJiraData(jiradatalist, projectid, batch, createddate);
			// “测试人天”按钮：
			String sumtimeworked = connall.getTester_worklog(batch,
					createddate, projectid);
			insertall.getTester_worklog(sumtimeworked, batch, createddate,
					projectid);
			// 获取 "测试人天"的字段成json格式
			String qaJson = this.getqareportJson(createddate);
			// System.out.println(resultJson);
			pw = response.getWriter();
			pw.print(qaJson);
			pw.close();
			out.println("<script>alert('测试工时统计完成！');history.back();</script>");
		}

	}

	public String getqareportJson(String createddate) {
		java.sql.Statement st = null;
		java.sql.Statement st1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		Connection conn = null;
		String json = new String();
		StringBuffer resultJson = new StringBuffer();
		try {
			conn = DBConn.getConn();
			st = conn.createStatement();
			st1 = conn.createStatement();
			// SQL语句
			String sql = " select projid,sumworked,batch,createddate "
					+ " from tester_worklog where createddate='" + createddate
					+ "' ";
			rs = st.executeQuery(sql);// 返回查询结果
			String SQL = "  select count(*) as sum  from tester_worklog where createddate='"
					+ createddate + "' ";
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
					String cn = new String();
					// 将createddate字段毫秒除去
					if (j == rs.getMetaData().getColumnCount() - 1) {
						String bn = rs.getString(j + 1);
						cn = bn.substring(0, bn.length() - 2);
					} else {
						cn = rs.getString(j + 1);
					}
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
			json = resultJson.toString();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 最终关闭连接
			DBConn.close(conn, st, rs);
		}
		return json;
	}
}
