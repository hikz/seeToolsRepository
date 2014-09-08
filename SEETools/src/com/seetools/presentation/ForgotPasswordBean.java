package com.seetools.presentation;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seetools.businesslayer.ForgotPasswordServiceImpl;
import com.seetools.framework.exceptions.EmailException;
import com.seetools.presentation.validation.Messages;

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
		FacesMessage doneMessage = null;
		String result = "";
		try {
			forgotPasswordServiceImpl.recoverPassword(this.getEmailAddress());
			 String text ="A password recovery email had been sent to your email address. Please click on the link to recover your password..";
			  doneMessage = new FacesMessage(text);
			  doneMessage.setSeverity(FacesMessage.SEVERITY_INFO);
			  result =  "forgotPasswordEmailSentSuccess";
		} catch (EmailException e) {
			logger.error(e.getMessage());
			String errorText = "There is an error while sending email. Please click here to re-send.";
	    	doneMessage = new FacesMessage(errorText);
	    	doneMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			result = "forgotPasswordEmailSentFailure.xhtml";
		}
		
		  FacesContext.getCurrentInstance().addMessage(null, doneMessage);
		  return result;
	}


	public ForgotPasswordServiceImpl getForgotPasswordServiceImpl() {
		return forgotPasswordServiceImpl;
	}


	public void setForgotPasswordServiceImpl(
			ForgotPasswordServiceImpl forgotPasswordServiceImpl) {
		this.forgotPasswordServiceImpl = forgotPasswordServiceImpl;
	}
}
