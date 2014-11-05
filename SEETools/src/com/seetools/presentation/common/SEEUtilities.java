package com.seetools.presentation.common;


import java.sql.Timestamp;
import java.util.Date;

public class SEEUtilities {

	public static Timestamp getCurrentTimeStamp(){
		
		Date date = new Date();
		Timestamp t = new Timestamp(date.getTime()); 
		return t;
		
	}
}
