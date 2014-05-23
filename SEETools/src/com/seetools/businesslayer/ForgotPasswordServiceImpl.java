package com.seetools.businesslayer;

import java.util.List;
import java.util.UUID;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.seetools.daolayer.EmailDAOImpl;
import com.seetools.daolayer.LoginDAOImpl;
import com.seetools.daolayer.RegisterDAOImpl;
import com.seetools.daolayer.TokenVerificationDAOImpl;
import com.seetools.dto.AccountActivationTokenBean;
import com.seetools.dto.EmailBean;
import com.seetools.util.SendEmail;
import com.seetools.util.Utilities;

public class ForgotPasswordServiceImpl {

	TokenVerificationDAOImpl tokenVerificationDAO;
	LoginDAOImpl loginDAOImpl;
	
	EmailDAOImpl emailDAO;
	
	
	public void recoverPassword(String emailAddress){
		
		String token = UUID.randomUUID().toString();
		//Save the token in database with email address.
		
		List<EmailBean> emails = emailDAO.getEmailDetail(emailAddress);
		
		if(emails != null && emails.size() == 1) {
			tokenVerificationDAO.saveAccountActivationToken(emails.get(0).getEmailID(), token);
		}
		
		//Send Registration confirmation email.
		this.sendRecoverPasswordConfirmationEmail(emailAddress, token);
		
	}
	
	public boolean checkToken(String emailAddress, String token){
		
		return Utilities.validateTokenWithEmail(emailAddress, token);
		
	}
	
	public boolean changePassword(String emailAddress, String password) {
		
		return loginDAOImpl.updatePassword(emailAddress, password);
	}
	
	
	public LoginDAOImpl getLoginDAOImpl() {
		return loginDAOImpl;
	}

	public void setLoginDAOImpl(LoginDAOImpl loginDAOImpl) {
		this.loginDAOImpl = loginDAOImpl;
	}

	private void sendRecoverPasswordConfirmationEmail(String emailAddress, String token) {
		
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
	
	

}
