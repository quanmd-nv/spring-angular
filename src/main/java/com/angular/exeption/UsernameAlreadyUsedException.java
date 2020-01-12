package com.angular.exeption;

public class UsernameAlreadyUsedException extends RuntimeException {

	private static final long serialVersionUID = 3691626723775592873L;

	public UsernameAlreadyUsedException() {
		super();
	}

	public UsernameAlreadyUsedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UsernameAlreadyUsedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsernameAlreadyUsedException(String message) {
		super(message);
	}

	public UsernameAlreadyUsedException(Throwable cause) {
		super(cause);
	}

	
}
