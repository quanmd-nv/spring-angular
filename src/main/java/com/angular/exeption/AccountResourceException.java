package com.angular.exeption;

public class AccountResourceException extends RuntimeException {

	private static final long serialVersionUID = -6229317703163457705L;

	public AccountResourceException() {
		super();
	}

	public AccountResourceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AccountResourceException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccountResourceException(String message) {
		super(message);
	}

	public AccountResourceException(Throwable cause) {
		super(cause);
	}

	
}
