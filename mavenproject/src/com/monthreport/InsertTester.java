package com.monthreport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class InsertTester {
	public void insertTester(String sumtimeworked, String batch,
			String createddate, String projectid) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // 加载MYSQL JDBC驱动程序
			System.out.println("Success loading Mysql Driver!InsertTester!");
		} catch (Exception e) {
			System.out.print("Error loading Mysql Driver!InsertTester!");
			e.printStackTrace();
		}
		Connection connect = null;
		try {
			connect = DriverManager
					.getConnection(
							"jdbc:mysql://10.47.12.174:3306/jiradata?characterEncoding=utf-8&allowMultiQueries=true",
							"root", "root");
			System.out.println("Success connect Mysql server!InsertTester!");
			Statement stmt = connect.createStatement();

			String sql = " INSERT INTO tester_worklog (projid,sumworked,batch,createddate)  VALUES ('"
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
			System.out.print("get data error!InsertTester!");
			e.printStackTrace();
		} finally {
			System.out.println("已插入完成！");
			connect.close();
		}
	}
}
