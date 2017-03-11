package com.workdaycal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConnectEveryWorklog {
	public List<EveryWorklog> getconn(String batch,String createddate) throws SQLException {
	    try {
	     Class.forName("com.mysql.jdbc.Driver");    
	     System.out.println("Success loading Mysql Driver!ConnectEveryWorklog!");
	    }
	    catch (Exception e) {
	      System.out.print("Error loading Mysql Driver!ConnectEveryWorklog!");
	      e.printStackTrace();
	    }
	    List<EveryWorklog> everyworkloglist = new ArrayList<EveryWorklog>();
	    Connection connect=null;
	    try {
	     connect= DriverManager.getConnection(
	         "jdbc:mysql://10.47.12.174:3306/jiradata?characterEncoding=utf-8&allowMultiQueries=true","root","root");
	     System.out.println("Success connect Mysql server!ConnectEveryWorklog!");
	     Statement stmt = connect.createStatement();
	     String sql=" SELECT tmp.projectid, tmp.projname, tmp.sum_everyday_work, p.sumworked, tmp.batch, tmp.createddate "
		   +" FROM ( SELECT w.projectid, w.projname, sum(w.timeworked) AS sum_everyday_work, w.batch, w.createddate  "
	    	+" 	FROM ( SELECT DISTINCT lower_user_name, batch, createddate FROM user_everyday "
	    	 +"  WHERE flag = 1 and batch='"+batch+"' and createddate='"+createddate
			  +"' ) AS u, worklog AS w "
			   +" WHERE u.lower_user_name = w.lower_user_name AND w.batch = u.batch AND w.createddate = u.createddate GROUP BY w.projectid "
			   +" ) AS tmp, project_worklog AS p WHERE tmp.projectid = p.projectid AND tmp.batch = p.batch AND tmp.createddate = p.createddate ";
	      //System.out.println("sql_____"+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	   
	      while (rs.next()) {
	    	  EveryWorklog everyworklog =new EveryWorklog();
	    	  everyworklog.setProjectid(rs.getString("projectid"));
	    	  everyworklog.setProjname(rs.getString("projname"));
	    	  everyworklog.setSum_everyday_work(rs.getString("sum_everyday_work"));
	    	  everyworklog.setSumworked(rs.getString("sumworked"));
	    	  everyworklog.setBatch(rs.getString("batch"));
	    	  everyworklog.setCreateddate(rs.getString("createddate"));
	    	  everyworkloglist.add(everyworklog);   	 
	      }
	    }
	    catch (Exception e) {
	      System.out.print("get data error!ConnectEveryWorklog!");
	      e.printStackTrace();
	    }
	    finally{
	    	connect.close();
	    }
	    return everyworkloglist;
	  }
}
