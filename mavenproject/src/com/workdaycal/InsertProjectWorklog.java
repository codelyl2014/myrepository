package com.workdaycal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class InsertProjectWorklog {
	public void insertProjectWorklog(List<ProjectWorklog> params, String batch,
			String createddate) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // 加载MYSQL JDBC驱动程序
			System.out
					.println("Success loading Mysql Driver!InsertProjectWorklog!");
		} catch (Exception e) {
			System.out
					.print("Error loading Mysql Driver!InsertProjectWorklog!");
			e.printStackTrace();
		}
		Connection connect = null;
		try {
			connect = DriverManager
					.getConnection(
							"jdbc:mysql://10.47.12.174:3306/jiradata?characterEncoding=utf-8&allowMultiQueries=true",
							"root", "root");
			System.out
					.println("Success connect Mysql server!InsertProjectWorklog!");
			List<ProjectWorklog> projectworkloglist = params;
			Statement stmt = connect.createStatement();

			System.out.println(projectworkloglist.size());
			String totalSql = "";
			for (int i = 0; i < projectworkloglist.size(); i++) {
				String sql = " ";
				String projectid = ((ProjectWorklog) projectworkloglist.get(i))
						.getProjectid();
				String projname = ((ProjectWorklog) projectworkloglist.get(i))
						.getProjname();
				String sumworked = ((ProjectWorklog) projectworkloglist.get(i))
						.getSumworked();
				sql += " INSERT INTO project_worklog (projectid,projname,sumworked,batch,createddate)  ";
				sql += " VALUES ('" + projectid + "','" + projname + "','"
						+ sumworked + "','" + batch + "','" + createddate
						+ "'); ";
				totalSql += sql;
			}
			stmt.execute(totalSql);
		} catch (Exception e) {
			System.out.print("get data error!InsertProjectWorklog!");
			e.printStackTrace();
		} finally {
			System.out.println("已插入完成！");
			connect.close();
		}
	}
}
