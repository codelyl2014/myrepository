package com.monthreport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class InsertDeveloper {
	public void insertDeveloper(String sumtimeworked, String batch,
			String createddate, String projectid) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Success loading Mysql Driver!InsertDeveloper!");
		} catch (Exception e) {
			System.out.print("Error loading Mysql Driver!InsertDeveloper!");
			e.printStackTrace();
		}
		Connection connect = null;
		try {
			connect = DriverManager
					.getConnection(
							"jdbc:mysql://10.47.12.174:3306/jiradata?characterEncoding=utf-8&allowMultiQueries=true",
							"root", "root");
			System.out.println("Success connect Mysql server!InsertDeveloper!");
			Statement stmt = connect.createStatement();

			String sql = " INSERT INTO developer_worklog (projid,sumworked,batch,createddate)  VALUES ('"
					+ projectid
					+ "','"
					+ sumtimeworked
					+ "','"
					+ batch
					+ "','"
					+ createddate + "'); ";
			// System.out.println(totalSql);
			stmt.execute(sql);
		} catch (Exception e) {
			System.out.print("get data error!InsertDeveloper!");
			e.printStackTrace();
		} finally {
			System.out.println("已插入完成！");
			connect.close();
		}
	}
}
