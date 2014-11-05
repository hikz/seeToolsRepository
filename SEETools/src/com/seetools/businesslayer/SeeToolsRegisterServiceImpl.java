package com.seetools.businesslayer;

import java.util.List;

import com.seetools.daolayer.EmailDAOImpl;
import com.seetools.daolayer.RegisterDAOImpl;
import com.seetools.daolayer.TokenVerificationDAOImpl;
import com.seetools.dto.EmailBean;
import com.seetools.dto.UserBean;
import com.seetools.framework.exceptions.EmailException;
import com.seetools.util.SendEmail;
import com.seetools.util.Utilities;

public class SeeToolsRegisterServiceImpl {
	
	RegisterDAOImpl registerDAOImpl;
	TokenVerificationDAOImpl tokenVerificationDAOImpl;
	EmailDAOImpl emailDAOImpl;

	public boolean processRegistration(UserBean userDto) throws EmailException {
		
		boolean registerSuccess = true;
		
		//try{
			setTimeStampAndUserDetails(userDto);
			
			UserBean user =  registerDAOImpl.registerUser(userDto);
			
			//Create a token, save in database and send confirmation email.
			String token = Utilities.getRandomToken();
			saveActivationTokenDetails(userDto, token);
			sendRegistrationConfirmationEmail(user.getEmail().getEmailAddress(), token);
			
		/*} catch(Exception e){
			e.printStackTrace();
		}*/
		
		return registerSuccess;
	}
	
	private void saveActivationTokenDetails(UserBean userDto, String token) {
		
		tokenVerificationDAOImpl.saveAccountActivationToken(userDto.getEmail().getEmailID(), token);
		
	}
	
	private void setTimeStampAndUserDetails(UserBean userDto){
		
		userDto.setCreatedDate(Utilities.getCurrentTimestamp());
		userDto.setModifiedDate(Utilities.getCurrentTimestamp());
		userDto.setCreatedByUserId("test");
		userDto.setModifiedByUserId("test");
		
		userDto.getEmail().setCreatedDate(Utilities.getCurrentTimestamp());
		userDto.getEmail().setModifiedDate(Utilities.getCurrentTimestamp());
		userDto.getEmail().setCreatedByUserId("test");
		userDto.getEmail().setModifiedByUserId("test");
		
	}
	
	private void sendRegistrationConfirmationEmail(String emailAddress, String token) throws EmailException {
		
		SendEmail sendEmail = new SendEmail();
		sendEmail.sendEmail(emailAddress,token,"REGISTER");
	}
	
	public boolean registrationActivation(String emailAddress, String token){
		
		boolean registrationActivationSuccess = false;
		
		if(Utilities.validateTokenWithEmail(emailAddress, token)){
			registrationActivationSuccess = registerDAOImpl.updateRegistrationActivation(emailAddress, token);
		}
		return registrationActivationSuccess;
	}
	
	
	public boolean checkDuplicateEmail(String emailAddress) {
		
		List<EmailBean> emailBean = emailDAOImpl.getEmailDetail(emailAddress);
		if(emailBean != null && emailBean.size() > 0 ){
			return true;
		}
		return false;
	}

	public RegisterDAOImpl getRegisterDAOImpl() {
		return registerDAOImpl;
	}

	public void setRegisterDAOImpl(RegisterDAOImpl registerDAOImpl) {
		this.registerDAOImpl = registerDAOImpl;
	}

	public TokenVerificationDAOImpl getTokenVerificationDAOImpl() {
		return tokenVerificationDAOImpl;
	}

	public void setTokenVerificationDAOImpl(
			TokenVerificationDAOImpl tokenVerificationDAOImpl) {
		this.tokenVerificationDAOImpl = tokenVerificationDAOImpl;
	}

	public EmailDAOImpl getEmailDAOImpl() {
		return emailDAOImpl;
	}

	public void setEmailDAOImpl(EmailDAOImpl emailDAOImpl) {
		this.emailDAOImpl = emailDAOImpl;
	}
}
