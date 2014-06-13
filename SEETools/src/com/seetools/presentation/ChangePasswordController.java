package com.seetools.presentation;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seetools.businesslayer.ForgotPasswordServiceImpl;
import com.seetools.util.BeanFactory;

@ManagedBean(name="changePasswordController")
@SessionScoped
public class ChangePasswordController {
	

	private String key;
	
	private String email;
	
	private String password;
	
    private boolean valid;

    ForgotPasswordServiceImpl forgotPasswordServiceImpl;
    final Logger logger = LoggerFactory.getLogger(ChangePasswordController.class);
  
	public boolean check() throws IOException {
		
		boolean validToken = false;
		
		forgotPasswordServiceImpl = (ForgotPasswordServiceImpl)BeanFactory.getBean("forgotPasswordService");
		
		logger.info("Enter change password token validation");
		logger.debug("Email: {} , Token: {}", this.email, this.key);
		if(this.email != null && this.key != null){
			if(forgotPasswordServiceImpl.checkToken(this.email, this.key)){
				validToken = true;
				logger.info("Token is valid. Redirecting to Change Password Form");

				System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRequestMap().toString());
				FacesContext.getCurrentInstance().getExternalContext().redirect("changePasswordForm.xhtml");
			}
		}
		
		return validToken;
	}
	
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
