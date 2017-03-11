package com.workdaycal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//鑾峰彇闇�鎻掑叆鍒皃roject_worklog琛ㄩ噷鐨勬暟鎹�
public class ConnectProjectWorklog{
	  public  List<ProjectWorklog> getconn(String batch,String createddate) throws SQLException {
		    try {
		     Class.forName("com.mysql.jdbc.Driver");    
		     System.out.println("Success loading Mysql Driver!ConnectProjectWorklog!");
		    }
		    catch (Exception e) {
		      System.out.print("Error loading Mysql Driver!ConnectProjectWorklog!");
		      e.printStackTrace();
		    }
		    List<ProjectWorklog> projectworkloglist = new ArrayList<ProjectWorklog>();
		    Connection connect=null;
		    try {
		     connect= DriverManager.getConnection(
		         "jdbc:mysql://10.47.12.174:3306/jiradata?characterEncoding=utf-8&allowMultiQueries=true","root","root");
		     System.out.println("Success connect Mysql server!ConnectProjectWorklog!");
		     Statement stmt = connect.createStatement();
		     String sql=" select  w.projectid,w.projname,sum(w.timeworked) as sumworked "
			   +" from worklog as w,users as u  "
		        +" where u.user_name=w.lower_user_name and w.batch='"+batch+"' and w.createddate='"+createddate
				 +"' group by w.projectid order by w.projectid ";
		      //System.out.println("sql_____"+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		   
		      while (rs.next()) {
		    	  ProjectWorklog projectworklog =new ProjectWorklog();
		    	  projectworklog.setProjectid(rs.getString("projectid"));
		    	  projectworklog.setProjname(rs.getString("projname"));
		    	  projectworklog.setSumworked(rs.getString("sumworked"));
		    	  projectworkloglist.add(projectworklog);   	 
		      }
		    }
		    catch (Exception e) {
		      System.out.print("get data error!ConnectProjectWorklog!");
		      e.printStackTrace();
		    }
		    finally{
		    	connect.close();
		    }
		    return projectworkloglist;
		  }
		}