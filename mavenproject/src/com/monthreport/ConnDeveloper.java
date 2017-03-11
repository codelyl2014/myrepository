package com.monthreport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnDeveloper {
	public String getconn(String projectid, String createddate)
			throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Success loading Mysql Driver!ConnDeveloper!");
		} catch (Exception e) {
			System.out.print("Error loading Mysql Driver!ConnDeveloper!");
			e.printStackTrace();
		}
		String sumtimeworked = null;
		Connection connect = null;
		try {
			connect = DriverManager
					.getConnection(
							"jdbc:mysql://10.47.12.174:3306/jiradata?characterEncoding=utf-8&allowMultiQueries=true",
							"root", "root");
			System.out.println("Success connect Mysql server!ConnDeveloper!");
			Statement stmt = connect.createStatement();
			String sql = " ";
			sql = " SELECT sum(j.timeworked) as sumtimeworked FROM jiradata as j,users as u WHERE u.position='1' AND u.user_name=j.lower_user_name "
					+ " AND j.projectid='"
					+ projectid
					+ "'"
					+ " AND j.createddate='"
					+ createddate
					+ "'"
					+ " GROUP BY j.createddate ";
			ResultSet rsf = stmt.executeQuery(sql);
			while (rsf.next()) {
				sumtimeworked = rsf.getString("sumtimeworked");
			}

		} catch (Exception e) {
			System.out.print("get data error!ConnDeveloper!");
			e.printStackTrace();
		} finally {
			connect.close();
		}
		return sumtimeworked;
	}
}