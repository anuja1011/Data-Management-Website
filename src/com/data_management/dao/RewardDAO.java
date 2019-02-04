package com.data_management.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.omg.PortableInterceptor.USER_EXCEPTION;

import com.data_management.bean.GivingBalancePOJO;
import com.data_management.bean.ReceivingBalancePOJO;
import com.data_management.bean.ReceivingUsersListPOJO;
import com.data_management.bean.ReportsPOJO;
import com.data_management.bean.UserPOJO;
import com.data_management.bean.UserTransactionPOJO;
import com.data_management.constants.AllContants;
import com.data_management.util.DatabaseUtil;
import com.data_management.utilities.IDandTimestampGenerator;

public class RewardDAO {
	
	public UserPOJO login(String username) {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		UserPOJO user = new UserPOJO();
		
		try {
			
			conn = DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(AllContants.FETCH_QUERY_FROM_USER_DETAILS_TABLE);
			pstmt.setString(1, username);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				user.setUserId(rs.getString(AllContants.USER_ID));
				user.setUsername(rs.getString(AllContants.USERNAME));
				user.setPassword(rs.getString(AllContants.PASSWORD));
				user.setFullName(rs.getString(AllContants.FULL_NAME));
				user.setEmail(rs.getString(AllContants.EMAIL));
				user.setType(rs.getString(AllContants.USER_TYPE));
				
				//System.out.println("For Testing");
				//System.out.println(user.getPassword());
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DatabaseUtil.closeConnection(conn);
			DatabaseUtil.closeStatement(pstmt);
			DatabaseUtil.closeResult(rs);
		}
		
		
		return user;
	}
	
public ArrayList<UserPOJO> login2() {
	
		ArrayList<UserPOJO> user_details_arr =new ArrayList<UserPOJO>();
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		UserPOJO user = new UserPOJO();
		
		try {
			
			conn = DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(AllContants.FETCH_QUERY_ALL_FROM_USER_DETAILS_TABLE);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				user = new UserPOJO();
				user.setUserId(rs.getString(AllContants.USER_ID));
				user.setUsername(rs.getString(AllContants.USERNAME));
				user.setPassword(rs.getString(AllContants.PASSWORD));
				user.setFullName(rs.getString(AllContants.FULL_NAME));
				user.setEmail(rs.getString(AllContants.EMAIL));
				user.setType(rs.getString(AllContants.USER_TYPE));
				
				//System.out.println("For Testing");
				//System.out.println(user.getPassword());
				
				user_details_arr.add(user);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DatabaseUtil.closeConnection(conn);
			DatabaseUtil.closeStatement(pstmt);
			DatabaseUtil.closeResult(rs);
		}
		
		
		return user_details_arr;
	}
	
	public GivingBalancePOJO givingBalance(String userId) {
		
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		GivingBalancePOJO user_giving_details = new GivingBalancePOJO();
		
		try {
			
			conn= DatabaseUtil.getConnection();
			pstmt= conn.prepareStatement(AllContants.FETCH_QUERY_FROM_GIVING_BALANCE_TABLE);
			pstmt.setString(1, userId);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				user_giving_details.setUserId(rs.getString(AllContants.USER_ID));
				user_giving_details.setAmount((double)rs.getInt(AllContants.AMOUNT));
				
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DatabaseUtil.closeConnection(conn);
			DatabaseUtil.closeStatement(pstmt);
			DatabaseUtil.closeResult(rs);
		}
		
		return user_giving_details;
		
		
	}
	
	
	public ArrayList<ReceivingBalancePOJO> receivingUsersList(String userId){
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		ReceivingBalancePOJO receiving_user;
		ArrayList<ReceivingBalancePOJO> receiving_user_arr = new ArrayList<ReceivingBalancePOJO>();
		
		try {
			
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(AllContants.FETCH_QUERY_FROM_RECEIVING_BALANCE_AND_USER_TABLE);
			//this is to get a list of all the user list to whom current user can transfer money
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				receiving_user= new ReceivingBalancePOJO();
				if((rs.getString(AllContants.USER_ID)).equals(userId)) {
				}else {
					receiving_user.setUserId(rs.getString(AllContants.USER_ID));
					receiving_user.setAmount(rs.getDouble(AllContants.AMOUNT));
					receiving_user.setFullName(rs.getString(AllContants.FULL_NAME));
					receiving_user_arr.add(receiving_user);
				}
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			DatabaseUtil.closeConnection(conn);
			DatabaseUtil.closeStatement(pstmt);
			DatabaseUtil.closeResult(rs);
			
		}
		
		ReceivingUsersListPOJO all_user_list = new ReceivingUsersListPOJO(receiving_user_arr);
		return receiving_user_arr;
		
	}
	
