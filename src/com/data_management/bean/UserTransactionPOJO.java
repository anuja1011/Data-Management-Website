package com.data_management.bean;

public class UserTransactionPOJO {
	
	private String fromUser;
	private String amountGiven;
	private String toUser;
	private String onDate;
	private String fromUserName;
	private String toUserName;
	
	public UserTransactionPOJO(String fromUser, String amountGiven, String toUser, String onDate, String fromUserName,
			String toUserName) {
		super();
		this.fromUser = fromUser;
		this.amountGiven = amountGiven;
		this.toUser = toUser;
		this.onDate = onDate;
		this.fromUserName = fromUserName;
		this.toUserName = toUserName;
	}
	public UserTransactionPOJO() {
		
	}
	public String getFromUser() {
		return fromUser;
	}
	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
	public String getAmountGiven() {
		return amountGiven;
	}
	public void setAmountGiven(String amountGiven) {
		this.amountGiven = amountGiven;
	}
	public String getToUser() {
		return toUser;
	}
	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	public String getOnDate() {
		return onDate;
	}
	public void setOnDate(String onDate) {
		this.onDate = onDate;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	
	
}
	