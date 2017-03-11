package com.monthreport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.model.DbDevProject;
import com.model.DbJiraData;

public class InsertDevProject {
	public void insertDevProject(List<DbDevProject> params, String batch,
			String createddate) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Success loading Mysql Driver!InsertDevProject!");
		} catch (Exception e) {
			System.out.print("Error loading Mysql Driver!InsertDevProject!");
			e.printStackTrace();
		}
		Connection connect = null;
		try {
			connect = DriverManager
					.getConnection(
							"jdbc:mysql://10.47.12.174:3306/jiradata?characterEncoding=utf-8&allowMultiQueries=true",
							"root", "root");
			System.out.println("Success connect Mysql server!InsertDevProject!");
			List<DbDevProject> devprojectlist = params;
			Statement stmt = connect.createStatement();

			System.out.println(devprojectlist.size());
			String totalSql = "";
			for (int i = 0; i < devprojectlist.size(); i++) {
				String sql = " ";
				String project = ((DbDevProject) devprojectlist.get(i)).getProject();
				String pname = ((DbDevProject) devprojectlist.get(i)).getPname();
				String sum = ((DbDevProject) devprojectlist.get(i)).getSum();


				sql += " INSERT INTO alldev_worklog (project,pname,sum,batch,createddate)  VALUES ('"
						+ project
						+ "','"
						+ pname
						+ "','"
						+ sum
						+ "','"
						+ batch
						+ "','"
						+ createddate + "'); ";
				totalSql += sql;
			}
			// System.out.println(totalSql);
			stmt.execute(totalSql);
		} catch (Exception e) {
			System.out.print("get data error!InsertDevProject!");
			e.printStackTrace();
		} finally {
			System.out.println("已插入完成！");
			connect.close();
		}
	}
}
