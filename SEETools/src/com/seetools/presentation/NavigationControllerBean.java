package com.seetools.presentation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="navigator")
@SessionScoped
public class NavigationControllerBean {

	private String navigateTo;
	private String navigate;
	
	public String navigate(){
		System.out.println("Inside navigate controller");
		String parameterName =  FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("fromTool");
		System.out.println("Next Page : " + parameterName);
		//return "hipConverter";
		return parameterName;	
		
	}

	public String getNavigateTo() {
		return navigateTo;
	}

	public void setNavigateTo(String navigateTo) {
		this.navigateTo = navigateTo;
	}

	public String getNavigate() {
		return navigate;
	}

	public void setNavigate(String navigate) {
		this.navigate = navigate;
	}


	
}
