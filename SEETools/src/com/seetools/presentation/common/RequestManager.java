package com.seetools.presentation.common;

import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class RequestManager {

public static void addRequestAttribute(String key, Object value) {
		
		getRequestMap().put(key, value);
		System.out.println("Set into request manager");
		
	}
	
	public static Object getRequestAttribute(String key){
		return getRequestMap().get(key);
	}
	
	private static Map<String, Object> getRequestMap(){
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		return externalContext.getRequestMap();
	}
	
}
