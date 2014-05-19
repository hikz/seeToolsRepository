package com.seetools.presentation.validation;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.validator.ValidatorException;

public class Messages {

	public static void addMessage(String message, Severity severity) throws ValidatorException {
		FacesMessage facesMessage = new FacesMessage(message);
		facesMessage.setSeverity(severity);
        throw new ValidatorException(facesMessage);
	}
}
