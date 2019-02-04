package com.data_management.validations;

import com.data_management.bean.ReceivingBalancePOJO;

public class Validate {

	public static String validateRedeemAmount(ReceivingBalancePOJO receivingBalancePOJO){
		
		if(receivingBalancePOJO.getTran_amount()>receivingBalancePOJO.getAmount()) {
			return "You do not have enough balance.";
		}
		if(receivingBalancePOJO.getTran_amount() % 10000 != 0) {
			return "Please enter amount multiple of 10,000 to redeem.. 10,000 point=$100 Gift Card";
		}
		return null;
	}
}
