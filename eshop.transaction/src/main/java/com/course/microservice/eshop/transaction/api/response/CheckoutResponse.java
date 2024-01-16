package com.course.microservice.eshop.transaction.api.response;

import java.util.UUID;

public class CheckoutResponse {

	private UUID transactionId;
	private String message;

	public String getMessage() {
		return message;
	}

	public UUID getTransactionId() {
		return transactionId;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setTransactionId(UUID transactionId) {
		this.transactionId = transactionId;
	}

}
