package com.monthreport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.model.DbJiraIssue;

public class InsertBugs {
	public void insertBugs(List<DbJiraIssue> params, String batch,
			String createddate) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Success loading Mysql Driver!InsertBugs!");
		} catch (Exception e) {
			System.out.print("Error loading Mysql Driver!InsertBugs!");
			e.printStackTrace();
		}
		Connection connect = null;
		try {
			connect = DriverManager
					.getConnection(
							"jdbc:mysql://10.47.12.174:3306/jiradata?characterEncoding=utf-8&allowMultiQueries=true",
							"root", "root");
			System.out.println("Success connect Mysql server!InsertBugs!");
			List<DbJiraIssue> jiraissuelist = params;
			Statement stmt = connect.createStatement();

			System.out.println(jiraissuelist.size());
			String totalSql = "";
			for (int i = 0; i < jiraissuelist.size(); i++) {
				String sql = " ";
				String resolution = null;
				String resolution_name = null;
				String issueid = ((DbJiraIssue) jiraissuelist.get(i))
						.getJiraissueID();
				String issuenum = ((DbJiraIssue) jiraissuelist.get(i))
						.getIssuenum();
				String projectid = ((DbJiraIssue) jiraissuelist.get(i))
						.getProjectid();
				String projname = ((DbJiraIssue) jiraissuelist.get(i))
						.getProjname();
				String projkey = ((DbJiraIssue) jiraissuelist.get(i))
						.getProjkey();
				String issue_summary = ((DbJiraIssue) jiraissuelist.get(i))
						.getIssue_summary();
				String priority = ((DbJiraIssue) jiraissuelist.get(i))
						.getPriority();
				String priority_name = ((DbJiraIssue) jiraissuelist.get(i))
						.getPriority_name();
				if (((DbJiraIssue) jiraissuelist.get(i)).getResolution() == null) {
					resolution = "0";
				} else {
					resolution = ((DbJiraIssue) jiraissuelist.get(i))
							.getResolution();
				}
				if (((DbJiraIssue) jiraissuelist.get(i)).getResolution_name() == null) {
					resolution_name = "NAN";
				} else {
					resolution_name = ((DbJiraIssue) jiraissuelist.get(i))
							.getResolution_name();
				}
				String issuestatus = ((DbJiraIssue) jiraissuelist.get(i))
						.getIssuestatus();
				String status_name = ((DbJiraIssue) jiraissuelist.get(i))
						.getStatus_name();
				String created = ((DbJiraIssue) jiraissuelist.get(i))
						.getCREATED();
				String lower_user_name = ((DbJiraIssue) jiraissuelist.get(i))
						.getLower_user_name();
				String display_name = ((DbJiraIssue) jiraissuelist.get(i))
						.getDisplay_name();
				String bug_customfield = ((DbJiraIssue) jiraissuelist.get(i))
						.getBug_customfield();
				String bug_course_id = ((DbJiraIssue) jiraissuelist.get(i))
						.getBug_course_id();
				String bug_course = ((DbJiraIssue) jiraissuelist.get(i))
						.getBug_course();
				String phase_customfield = ((DbJiraIssue) jiraissuelist.get(i))
						.getPhase_customfield();
				String occur_phase_id = ((DbJiraIssue) jiraissuelist.get(i))
						.getOccur_phase_id();
				String occur_phase = ((DbJiraIssue) jiraissuelist.get(i))
						.getOccur_phase();
				String environment_customfield = ((DbJiraIssue) jiraissuelist
						.get(i)).getEnvironment_customfield();
				String occur_environment_id = ((DbJiraIssue) jiraissuelist
						.get(i)).getOccur_environment_id();
				String occur_environment = ((DbJiraIssue) jiraissuelist.get(i))
						.getOccur_environment();
				String resolution_created = ((DbJiraIssue) jiraissuelist.get(i))
						.getResolution_created();
				String closed_created = ((DbJiraIssue) jiraissuelist.get(i))
						.getClosed_created();
				String count_reopen = ((DbJiraIssue) jiraissuelist.get(i))
						.getCount_reopen();
				if (count_reopen == null) {
					count_reopen = "0";
				}
				if (resolution_created == null) {
					resolution_created = "0000-00-00 00:00:00";
				}
				if (closed_created == null) {
					closed_created = "0000-00-00 00:00:00";
				}
				String regexp = "\'";
				issue_summary = issue_summary.replaceAll(regexp, "");
				resolution_name = resolution_name.replaceAll(regexp, "");
				sql += " INSERT INTO bugs (issueid,issuenum,projectid,projname,projkey,issue_summary,priority,priority_name,  "
						+ " resolution,resolution_name,issuestatus,status_name,createdtime,lower_user_name,display_name, "
						+ " bug_course_id,bug_course,occur_phase_id,occur_phase,  "
						+ " occur_environment_id,occur_environment,resolution_created, "
						+ " closed_created,count_reopen,batch,createddate) "
						+ " VALUES ('"
						+ issueid
						+ "','"
						+ issuenum
						+ "','"
						+ projectid
						+ "','"
						+ projname
						+ "','"
						+ projkey
						+ "','"
						+ issue_summary
						+ "','"
						+ priority
						+ "','"
						+ priority_name
						+ "', "
						+ " '"
						+ resolution
						+ "','"
						+ resolution_name
						+ "','"
						+ issuestatus
						+ "','"
						+ status_name
						+ "','"
						+ created
						+ "','"
						+ lower_user_name
						+ "','"
						+ display_name
						+ "', "
						+ " '"
						+ bug_course_id
						+ "','"
						+ bug_course
						+ "','"
						+ occur_phase_id
						+ "','"
						+ occur_phase
						+ "','"
						+ occur_environment_id
						+ "','"
						+ occur_environment
						+ "','"
						+ resolution_created
						+ "', "
						+ " '"
						+ closed_created
						+ "','"
						+ count_reopen
						+ "','"
						+ batch
						+ "','" + createddate + "'); ";
				totalSql += sql;
			}
			System.out.println(totalSql);
			stmt.execute(totalSql);
		} catch (Exception e) {
			System.out.print("get data error!InsertBugs!");
			e.printStackTrace();
		} finally {
			System.out.println("已插入完成！");
			connect.close();
		}
	}
}