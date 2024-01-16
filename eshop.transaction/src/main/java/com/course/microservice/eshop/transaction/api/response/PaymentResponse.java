package com.course.microservice.eshop.transaction.api.response;

import java.util.UUID;

public class PaymentResponse {

	private UUID paymentId;

	private String message;

	public UUID getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(UUID paymentId) {
		this.paymentId = paymentId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
