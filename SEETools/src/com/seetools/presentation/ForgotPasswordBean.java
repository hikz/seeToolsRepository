package com.seetools.presentation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seetools.businesslayer.ForgotPasswordServiceImpl;

@ManagedBean(name="forgotPasswordBean")
@RequestScoped
public class ForgotPasswordBean {

	final Logger logger = LoggerFactory.getLogger(ForgotPasswordBean.class);
	private String emailAddress;
	ForgotPasswordServiceImpl forgotPasswordServiceImpl;
	
	public String getEmailAddress() {
		return emailAddress;
	}


	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}


	public String recoverPassword(){
		
		logger.info("Recovering Password for Email Address : {}", emailAddress);
		String outcome = "forgotPasswordEmailSentSuccess";
		forgotPasswordServiceImpl.recoverPassword(this.getEmailAddress());
		return outcome;
	}


	public ForgotPasswordServiceImpl getForgotPasswordServiceImpl() {
		return forgotPasswordServiceImpl;
	}


	public void setForgotPasswordServiceImpl(
			ForgotPasswordServiceImpl forgotPasswordServiceImpl) {
		this.forgotPasswordServiceImpl = forgotPasswordServiceImpl;
	}
}
