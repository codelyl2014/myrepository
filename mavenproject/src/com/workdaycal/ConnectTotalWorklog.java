package com.workdaycal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConnectTotalWorklog {
	public List<TotalWorklog> getconn(String batch,String createddate) throws SQLException {
	    try {
	     Class.forName("com.mysql.jdbc.Driver");    
	     System.out.println("Success loading Mysql Driver!ConnectTotalWorklog!");
	    }
	    catch (Exception e) {
	      System.out.print("Error loading Mysql Driver!ConnectTotalWorklog!");
	      e.printStackTrace();
	    }
	    List<TotalWorklog> totalworkloglist = new ArrayList<TotalWorklog>();
	    Connection connect=null;
	    try {
	     connect= DriverManager.getConnection(
	         "jdbc:mysql://10.47.12.174:3306/jiradata?characterEncoding=utf-8&allowMultiQueries=true","root","root");
	     System.out.println("Success connect Mysql server!ConnectTotalWorklog!");
	     Statement stmt = connect.createStatement();
	     String sql=" SELECT tmp.projectid, tmp.projname, tmp.sum_total_work, p.sumworked, tmp.batch, tmp.createddate "
		   +" FROM (SELECT w.projectid, w.projname, sum(w.timeworked) AS sum_total_work, w.batch, w.createddate  "
	    	+" 	FROM ( SELECT DISTINCT lower_user_name, batch, createddate FROM user_total WHERE flag = 1 "
	    	 +"  and batch='"+batch+"' and createddate='"+createddate
			  +"' ) AS u, worklog AS w WHERE u.lower_user_name = w.lower_user_name AND w.batch = u.batch"
			   +" AND w.createddate = u.createddate GROUP BY w.projectid) AS tmp,  project_worklog AS p  "
			   +" WHERE tmp.projectid = p.projectid AND tmp.batch = p.batch AND tmp.createddate = p.createddate ";
	      //System.out.println("sql_____"+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	   
	      while (rs.next()) {
	    	  TotalWorklog totalworklog =new TotalWorklog();
	    	  totalworklog.setProjectid(rs.getString("projectid"));
	    	  totalworklog.setProjname(rs.getString("projname"));
	    	  totalworklog.setSum_total_work(rs.getString("sum_total_work"));
	    	  totalworklog.setSumworked(rs.getString("sumworked"));
	    	  totalworklog.setBatch(rs.getString("batch"));
	    	  totalworklog.setCreateddate(rs.getString("createddate"));
	    	  totalworkloglist.add(totalworklog);   	 
	      }
	    }
	    catch (Exception e) {
	      System.out.print("get data error!ConnectTotalWorklog!");
	      e.printStackTrace();
	    }
	    finally{
	    	connect.close();
	    }
	    return totalworkloglist;
	  }
}
