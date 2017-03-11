package com.workdaycal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConnectUserTotal {
	public  List<UserTotal> getconn(String batch,String createddate) throws SQLException {
	    try {
	     Class.forName("com.mysql.jdbc.Driver");    
	     System.out.println("Success loading Mysql Driver!ConnectUserTotal!");
	    }
	    catch (Exception e) {
	      System.out.print("Error loading Mysql Driver!ConnectUserTotal!");
	      e.printStackTrace();
	    }
	    List<UserTotal> usertotallist = new ArrayList<UserTotal>();
	    Connection connect=null;
	    try {
	     connect= DriverManager.getConnection(
	         "jdbc:mysql://10.47.12.174:3306/jiradata?characterEncoding=utf-8&allowMultiQueries=true","root","root");
	     System.out.println("Success connect Mysql server!ConnectUserTotal!");
	     Statement stmt = connect.createStatement();
	     String sql="  select lower_user_name,display_name,SUM(timeworked) AS timeworked,batch,createddate "
		   +" from user_everyday  "
	        +" where batch='"+batch+"' and createddate='"+createddate
			 +"' group by lower_user_name ";
	      //System.out.println("sql_____"+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	   
	      while (rs.next()) {
	    	  UserTotal usertotal =new UserTotal();
	    	  usertotal.setLower_user_name(rs.getString("lower_user_name"));
	    	  usertotal.setDisplay_name(rs.getString("display_name"));
	    	  usertotal.setTimeworked(rs.getString("timeworked"));
	    	  usertotal.setBatch(rs.getString("batch"));
	    	  usertotal.setCreateddate(rs.getString("createddate"));
	    	  usertotallist.add(usertotal);   	 
	      }
	    }
	    catch (Exception e) {
	      System.out.print("get data error!ConnectUserTotal!");
	      e.printStackTrace();
	    }
	    finally{
	    	connect.close();
	    }
	    return usertotallist;
	  }
}
