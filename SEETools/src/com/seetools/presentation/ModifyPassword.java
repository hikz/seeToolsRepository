package com.seetools.presentation;


import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlInputHidden;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.seetools.businesslayer.ForgotPasswordServiceImpl;

@ManagedBean(name="modifyPassword")
@RequestScoped
public class ModifyPassword implements Serializable {

	/**
	 * 
	 */
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

	public String modifyPasswordService(){
		
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("seetools-bean-config.xml");
		email.getValue();
		forgotPasswordServiceImpl = (ForgotPasswordServiceImpl)applicationContext.getBean("forgotPasswordService");
		
		forgotPasswordServiceImpl.changePassword((String)email.getValue(), this.getPassword());
		
		return "changePasswordSuccess";
	}


	public void setEmail(HtmlInputHidden email) {
		this.email = email;
	}

	public HtmlInputHidden getEmail() {
		return email;
	}


}
