package com.course.microservice.eshop.payment.api.server;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.microservice.eshop.payment.api.client.EshopAccountingClient;
import com.course.microservice.eshop.payment.api.request.LedgerRequest;
import com.course.microservice.eshop.payment.api.request.PaymentRequest;
import com.course.microservice.eshop.payment.api.response.PaymentResponse;

@RestController
@RequestMapping("/api/eshop/payment")
public class EshopPaymentApi {

	@Autowired
	private EshopAccountingClient eshopAccountingClient;

	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PaymentResponse> payment(@RequestBody PaymentRequest paymentRequest) {
		var paymentId = UUID.randomUUID();

		var ledgerRequest = new LedgerRequest();
		ledgerRequest.setChartOfAccount("Dummy CoA : " + ThreadLocalRandom.current().nextInt(1000, 10000));
		ledgerRequest.setCurrency(paymentRequest.getCurrency());
		ledgerRequest.setCreditAmount(paymentRequest.getPaymentAmount());
		ledgerRequest.setNote("Payment ID : " + paymentId);

		var ledgerResponse = eshopAccountingClient.ledger(ledgerRequest);

		var paymentResponse = new PaymentResponse();
		paymentResponse.setPaymentId(paymentId);
		paymentResponse.setMessage("Payment done, created ledger ID : " + ledgerResponse.getBody().getLedgerId());

		return ResponseEntity.ok().body(paymentResponse);
	}

}