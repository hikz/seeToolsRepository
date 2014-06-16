package com.seetools.presentation.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.seetools.businesslayer.SeeToolsRegisterServiceImpl;
import com.seetools.util.BeanFactory;
import com.seetools.util.Utilities;

@FacesValidator("RegistrationEmailValidator")
public class RegistrationEmailValidator implements Validator {

	
	public RegistrationEmailValidator() {
		
	}

	/**
	@Autowired
	private SeeToolsRegisterServiceImpl seeToolsRegisterServiceImpl;
	*/
	
	@Override
	public void validate(FacesContext facesContext, UIComponent uiComponent, Object object)
			throws ValidatorException {
		
		if(Utilities.validateEmail(object.toString())){
			Messages.addMessage("Error: Invalid Email Address !!", FacesMessage.SEVERITY_ERROR);
		}
		
		if(((SeeToolsRegisterServiceImpl)BeanFactory.getBean("seeToolsRegisterServiceImpl")).checkDuplicateEmail(object.toString())){
			Messages.addMessage("Error: Duplicate Email Address. Please change email address !!", FacesMessage.SEVERITY_ERROR);
		}
		
	}

 
	
}
