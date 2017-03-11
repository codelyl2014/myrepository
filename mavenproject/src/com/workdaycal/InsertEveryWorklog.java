package com.workdaycal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class InsertEveryWorklog {
	public void insertEveryWorklog(List<EveryWorklog> params)
			throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out
					.println("Success loading Mysql Driver!InsertEveryWorklog!");
		} catch (Exception e) {
			System.out.print("Error loading Mysql Driver!InsertEveryWorklog!");
			e.printStackTrace();
		}
		Connection connect = null;
		try {
			connect = DriverManager
					.getConnection(
							"jdbc:mysql://10.47.12.174:3306/jiradata?characterEncoding=utf-8&allowMultiQueries=true",
							"root", "root");
			System.out
					.println("Success connect Mysql server!InsertEveryWorklog!");
			List<EveryWorklog> everyworkloglist = params;
			Statement stmt = connect.createStatement();

			System.out.println(everyworkloglist.size());
			String totalSql = "";
			for (int i = 0; i < everyworkloglist.size(); i++) {
				String sql = " ";
				String projectid = ((EveryWorklog) everyworkloglist.get(i))
						.getProjectid();
				String projname = ((EveryWorklog) everyworkloglist.get(i))
						.getProjname();
				String batch = ((EveryWorklog) everyworkloglist.get(i))
						.getBatch();
				String createddate = ((EveryWorklog) everyworkloglist.get(i))
						.getCreateddate();
				String sum_everyday_work = ((EveryWorklog) everyworkloglist
						.get(i)).getSum_everyday_work();
				String sumworked = ((EveryWorklog) everyworkloglist.get(i))
						.getSumworked();
				sql += " INSERT INTO every_worklog (projname,projectid,sum_everyday_work,sumworked,batch,createddate)  ";
				sql += " VALUES ('" + projname + "','" + projectid + "','"
						+ sum_everyday_work + "','" + sumworked + "','" + batch
						+ "','" + createddate + "'); ";
				totalSql += sql;
			}
			stmt.execute(totalSql);
		} catch (Exception e) {
			System.out.print("get data error!InsertEveryWorklog!");
			e.printStackTrace();
		} finally {
			System.out.println("已插入完成！");
			connect.close();
		}
	}
}
