package com.checkout.management.model.response;

public class ResponseModel {
	private Boolean status;
	private String message;
	private Object data;
	private int errorCode;

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public ResponseModel(Boolean status, String message, Object data, int errorCode) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
		this.errorCode = errorCode;
	}
}
