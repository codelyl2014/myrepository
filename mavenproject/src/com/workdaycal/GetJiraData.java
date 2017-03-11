package com.workdaycal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GetJiraData {
	public List<UserPO> getconn(String startTime, String endTime)
			throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // 加载MYSQL JDBC驱动程序
			// Class.forName("org.gjt.mm.mysql.Driver");
			System.out.println("Success loading Mysql Driver!JiraData!");
		} catch (Exception e) {
			System.out.print("Error loading Mysql Driver!JiraData!");
			e.printStackTrace();
		}
		List<UserPO> userlist = new ArrayList<UserPO>();
		Connection connect = null;
		try {
			connect = DriverManager
					.getConnection(
							"jdbc:mysql://10.32.135.62:3306/jira?characterEncoding=utf-8&allowMultiQueries=true",
							"read_only", "read_only");
			// 连接URL为 jdbc:mysql//服务器地址/数据库名 ，后面的2个参数分别是登陆用户名和密码
			System.out.println("Success connect Mysql server!JiraData!");
			Statement stmt = connect.createStatement();
			String sql = " SELECT w.ID,w.issueid,w.STARTDATE as workdate,w.UPDATEAUTHOR as updateauthor,c.lower_user_name,c.display_name,w.timeworked,p.ID as projectid,p.pname as projname,date_format(w.STARTDATE, '%Y-%m-%d') AS workday,pc.id as categoryid,pc.cname as categoryname  "
					+ " 	 FROM cwd_user c,app_user a,worklog w,jiraissue j,project p,nodeassociation n,projectcategory pc "
					+ " 		WHERE c.lower_user_name=a.lower_user_name and a.user_key=w.UPDATEAUTHOR and w.issueid=j.ID and j.PROJECT=p.ID and p.id=n.SOURCE_NODE_ID and n.SINK_NODE_ID=pc.id "
					+ " and w.STARTDATE>='"
					+ startTime
					+ "' and w.STARTDATE<='" + endTime + "'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				UserPO user = new UserPO();
				user.setID(rs.getString("ID"));
				user.setIssueid(rs.getString("issueid"));
				user.setWorkdate(rs.getString("workdate"));
				user.setUpdateauthor(rs.getString("updateauthor"));
				user.setLower_user_name(rs.getString("lower_user_name"));
				;
				user.setDisplay_name(rs.getString("display_name"));
				user.setTimeworked(rs.getString("timeworked"));
				user.setProjectid(rs.getString("projectid"));
				user.setProjname(rs.getString("projname"));
				user.setWorkday(rs.getString("workday"));
				user.setCategoryid(rs.getString("categoryid"));
				user.setCategory_name(rs.getString("categoryname"));
				userlist.add(user);
			}
			// System.out.println(userlist);
		} catch (Exception e) {
			System.out.print("get data error!JiraData!");
			e.printStackTrace();
		} finally {
			connect.close();
		}
		return userlist;
	}
}