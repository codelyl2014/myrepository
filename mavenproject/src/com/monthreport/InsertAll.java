package com.monthreport;

import java.util.ArrayList;
import java.util.List;

import com.model.DbDevProject;
import com.model.DbJiraData;
import com.model.DbJiraIssue;

public class InsertAll {

	// 获取所有需求字段--插入到BUG表
	public void getmaintable(List<DbJiraIssue> jiraissuelist, String batch,
			String createddate) {
		try {
			// 数据插入到bug表
			InsertBugs insertbs = new InsertBugs();
			insertbs.insertBugs(jiraissuelist, batch, createddate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// "计算度量项"按钮
	public void getBugData(List<String> qareport, String projectid,
			String createddate, String batch) {
		try {
			// 数据插入qareport表
			InsertQaReport insertQr = new InsertQaReport();
			insertQr.insertQareport(qareport, batch, createddate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// jira项目总工时
	public void getJiraData(List<DbJiraData> jiradatalist, String projectid,
			String batch, String createddate) {
		try {
			// 数据插入到jiradata表
			Insertjiradata insertjd = new Insertjiradata();
			insertjd.insertJiradata(jiradatalist, batch, createddate, projectid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// "测试人天"按钮
	public void getTester_worklog(String sumtimeworked, String batch,
			String createddate, String projectid) {
		try {
			// 数据插入到tester_worklog表
			InsertTester inserttw = new InsertTester();
			inserttw.insertTester(sumtimeworked, batch, createddate, projectid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// "开发人天"按钮：
	public void getDeveloper_worklog(String sumtimeworked, String batch,
			String createddate, String projectid) {
		try {
			// 数据插入到developer_worklog表
			InsertDeveloper insertdw = new InsertDeveloper();
			insertdw.insertDeveloper(sumtimeworked, batch, createddate,
					projectid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//开发人员总工时：
	public void getAllDevWorklog(List<DbDevProject> devprojectlist, String batch,
			String createddate) {
		try {
			// 数据插入到alldev_worklog表
			InsertDevProject insertdp = new InsertDevProject();
			insertdp.insertDevProject(devprojectlist, batch, createddate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
