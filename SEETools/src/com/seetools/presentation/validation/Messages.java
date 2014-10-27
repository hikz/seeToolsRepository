package com.seetools.presentation.validation;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

public class Messages {

	public static void addValidationMessage(String message, Severity severity) throws ValidatorException {
		FacesMessage facesMessage = new FacesMessage(message);
		facesMessage.setSeverity(severity);
        throw new ValidatorException(facesMessage);
	}
	
	public static void addMessageWithSeverity(String message, Severity severity) throws ValidatorException {
		
		// Add View Faces Message
		FacesMessage facesMessage = new FacesMessage(severity,message, message);
		// The component id is null, so this message is considered as a view message
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
}
