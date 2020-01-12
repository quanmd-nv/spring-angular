package com.angular.exeption;

public class PostException extends RuntimeException {

	private static final long serialVersionUID = -242197339167455431L;

	public PostException() {
		super();
	}

	public PostException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PostException(String message, Throwable cause) {
		super(message, cause);
	}

	public PostException(String message) {
		super(message);
	}

	public PostException(Throwable cause) {
		super(cause);
	}

	
}
