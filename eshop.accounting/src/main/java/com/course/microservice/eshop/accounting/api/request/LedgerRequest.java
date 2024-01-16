package com.course.microservice.eshop.accounting.api.request;

public class LedgerRequest {

	private double debitAmount;

	private double creditAmount;

	private String currency;

	private String chartOfAccount;

	private String note;

	public double getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(double debitAmount) {
		this.debitAmount = debitAmount;
	}

	public double getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getChartOfAccount() {
		return chartOfAccount;
	}

	public void setChartOfAccount(String chartOfAccount) {
		this.chartOfAccount = chartOfAccount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
