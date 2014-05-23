package com.seetools.presentation;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.seetools.businesslayer.SeeToolsRegisterServiceImpl;

@ManagedBean(name="registrationActivation")
@RequestScoped
public class RegistrationActivation {
	
	@ManagedProperty(value="#{param.token}")
    private String key;
	@ManagedProperty(value="#{param.email}")
	private String email;
    private boolean valid;

    @PostConstruct
    public void init() {
       valid = (check(getEmail(),getKey())); // And auto-login if valid?
    }

	public boolean check(String emailAddress,String token){
		
		boolean validToken = false;
		//String token = (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("token");
		if(new SeeToolsRegisterServiceImpl().registrationActivation(emailAddress, token)){
			validToken = true;
		}
		
		System.out.println("Token : " + token);
		
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
