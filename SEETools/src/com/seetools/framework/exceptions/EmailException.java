package com.seetools.framework.exceptions;

public class EmailException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public EmailException() {

	}

	public EmailException(String arg0) {
		super(arg0);
	}

	public EmailException(Throwable arg0) {
		super(arg0);
	}

	public EmailException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public EmailException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}


	@Override
	public String getMessage() {
		return "Exception While user registration is going on";
	}
}
