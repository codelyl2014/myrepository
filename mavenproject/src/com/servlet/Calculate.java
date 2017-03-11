package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.workdaycal.*;

import java.util.List;
import java.util.Date;

public class Calculate extends HttpServlet implements Servlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// doPost(request, response);
	}

	@SuppressWarnings("static-access")
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		response.setContentType("text/html;charset=UTF-8");
		JiraTest jiratest = new JiraTest();
		String starttimeString = request.getParameter("startdate");
		String endtimeString = request.getParameter("enddate");
		String batch = starttimeString + "——" + endtimeString;

		UTCTransform transform = new UTCTransform();
		Date date = new Date();
		String utctimestring = String.format("%tF", date) + " "
				+ String.format("%tT", date);
		String createddate = transform.utc2Local(utctimestring,
				"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss");
		System.out.println(utctimestring);
		System.out.println(createddate);
		PrintWriter pw = response.getWriter();

		try {
			// 通过输入起止时间获取到期间具体的工作日的一个workdatelist集合
			List<Date> workdatelist = jiratest.getWorkdate(starttimeString,
					endtimeString);
			// 将工作日集合输入到workdate表中
			jiratest.getWorkDate(batch, createddate, workdatelist);
			// worklog中条数
			List<UserPO> userlist = jiratest.getWorklogList(starttimeString,
					endtimeString, batch, createddate);
			// 获取jira库中的数据,插入我们可操作的相应表中;项目工时投入
			List<ProjectWorklog> projectworklog = jiratest.getData(
					starttimeString, endtimeString, batch, createddate);
			String sqlPjwl = " select projectid,projname,sumworked,batch,createddate "
					+ " from project_worklog where createddate='"
					+ createddate
					+ "'";
			String SQLPjwl = " select count(*) as sum  from project_worklog where createddate='"
					+ createddate + "' ";
			String pjwlJson = this.getTableJson(sqlPjwl, SQLPjwl,
					"project_worklog");
			// ---每天8H异常
			List<UserEveryday> usereverydaylist = jiratest.getAbnormalStaff(
					batch, createddate, workdatelist);
			String sqlUed = " select lower_user_name,display_name,workday,timeworked,batch,createddate "
					+ " from user_everyday where flag=1 and createddate='"
					+ createddate + "'";
			String SQLUed = " select count(*) as sum  from user_everyday where flag=1 and createddate='"
					+ createddate + "' ";
			String uedJson = this.getTableJson(sqlUed, SQLUed, "user_everyday");
			// ---未满8H人员的项目工时占比
			List<EveryWorklog> everyworklog = jiratest.getAbStaffWorktime(
					batch, createddate);
			String sqlEwl = " select projname,projectid,sum_everyday_work,sumworked,batch,createddate "
					+ " from every_worklog where createddate='"
					+ createddate
					+ "'";
			String SQLEWl = " select count(*) as sum  from every_worklog where createddate='"
					+ createddate + "' ";
			String ewlJson = this.getTableJson(sqlEwl, SQLEWl, "every_worklog");
			// ---理论80%异常人员
			List<UserTotal> usertotallist = jiratest.getTheoreticAbStaff(batch,
					createddate, workdatelist);
			String sqlUt = " select lower_user_name,display_name,timeworked,batch,createddate "
					+ " from user_total where flag=1 and createddate='"
					+ createddate + "'";
			String SQLUt = " select count(*) as sum  from user_total where  flag=1 and createddate='"
					+ createddate + "' ";
			String utJson = this.getTableJson(sqlUt, SQLUt, "user_total");
			// ---未满80%人员的项目工时占比
			List<TotalWorklog> totalworkloglist = jiratest.getAbStaffProjWt(
					batch, createddate);
			String sqlTwl = " select projname,projectid,sum_total_work,sumworked,batch,createddate "
					+ " from total_worklog where createddate='"
					+ createddate
					+ "'";
			String SQLTwl = " select count(*) as sum  from total_worklog where  createddate='"
					+ createddate + "' ";
			String twlJson = this.getTableJson(sqlTwl, SQLTwl, "total_worklog");

			String finalJson = "{" + pjwlJson + "," + uedJson + "," + ewlJson
					+ "," + utJson + "," + twlJson + "}";
			pw = response.getWriter();
			pw.print(finalJson);
			pw.close();
			// System.out.println(finalJson);
			System.out.println("任务完成！");

		} catch (ParseException exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
		}

	}

	public String getTableJson(String sql, String SQL, String TableName) {
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
			rs = st.executeQuery(sql);// 返回查询结果
			rs1 = st1.executeQuery(SQL);
			rs1.next();
			int count = rs1.getInt("sum");
			int i = 0;
			// resultJson.append("{"+"\"project\""+":[");
			resultJson.append("\"" + TableName + "\"" + ":[");
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
			// System.out.println("输出json字符串"+json);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 最终关闭连接
			DBConn.close(conn, st, rs);
		}
		return json;
	}

}
