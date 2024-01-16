package com.course.microservice.eshop.transaction.api.response;

public class PlainMessage {

	private String message;

	public PlainMessage() {

	}

	public PlainMessage(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
