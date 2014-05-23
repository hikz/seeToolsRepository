package com.seetools.businesslayer;

import java.util.List;
import java.util.UUID;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.seetools.daolayer.EmailDAOImpl;
import com.seetools.daolayer.RegisterDAOImpl;
import com.seetools.daolayer.TokenVerificationDAOImpl;
import com.seetools.dto.AccountActivationTokenBean;
import com.seetools.dto.EmailBean;
import com.seetools.dto.UserBean;
import com.seetools.presentation.common.SEEUtilities;
import com.seetools.util.SendEmail;
import com.seetools.util.Utilities;

public class SeeToolsRegisterServiceImpl {
	
	

	public boolean processRegistration(UserBean userDto){
		
		boolean registerSuccess = true;
		
		userDto.setCreatedDate(SEEUtilities.getCurrentTimeStamp());
		userDto.setModifiedDate(SEEUtilities.getCurrentTimeStamp());
		userDto.setCreatedByUserId("test");
		userDto.setModifiedByUserId("test");
		
		userDto.getEmail().setCreatedDate(SEEUtilities.getCurrentTimeStamp());
		userDto.getEmail().setModifiedDate(SEEUtilities.getCurrentTimeStamp());
		userDto.getEmail().setCreatedByUserId("test");
		userDto.getEmail().setModifiedByUserId("test");
		
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("seetools-bean-config.xml");
		
		RegisterDAOImpl registerDAOImpl = (RegisterDAOImpl)applicationContext.getBean("seeToolsRegisterDAO");
		TokenVerificationDAOImpl tokenVerificationDAOImpl = (TokenVerificationDAOImpl)applicationContext.getBean("tokenVerificationDAO");
		
		//TODO Need to have transactions.
		UserBean user =  registerDAOImpl.registerUser(userDto);
		//applicationContext.close();
		
		String token = UUID.randomUUID().toString();
		//Save the token in database with email address.
		tokenVerificationDAOImpl.saveAccountActivationToken(userDto.getEmail().getEmailID(), token);
		//Send Registration confirmation email.
		this.sendRegistrationConfirmationEmail(user.getEmail().getEmailAddress(), token);
		return registerSuccess;
	}
	
	private void sendRegistrationConfirmationEmail(String emailAddress, String token){
		
		SendEmail sendEmail = new SendEmail();
		sendEmail.sendEmail(emailAddress,token,"REGISTER");
	}
	
	public boolean registrationActivation(String emailAddress, String token){
		
		boolean registrationActivationSuccess = false;
		
		if(Utilities.validateTokenWithEmail(emailAddress, token)){
			ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("seetools-bean-config.xml");
			RegisterDAOImpl registerDAOImpl = (RegisterDAOImpl)applicationContext.getBean("seeToolsRegisterDAO");
			registrationActivationSuccess = registerDAOImpl.updateRegistrationActivation(emailAddress, token);
		}
		return registrationActivationSuccess;
	}
	
	public boolean checkDuplicateEmail(String emailAddress) {

		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("seetools-bean-config.xml");
		
		RegisterDAOImpl registerDAOImpl = (RegisterDAOImpl)applicationContext.getBean("seeToolsRegisterDAO");
		EmailDAOImpl emailDAOImpl = (EmailDAOImpl)applicationContext.getBean("emailDAO");
		
		applicationContext.close();
		
		
		List<EmailBean> emailBean = emailDAOImpl.getEmailDetail(emailAddress);
		
		if(emailBean != null && emailBean.size() > 0 ){
			return true;
		}
		
		return false;
	}
}
