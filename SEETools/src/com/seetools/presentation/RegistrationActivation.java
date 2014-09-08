package com.seetools.presentation;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seetools.businesslayer.SeeToolsRegisterServiceImpl;
import com.seetools.presentation.validation.Messages;
import com.seetools.util.BeanFactory;

@ManagedBean(name="registrationActivation")
@RequestScoped
public class RegistrationActivation {
	
	@ManagedProperty(value="#{param.token}")
    private String key;
	@ManagedProperty(value="#{param.email}")
	private String email;
    private boolean valid;

    final Logger logger = LoggerFactory.getLogger(RegistrationActivation.class);
    
    @PostConstruct
    public void init() {
       FacesMessage doneMessage = null;
       if(check(getEmail(),getKey())) {
    	   valid = true;
    	   String text ="Your account is successfully activated";
		   doneMessage = new FacesMessage(text);
		   doneMessage.setSeverity(FacesMessage.SEVERITY_INFO);
       } else {
    	   String text ="Activation failed! Please enter your email address to try once again.";
		   doneMessage = new FacesMessage(text);
		   doneMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
       }
       FacesContext.getCurrentInstance().addMessage(null, doneMessage);
    }

	public boolean check(String emailAddress,String token){
		
		logger.info("Checking validity of token : {} with email address : {} ", token, emailAddress);		
		boolean validToken = false;
		//String token = (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("token");
		if(((SeeToolsRegisterServiceImpl)BeanFactory.getBean("seeToolsRegisterServiceImpl")).registrationActivation(emailAddress, token)){
			logger.info("Token valid");
			validToken = true;
		}
		logger.info("Validity checking of token - Complete");
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

	
}
