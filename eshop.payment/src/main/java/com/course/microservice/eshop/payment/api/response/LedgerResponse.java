package com.course.microservice.eshop.payment.api.response;

import java.util.UUID;

public class LedgerResponse {

	private UUID ledgerId;

	private String message;

	public UUID getLedgerId() {
		return ledgerId;
	}

	public void setLedgerId(UUID ledgerId) {
		this.ledgerId = ledgerId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
