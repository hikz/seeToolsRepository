package com.seetools.presentation;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable, UserDetails {

	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
	private String firstName;
	
	@ManagedProperty(value="#{authenticationManager}")
    private AuthenticationManager authenticationManager = null;
	
	 @ManagedProperty(value="#{userDetailsService}")
	    private UserDetailsService userDetailsService = null;
	
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String login() throws IOException, ServletException {
		String loginNavigation = "";
		 try {
			 
			 /*	Authentication result = null;
	            Authentication request = new UsernamePasswordAuthenticationToken(this.getName(), this.getPassword());
	            result = authenticationManager.authenticate(request);
	            //SEcurity Context holds the session information required between requests.
	            SecurityContextHolder.getContext().setAuthentication(result);
	           
	            loginNavigation = "loginSuccess";*/
	            
	            
	            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

	            RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/xhtml/login/tools_login");

	            System.out.println("session id - " + context.getSessionId(true));
	            System.out.println("session id - " + RequestContextHolder.currentRequestAttributes().getSessionId());
	            dispatcher.forward((ServletRequest) context.getRequest(),
	                    (ServletResponse) context.getResponse());
	            
	            Authentication auth = (Authentication)SecurityContextHolder.getContext().getAuthentication();
	            if(auth != null){
	            	System.out.println(((User)auth.getPrincipal()).getUsername());
		            System.out.println(auth.getName());
		    		this.setFirstName(auth.getName());	
	            }
	            
	            FacesContext.getCurrentInstance().responseComplete();
	            
	            return null;
	        }catch (UsernameNotFoundException unfe) {
	        	FacesMessage badCredentialsMessage = new FacesMessage("Invalid credentials. Please login again");
	        	FacesContext.getCurrentInstance().addMessage(null, badCredentialsMessage);
	        	loginNavigation = "failure";
	        }
		 	catch (BadCredentialsException bce){
	        	FacesMessage badCredentialsMessage = new FacesMessage("Invalid credentials. Please login again");
	        	FacesContext.getCurrentInstance().addMessage(null, badCredentialsMessage);
	        	loginNavigation = "failure";
	        	//bce.printStackTrace();
	        }
		 	catch (AuthenticationException e) {
	            loginNavigation = "failure";
	        	//e.printStackTrace();
	        }
		 return loginNavigation;
	}
	
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }
 
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		
		Authentication auth = (Authentication)SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

}
