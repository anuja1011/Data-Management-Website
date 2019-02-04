package com.data_management.bean;

import java.util.ArrayList;

public class ReceivingUsersListPOJO {
	
	private ArrayList<ReceivingBalancePOJO> allReceivingUsersList = new ArrayList<ReceivingBalancePOJO>();

	public ReceivingUsersListPOJO() {
		
	}
	
	public ReceivingUsersListPOJO(ArrayList<ReceivingBalancePOJO> allReceivingUsersList) {
		this.allReceivingUsersList = allReceivingUsersList;
	}

	public ArrayList<ReceivingBalancePOJO> getAllReceivingUsersList() {
		return allReceivingUsersList;
	}

	public void setAllReceivingUsersList(ArrayList<ReceivingBalancePOJO> allReceivingUsersList) {
		this.allReceivingUsersList = allReceivingUsersList;
	}
	
}
	