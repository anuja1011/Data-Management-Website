package com.data_management.bean;

import java.util.ArrayList;

public class GivinBalanceDetailsArrayListPOJO {
	
	private ArrayList<GivingBalancePOJO> allReceivingUsersList = new ArrayList<GivingBalancePOJO>();
	
	

	public GivinBalanceDetailsArrayListPOJO() {

	}

	public ArrayList<GivingBalancePOJO> getAllReceivingUsersList() {
		return allReceivingUsersList;
	}

	public void setAllReceivingUsersList(ArrayList<GivingBalancePOJO> allReceivingUsersList) {
		this.allReceivingUsersList = allReceivingUsersList;
	}
	
	

}
