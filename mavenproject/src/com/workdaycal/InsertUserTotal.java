package com.workdaycal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class InsertUserTotal {
	 public  void insertUserTotal(List<UserTotal> params,List<Date> workdate) throws SQLException {
		    try {
		    	Class.forName("com.mysql.jdbc.Driver");     
		    	System.out.println("Success loading Mysql Driver!InsertUserTotal!");
		    }
		    catch (Exception e) {
		    	System.out.print("Error loading Mysql Driver!InsertUserTotal!");
		    	e.printStackTrace();
		    }
		    Connection connect=null;
		    try {
		    	connect = DriverManager.getConnection(
		    			"jdbc:mysql://10.47.12.174:3306/jiradata?characterEncoding=utf-8&allowMultiQueries=true","root","root");
		    	System.out.println("Success connect Mysql server!InsertUserTotal!");
		    	List<UserTotal> usertotallist=params;
		    	List<Date> workdatelist=workdate;
		    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    	Statement stmt = connect.createStatement();
		    	System.out.println(usertotallist.size());
		    	String totalSql="";
		    	//閼惧嘲褰囧銉ょ稊閺冦儱銇夐弫锟�		    	
		    	int getdays=workdatelist.size();
		    	//閹銇夐弫锟�8800*80%
		    	int sumtheoreticworkedtime=getdays*2880*8;
		    	for (int i = 0; i < usertotallist.size(); i++) {	
		    		String sql=" ";
		    		String flag=null;
		    		String lower_user_name=((UserTotal) usertotallist.get(i)).getLower_user_name();
		    		String display_name=((UserTotal) usertotallist.get(i)).getDisplay_name();
		    		String batch=((UserTotal) usertotallist.get(i)).getBatch();
		    		String createddate=((UserTotal) usertotallist.get(i)).getCreateddate();
			    	String timeworked=((UserTotal) usertotallist.get(i)).getTimeworked();
			    	int tmpTimeworked=Integer.parseInt(timeworked);		    	
			    	if(tmpTimeworked>=sumtheoreticworkedtime){
			    		flag="0";
		    		}		  	    		
			    	else if(tmpTimeworked<sumtheoreticworkedtime){
		    			flag="1";
		    		}
		    		sql+=" INSERT INTO user_total (lower_user_name,display_name,timeworked,flag,batch,createddate)  ";
				    sql+=" VALUES ('"+lower_user_name+"','"+display_name+"','"+timeworked+"','"+flag+"','"+batch+"','"+createddate+"'); ";
				    totalSql+=sql;	
		    	}
		    	stmt.execute(totalSql);
		    }
		    catch (Exception e) {
		      System.out.print("get data error!InsertUserTotal!");
		      e.printStackTrace();
		    }
		    finally{
		    	System.out.println("已插入完成！");
		    	connect.close();
		    }
	 }
}
