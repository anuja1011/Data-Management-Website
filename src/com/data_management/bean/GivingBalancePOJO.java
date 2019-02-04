package com.data_management.bean;

public class GivingBalancePOJO {
	
	private String userId;
	private String transactionId;
	private double amount;
	private double tran_amount;
	
	public GivingBalancePOJO(String userId, String transactionId, double amount, double tran_amount) {
		super();
		this.userId = userId;
		this.transactionId = transactionId;
		this.amount = amount;
		this.tran_amount=tran_amount;
	}

	public GivingBalancePOJO() {
		
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getTran_amount() {
		return tran_amount;
	}

	public void setTran_amount(double tran_amount) {
		this.tran_amount = tran_amount;
	}
	
	
	
	
	
	
	

}
