package com.seetools.util;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.seetools.daolayer.TokenVerificationDAOImpl;
import com.seetools.dto.AccountActivationTokenBean;

public class Utilities {

	/**
	 * This method will validate the email address against a pattern for existence of @.
	 * @param emailAddress
	 * @return
	 */
	public static boolean validateEmail(String emailAddress) {
	 
		final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\." + "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" + "(\\.[A-Za-z]{2,})$";
		 
		Pattern pattern;
		Matcher matcher;
		
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(emailAddress);
		if(!matcher.matches()){
			return true;
		}
		return false;
	}

	public static boolean validatePassword(String password) {
		
		//Pattern to check digit, one lower case, one upper case, special symbols @#$% and minimum 6 characters and maximum 20 characters 
		final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[~!@#$%^&*()]).{6,20})";
		 
		Pattern pattern;
		Matcher matcher;
		
		pattern = Pattern.compile(PASSWORD_PATTERN);
		matcher = pattern.matcher(password);
		if(!matcher.matches()){
			return true;
		}
		return false;
	}
	
	public static boolean validateTokenWithEmail(String emailAddress, String token) {
		
		
		TokenVerificationDAOImpl tokenVerificationDAOImpl = (TokenVerificationDAOImpl)BeanFactory.getBean("tokenVerificationDAO");
		
		boolean validToken = false;
		
		List<AccountActivationTokenBean> activationTokenBeans = tokenVerificationDAOImpl.getAccountActivationTokenDetail(emailAddress, token); 
		if(activationTokenBeans != null && activationTokenBeans.size() == 1){
			if(getTimeDiffInMin(activationTokenBeans.get(0).getCreatedDate()) < 15)
			validToken = true;
		}
		
		return validToken;  
		
	}
	
	public static long getTimeDiffInMin(Timestamp tokenCreatedTime) {
		
		long timeDiff = 0;
		
		long tokenTime = tokenCreatedTime.getTime();
		
		long currentTime = getCurrentTimestamp().getTime();
		
		long difference = currentTime - tokenTime;
		
		timeDiff = difference / (60*1000) % 60;
		
		return timeDiff;
		
	}
	
	public static Timestamp getCurrentTimestamp(){
		
		java.util.Date date= new java.util.Date();
		Timestamp currentTimestamp = new Timestamp(date.getTime());
		return currentTimestamp;
	}
	public static String getRandomToken() {
		
		return UUID.randomUUID().toString();
		
	}
}
