package com.data_management.utilities;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class IDandTimestampGenerator {
	
    //private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
	
	
	public String createOldTransactionId(int value) {
		
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.DAY_OF_YEAR, -value);
	long olderTime = cal.getTimeInMillis();
	
	String time = Long.toString(olderTime);
	
	
	return time;

	}
	
	public String older_formatted_timestamp(int value) {
			    
		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DAY_OF_YEAR, -value);
		
		Date previousDate = cal.getTime();
		
		String result = sdf.format(previousDate);
		
		
		return result;
		
		
	}
	
	public String createTranscationId() {
        
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		Long time = timestamp.getTime();
		
		String time_str = Long.toString(time);
		
		return time_str;
	}
	
	
	public String formatted_timestamp() {
		

	    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	    
	    String time = sdf.format(timestamp);
		
		return time;
		
		
	}
	
	public static void main(String args[]) {
		
		IDandTimestampGenerator obj = new IDandTimestampGenerator();
		int value=60;
		String a1=obj.createOldTransactionId(value);
		System.out.println(a1);
	}

}
