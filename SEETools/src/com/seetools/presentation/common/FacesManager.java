package com.seetools.presentation.common;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class FacesManager {

	public static ExternalContext getExternalContext(){
		return getFacesContext().getExternalContext();
	}
	
	public static FacesContext getFacesContext(){
		return FacesContext.getCurrentInstance();
	}
}
