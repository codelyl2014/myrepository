package com.servlet;

import java.sql.*;

public class DBConn {
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("驱动加载成功！");
		} catch (Exception ex) {
			System.out.println("驱动加载失败！");
			ex.printStackTrace();
		}
	}

	public static Connection getConn() {
		try {
			String url = "jdbc:mysql://10.47.12.174:3306/jiradata?useUnicode=true&characterEncoding=utf-8";
			Connection conn = DriverManager.getConnection(url,"root","root");
			System.out.println("获取数据库连接成功！");
			return conn;
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
