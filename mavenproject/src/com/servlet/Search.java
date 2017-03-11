package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.workdaycal.EveryWorklog;
import com.workdaycal.JiraTest;
import com.workdaycal.ProjectWorklog;
import com.workdaycal.TotalWorklog;
import com.workdaycal.UserEveryday;
import com.workdaycal.UserPO;
import com.workdaycal.UserTotal;

public class Search extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// doPost(request, response);
	}

	@SuppressWarnings("static-access")
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		JiraTest jiratest = new JiraTest();
		String starttimeString = request.getParameter("startdate");
		String endtimeString = request.getParameter("enddate");
		String batch = starttimeString + "——" + endtimeString;
		Date date = new Date();
		String createddate = String.format("%tF", date) + " "
				+ String.format("%tT", date);

		try {
	
			List<Date> workdatelist = jiratest.getWorkdate(starttimeString,
					endtimeString);
	
			jiratest.getWorkDate(batch, createddate, workdatelist);
		
			List<UserPO> userlist = jiratest.getWorklogList(starttimeString,
					endtimeString, batch, createddate);
			
			List<ProjectWorklog> projectworklog = jiratest.getData(
					starttimeString, endtimeString, batch, createddate);

			List<UserEveryday> usereverydaylist = jiratest.getAbnormalStaff(
					batch, createddate, workdatelist);
			
			List<EveryWorklog> everyworklog = jiratest.getAbStaffWorktime(
					batch, createddate);
		
			List<UserTotal> usertotallist = jiratest.getTheoreticAbStaff(batch,
					createddate, workdatelist);
		
			List<TotalWorklog> totalworkloglist = jiratest.getAbStaffProjWt(
					batch, createddate);

			request.getSession().setAttribute("workdays", workdatelist.size());
			request.getSession().setAttribute("number1", userlist.size());
			request.getSession().setAttribute("number2", projectworklog.size());
			request.getSession().setAttribute("number3",
					usereverydaylist.size());
			request.getSession().setAttribute("number4", everyworklog.size());
			request.getSession().setAttribute("number5", usertotallist.size());
			request.getSession().setAttribute("number6",
					totalworkloglist.size());
			response.sendRedirect("showpage.jsp");
		} catch (ParseException exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
		}

	}
}
