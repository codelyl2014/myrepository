package com.workdaycal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.workdaycal.UserPO;

public class InsertWorklog {
	public void insertWorklog(List<UserPO> params, String batch,
			String createddate) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			System.out.println("Success loading Mysql Driver!InsertWorkLog!");
		} catch (Exception e) {
			System.out.print("Error loading Mysql Driver!InsertWorkLog!");
			e.printStackTrace();
		}
		Connection connect = null;
		try {
			connect = DriverManager
					.getConnection(
							"jdbc:mysql://10.47.12.174:3306/jiradata?characterEncoding=utf-8&allowMultiQueries=true",
							"root", "root");
			System.out.println("Success connect Mysql server!InsertWorkLog!");
			List<UserPO> userlist = params;
			Statement stmt = connect.createStatement();
			System.out.println(userlist.size());
			// 批量处理，每批次5000条
			int len = userlist.size();
			int j = 0;
			while (len >= 5000) {
				len = (len - 5000);
				String totalSql = "";
				int tmp = (5000 * j);
				for (int i = tmp; i < (tmp + 5000); i++) {
					String sql = " ";
					String worklogid = ((UserPO) userlist.get(i)).getID();
					String issueid = ((UserPO) userlist.get(i)).getIssueid();
					String workdate = ((UserPO) userlist.get(i)).getWorkdate();
					String updateauthor = ((UserPO) userlist.get(i))
							.getUpdateauthor();
					String lower_user_name = ((UserPO) userlist.get(i))
							.getLower_user_name();
					String display_name = ((UserPO) userlist.get(i))
							.getDisplay_name();
					String timeworked = ((UserPO) userlist.get(i))
							.getTimeworked();
					String projectid = ((UserPO) userlist.get(i))
							.getProjectid();
					String projname = ((UserPO) userlist.get(i)).getProjname();
					String workday = ((UserPO) userlist.get(i)).getWorkday();
					String categoryid = ((UserPO) userlist.get(i))
							.getCategoryid();
					String categoryname = ((UserPO) userlist.get(i))
							.getCategory_name();
					sql += "  INSERT INTO worklog (worklogid,issueid,workdate,updateauthor,lower_user_name,display_name,timeworked,projectid,projname,batch,createddate,workday,categoryid,category_name)  ";
					sql += "  VALUES ('" + worklogid + "','" + issueid + "','"
							+ workdate + "','" + updateauthor + "','"
							+ lower_user_name + "','" + display_name + "','";
					sql += timeworked + "','" + projectid + "','" + projname
							+ "','" + batch + "','" + createddate + "','"
							+ workday + "','" + categoryid + "','"
							+ categoryname + "');  " + "\n";
					totalSql += sql;
				}
				stmt.execute(totalSql);
				j++;
				// System.out.println("done" + j);
			}
			if (len < 5000) {
				String totalSql = "";
				int tmp = (5000 * j);
				for (int i = tmp; i < (tmp + len); i++) {
					String sql = " ";
					String worklogid = ((UserPO) userlist.get(i)).getID();
					String issueid = ((UserPO) userlist.get(i)).getIssueid();
					String workdate = ((UserPO) userlist.get(i)).getWorkdate();
					String updateauthor = ((UserPO) userlist.get(i))
							.getUpdateauthor();
					String lower_user_name = ((UserPO) userlist.get(i))
							.getLower_user_name();
					String display_name = ((UserPO) userlist.get(i))
							.getDisplay_name();
					String timeworked = ((UserPO) userlist.get(i))
							.getTimeworked();
					String projectid = ((UserPO) userlist.get(i))
							.getProjectid();
					String projname = ((UserPO) userlist.get(i)).getProjname();
					String workday = ((UserPO) userlist.get(i)).getWorkday();
					String categoryid = ((UserPO) userlist.get(i))
							.getCategoryid();
					String categoryname = ((UserPO) userlist.get(i))
							.getCategory_name();
					sql += "  INSERT INTO worklog (worklogid,issueid,workdate,updateauthor,lower_user_name,display_name,timeworked,projectid,projname,batch,createddate,workday,categoryid,category_name)  ";
					sql += "  VALUES ('" + worklogid + "','" + issueid + "','"
							+ workdate + "','" + updateauthor + "','"
							+ lower_user_name + "','" + display_name + "','";
					sql += timeworked + "','" + projectid + "','" + projname
							+ "','" + batch + "','" + createddate + "','"
							+ workday + "','" + categoryid + "','"
							+ categoryname + "');  " + "\n";
					totalSql += sql;
				}
				stmt.execute(totalSql);
			}

		} catch (Exception e) {
			System.out.print("get data error!InsertWorkLog!");
			e.printStackTrace();
		} finally {
			System.out.println("已插入完成！");
			connect.close();
		}
	}
}