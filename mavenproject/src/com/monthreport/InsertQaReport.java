package com.monthreport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class InsertQaReport {
	public void insertQareport(List<String> qr, String batch, String createddate)
			throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Success loading Mysql Driver!InsertQaReport!");
		} catch (Exception e) {
			System.out.print("Error loading Mysql Driver!InsertQaReport!");
			e.printStackTrace();
		}
		Connection connect = null;
		try {
			connect = DriverManager
					.getConnection(
							"jdbc:mysql://10.47.12.174:3306/jiradata?characterEncoding=utf-8&allowMultiQueries=true",
							"root", "root");
			System.out.println("Success connect Mysql server!InsertQaReport!");

			Statement stmt = connect.createStatement();

			String sql = " ";
			String projectid = qr.get(0);
			String projname = qr.get(1);
			String totalbugs = qr.get(2);
			String valid = qr.get(3);
			String beforepre = qr.get(4);
			String reopen = qr.get(5);
			String productenv = qr.get(6);
			String p0p1 = qr.get(7);
			String p2p3 = qr.get(8);
			if ("NaN".equals(p0p1)) {
				p0p1 = "0";
			}
			if ("NaN".equals(p2p3)) {
				p2p3 = "0";
			}

			sql += " INSERT INTO qareport (projectid,projname,totalbugs,valid,beforepre,reopen,productenv,p0p1,p2p3,batch,createddate)  VALUES ('"
					+ projectid
					+ "','"
					+ projname
					+ "','"
					+ totalbugs
					+ "','"
					+ valid
					+ "','"
					+ beforepre
					+ "','"
					+ reopen
					+ "','"
					+ productenv
					+ "','"
					+ p0p1
					+ "','"
					+ p2p3
					+ "','"
					+ batch + "','" + createddate + "'); ";
			System.out.print(sql);
			stmt.execute(sql);
		} catch (Exception e) {
			System.out.print("get data error!InsertQaReport!");
			e.printStackTrace();
		} finally {
			System.out.println("已插入完成！");
			connect.close();
		}
	}
}
