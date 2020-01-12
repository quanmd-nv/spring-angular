package com.angular.exeption;

public class EntryNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5807010424080465993L;

	public EntryNotFoundException() {
		super();
	}

	public EntryNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EntryNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntryNotFoundException(String message) {
		super(message);
	}

	public EntryNotFoundException(Throwable cause) {
		super(cause);
	}

	
}
