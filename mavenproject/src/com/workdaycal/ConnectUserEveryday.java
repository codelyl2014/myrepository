package com.workdaycal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//鑾峰彇闇�鎻掑叆鍒癠ser_Everyday琛ㄩ噷鐨勬暟鎹�
public class ConnectUserEveryday {
	 public  List<UserEveryday> getconn(String batch,String createddate) throws SQLException {
		    try {
		     Class.forName("com.mysql.jdbc.Driver");    
		     System.out.println("Success loading Mysql Driver!ConnectUserEveryday!");
		    }
		    catch (Exception e) {
		      System.out.print("Error loading Mysql Driver!ConnectUserEveryday!");
		      e.printStackTrace();
		    }
		    List<UserEveryday> usereverydaylist = new ArrayList<UserEveryday>();
		    Connection connect=null;
		    try {
		     connect= DriverManager.getConnection(
		         "jdbc:mysql://10.47.12.174:3306/jiradata?characterEncoding=utf-8&allowMultiQueries=true","root","root");
		     System.out.println("Success connect Mysql server!ConnectUserEveryday!");
		     Statement stmt = connect.createStatement();
		     String sql="  SELECT tmp.user_name as lower_user_name,tmp.display_name,sum(w.timeworked) AS timeworked,tmp.workday,tmp.batch,tmp.createddate "
			   +" FROM worklog AS w RIGHT OUTER JOIN ( SELECT u.user_name,u.display_name,wd.workday,wd.batch,wd.createddate  "
		        +" FROM users AS u, workdate AS wd WHERE wd.createddate = '"+createddate+"' and wd.batch='"+batch+"' ) AS tmp ON ( "
		         +"tmp.user_name = w.lower_user_name AND w.workday = tmp.workday and w.batch='"+batch+"' and w.createddate='"+createddate
				  +"' )GROUP BY tmp.user_name, tmp.workday ORDER BY tmp.user_name, tmp.workday";
		      //System.out.println("sql_____"+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		   
		      while (rs.next()) {
		    	  UserEveryday usereveryday =new UserEveryday();
		    	  usereveryday.setLower_user_name(rs.getString("lower_user_name"));
		    	  usereveryday.setDisplay_name(rs.getString("display_name"));
		    	  usereveryday.setWorkday(rs.getString("workday"));
		    	  usereveryday.setTimeworked(rs.getString("timeworked"));
		    	  usereveryday.setBatch(rs.getString("batch"));
		    	  usereveryday.setCreateddate(rs.getString("createddate"));
		    	  usereverydaylist.add(usereveryday);   	 
		      }
		    }
		    catch (Exception e) {
		      System.out.print("get data error!ConnectUserEveryday!");
		      e.printStackTrace();
		    }
		    finally{
		    	connect.close();
		    }
		    return usereverydaylist;
		  }
}
