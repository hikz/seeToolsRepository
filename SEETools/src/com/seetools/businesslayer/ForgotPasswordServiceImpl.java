package com.seetools.businesslayer;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seetools.daolayer.EmailDAOImpl;
import com.seetools.daolayer.LoginDAOImpl;
import com.seetools.daolayer.TokenVerificationDAOImpl;
import com.seetools.dto.EmailBean;
import com.seetools.framework.exceptions.EmailException;
import com.seetools.util.SendEmail;
import com.seetools.util.Utilities;

public class ForgotPasswordServiceImpl {

	final Logger logger = LoggerFactory.getLogger(ForgotPasswordServiceImpl.class);
	
	TokenVerificationDAOImpl tokenVerificationDAO;
	LoginDAOImpl loginDAOImpl;
	EmailDAOImpl emailDAO;
	
	/**
	 * This method would save the password recovery activation link and send confirmation mail to user.
	 * @param emailAddress
	 */
	public void recoverPassword(String emailAddress) throws EmailException {
		
		String token = UUID.randomUUID().toString();
		
		//Save the token in database with email address.
		List<EmailBean> emails = emailDAO.getEmailDetail(emailAddress);
		if(emails != null && emails.size() == 1) {			
			tokenVerificationDAO.saveAccountActivationToken(emails.get(0).getEmailID(), token);
		}
		//Send Registration confirmation email.
		this.sendRecoverPasswordConfirmationEmail(emailAddress, token);
		logger.info("Sent Password Recovery Confirmation Mail to : {}", emailAddress);
	}
	
	/**
	 * This method would verify token and email address validity.
	 * @param emailAddress
	 * @param token
	 * @return
	 */
	public boolean checkToken(String emailAddress, String token){
		
		logger.info("Checking Validity for Email Address : {} and Token : {}", emailAddress, token);
		return Utilities.validateTokenWithEmail(emailAddress, token);
		
	}
	
	public boolean changePassword(String emailAddress, String password) {
		
		return loginDAOImpl.updatePassword(emailAddress, password);
		
	}
	
	
	private void sendRecoverPasswordConfirmationEmail(String emailAddress, String token) throws EmailException {
		logger.info("Sending Password Recovery Confirmation Mail to Email Address : {}", emailAddress);
		SendEmail sendEmail = new SendEmail();
		sendEmail.sendEmail(emailAddress,token,"FORGOT_PASSWORD");
	}

	public TokenVerificationDAOImpl getTokenVerificationDAO() {
		return tokenVerificationDAO;
	}

	public void setTokenVerificationDAO(
			TokenVerificationDAOImpl tokenVerificationDAO) {
		this.tokenVerificationDAO = tokenVerificationDAO;
	}

	public EmailDAOImpl getEmailDAO() {
		return emailDAO;
	}

	public void setEmailDAO(EmailDAOImpl emailDAO) {
		this.emailDAO = emailDAO;
	}
	
	public LoginDAOImpl getLoginDAOImpl() {
		return loginDAOImpl;
	}

	public void setLoginDAOImpl(LoginDAOImpl loginDAOImpl) {
		this.loginDAOImpl = loginDAOImpl;
	}

}
