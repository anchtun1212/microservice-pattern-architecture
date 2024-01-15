package com.course.microservice.googlecloud.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MockbinResponse {

	private String code;
	private String message;
	private long delay;

	public String getCode() {
		return code;
	}

	public long getDelay() {
		return delay;
	}

	public String getMessage() {
		return message;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
