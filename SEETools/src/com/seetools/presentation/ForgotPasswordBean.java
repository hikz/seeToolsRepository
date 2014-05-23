package com.seetools.presentation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.seetools.businesslayer.ForgotPasswordServiceImpl;
import com.seetools.daolayer.RegisterDAOImpl;

@ManagedBean(name="forgotPasswordBean")
@RequestScoped
public class ForgotPasswordBean {

	private String emailAddress;
	ForgotPasswordServiceImpl forgotPasswordServiceImpl;
	
	public String getEmailAddress() {
		return emailAddress;
	}


	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}


	public String recoverPassword(){
		
		String outcome = "forgotPasswordEmailSentSuccess";
		
		/*ForgotPasswordServiceImpl forgotPasswordServiceImpl = new ForgotPasswordServiceImpl();
		forgotPasswordServiceImpl.recoverPassword(this.emailAddress);*/
		
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("seetools-bean-config.xml");
		
		forgotPasswordServiceImpl = (ForgotPasswordServiceImpl)applicationContext.getBean("forgotPasswordService");
		forgotPasswordServiceImpl.recoverPassword(this.getEmailAddress());
		
		return outcome;
	}
}
