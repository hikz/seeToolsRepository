package com.seetools.presentation.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.seetools.util.Utilities;

@FacesValidator("PasswordValidator")
public class PasswordValidator  implements Validator {

	
	@Override
	public void validate(FacesContext facesContext, UIComponent uiComponent, Object object)
			throws ValidatorException {
	
		
		if(Utilities.validatePassword(object.toString())){
			Messages.addMessage("Error: Password Requirements - One lowercase, One uppercase, Special Characters allowed are @#$%, Minimum Length is 6, Maximum Length is 20", FacesMessage.SEVERITY_ERROR);
		}
	}

}
