package com.seetools.presentation.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

@FacesValidator("FileUploadValidator")
public class FileUploadValidatort implements Validator {

	@Override
	public void validate(FacesContext facesContext, UIComponent uiComponent, Object obj)
			throws ValidatorException {
		
		 Part part = (Part)obj;
		 
		 //Validating file type
		 if (!"application/vnd.ms-excel".equals(part.getContentType())) {
	            Messages.addValidationMessage("Error: File type is invalid !!", FacesMessage.SEVERITY_ERROR);
	     }
		 
		/* if (part.getSize() > 512) {
	            FacesMessage message = new FacesMessage("Error: File size is too big !!");
	            throw new ValidatorException(message);
	     }*/
		 
		 
		
	}

}
