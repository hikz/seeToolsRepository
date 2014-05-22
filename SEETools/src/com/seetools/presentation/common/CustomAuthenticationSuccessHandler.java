package com.seetools.presentation.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;

public class CustomAuthenticationSuccessHandler extends 
		SavedRequestAwareAuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		
		System.out.println("Inside authn handler");
		// String ctoken = (String) request.getSession().getAttribute(WebConstants.CSRF_TOKEN);
	        DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST_KEY");
	        System.out.println("Query String-"+defaultSavedRequest.getQueryString());
	        System.out.println("Query String2"+defaultSavedRequest.getRedirectUrl());
	        /*if( defaultSavedRequest != null ) {
	            String requestUrl = defaultSavedRequest.getRequestURL() + "?" + defaultSavedRequest.getQueryString();
	            getRedirectStrategy().sendRedirect(request, response, requestUrl);
	        } else {
	            super.onAuthenticationSuccess(request, response, authentication);
	        }*/
	        
	        
	}
	
	

}
