package com.monthreport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SelectProject {
	public List<ProjectPo> getconn()throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Success loading Mysql Driver!");
		} catch (Exception e) {
			System.out.print("Error loading Mysql Driver!");
			e.printStackTrace();
		}
		List<ProjectPo> projectlist = new ArrayList<>();
		Connection connect = null;
		try {
			connect = DriverManager
					.getConnection(
							"jdbc:mysql://10.32.135.62:3306/jira?characterEncoding=utf-8&allowMultiQueries=true",
							"read_only", "read_only");
			System.out.println("Success connect Mysql server!");
			Statement stmt = connect.createStatement();
			String sql = " ";
			sql = "select ID,pname from project" ;
			ResultSet rsf = stmt.executeQuery(sql);
			while (rsf.next()) {
				ProjectPo projectPo=new ProjectPo();
				projectPo.setID(rsf.getString("ID"));
				projectPo.setName(rsf.getString("pname"));
				projectlist.add(projectPo);
			}
			// System.out.println("sql_____"+sql);

		} catch (Exception e) {
			System.out.print("get data error!");
			e.printStackTrace();
		} finally {
			connect.close();
		}
		return projectlist;
	}
}