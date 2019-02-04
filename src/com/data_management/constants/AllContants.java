package com.data_management.constants;

public class AllContants {
	
	/** constants **/
	public static final double amount_admin=1000.0;
	public static final double amount_rewards=30000;
	public static final double amount_initial=0.0;
	

	/** UserTable Column Value **/
	
	public static final String USER_ID = "userId";
	public static final String FULL_NAME = "fullName";
	public static final String USERNAME = "username";
	public static final String EMAIL = "email";
	public static final String PASSWORD = "password";
	public static final String USER_TYPE = "usertype";
	
	/** Giving_Balance Column Value **/
	
	//public static final String USER_ID = "userId";
	
	public static final String AMOUNT="amount";
	public static final String DATE="balancedate";
	public static final String TRANSACTIONID="transactionId";
	public static final String TYPE="transactionType";
	public static final String TRAN_AMOUNT="tran_amount";
	public static final String ToUser="GivenTo";
	public static final String OnDate="OnDate";

	
	/** Queries **/
	
	public static final String FETCH_QUERY_FROM_USER_DETAILS_TABLE_ALL_USERS ="Select * from User_Details";
	public static final String FETCH_QUERY_FROM_USER_DETAILS_TABLE = "Select * from User_Details where username=?";
	public static final String FETCH_QUERY_ALL_FROM_USER_DETAILS_TABLE = "Select * from User_Details where usertype='u'";
	public static final String FETCH_QUERY_FROM_GIVING_BALANCE_TABLE = "Select * from giving_balance where userid=? order by transactionId desc fetch first 1 rows only";
	public static final String FETCH_QUERY_FROM_RECEIVING_BALANCE_AND_USER_TABLE = "select * from (select rb.transactionId, rb.userId, ud.fullname,rb.amount, Row_number() over (partition by rb.userId order by rb.transactionId desc ) as Rnk from Receiving_Balance rb,User_Details ud where rb.userId=ud.userId and ud.usertype='u') where rnk=1";
	public static final String FETCH_QUERY_FROM_RECEIVING_BALANCE_TABLE ="Select * from receiving_balance where userid=? order by transactionId desc fetch first 1 rows only";
	public static final String FETCH_ALL_FROM_GIVING_BALANCE_TABLE="Select gb.userId,gb.amount, tt.time_stamp from giving_balance gb, transaction_table tt where gb.transactionId=tt.transactionId order by gb.transactionId desc fetch first 5 rows only";
	
	public static final String FETCH_QUERY_FOR_TRANSACTION_HISTORY="select a.userId, a.Tran_Amount as Amount, b.receiver_userId as GivenTo, b.time_stamp as OnDate from \n" + 
    		"(select transactionId, UserId,Tran_Amount\n" + 
    		"from Giving_Balance\n" + 
    		"where UserId=?)a\n" + 
    		"join\n" + 
    		"(select transactionId, receiver_userId, time_stamp \n" + 
    		"from transaction_table\n" + 
    		"where sender_userId=?)b\n" + 
    		"on a.transactionId = b.transactionId";
    
    public static final String FETCH_QUERY_FOR_TRANSACTION_HISTORY_RECEIVED="select b.sender_userId as userId, a.Tran_Amount as Amount, b.receiver_userId as GivenTo, b.time_stamp as OnDate from \n" + 
    		"(select transactionId, UserId,Tran_Amount\n" + 
    		"from Receiving_Balance\n" + 
    		"where UserId=?)a\n" + 
    		"join\n" + 
    		"(select transactionId, sender_userId,receiver_userId, time_stamp \n" + 
    		"from transaction_table\n" + 
    		"where receiver_userId=?)b\n" + 
    		"on a.transactionId = b.transactionId";
	
	/** Insert Queries for InitialValues **/
	
	public static final String INITIAL_INSERT_QUERY_INTO_TRANSACTION_TABLE="INSERT INTO TRANSACTION_TABLE VALUES (?,?,?,?,?,?)";
	public static final String INITIAL_INSERT_QUERY_INTO_GIVING_BALANCE_TABLE="INSERT INTO GIVING_BALANCE VALUES (?,?,?,?)";
	public static final String INITIAL_INSERT_QUERY_INTO_RECEIVING_BALANCE_TABLE="INSERT INTO RECEIVING_BALANCE VALUES(?,?,?,?,?)";

	
	/** Procedures for Transcation **/
	
	public static final String PROCEDURE_FOR_GIVING_REWARDS ="Call givingTransaction(?,?,?,?,?,?)";
	
	/** Generate Reports Queries **/
	
	public static final String SELECT_FOR_REPORT_1 ="select temp1.userId as User_ID,temp1.total as total_given,temp2.total as total_received, temp1.month, Row_number() over(partition by temp1.month order by temp2.total desc) as Rnk  \n" + 
			"from temp1,temp2\n" + 
			"where temp1.userId=temp2.userId and temp1.month=temp2.month";
	
	public static final String SELECT_FOR_REPORT_2="select gb.userId, sum(tt.amount) as Amount_given, Row_number() over (order by sum(tt.amount) desc) as Rank from\n" + 
			"transaction_table tt, giving_balance gb\n" + 
			"where gb.transactionid=tt.transactionId and tt.TIME_STAMP between add_months(trunc(sysdate,'mm'),-1) and last_day(add_months(trunc(sysdate,'mm'),-1)) and message='transfer'\n" + 
			"group by gb.userId";
	
	public static final String SELECT_FOR_REPORT_3="select rw.userId, extract(month from tt.time_stamp) as Transaction_Month, sum(rw.rewards_Redemeed) as Total_redeemed, Row_Number() over(order by sum(rw.rewards_Redemeed) desc) as Rank\n" + 
			"from rewards rw, transaction_table tt\n" + 
			"where tt.transactionId=rw.transactionId\n" + 
			"group by rw.userId,extract(month from tt.time_stamp)";

	

	


}
