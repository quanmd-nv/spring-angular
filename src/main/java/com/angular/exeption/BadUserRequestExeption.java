package com.angular.exeption;

public class BadUserRequestExeption extends RuntimeException{

	private static final long serialVersionUID = -2011173586805130159L;

	public BadUserRequestExeption() {
		super();
	}

	public BadUserRequestExeption(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BadUserRequestExeption(String message, Throwable cause) {
		super(message, cause);
	}

	public BadUserRequestExeption(String message) {
		super(message);
	}

	public BadUserRequestExeption(Throwable cause) {
		super(cause);
	}

}
