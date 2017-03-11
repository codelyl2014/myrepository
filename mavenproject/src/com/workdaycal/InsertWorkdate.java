package com.workdaycal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class InsertWorkdate {
	public void insertWorkdate(List<Date> workdate, String batch,
			String createddate) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Success loading Mysql Driver!InsertWorkdate!");
		} catch (Exception e) {
			System.out.print("Error loading Mysql Driver!InsertWorkdate!");
			e.printStackTrace();
		}
		Connection connect = null;
		try {
			connect = DriverManager
					.getConnection(
							"jdbc:mysql://10.47.12.174:3306/jiradata?characterEncoding=utf-8&allowMultiQueries=true",
							"root", "root");
			System.out.println("Success connect Mysql server!InsertWorkdate!");
			List<Date> workdatelist = workdate;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Statement stmt = connect.createStatement();
			;
			String totalSql = "";
			for (Date list : workdatelist) {
				String sql = " ";
				String workday = sdf.format(list);
				sql += " INSERT INTO workdate (workday,batch,createddate)  ";
				sql += " VALUES ('" + workday + "','" + batch + "','"
						+ createddate + "'); ";
				totalSql += sql;
			}
			stmt.execute(totalSql);
		} catch (Exception e) {
			System.out.print("get data error!InsertWorkdate!");
			e.printStackTrace();
		} finally {
			System.out.println("已插入完成！");
			connect.close();
		}
	}
}