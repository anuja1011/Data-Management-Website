package com.data_management.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.data_management.util.DatabaseUtil;


public class UserDAO{
	
	public static ArrayList<String> test(){
	
	ArrayList<String> arr = new ArrayList<String>();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	try {
		con = DatabaseUtil.getConnection();
		ps = con.prepareStatement("select * from user_details");
		rs = ps.executeQuery();
		while (rs.next()) {
			arr.add(rs.getString(2));

		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		DatabaseUtil.closeConnection(con);
		DatabaseUtil.closeStatement(ps);
	}
	
	return arr;
}
	
	public static void main(String args[]) {
		ArrayList<String> arr = new ArrayList<String>();  
		arr = test();
		
		for (int x=0; x<arr.size();x++) {
			System.out.println(arr.get(x)+" ");
			
		}
	}
}

