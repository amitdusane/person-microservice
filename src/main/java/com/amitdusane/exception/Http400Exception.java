package com.amitdusane.exception;

public class Http400Exception extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Http400Exception() {
		super();
	}
	
	public Http400Exception(String message, Throwable cause){
		super(message, cause);
	}
	
	public Http400Exception(String message) {
		super(message);
	}
	
	public Http400Exception(Throwable cause) {
		super(cause);
	}
	
}
