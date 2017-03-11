package com.monthreport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.model.DbJiraData;

public class Insertjiradata {
	public void insertJiradata(List<DbJiraData> params, String batch,
			String createddate, String projectid) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Success loading Mysql Driver!Insertjiradata!");
		} catch (Exception e) {
			System.out.print("Error loading Mysql Driver!Insertjiradata!");
			e.printStackTrace();
		}
		Connection connect = null;
		try {
			connect = DriverManager
					.getConnection(
							"jdbc:mysql://10.47.12.174:3306/jiradata?characterEncoding=utf-8&allowMultiQueries=true",
							"root", "root");
			System.out.println("Success connect Mysql server!Insertjiradata!");
			List<DbJiraData> jiradatalist = params;
			Statement stmt = connect.createStatement();

			System.out.println(jiradatalist.size());
			String totalSql = "";
			for (int i = 0; i < jiradatalist.size(); i++) {
				String sql = " ";
				String issueid = ((DbJiraData) jiradatalist.get(i)).getIssuid();
				String lower_user_name = ((DbJiraData) jiradatalist.get(i))
						.getLower_user_name();
				String workday = ((DbJiraData) jiradatalist.get(i))
						.getWorkday();
				String timeworked = ((DbJiraData) jiradatalist.get(i))
						.getTimeworked();

				sql += " INSERT INTO jiradata (issueid,lower_user_name,workday,timeworked,batch,createddate,projectid)  VALUES ('"
						+ issueid
						+ "','"
						+ lower_user_name
						+ "','"
						+ workday
						+ "','"
						+ timeworked
						+ "','"
						+ batch
						+ "','"
						+ createddate + "','" + projectid + "'); ";
				totalSql += sql;
			}
			// System.out.println(totalSql);
			stmt.execute(totalSql);
		} catch (Exception e) {
			System.out.print("get data error!Insertjiradata!");
			e.printStackTrace();
		} finally {
			System.out.println("已插入完成！");
			connect.close();
		}
	}
}
