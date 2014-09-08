package com.seetools.presentation;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.seetools.businesslayer.SeeToolsRegisterServiceImpl;
import com.seetools.dto.UserBean;
import com.seetools.framework.exceptions.EmailException;
import com.seetools.presentation.common.SessionManager;
import com.seetools.presentation.validation.Messages;

@ManagedBean(name="registerBean")
@RequestScoped
public class RegisterBean implements Serializable {

	final Logger logger = LoggerFactory.getLogger(RegisterBean.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 @Autowired @Qualifier("authMgr") 
	 private AuthenticationManager authMgr;
	 @Autowired 
	 private UserDetailsService userDetailsSvc;
	 SeeToolsRegisterServiceImpl seeToolsRegisterServiceImpl;
	 
	 @ManagedProperty(value="#{userBean}")
	 private UserBean userBean;
	 
	 
	/*public String confirmRegistration(){

		System.out.println("Logger Info enabled: " + logger.isInfoEnabled());
		logger.info("Confirming registration");
		this.setUser((UserBean)RequestManager.getRequestAttribute("user"));
		logger.debug("User Details : {}", this.getUser().toString());
		RequestManager.addRequestAttribute("user",this.userBean);
		return "confirmRegistration";
	}*/
	
	
	public String register(){
		
		logger.info("Start registration process");
		System.out.println("Log Info level : " + logger.isInfoEnabled());
		System.out.println("Log Debug level : " + logger.isDebugEnabled());
		System.out.println("Log Warn level : " + logger.isWarnEnabled());
		
		//SeeToolsRegisterServiceImpl seeToolsRegisterServiceImpl = (SeeToolsRegisterServiceImpl)BeanFactory.getBean("seeToolsRegisterServiceImpl");
		this.setUser((UserBean)SessionManager.getSessionAttribute("userBean"));
		logger.debug("User Details : {}", this.getUser().toString());
		SessionManager.invalidateSession();
		String result = "";
		FacesMessage doneMessage = null;
		try {
			  this.seeToolsRegisterServiceImpl.processRegistration(this.getUser());
			  logger.info("End registration process");
			  String text = "Congratulations !!!Registration Successful. A registration email had been sent to your email address. Please click on the link to activate your account.";
			  doneMessage = new FacesMessage(text);
			  doneMessage.setSeverity(FacesMessage.SEVERITY_INFO);
			  result = "registrationSuccess";
		    } catch (EmailException e) {
		    	logger.error(e.getMessage());
		    	String errorText = "There is an error while sending email. Please click here to re-send.";
		    	doneMessage = new FacesMessage(errorText);
		    	doneMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		    	result =  "registrationFailure";
		    }
		   FacesContext.getCurrentInstance().addMessage(null, doneMessage);
		   return result;

	}
	public AuthenticationManager getAuthMgr() {
		return authMgr;
	}
	public void setAuthMgr(AuthenticationManager authMgr) {
		this.authMgr = authMgr;
	}
	public UserDetailsService getUserDetailsSvc() {
		return userDetailsSvc;
	}
	public void setUserDetailsSvc(UserDetailsService userDetailsSvc) {
		this.userDetailsSvc = userDetailsSvc;
	}


	public UserBean getUser() {
		return userBean;
	}

	public void setUser(UserBean userBean) {
		this.userBean = userBean;
	}
	public SeeToolsRegisterServiceImpl getSeeToolsRegisterServiceImpl() {
		return seeToolsRegisterServiceImpl;
	}
	public void setSeeToolsRegisterServiceImpl(
			SeeToolsRegisterServiceImpl seeToolsRegisterServiceImpl) {
		this.seeToolsRegisterServiceImpl = seeToolsRegisterServiceImpl;
	}
	
}
