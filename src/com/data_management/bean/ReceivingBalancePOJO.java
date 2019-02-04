package com.data_management.bean;

public class ReceivingBalancePOJO {
	
	private String userId;
	private String transactionId;
	private double amount;
	private String transactionType;
	private String fullName;
	private double tran_amount;
	
	public ReceivingBalancePOJO() {
		
	}

	public ReceivingBalancePOJO(String userId, String transactionId, double amount, String transactionType,
			String fullName, double tran_amount) {
		this.userId = userId;
		this.transactionId = transactionId;
		this.amount = amount;
		this.transactionType = transactionType;
		this.fullName = fullName;
		this.tran_amount=tran_amount;
	}

	public String getUserId() {
		return userId;
	}

	
	public double getTran_amount() {
		return tran_amount;
	}

	public void setTran_amount(double tran_amount) {
		this.tran_amount = tran_amount;
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

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	
	
	
	

}
