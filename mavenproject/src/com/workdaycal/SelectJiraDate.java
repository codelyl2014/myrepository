package com.workdaycal;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SelectJiraDate {

	public static List<Date> getWorkdate(String startTime, String endTime)
			throws ParseException {
		List<Date> holidays = new ArrayList<Date>();
		List<Date> weekendatworkdays = new ArrayList<Date>();
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Wishdate wish = new Wishdate();
		holidays = wish.initHoliday();
		weekendatworkdays = wish.weekendAtWorkDays();
		List<Date> strList = new ArrayList<Date>();
		strList = wish.total(startTime, endTime, holidays, weekendatworkdays);
		return strList;
	}

	public static List<UserPO> getWorklogList(String startTime, String endTime,
			String batch, String createddate) {
		List<UserPO> userlist = new ArrayList<UserPO>();
		try {
			// 获取jira库中的相关数据
			GetJiraData getJira = new GetJiraData();
			userlist = getJira.getconn(startTime, endTime);
			// 数据插入到worklog表
			// InsertWorklog insertWl = new InsertWorklog();
			// insertWl.insertWorklog(userlist, batch, createddate);
		} catch (Exception exception) {
			System.out.println("输入的日期格式不正确");

		}
		return userlist;
	}

	public static List<ProjectWorklog> getData(String startTime,
			String endTime, String batch, String createddate) {
		List<ProjectWorklog> projectworkloglist = new ArrayList<ProjectWorklog>();
		try {
			// -----项目工时投入------
			// 获取需要插入到project_worklog表里的数据
			ConnectProjectWorklog conPjwl = new ConnectProjectWorklog();
			projectworkloglist = conPjwl.getconn(batch, createddate);
		} catch (Exception e) {
			System.out.println("输入的日期格式不正确");
		}
		return projectworkloglist;
	}

	// ---每天8H异常人员-----
	public static List<UserEveryday> getAbnormalStaff(String batch,
			String createddate, List<Date> workdatelist) {
		List<UserEveryday> usereverydaylist = new ArrayList<UserEveryday>();
		try {
			// 获取需要插入到user_everyday表里的数据
			ConnectUserEveryday conUed = new ConnectUserEveryday();
			usereverydaylist = conUed.getconn(batch, createddate);

		} catch (Exception e) {
			System.out.println("输入的日期格式不正确");
		}
		return usereverydaylist;
	}

	// ---未满8H人员的项目工时占比-----
	public static List<EveryWorklog> getAbStaffWorktime(String batch,
			String createddate) {
		List<EveryWorklog> everyworkloglist = new ArrayList<EveryWorklog>();
		try {
			// 获取需要插入到every_worklog表里的数据
			ConnectEveryWorklog conEwl = new ConnectEveryWorklog();
			everyworkloglist = conEwl.getconn(batch, createddate);

		} catch (Exception e) {
			System.out.println("输入的日期格式不正确");
		}
		return everyworkloglist;

	}

	// ---理论80%异常人员------
	public static List<UserTotal> getTheoreticAbStaff(String batch,
			String createddate, List<Date> workdatelist) {
		List<UserTotal> usertotallist = new ArrayList<UserTotal>();
		try {
			// 获取需要插入到user_total表里的数据
			ConnectUserTotal conUt = new ConnectUserTotal();
			usertotallist = conUt.getconn(batch, createddate);

		} catch (Exception e) {
			System.out.println("输入的日期格式不正确");
		}
		return usertotallist;
	}

	// ---未满80%人员的项目工时占比------
	public static List<TotalWorklog> getAbStaffProjWt(String batch,
			String createddate) {
		List<TotalWorklog> totalworkloglist = new ArrayList<TotalWorklog>();
		try {
			// 获取需要插入到total_worklog表里的数据
			ConnectTotalWorklog conTwl = new ConnectTotalWorklog();
			totalworkloglist = conTwl.getconn(batch, createddate);

		} catch (Exception e) {
			System.out.println("输入的日期格式不正确");
		}
		return totalworkloglist;
	}

}
