package com.monthreport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.DbDevProject;
import com.model.DbJiraData;

public class ConnDevProject {
	public List<DbDevProject> getconn(String startTime, String endTime,
			String projectid) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Success loading Mysql Driver!ConnDevProject!");
		} catch (Exception e) {
			System.out.print("Error loading Mysql Driver!ConnDevProject!");
			e.printStackTrace();
		}
		List<DbDevProject> devprojectlist = new ArrayList<DbDevProject>();
		Connection connect = null;
		try {
			connect = DriverManager
					.getConnection(
							"jdbc:mysql://10.32.135.62:3306/jira?characterEncoding=utf-8&allowMultiQueries=true",
							"read_only", "read_only");
			System.out.println("Success connect Mysql server!ConnDevProject!");
			Statement stmt = connect.createStatement();
			String sql = " SELECT j.project,p.pname,sum(w.timeworked) as sum FROM worklog w, jiraissue j, project p "
					+ " WHERE w.issueid = j.ID AND j.PROJECT = p.id AND DATE_FORMAT(w.STARTDATE, '%Y-%m-%d') >= '"
					+ startTime
					+ "' AND DATE_FORMAT(w.STARTDATE, '%Y-%m-%d') <='"
					+ endTime
					+ "' AND j.PROJECT = '"
					+projectid
					+"' AND w.UPDATEAUTHOR IN ( SELECT DISTINCT MEMBER_KEY FROM ao_aefed0_team_member_v2 WHERE TEAM_ID = 59) ";
			// System.out.println("sql_____"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				DbDevProject devproject = new DbDevProject();
				devproject.setProject(rs.getString("project"));
				devproject.setPname(rs.getString("pname"));
				devproject.setSum(rs.getString("sum"));
				devprojectlist.add(devproject);
			}
		} catch (Exception e) {
			System.out.print("get data error!ConnDevProject!");
			e.printStackTrace();
		} finally {
			connect.close();
		}
		return devprojectlist;
	}
}