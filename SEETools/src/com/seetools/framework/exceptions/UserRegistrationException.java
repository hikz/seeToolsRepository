package com.seetools.framework.exceptions;

public class UserRegistrationException extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Exception While user registration is going on";
	}
	
}
