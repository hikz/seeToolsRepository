package com.seetools.presentation;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.seetools.businesslayer.ForgotPasswordServiceImpl;

@ManagedBean(name="modifyPassword")
@RequestScoped
public class ModifyPassword {

	private String password;
	ForgotPasswordServiceImpl forgotPasswordServiceImpl;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String modifyPasswordService(){
		
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("seetools-bean-config.xml");
		
		forgotPasswordServiceImpl = (ForgotPasswordServiceImpl)applicationContext.getBean("forgotPasswordService");
		
		forgotPasswordServiceImpl.changePassword("ramprasad.pedapatnam@gmail.com", this.getPassword());
		return "changePasswordSuccess";
	}


}
