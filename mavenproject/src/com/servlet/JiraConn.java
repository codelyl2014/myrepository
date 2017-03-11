package com.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JiraConn {
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("驱动加载成功！");
		} catch (Exception ex) {
			System.out.println("驱动加载失败！");
			ex.printStackTrace();
		}
	}

	public static Connection getConn() {// ��ȡ���¼
		ResultSet rs = null;
		try {
			String url = "jdbc:mysql://10.32.135.62:3306/jira?characterEncoding=utf-8&allowMultiQueries=true";
			Connection connect = DriverManager.getConnection(url, "read_only",
					"read_only");
			System.out.println("获取数据库连接成功！");
			return connect;
		} catch (Exception ex) {
			System.out.println("获取数据库连接失败！");
			ex.printStackTrace();
			return null;
		}
	}

	public static void close(Connection conn, Statement st, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (Exception ex) {
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception ex) {
			}
		}
	}

}
