package com.data_management.bean;

public class ReportsPOJO {
	
	private String userId;
	private String amount_given;
	private String amount_received;
	private String transaction_month;
	private String amount_redeemed;
	private String rank;
	
	public ReportsPOJO(String userId, String amount_given, String amount_received, String transaction_month,
			String amount_redeemed, String rank) {
		super();
		this.userId = userId;
		this.amount_given = amount_given;
		this.amount_received = amount_received;
		this.transaction_month = transaction_month;
		this.amount_redeemed = amount_redeemed;
		this.rank = rank;
	}

	public ReportsPOJO() {

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAmount_given() {
		return amount_given;
	}

	public void setAmount_given(String amount_given) {
		this.amount_given = amount_given;
	}

	public String getAmount_received() {
		return amount_received;
	}

	public void setAmount_received(String amount_received) {
		this.amount_received = amount_received;
	}

	public String getTransaction_month() {
		return transaction_month;
	}

	public void setTransaction_month(String transaction_month) {
		this.transaction_month = transaction_month;
	}

	public String getAmount_redeemed() {
		return amount_redeemed;
	}

	public void setAmount_redeemed(String amount_redeemed) {
		this.amount_redeemed = amount_redeemed;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}
	
	
	
	

}
