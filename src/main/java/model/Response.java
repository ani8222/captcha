package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import utility.ServiceUtility;


public class Response {

	public static final String SUCCESS_CODE = "0";
	public static final String SUCCESS = "SUCCESS";
	public static final String FAIL = "FAIL";

	private String errorCode;
	private String errorDescription;
	private String status;

	public Response(String errorCode, String errorDescription) {
		this.status = SUCCESS_CODE.equals(errorCode) ? Response.SUCCESS : Response.FAIL;
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}

	public String getErrorCode() {
		return ServiceUtility.notNull(errorCode);
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDescription() {
		return ServiceUtility.notNull(errorDescription);
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	
	public String getStatus() {
		return ServiceUtility.notNull(status);
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonIgnore
	public boolean isSuccess() {
		return SUCCESS_CODE.equals(getErrorCode()) ? true : false; 
	}
}