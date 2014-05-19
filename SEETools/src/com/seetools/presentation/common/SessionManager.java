package com.seetools.presentation.common;

import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class SessionManager {

	
	public static void addSessionAttribute(String key, Object value) {
		
		getSessionMap().put(key, value);
		
	}
	
	public static Object getSessionAttribute(String key){
		return getSessionMap().get(key);
	}
	
	private static Map<String, Object> getSessionMap(){
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		return externalContext.getSessionMap();
	}		
	
	public static void logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
	
	public static void invalidateSession(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
}
