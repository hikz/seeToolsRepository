package com.seetools.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
}
