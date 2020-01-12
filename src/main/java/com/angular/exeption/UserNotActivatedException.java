package com.angular.exeption;

public class UserNotActivatedException extends RuntimeException {

	private static final long serialVersionUID = 4936273643133915289L;

	public UserNotActivatedException() {
		super();
	}

	public UserNotActivatedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserNotActivatedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotActivatedException(String message) {
		super(message);
	}

	public UserNotActivatedException(Throwable cause) {
		super(cause);
	}

	
}
