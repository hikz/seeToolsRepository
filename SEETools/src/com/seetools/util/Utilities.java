package com.seetools.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.seetools.daolayer.RegisterDAOImpl;
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

	
	public static boolean validateTokenWithEmail(String emailAddress, String token) {
		
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("seetools-bean-config.xml");
		
		RegisterDAOImpl registerDAOImpl = (RegisterDAOImpl)applicationContext.getBean("seeToolsRegisterDAO");
		TokenVerificationDAOImpl tokenVerificationDAOImpl = (TokenVerificationDAOImpl)applicationContext.getBean("tokenVerificationDAO");
		
		applicationContext.close();
		
		boolean validToken = false;
		
		List<AccountActivationTokenBean> activationTokenBeans = tokenVerificationDAOImpl.getAccountActivationTokenDetail(emailAddress, token); 
		if(activationTokenBeans != null && activationTokenBeans.size() == 1){
			validToken = true;
		}
		
		return validToken; 
		
	}
}
