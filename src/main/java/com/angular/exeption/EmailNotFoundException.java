package com.angular.exeption;

public class EmailNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5807010424080465993L;

	public EmailNotFoundException() {
		super();
	}

	public EmailNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EmailNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmailNotFoundException(String message) {
		super(message);
	}

	public EmailNotFoundException(Throwable cause) {
		super(cause);
	}

	
}