	public ArrayList<UserPOJO> allUsers() {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		UserPOJO user = new UserPOJO();
		ArrayList<UserPOJO> user_arr = new ArrayList<UserPOJO>();
		
		try {
			
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(AllContants.FETCH_QUERY_FROM_USER_DETAILS_TABLE_ALL_USERS);
			rs = pstmt.executeQuery();
			
			
			
			while(rs.next()) {
				user= new UserPOJO();
				user.setUserId(rs.getString(AllContants.USER_ID));
				user.setUsername(rs.getString(AllContants.USERNAME));
				user.setPassword(rs.getString(AllContants.PASSWORD));
				user.setFullName(rs.getString(AllContants.FULL_NAME));
				user.setEmail(rs.getString(AllContants.EMAIL));
				user.setType(rs.getString(AllContants.USER_TYPE));
				
				user_arr.add(user);
				
				//System.out.println("For Testing");
				//System.out.println(user.getPassword());
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DatabaseUtil.closeConnection(conn);
			DatabaseUtil.closeStatement(pstmt);
			DatabaseUtil.closeResult(rs);
		}
		
		return user_arr;
		
		
	}
	
	public void transactionOfRewards(String giving, String receiving, double amount, String type) {
		
		IDandTimestampGenerator tid = new IDandTimestampGenerator();

		
		Connection conn=null;
		PreparedStatement pstmt=null;

		
		try {
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(AllContants.PROCEDURE_FOR_GIVING_REWARDS);

			pstmt.setString(1, tid.createTranscationId()); //transactionId
			pstmt.setString(2, giving); //sender userId
			pstmt.setString(3, receiving); //receiver userId
			pstmt.setDouble(4, amount); //amount
			pstmt.setString(5, tid.formatted_timestamp()); //date
			pstmt.setString(6, type);
			pstmt.executeQuery();
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DatabaseUtil.closeConnection(conn);
			DatabaseUtil.closeStatement(pstmt);

		}
		
		
	}
	
	public GivingBalancePOJO fetchCurrentGivingUserDetails(String giving) {
		
		
		GivingBalancePOJO giving_user = new GivingBalancePOJO();
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(AllContants.FETCH_QUERY_FROM_GIVING_BALANCE_TABLE);
			
			pstmt.setString(1, giving);
			rs = pstmt.executeQuery();
			
	
			while(rs.next()) {
				
				giving_user.setUserId(rs.getString(AllContants.USER_ID));
				giving_user.setTransactionId(rs.getString(AllContants.TRANSACTIONID));
				giving_user.setAmount(rs.getDouble(AllContants.AMOUNT));
				giving_user.setTran_amount(rs.getDouble(AllContants.TRAN_AMOUNT));
			}
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DatabaseUtil.closeConnection(conn);
			DatabaseUtil.closeStatement(pstmt);
			DatabaseUtil.closeResult(rs);


		}
		return giving_user;
		
	}
	
	
	public ReceivingBalancePOJO fetchCurrentReceivingUserDetails(String receiving) {
		
		ReceivingBalancePOJO receiving_user = new ReceivingBalancePOJO();
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		
		try {
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(AllContants.FETCH_QUERY_FROM_RECEIVING_BALANCE_TABLE);
			
			
			pstmt.setString(1, receiving);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				
				receiving_user.setUserId(rs.getString(AllContants.USER_ID));
				receiving_user.setAmount(rs.getDouble(AllContants.AMOUNT));
				receiving_user.setTransactionId(rs.getString(AllContants.TRANSACTIONID));
				receiving_user.setTransactionId(rs.getString(AllContants.TYPE));
				receiving_user.setTran_amount(rs.getDouble(AllContants.TRAN_AMOUNT));
				
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DatabaseUtil.closeConnection(conn);
			DatabaseUtil.closeStatement(pstmt);
			DatabaseUtil.closeResult(rs);


		}
		return receiving_user;
	}

	
	public ArrayList<UserTransactionPOJO> fetchCurrentUserHistory(String currentUser){
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		UserTransactionPOJO userTran = new UserTransactionPOJO();
		ArrayList<UserTransactionPOJO> userTran_arr = new ArrayList<UserTransactionPOJO>();
		
		try {
			
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(AllContants.FETCH_QUERY_FOR_TRANSACTION_HISTORY);
			
			pstmt.setString(1, currentUser);
			pstmt.setString(2, currentUser);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				userTran = new UserTransactionPOJO();
				userTran.setFromUser(rs.getString(AllContants.USER_ID));
				userTran.setToUser(rs.getString(AllContants.ToUser));
				userTran.setOnDate(rs.getString(AllContants.OnDate));
				userTran.setAmountGiven(rs.getString(AllContants.AMOUNT));
				
				userTran_arr.add(userTran);
				
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		
		}finally {
			DatabaseUtil.closeConnection(conn);
			DatabaseUtil.closeStatement(pstmt);
			DatabaseUtil.closeResult(rs);
			
		}
		
		return userTran_arr;
		
		
	}
	
public ArrayList<UserTransactionPOJO> fetchCurrentUserHistoryForReceived(String currentUser){
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		UserTransactionPOJO userTran = new UserTransactionPOJO();
		ArrayList<UserTransactionPOJO> userTran_arr = new ArrayList<UserTransactionPOJO>();
		
		try {
			
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(AllContants.FETCH_QUERY_FOR_TRANSACTION_HISTORY_RECEIVED);
			
			pstmt.setString(1, currentUser);
			pstmt.setString(2, currentUser);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				userTran = new UserTransactionPOJO();
				userTran.setFromUser(rs.getString(AllContants.USER_ID));
				userTran.setToUser(rs.getString(AllContants.ToUser));
				userTran.setOnDate(rs.getString(AllContants.OnDate));
				userTran.setAmountGiven(rs.getString(AllContants.AMOUNT));
				
				userTran_arr.add(userTran);
				
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		
		}finally {
			DatabaseUtil.closeConnection(conn);
			DatabaseUtil.closeStatement(pstmt);
			DatabaseUtil.closeResult(rs);
			
		}
		
		return userTran_arr;
		
		
	}

public ReceivingBalancePOJO getReceivingBalance(String userId) {
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	ReceivingBalancePOJO receivingBalancePOJO = null;
	
	try {
		
		conn=DatabaseUtil.getConnection();
		pstmt=conn.prepareStatement(AllContants.FETCH_QUERY_FROM_RECEIVING_BALANCE_TABLE);
		
		pstmt.setString(1, userId);
		
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			receivingBalancePOJO = new ReceivingBalancePOJO();
			receivingBalancePOJO.setUserId(rs.getString(AllContants.USER_ID));
			receivingBalancePOJO.setAmount(rs.getDouble(AllContants.AMOUNT));			
		}
		
		
	}catch(Exception e) {
		e.printStackTrace();
	
	}finally {
		DatabaseUtil.closeResult(rs);
		DatabaseUtil.closeStatement(pstmt);
		DatabaseUtil.closeConnection(conn);
	}
	
