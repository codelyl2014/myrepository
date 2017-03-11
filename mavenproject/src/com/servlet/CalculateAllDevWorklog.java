package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.model.DbDevProject;

import com.monthreport.ConnAll;
import com.monthreport.InsertAll;

public class CalculateAllDevWorklog extends HttpServlet implements Servlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String projectid = request.getParameter("department");
		String starttimeString = request.getParameter("startdate");
		String endtimeString = request.getParameter("enddate");
		String batch = starttimeString + "——" + endtimeString;
		InsertAll insertall = new InsertAll();
		ConnAll connall = new ConnAll();
		CalculateMeasure calculatemeasure = new CalculateMeasure();
		String createddate = calculatemeasure.CreateDate();
		// PrintWriter out = response.getWriter();
		PrintWriter pw = response.getWriter();

		List<DbDevProject> devprojectlist = connall.getDevProject(
				starttimeString, endtimeString, projectid);
		if (devprojectlist.size() == 0) {
			pw = response.getWriter();
			pw.print("[]");
			pw.close();
			// out.println("<script>alert('查不到数据！');history.back();</script>");
		} else {
			insertall.getAllDevWorklog(devprojectlist, batch, createddate);
			JSONObject jObject = new JSONObject();
			// String json = jObject.toJSON(devprojectlist).toString();
			jObject.put("project", devprojectlist.get(0).getProject());
			jObject.put("pname", devprojectlist.get(0).getPname());
			jObject.put("sum", devprojectlist.get(0).getSum());
			jObject.put("batch", batch);
			jObject.put("createddate", createddate);
			String json = jObject.toString();
//			System.out.println(json);
			pw = response.getWriter();
			pw.print(json);
			pw.close();
			// out.println("<script>alert('测试工时统计完成！');history.back();</script>");
		}

	}

}
