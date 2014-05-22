package com.seetools.presentation;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.seetools.businesslayer.SeeToolsRegisterServiceImpl;
import com.seetools.dto.UserBean;
import com.seetools.presentation.common.RequestManager;
import com.seetools.presentation.common.SessionManager;

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
		
		SeeToolsRegisterServiceImpl seeToolsRegisterServiceImpl = new SeeToolsRegisterServiceImpl();
		this.setUser((UserBean)SessionManager.getSessionAttribute("userBean"));
		logger.debug("User Details : {}", this.getUser().toString());
		SessionManager.invalidateSession();
		
		
		try {
			  
			  seeToolsRegisterServiceImpl.processRegistration(this.getUser());
		      return "registerSuccess";
		    
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
		logger.info("End registration process");
		    return "redirect:/xhtml/login/error";
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
	
	
	/*private void authenticateUserAndSetSession(UserBean user, HttpServletRequest request) {
		
	    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
	            user.getUserId(), user.getPassword());

	    // generate session if one doesn't exist
	    request.getSession();

	    token.setDetails(new WebAuthenticationDetails(request));
	    Authentication authenticatedUser = authenticationManager.authenticate(token);

	    SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
	}*/
}
