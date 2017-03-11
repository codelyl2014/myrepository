package com.monthreport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.DbJiraIssue;

public class ConnMainTable {
	public List<DbJiraIssue> getconn(String startTime, String endTime,
			String projectid) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Success loading Mysql Driver!ConnMainTable!");
		} catch (Exception e) {
			System.out.print("Error loading Mysql Driver!ConnMainTable!");
			e.printStackTrace();
		}
		List<DbJiraIssue> jiraissuelist = new ArrayList<DbJiraIssue>();
		Connection connect = null;
		try {
			connect = DriverManager
					.getConnection(
							"jdbc:mysql://10.32.135.62:3306/jira?characterEncoding=utf-8&allowMultiQueries=true",
							"read_only", "read_only");
			System.out.println("Success connect Mysql server!ConnMainTable!");
			Statement stmt = connect.createStatement();
			String sql = " SELECT * FROM ( ( SELECT *, max( resolved.resolution_created ) AS finresolution_created, max(closed.closed_created) AS finclosed_created FROM ( ( "
					+ "  SELECT * FROM ( SELECT * FROM ( SELECT j.ID AS jiraissueID, j.issuenum, j.PROJECT AS projectid, pj.pname AS projname, pj.pkey AS projkey, "
					+ "  j.SUMMARY AS issue_summary, j.priority, pr.pname AS priority_name, j.resolution, j.issuestatus, i.pname AS status_name, j.created, c.lower_user_name, c.display_name "
					+ "  FROM jiraissue AS j, project AS pj, app_user AS a, cwd_user AS c, issuestatus AS i, priority AS pr WHERE j.issuetype = 1  "
					+ " AND DATE_FORMAT(j.CREATED,'%Y-%m-%d')>='"
					+ startTime
					+ "' AND DATE_FORMAT(j.CREATED,'%Y-%m-%d')<='"
					+ endTime
					+ "'"
					+ " AND j.PROJECT = '"
					+ projectid
					+ "' "
					+ " AND j.PROJECT = pj.ID AND j.CREATOR = a.user_key "
					+ " AND a.lower_user_name = c.lower_user_name AND j.PRIORITY = pr.ID  AND j.issuestatus = i.ID ) m "
					+ " LEFT JOIN ( SELECT ID, pname AS resolution_name FROM resolution ) n ON m.resolution = n.ID ) a "
					+ " LEFT JOIN ( SELECT cfv.ISSUE AS bug_issue, cfv.customfield AS bug_customfield, cfv.stringvalue AS bug_course_id, cfo.customvalue AS bug_course "
					+ " FROM customfieldvalue AS cfv, customfieldoption AS cfo WHERE cfv.stringvalue = cfo.id AND cfv.CUSTOMFIELD = 13116 ) b ON a.jiraissueID = b.bug_issue "
					+ " LEFT JOIN ( SELECT cfv.ISSUE AS phase_issue, cfv.customfield AS phase_customfield, cfv.stringvalue AS occur_phase_id, cfo.customvalue AS occur_phase "
					+ " FROM customfieldvalue AS cfv, customfieldoption AS cfo WHERE cfv.stringvalue = cfo.id AND cfv.CUSTOMFIELD = 10124 ) c ON a.jiraissueID = c.phase_issue "
					+ " LEFT JOIN ( SELECT cfv.ISSUE AS environment_issue, cfv.customfield AS environment_customfield, cfv.stringvalue AS occur_environment_id, cfo.customvalue AS occur_environment "
					+ " FROM customfieldvalue AS cfv, customfieldoption AS cfo WHERE cfv.stringvalue = cfo.id AND cfv.CUSTOMFIELD = 12879 ) d ON a.jiraissueID = d.environment_issue ) AS jiradata "
					+ " LEFT JOIN ( SELECT resolution_created, resolution_cgid, resolution_issueid, resolution_project, resolution_newstring "
					+ " FROM ( ( SELECT changegroup.ID AS resolution_cgid, changegroup.issueid AS resolution_issueid, changegroup.CREATED AS resolution_created "
					+ " FROM changegroup ) aa "
					+ " RIGHT JOIN ( SELECT jiraissue.ID, jiraissue.PROJECT AS resolution_project FROM jiraissue WHERE jiraissue.issuetype = 1 ) bb ON aa.resolution_issueid = bb.ID "
					+ " INNER JOIN ( SELECT changeitem.NEWSTRING AS resolution_NEWSTRING, changeitem.groupid FROM changeitem WHERE changeitem.NEWSTRING = 'Resolved' ) cc ON aa.resolution_cgid = cc.groupid "
					+ " ) ) AS resolved ON jiradata.jiraissueID = resolved.resolution_issueid "
					+ " LEFT JOIN ( SELECT closed_created, closed_cgid, closed_issueid, closed_project, closed_newstring FROM ( ( SELECT changegroup.ID AS closed_cgid, changegroup.issueid AS closed_issueid, "
					+ " changegroup.CREATED AS closed_created FROM changegroup ) xx "
					+ " RIGHT JOIN ( SELECT jiraissue.ID, jiraissue.PROJECT closed_project FROM jiraissue WHERE jiraissue.issuetype = 1 ) yy ON xx.closed_issueid = yy.ID "
					+ " INNER JOIN ( SELECT changeitem.NEWSTRING AS closed_NEWSTRING, changeitem.groupid FROM changeitem WHERE changeitem.NEWSTRING = 'Closed' ) zz ON xx.closed_cgid = zz.groupid "
					+ " ) ) AS closed ON jiradata.jiraissueID = closed.closed_issueid ) GROUP BY jiradata.jiraissueID ) bug1 "
					+ " LEFT JOIN ( SELECT changegroup.issueid, COUNT(*) AS count_reopen FROM changegroup, changeitem WHERE changegroup.ID = changeitem.groupid AND changeitem.OLDSTRING LIKE 'resolved' "
					+ " AND changeitem.NEWSTRING LIKE 'reopened' GROUP BY issueid ) bug2 ON bug1.jiraissueID = bug2.issueid) ";

			System.out.println("sql_____" + sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				DbJiraIssue jiraissue = new DbJiraIssue();
				jiraissue.setJiraissueID(rs.getString("jiraissueID"));
				jiraissue.setIssuenum(rs.getString("issuenum"));
				jiraissue.setProjectid(rs.getString("projectid"));
				jiraissue.setProjname(rs.getString("projname"));
				jiraissue.setProjkey(rs.getString("projkey"));
				jiraissue.setIssue_summary(rs.getString("issue_summary"));
				jiraissue.setPriority(rs.getString("priority"));
				jiraissue.setPriority_name(rs.getString("priority_name"));
				jiraissue.setResolution(rs.getString("resolution"));
				jiraissue.setResolution_name(rs.getString("resolution_name"));
				jiraissue.setIssuestatus(rs.getString("issuestatus"));
				jiraissue.setStatus_name(rs.getString("status_name"));
				jiraissue.setCREATED(rs.getString("created"));
				jiraissue.setLower_user_name(rs.getString("lower_user_name"));
				jiraissue.setDisplay_name(rs.getString("display_name"));
				jiraissue.setBug_customfield(rs.getString("bug_customfield"));
				jiraissue.setBug_course_id(rs.getString("bug_course_id"));
				jiraissue.setBug_course(rs.getString("bug_course"));
				jiraissue.setPhase_customfield(rs
						.getString("phase_customfield"));
				jiraissue.setOccur_phase_id(rs.getString("occur_phase_id"));
				jiraissue.setOccur_phase(rs.getString("occur_phase"));
				jiraissue.setEnvironment_customfield(rs
						.getString("environment_customfield"));
				jiraissue.setOccur_environment_id(rs
						.getString("occur_environment_id"));
				jiraissue.setOccur_environment(rs
						.getString("occur_environment"));
				jiraissue.setResolution_created(rs
						.getString("finresolution_created"));
				jiraissue.setClosed_created(rs.getString("finclosed_created"));
				jiraissue.setCount_reopen(rs.getString("count_reopen"));
				jiraissuelist.add(jiraissue);
			}
		} catch (Exception e) {
			System.out.print("get data error!ConnMainTable!");
			e.printStackTrace();
		} finally {
			connect.close();
		}
		return jiraissuelist;
	}
}