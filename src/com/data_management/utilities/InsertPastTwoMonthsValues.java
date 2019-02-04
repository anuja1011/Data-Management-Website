package com.data_management.utilities;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.data_management.bean.UserPOJO;
import com.data_management.constants.AllContants;
import com.data_management.dao.InserDBValuesDAO;
import com.data_management.dao.RewardDAO;
import com.data_management.util.DatabaseUtil;
import com.data_management.utilities.IDandTimestampGenerator;

public class InsertPastTwoMonthsValues {
	
	public void insertOldValues() {
	
	IDandTimestampGenerator tid = new IDandTimestampGenerator();
	int value=30;
	
	RewardDAO rd = new RewardDAO();
	
	ArrayList<UserPOJO> user_arr = new ArrayList<UserPOJO>();
	
	user_arr = rd.allUsers();
			
	Connection conn=null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt1 = null;

	ResultSet rs=null;
	
	conn=DatabaseUtil.getConnection();
		
		///User who gave all points
		
		try {
			
			for(int x=0;x<user_arr.size()-3;x++) {
				double amount=1000;
				if(user_arr.get(x).getType().equals("u")) {
					for(int y=x;y<user_arr.size()-3;y++) {
						if(user_arr.get(y).getType().equals("u")) {
							if(!user_arr.get(x).getUserId().equals(user_arr.get(y).getUserId())){
								String oldTransactionId = tid.createOldTransactionId(value);
								String oldDate = tid.older_formatted_timestamp(value);
								//amount=amount;

								pstmt=conn.prepareStatement(AllContants.PROCEDURE_FOR_GIVING_REWARDS);
								
								pstmt.setString(1,oldTransactionId );
								pstmt.setString(2, user_arr.get(x).getUserId());
								pstmt.setString(3, user_arr.get(y).getUserId());
								pstmt.setDouble(4, amount);
								pstmt.setString(6, "transfer");
								pstmt.setString(5, oldDate);
								pstmt.executeQuery();
								
								value=value-10;
							}}}}}
			value=30;
			for(int x=3;x<user_arr.size();x++) {
				double amount=0;
				if(user_arr.get(x).getType().equals("u")) {
					for(int y=3;y<user_arr.size();y++) {
						if(user_arr.get(y).getType().equals("u")) {
							if(!user_arr.get(x).getUserId().equals(user_arr.get(y).getUserId())){
								String oldTransactionId = tid.createOldTransactionId(value);
								String oldDate = tid.older_formatted_timestamp(value);
								amount=amount+100;

								pstmt=conn.prepareStatement(AllContants.PROCEDURE_FOR_GIVING_REWARDS);
								
								pstmt.setString(1,oldTransactionId );
								pstmt.setString(2, user_arr.get(x).getUserId());
								pstmt.setString(3, user_arr.get(y).getUserId());
								pstmt.setDouble(4, amount);
								pstmt.setString(6, "transfer");
								pstmt.setString(5, oldDate);
								pstmt.executeQuery();
								
								value=value-1;
							}}}}}
			}catch(Exception e){
			e.printStackTrace();
		}finally {
			DatabaseUtil.closeConnection(conn);
			DatabaseUtil.closeStatement(pstmt);
			DatabaseUtil.closeStatement(pstmt1);
			DatabaseUtil.closeResult(rs);
			
		}
			
		}	
	
public static void main(String args[]) {
		
		InsertPastTwoMonthsValues obj1 = new InsertPastTwoMonthsValues();
		
		obj1.insertOldValues();

}
}
