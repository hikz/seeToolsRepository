package com.seetools.presentation;


import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlInputHidden;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seetools.businesslayer.ForgotPasswordServiceImpl;

@ManagedBean(name="modifyPasswordBean")
@RequestScoped
public class ModifyPasswordBean implements Serializable {

	final Logger logger = LoggerFactory.getLogger(ModifyPasswordBean.class);
	private static final long serialVersionUID = 2L;
	private String password;
	private HtmlInputHidden email = new HtmlInputHidden();
	ForgotPasswordServiceImpl forgotPasswordServiceImpl;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String modifyPassword(){
		
		logger.info("Changing Password for email : {}", email.getValue());
		forgotPasswordServiceImpl.changePassword((String)email.getValue(), this.getPassword());	
		logger.info("Password changed successfully for email : {}",  email.getValue());
		return "changePasswordSuccess";
	}


	public void setEmail(HtmlInputHidden email) {
		this.email = email;
	}

	public HtmlInputHidden getEmail() {
		return email;
	}

	public ForgotPasswordServiceImpl getForgotPasswordServiceImpl() {
		return forgotPasswordServiceImpl;
	}

	public void setForgotPasswordServiceImpl(
			ForgotPasswordServiceImpl forgotPasswordServiceImpl) {
		this.forgotPasswordServiceImpl = forgotPasswordServiceImpl;
	}


}
