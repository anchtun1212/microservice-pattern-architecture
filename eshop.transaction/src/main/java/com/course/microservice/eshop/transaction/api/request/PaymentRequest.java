package com.course.microservice.eshop.transaction.api.request;

import java.time.LocalDateTime;

public class PaymentRequest {

	private double paymentAmount;

	private String currency;

	private LocalDateTime paymentDateTime;

	private String note;

	public String getCurrency() {
		return currency;
	}

	public String getNote() {
		return note;
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public LocalDateTime getPaymentDateTime() {
		return paymentDateTime;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public void setPaymentDateTime(LocalDateTime paymentDateTime) {
		this.paymentDateTime = paymentDateTime;
	}

}
