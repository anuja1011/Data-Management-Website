package com.data_management.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.data_management.bean.UserPOJO;
import com.data_management.constants.AllContants;
import com.data_management.util.DatabaseUtil;
import com.data_management.utilities.IDandTimestampGenerator;

public class InserDBValuesDAO {
	
	public void insertInitialValues() {
		
		IDandTimestampGenerator tid = new IDandTimestampGenerator();
		
		RewardDAO rd = new RewardDAO();
		
		ArrayList<UserPOJO> user_arr = new ArrayList<UserPOJO>();
		
		user_arr = rd.allUsers();
				
		Connection conn=null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;

		ResultSet rs=null;
		//String currentTime_TID=null;
		String oldTransactionId=null;
		String oldDate=null;
		
		
		conn=DatabaseUtil.getConnection();
			
			try {
			for(int x=0;x<user_arr.size();x++) {
			if(user_arr.get(x).getType().equals("u")) {
			//currentTime_TID = tid.createTranscationId();
			
			oldTransactionId = tid.createOldTransactionId(0);
			oldDate = tid.older_formatted_timestamp(0);
			
			pstmt=conn.prepareStatement(AllContants.INITIAL_INSERT_QUERY_INTO_TRANSACTION_TABLE);
			pstmt1=conn.prepareStatement(AllContants.INITIAL_INSERT_QUERY_INTO_GIVING_BALANCE_TABLE);
			
			pstmt.setString(1,oldTransactionId );
			pstmt.setString(2, "a2018");
			pstmt.setString(3, user_arr.get(x).getUserId());
			pstmt.setDouble(4, AllContants.amount_admin);
			pstmt.setString(5, "Amount-Giving-Admin");
			pstmt.setString(6, tid.older_formatted_timestamp(0));
			pstmt.executeQuery();

			pstmt1.setString(1,oldTransactionId);
			pstmt1.setString(2, user_arr.get(x).getUserId());
			pstmt1.setDouble(3, AllContants.amount_admin);
			pstmt1.setDouble(4, AllContants.amount_admin);
			
			pstmt1.executeQuery();

			}
			}
			
			pstmt=null;
			for(int x=0;x<user_arr.size();x++) {
				if(user_arr.get(x).getType().equals("u")) {
					oldTransactionId = tid.createOldTransactionId(0);
					pstmt=conn.prepareStatement(AllContants.INITIAL_INSERT_QUERY_INTO_TRANSACTION_TABLE);
					pstmt1=conn.prepareStatement(AllContants.INITIAL_INSERT_QUERY_INTO_RECEIVING_BALANCE_TABLE);
					
					pstmt.setString(1,oldTransactionId );
					pstmt.setString(2, "a2018");
					pstmt.setString(3, user_arr.get(x).getUserId());
					pstmt.setDouble(4, AllContants.amount_rewards);
					pstmt.setString(5, "Redeem-Admin");
					pstmt.setString(6, tid.older_formatted_timestamp(0));
					pstmt.executeQuery();
					
					pstmt1.setString(1, oldTransactionId);
					pstmt1.setString(2, user_arr.get(x).getUserId());
					pstmt1.setDouble(3, AllContants.amount_rewards);
					pstmt1.setDouble(4, AllContants.amount_rewards);
					pstmt1.setString(5, "initial-r");
					
					pstmt1.executeQuery();
				}
			}
			}catch(Exception e) {
				
				e.printStackTrace();
			}finally {
				
				DatabaseUtil.closeConnection(conn);
				DatabaseUtil.closeStatement(pstmt);
				DatabaseUtil.closeStatement(pstmt1);
				DatabaseUtil.closeResult(rs);
			}
			
		}
	
	public static void main(String args[]) {
		
		InserDBValuesDAO obj1 = new InserDBValuesDAO();
		
		obj1.insertInitialValues();
	}

}
