package com.course.microservice.googlecloud.api.response;

public class ErrorMessage {

	private String message;

	private String reccomendation;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getReccomendation() {
		return reccomendation;
	}

	public void setReccomendation(String reccomendation) {
		this.reccomendation = reccomendation;
	}

}
