package com.monthreport;

import java.awt.print.Printable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.alibaba.fastjson.JSONObject;
import com.model.User;

public class JiraTest {

	public static void main(String[] args) {
	
		SelectProject st=new SelectProject();
		List<ProjectPo> projectlist = null;
		try {
			projectlist = st.getconn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(projectlist);
//		for(int i=0;i<projectlist.size();i++){
//			String id=((ProjectPo) projectlist.get(i)).getID();
//			String name=((ProjectPo) projectlist.get(i)).getName();
//			
//			System.out.println(id+"  "+name);
//		}
		
		JSONObject jObject = new JSONObject();
		String json = jObject.toJSON(projectlist).toString();
		System.out.println(json);
	}
}
