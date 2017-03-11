package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import java.util.Date;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.DbJiraIssue;
import com.monthreport.ConnAll;

import com.monthreport.InsertAll;

public class CalculateMeasure extends HttpServlet implements Servlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String CreateDate() {
		Date date = new Date();
		String createdate = String.format("%tF", date) + " "
				+ String.format("%tT", date);
		return createdate;
	}

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
		String createddate = CreateDate();
		PrintWriter out = response.getWriter();
		PrintWriter pw = response.getWriter();

		// 获取所有需求字段--插入到BUG表
		List<DbJiraIssue> jiraissuelist = connall.getmaintable(starttimeString,
				endtimeString, projectid, batch, createddate);
		if (jiraissuelist.size() == 0) {
			pw = response.getWriter();
			pw.print("[]");
			pw.close();
			// out.println("<script>alert('查不到数据！');history.back();</script>");
		} else {
			insertall.getmaintable(jiraissuelist, batch, createddate);
			// 获取这一批次的BUG总数
			double bugtotal = jiraissuelist.size();
			// “计算度量项”按钮
			List<String> qareport = connall.getBugData(projectid, createddate,
					batch, bugtotal);
			insertall.getBugData(qareport, projectid, createddate, batch);
			// 获取 "计算度量项"的字段成json格式
			String qaJson = this.getqareportJson(createddate);
			System.out.println(qaJson);

			pw = response.getWriter();
			pw.print(qaJson);
			pw.close();
			// out.println("<script>alert('计算度量项完成！');history.back();</script>");
		}
	}

	public String getqareportJson(String createddate) {
		java.sql.Statement st = null;
		java.sql.Statement st1 = null;
		java.sql.ResultSet rs = null;
		java.sql.ResultSet rs1 = null;
		java.sql.Connection conn = null;
		String json = new String();
		StringBuffer resultJson = new StringBuffer();
		try {
			conn = DBConn.getConn();
			st = conn.createStatement();
			st1 = conn.createStatement();
			// SQL语句
			String sql = " select projectid,projname,totalbugs,valid,beforepre,reopen,productenv,p0p1,p2p3,batch,createddate "
					+ " from qareport where createddate='" + createddate + "' ";
			rs = st.executeQuery(sql);// 返回查询结果
			String SQL = "  select count(*) as sum  from qareport where createddate='"
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
			// System.out.println(json);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// 最终关闭连接
			DBConn.close(conn, st, rs);
		}
		return json;

	}
}