	return receivingBalancePOJO;
}

public void redeemTransaction(String userid, double amount, String type) {
	
	IDandTimestampGenerator tid = new IDandTimestampGenerator();		
	Connection conn=null;
	PreparedStatement pstmt=null;

	try {
		conn=DatabaseUtil.getConnection();
		pstmt=conn.prepareStatement(AllContants.INITIAL_INSERT_QUERY_INTO_TRANSACTION_TABLE);

		pstmt.setString(1, tid.createTranscationId()); //transactionId
		pstmt.setString(2, "sys"); //system user
		pstmt.setString(3, userid);
		pstmt.setDouble(4, amount); //Amount of points redeem
		pstmt.setString(6, tid.formatted_timestamp()); //date
		pstmt.setString(5, "Redeem");
		pstmt.executeQuery();
		
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		DatabaseUtil.closeConnection(conn);
		DatabaseUtil.closeStatement(pstmt);
	}		
}

public ArrayList<ReportsPOJO> generateReports(int number) {
	Connection conn=null;
	ResultSet rs=null;
	PreparedStatement pstmt=null;
	
	ArrayList<ReportsPOJO> arr_rep = new ArrayList<ReportsPOJO>();
	
	try {
		
		conn=DatabaseUtil.getConnection();
		if(number==1) {
			pstmt=conn.prepareStatement(AllContants.SELECT_FOR_REPORT_1);
			rs=pstmt.executeQuery();

			while(rs.next()) {
				ReportsPOJO rep=new ReportsPOJO();
				rep.setUserId(rs.getString("user_Id"));
				rep.setAmount_given(rs.getString("Total_given"));
				rep.setAmount_received(rs.getString("Total_Received"));
				rep.setTransaction_month(rs.getString("month"));
				rep.setRank(rs.getString("rnk"));
				
				arr_rep.add(rep);
				
			}
			
		}else if(number==2) {
			pstmt=conn.prepareStatement(AllContants.SELECT_FOR_REPORT_2);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				ReportsPOJO rep=new ReportsPOJO();
				
				rep.setUserId(rs.getString("userId"));
				rep.setAmount_given(rs.getString("Amount_given"));
				rep.setRank(rs.getString("Rank"));
				
				arr_rep.add(rep);

			}
		}else {
			pstmt=conn.prepareStatement(AllContants.SELECT_FOR_REPORT_3);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				ReportsPOJO rep=new ReportsPOJO();
				
				rep.setUserId(rs.getString("UserId"));
				rep.setTransaction_month(rs.getString("transaction_month"));
				rep.setAmount_redeemed(rs.getString("total_redeemed"));
				rep.setRank(rs.getString("Rank"));

				arr_rep.add(rep);

			}
		}
		
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		DatabaseUtil.closeResult(rs);
		DatabaseUtil.closeStatement(pstmt);
		DatabaseUtil.closeConnection(conn);
	}
	return arr_rep;
}

