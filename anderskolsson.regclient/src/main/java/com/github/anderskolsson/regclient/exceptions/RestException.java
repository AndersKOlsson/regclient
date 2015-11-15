package com.github.anderskolsson.regclient.exceptions;

public class RestException extends Exception {
	private static final long serialVersionUID = 2036066033138447837L;

	public RestException(final String message) {
		super(message);
	}
	
	public RestException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	public RestException(final Throwable cause) {
		super(cause);
	}
}
