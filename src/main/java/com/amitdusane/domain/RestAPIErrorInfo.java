package com.amitdusane.domain;

public class RestAPIErrorInfo {
	
	private final String errorDetail;
	private final String errorMessage;
	
	public RestAPIErrorInfo(Exception ex, String errorDetail) {
		super();
		this.errorDetail = errorDetail;
		this.errorMessage = ex.getLocalizedMessage();
	}
	
	public String getErrorDetail() {
		return errorDetail;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	
	

}