public ArrayList<GivingBalancePOJO> refreshBalance (){
	
	IDandTimestampGenerator tid = new IDandTimestampGenerator();
	
	RewardDAO rd = new RewardDAO();
	
	ArrayList<UserPOJO> user_arr = new ArrayList<UserPOJO>();
	
	user_arr = rd.allUsers();
	
	Connection conn=null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt1 = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs=null;
	
	ArrayList<GivingBalancePOJO> arr_temp = new ArrayList<GivingBalancePOJO>();
	
	
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
		pstmt2=conn.prepareStatement(AllContants.FETCH_ALL_FROM_GIVING_BALANCE_TABLE);

		rs = pstmt2.executeQuery();

		while(rs.next()) {
			
			GivingBalancePOJO obj = new GivingBalancePOJO();

			obj.setUserId(rs.getString(AllContants.USER_ID));
			obj.setAmount(rs.getDouble(AllContants.AMOUNT));
			obj.setTransactionId(rs.getString("Time_stamp"));
			
			arr_temp.add(obj);
		}
		
		
		
		
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		DatabaseUtil.closeStatement(pstmt);
		DatabaseUtil.closeConnection(conn);
		DatabaseUtil.closeResult(rs);
		
	}
	
	return arr_temp;
	

	
}
		
	public static void main(String args[]) {
		//UserBean arr = new UserBean();  
		//arr = login("as009");
		
		//System.out.println(arr.getEmail());
		//System.out.println(arr.getFullName());
		//System.out.println(arr.getUsername());

	}
	
}
