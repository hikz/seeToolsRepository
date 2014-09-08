package com.seetools.presentation.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.seetools.util.Utilities;

@FacesValidator("EmailValidator")
public class EmailValidator implements Validator {

	
	
	public EmailValidator(){
		  
	}
	
	@Override
	public void validate(FacesContext facesContext, UIComponent uiComponent, Object object)
			throws ValidatorException {
		
		if(Utilities.validateEmail(object.toString())){
			Messages.addValidationMessage("Error: Invalid Email Address !!", FacesMessage.SEVERITY_ERROR);
		}
	}

}
