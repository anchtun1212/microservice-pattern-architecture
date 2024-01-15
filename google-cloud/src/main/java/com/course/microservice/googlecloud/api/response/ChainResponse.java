package com.course.microservice.googlecloud.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChainResponse {

	private String message;
	private MockbinResponse mockbin;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MockbinResponse getMockbin() {
		return mockbin;
	}

	public void setMockbin(MockbinResponse mockbin) {
		this.mockbin = mockbin;
	}

}
