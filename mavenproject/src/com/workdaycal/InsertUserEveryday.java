package com.workdaycal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class InsertUserEveryday {
	public void insertUserEveryday(List<UserEveryday> params,
			List<Date> workdate) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // 加载MYSQL JDBC驱动程序
			System.out
					.println("Success loading Mysql Driver!InsertUserEveryday!");
		} catch (Exception e) {
			System.out.print("Error loading Mysql Driver!InsertUserEveryday!");
			e.printStackTrace();
		}
		Connection connect = null;
		try {
			connect = DriverManager
					.getConnection(
							"jdbc:mysql://10.47.12.174:3306/jiradata?characterEncoding=utf-8&allowMultiQueries=true",
							"root", "root");
			System.out
					.println("Success connect Mysql server!InsertUserEveryday!");
			List<UserEveryday> usereverydaylist = params;
			List<Date> workdatelist = workdate;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Statement stmt = connect.createStatement();
			System.out.println(usereverydaylist.size());
			String totalSql = "";
			for (int i = 0; i < usereverydaylist.size(); i++) {
				for (Date list : workdatelist) {
					// dutydays =sdf.format(list);
					String workday = ((UserEveryday) usereverydaylist.get(i))
							.getWorkday();
					String dutydays = sdf.format(list);
					if (workday.equals(dutydays)) {
						String sql = " ";
						String flag = null;
						String lower_user_name = ((UserEveryday) usereverydaylist
								.get(i)).getLower_user_name();
						String display_name = ((UserEveryday) usereverydaylist
								.get(i)).getDisplay_name();
						String batch = ((UserEveryday) usereverydaylist.get(i))
								.getBatch();
						String createddate = ((UserEveryday) usereverydaylist
								.get(i)).getCreateddate();
						String timeworked = ((UserEveryday) usereverydaylist
								.get(i)).getTimeworked();
						if (timeworked == null) {
							flag = "1";
							timeworked = "0";
						} else {
							int tmpTimeworked = Integer.parseInt(timeworked);
							if (tmpTimeworked < 28800) {
								flag = "1";
							} else if (tmpTimeworked >= 28800) {
								flag = "0";
							}
						}
						sql += " INSERT INTO user_everyday (lower_user_name,display_name,workday,timeworked,flag,batch,createddate)  ";
						sql += " VALUES ('" + lower_user_name + "','"
								+ display_name + "','" + workday + "','"
								+ timeworked + "','" + flag + "','" + batch
								+ "','" + createddate + "'); ";
						totalSql += sql;
					}
				}
			}
			stmt.execute(totalSql);
		} catch (Exception e) {
			System.out.print("get data error!InsertUserEveryday!");
			e.printStackTrace();
		} finally {
			System.out.println("已插入完成！");
			connect.close();
		}
	}
}