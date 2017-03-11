package com.monthreport;

import java.util.ArrayList;
import java.util.List;

import com.model.DbDevProject;
import com.model.DbJiraData;
import com.model.DbJiraIssue;

public class ConnAll {

	public static List<DbJiraIssue> getmaintable(String startTime,
			String endTime, String projectid, String batch, String createddate) {
		List<DbJiraIssue> jiraissuelist = new ArrayList<DbJiraIssue>();
		try {
			ConnMainTable connmaintable = new ConnMainTable();
			jiraissuelist = connmaintable
					.getconn(startTime, endTime, projectid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jiraissuelist;
	}

	public static List<String> getBugData(String projectid, String createddate,
			String batch, double bugtotal) {
		ConnTotal conntotal = new ConnTotal();
		List<String> qareport = new ArrayList<String>();
		try {
			// (A)缺陷有效率
			double getrealbug = Integer.parseInt(conntotal.getconn("A",
					createddate, projectid)[0]);
			String projname = conntotal.getconn("A", createddate, projectid)[1];
			String effectiverate = String.valueOf(getrealbug / bugtotal);
			System.out.println("缺陷有效率:" + effectiverate);
			// (B)缺陷及时率
			double getdevsitdata = Integer.parseInt(conntotal.getconn("B",
					createddate, projectid)[0]);
			String bugtimelyrate = String.valueOf(getdevsitdata / getrealbug);
			System.out.println("缺陷及时率:" + bugtimelyrate);
			// (C)缺陷reopen率
			double getreopendata = Integer.parseInt(conntotal.getconn("C",
					createddate, projectid)[0]);
			String reopenrate = String.valueOf((getrealbug - getreopendata)
					/ getrealbug);
			System.out.println("缺陷reopen率:" + reopenrate);
			// (D)缺陷遗漏率
			double getproductbugdata = Integer.parseInt(conntotal.getconn("D",
					createddate, projectid)[0]);
			String lostrate = String.valueOf(getproductbugdata / getrealbug);
			System.out.println("缺陷遗漏率:" + lostrate);
			// (E)P0P1缺陷验证时长
			String[] result = conntotal.getconn("E", createddate, projectid);
			double getp0p1time = Integer.parseInt(result[0]);
			double bugnumber = Integer.parseInt(result[1]);
			String p0p1comfirmtime = String.valueOf(getp0p1time / bugnumber);
			System.out.println("P0P1缺陷验证时长:" + p0p1comfirmtime);
			// (F)P2P3缺陷验证时长
			String[] result1 = conntotal.getconn("F", createddate, projectid);
			double getp2p3time = Integer.parseInt(result1[0]);
			double bugnumber1 = Integer.parseInt(result1[1]);
			String p2p3comfirmtime = String.valueOf(getp2p3time
					/ bugnumber1);
			System.out.println("P2P3缺陷验证时长:" + p2p3comfirmtime);
			String totalbugs = Double.toString(bugtotal);
			qareport.add(projectid);
			qareport.add(projname);
			qareport.add(totalbugs);
			qareport.add(effectiverate);
			qareport.add(bugtimelyrate);
			qareport.add(reopenrate);
			qareport.add(lostrate);
			qareport.add(p0p1comfirmtime);
			qareport.add(p2p3comfirmtime);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return qareport;
	}

	public static List<DbJiraData> getJiraData(String startTime,
			String endTime, String projectid, String batch, String createddate) {
		List<DbJiraData> jiradatalist = new ArrayList<DbJiraData>();
		try {
			ConnJiraData connjiradata = new ConnJiraData();
			jiradatalist = connjiradata.getconn(startTime, endTime, projectid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jiradatalist;
	}

	public static String getTester_worklog(String batch, String createddate,
			String projectid) {
		String sumtimeworked = null;
		try {
			ConnTester conntseter = new ConnTester();
			sumtimeworked = conntseter.getconn(projectid, createddate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sumtimeworked;
	}

	public static String getDeveloper_worklog(String batch, String createddate,
			String projectid) {
		String sumtimeworked = null;
		try {
			ConnDeveloper conndeveloper = new ConnDeveloper();
			sumtimeworked = conndeveloper.getconn(projectid, createddate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sumtimeworked;
	}
	
	public static List<DbDevProject> getDevProject(String startTime,
			String endTime, String projectid) {
		List<DbDevProject> devprojectlist = new ArrayList<DbDevProject>();
		try {
			ConnDevProject connDevProject = new ConnDevProject();
			devprojectlist = connDevProject.getconn(startTime, endTime, projectid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return devprojectlist;
	}
}