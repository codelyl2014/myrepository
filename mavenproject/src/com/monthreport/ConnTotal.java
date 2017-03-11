package com.monthreport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnTotal {
	public String[] getconn(String params, String createddate, String projectid)
			throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// System.out.println("Success loading Mysql Driver!ConnTotal!");
		} catch (Exception e) {
			System.out.print("Error loading Mysql Driver!ConnTotal!");
			e.printStackTrace();
		}
		String[] result = new String[2];
		String BugData = null;
		String ProjName = null;
		String bugnumber = null;
		Connection connect = null;
		try {
			connect = DriverManager
					.getConnection(
							"jdbc:mysql://10.47.12.174:3306/jiradata?characterEncoding=utf-8&allowMultiQueries=true",
							"root", "root");
			// System.out.println("Success connect Mysql server!ConnTotal!");
			Statement stmt = connect.createStatement();
			String sql = " ";
			switch (params) {
			case "A":
				sql = "SELECT count(createddate)AS realbugs,projname  FROM bugs WHERE resolution<>'10100' AND resolution<>'3' "
						+ " and projectid='"
						+ projectid
						+ "' and createddate='" + createddate + "'";
				ResultSet rsa = stmt.executeQuery(sql);
				while (rsa.next()) {
					BugData = rsa.getString("realbugs");
					ProjName = rsa.getString("projname");
				}
				result[0] = BugData;
				result[1] = ProjName;
				break;
			case "B":
				sql = " SELECT count(occur_phase) as totalOccurPhase FROM bugs WHERE (occur_phase = 'DEV' OR occur_phase = 'SIT') "
						+ " AND resolution<>'10100' AND resolution<>'3'  AND createddate='"
						+ createddate + "'";
				ResultSet rsb = stmt.executeQuery(sql);
				while (rsb.next()) {
					BugData = rsb.getString("totalOccurPhase");
				}
				result[0] = BugData;
				break;
			case "C":
				sql = " SELECT count(count_reopen) as totalReopen FROM bugs WHERE  count_reopen='0' "
						+ " AND resolution<>'10100' AND resolution<>'3' AND createddate='"
						+ createddate + "'";
				ResultSet rsc = stmt.executeQuery(sql);
				while (rsc.next()) {
					BugData = rsc.getString("totalReopen");
				}
				result[0] = BugData;
				break;
			case "D":
				sql = " SELECT count(createddate) as productbug FROM bugs WHERE  occur_phase='PRODUCT' AND bug_course='运营阶段' "
						+ " AND resolution<>'10100' AND resolution<>'3' and createddate='"
						+ createddate + "'";
				ResultSet rsd = stmt.executeQuery(sql);
				while (rsd.next()) {
					BugData = rsd.getString("productbug");
				}
				result[0] = BugData;
				break;
			case "E":
				sql = "  SELECT sum((unix_timestamp(closed_created)-unix_timestamp(resolution_created)))AS usetime,count(createddate) as bugnumber "
						+ " FROM bugs WHERE ( priority_name = 'Blocker' OR priority_name = 'critical' ) AND status_name = 'Closed' "
						+ " AND resolution_created <> '0000-00-00 00:00:00' AND closed_created <> '0000-00-00 00:00:00'  "
						+ " AND resolution<>'10100' AND resolution<>'3' AND createddate='"
						+ createddate + "'" + " GROUP BY createddate ";
				ResultSet rse = stmt.executeQuery(sql);
				while (rse.next()) {
					BugData = rse.getString("usetime");
					bugnumber = rse.getString("bugnumber");
				}
				if (BugData == null) {
					BugData = "0";
				}
				if (bugnumber == null) {
					bugnumber = "0";
				}
				result[0] = BugData;
				result[1] = bugnumber;
				break;
			case "F":
				sql = " SELECT sum((unix_timestamp(closed_created)-unix_timestamp(resolution_created)))AS usetime,count(createddate) as bugnumber "
						+ " FROM bugs WHERE ( priority_name = 'Major' OR priority_name = 'Minor' ) AND status_name = 'Closed' "
						+ " AND resolution_created <> '0000-00-00 00:00:00' AND closed_created <> '0000-00-00 00:00:00'  "
						+ " AND resolution<>'10100' AND resolution<>'3' AND createddate='"
						+ createddate + "'" + " GROUP BY createddate ";
				ResultSet rsf = stmt.executeQuery(sql);
				while (rsf.next()) {
					BugData = rsf.getString("usetime");
					bugnumber = rsf.getString("bugnumber");
				}
				if (BugData == null) {
					BugData = "0";
				}
				if (bugnumber == null) {
					bugnumber = "0";
				}
				result[0] = BugData;
				result[1] = bugnumber;
				break;
			}
			// System.out.println("sql_____"+sql);

		} catch (Exception e) {
			System.out.print("get data error!ConnTotal!");
			e.printStackTrace();
		} finally {
			connect.close();
		}
		return result;
	}
}