package com.workdaycal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class InsertTotalWorklog {
	public void insertTotalWorklog(List<TotalWorklog> params)
			throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // 加载MYSQL JDBC驱动程序
			System.out
					.println("Success loading Mysql Driver!InsertTotalWorklog!");
		} catch (Exception e) {
			System.out.print("Error loading Mysql Driver!InsertTotalWorklog!");
			e.printStackTrace();
		}
		Connection connect = null;
		try {
			connect = DriverManager
					.getConnection(
							"jdbc:mysql://10.47.12.174:3306/jiradata?characterEncoding=utf-8&allowMultiQueries=true",
							"root", "root");
			System.out
					.println("Success connect Mysql server!InsertTotalWorklog!");
			List<TotalWorklog> totalworkloglist = params;
			Statement stmt = connect.createStatement();

			System.out.println(totalworkloglist.size());
			String totalSql = "";
			for (int i = 0; i < totalworkloglist.size(); i++) {
				String sql = " ";
				String projectid = ((TotalWorklog) totalworkloglist.get(i))
						.getProjectid();
				String projname = ((TotalWorklog) totalworkloglist.get(i))
						.getProjname();
				String batch = ((TotalWorklog) totalworkloglist.get(i))
						.getBatch();
				String createddate = ((TotalWorklog) totalworkloglist.get(i))
						.getCreateddate();
				String sum_total_work = ((TotalWorklog) totalworkloglist.get(i))
						.getSum_total_work();
				String sumworked = ((TotalWorklog) totalworkloglist.get(i))
						.getSumworked();
				sql += " INSERT INTO total_worklog (projname,projectid,sum_total_work,sumworked,batch,createddate)  ";
				sql += " VALUES ('" + projname + "','" + projectid + "','"
						+ sum_total_work + "','" + sumworked + "','" + batch
						+ "','" + createddate + "'); ";
				totalSql += sql;
			}
			stmt.execute(totalSql);
		} catch (Exception e) {
			System.out.print("get data error!InsertTotalWorklog!");
			e.printStackTrace();
		} finally {
			System.out.println("已插入完成！");
			connect.close();
		}
	}
}
