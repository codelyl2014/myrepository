package com.workdaycal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JiraTest {

	public static List<Date> getWorkdate(String startTime, String endTime)
			throws ParseException {
		List<Date> holidays = new ArrayList<Date>();
		List<Date> weekendatworkdays = new ArrayList<Date>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Wishdate wish = new Wishdate();
		holidays = wish.initHoliday();
		weekendatworkdays = wish.weekendAtWorkDays();
		String dutydays = "";
		List<Date> strList = new ArrayList<Date>();
		try {
			strList = wish.total(startTime, endTime, holidays,
					weekendatworkdays);

			System.out.println("工作日共" + strList.size() + "天");
			for (Date list : strList) {
				dutydays = sdf.format(list);
				System.out.println(dutydays);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strList;
	}

	// workdate
	public static void getWorkDate(String batch, String createddate,
			List<Date> workdatelist) {

		try {
			InsertWorkdate insertWd = new InsertWorkdate();
			insertWd.insertWorkdate(workdatelist, batch, createddate);
		} catch (Exception e) {
			System.out.println("输入的日期格式不正确");
		}

	}

	public static List<UserPO> getWorklogList(String startTime, String endTime,
			String batch, String createddate) {
		List<UserPO> userlist = new ArrayList<UserPO>();
		try {
			// jira
			GetJiraData getJira = new GetJiraData();
			userlist = getJira.getconn(startTime, endTime);
			// worklog
			if (userlist.size() != 0) {
				InsertWorklog insertWl = new InsertWorklog();
				insertWl.insertWorklog(userlist, batch, createddate);
			}
		} catch (Exception exception) {
			System.out.println("输入的日期格式不正确");

		}
		return userlist;
	}

	public static List<ProjectWorklog> getData(String startTime,
			String endTime, String batch, String createddate) {
		List<ProjectWorklog> projectworkloglist = new ArrayList<ProjectWorklog>();
		try {
			// project_worklog
			ConnectProjectWorklog conPjwl = new ConnectProjectWorklog();
			projectworkloglist = conPjwl.getconn(batch, createddate);
			// project_worklog
			if (projectworkloglist.size() != 0) {
				InsertProjectWorklog insertPjwl = new InsertProjectWorklog();
				insertPjwl.insertProjectWorklog(projectworkloglist, batch,
						createddate);
			}
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
			// 閼惧嘲褰囬棁锟筋渽閹绘帒鍙嗛崚鐨峴er_everyday鐞涖劑鍣烽惃鍕殶閹癸拷
			ConnectUserEveryday conUed = new ConnectUserEveryday();
			usereverydaylist = conUed.getconn(batch, createddate);

			// ---姣忓ぉ8H寮傚父浜哄憳-----
			// 閺佺増宓侀幓鎺戝弳閸掔殟ser_everyday鐞涳拷
			if (usereverydaylist.size() != 0) {
				InsertUserEveryday insertUed = new InsertUserEveryday();
				insertUed.insertUserEveryday(usereverydaylist, workdatelist);
			}
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
			// 閼惧嘲褰囬棁锟筋渽閹绘帒鍙嗛崚鐧硋ery_worklog鐞涖劑鍣烽惃鍕殶閹癸拷
			ConnectEveryWorklog conEwl = new ConnectEveryWorklog();
			everyworkloglist = conEwl.getconn(batch, createddate);

			// ---閺堫亝寮�H娴滃搫鎲抽惃鍕�閻╊喖浼愰弮璺哄窗濮ｏ拷----
			// 閺佺増宓侀幓鎺戝弳閸掔櫝very_worklog鐞涳拷
			if (everyworkloglist.size() != 0) {
				InsertEveryWorklog insertEwl = new InsertEveryWorklog();
				insertEwl.insertEveryWorklog(everyworkloglist);
			}
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
			// 閼惧嘲褰囬棁锟筋渽閹绘帒鍙嗛崚鐨峴er_total鐞涖劑鍣烽惃鍕殶閹癸拷
			ConnectUserTotal conUt = new ConnectUserTotal();
			usertotallist = conUt.getconn(batch, createddate);

			// ---閻炲棜顔�0%瀵倸鐖舵禍鍝勬喅------
			// 閺佺増宓侀幓鎺戝弳閸掔殟ser_total鐞涳拷
			if (usertotallist.size() != 0) {
				InsertUserTotal insertUt = new InsertUserTotal();
				insertUt.insertUserTotal(usertotallist, workdatelist);
			}
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
			// 閼惧嘲褰囬棁锟筋渽閹绘帒鍙嗛崚鐨宱tal_worklog鐞涖劑鍣烽惃鍕殶閹癸拷
			ConnectTotalWorklog conTwl = new ConnectTotalWorklog();
			totalworkloglist = conTwl.getconn(batch, createddate);
			if (totalworkloglist.size() != 0) {
				// ---閺堫亝寮�0%娴滃搫鎲抽惃鍕�閻╊喖浼愰弮璺哄窗濮ｏ拷-----
				InsertTotalWorklog insertTwl = new InsertTotalWorklog();
				insertTwl.insertTotalWorklog(totalworkloglist);
			}
		} catch (Exception e) {
			System.out.println("输入的日期格式不正确");
		}
		return totalworkloglist;
	}

}
