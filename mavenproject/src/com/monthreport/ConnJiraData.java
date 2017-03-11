package com.monthreport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.DbJiraData;

public class ConnJiraData {
	public List<DbJiraData> getconn(String startTime, String endTime,
			String projectid) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Success loading Mysql Driver!ConnJiraData!");
		} catch (Exception e) {
			System.out.print("Error loading Mysql Driver!ConnJiraData!");
			e.printStackTrace();
		}
		List<DbJiraData> jiradatalist = new ArrayList<DbJiraData>();
		Connection connect = null;
		try {
			connect = DriverManager
					.getConnection(
							"jdbc:mysql://10.32.135.62:3306/jira?characterEncoding=utf-8&allowMultiQueries=true",
							"read_only", "read_only");
			System.out.println("Success connect Mysql server!ConnJiraData!");
			Statement stmt = connect.createStatement();
			String sql = " SELECT w.issueid,a.lower_user_name,w.timeworked,date_format(w.STARTDATE, '%Y-%m-%d') AS workday FROM worklog as w,app_user as a WHERE issueid IN ( SELECT id FROM jiraissue WHERE project ='"
					+ projectid
					+ "' ) AND  w.updateauthor=a.user_key "
					+ " AND DATE_FORMAT(STARTDATE,'%Y-%m-%d')>='"
					+ startTime
					+ "' AND DATE_FORMAT(STARTDATE,'%Y-%m-%d')<='"
					+ endTime
					+ "'";
			// System.out.println("sql_____"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				DbJiraData jiradata = new DbJiraData();
				jiradata.setIssuid(rs.getString("issueid"));
				jiradata.setLower_user_name(rs.getString("lower_user_name"));
				jiradata.setWorkday(rs.getString("workday"));
				jiradata.setTimeworked(rs.getString("timeworked"));
				jiradatalist.add(jiradata);
			}
		} catch (Exception e) {
			System.out.print("get data error!ConnJiraData!");
			e.printStackTrace();
		} finally {
			connect.close();
		}
		return jiradatalist;
	}
}