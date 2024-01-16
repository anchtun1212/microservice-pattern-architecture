package com.course.microservice.eshop.transaction.api.server;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.microservice.eshop.transaction.api.client.EshopPaymentClient;
import com.course.microservice.eshop.transaction.api.request.CheckoutRequest;
import com.course.microservice.eshop.transaction.api.request.PaymentRequest;
import com.course.microservice.eshop.transaction.api.response.CheckoutResponse;

@RestController
@RequestMapping("/api/eshop/transaction")
public class EshopTransactionApi {

	@Autowired
	private EshopPaymentClient eshopPaymentClient;

	@PostMapping(value = "/checkout", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CheckoutResponse> checkout(@RequestBody CheckoutRequest checkoutRequest) {
		var paymentRequest = new PaymentRequest();
		paymentRequest.setCurrency(checkoutRequest.getCurrency());
		paymentRequest.setNote("Transaction for username : " + checkoutRequest.getUsername());
		paymentRequest.setPaymentAmount(checkoutRequest.getAmount());
		paymentRequest.setPaymentDateTime(LocalDateTime.now());

		var paymentResponse = eshopPaymentClient.payment(paymentRequest);

		var response = new CheckoutResponse();
		var message = String.format("Checkout succeed with payment ID : %s", paymentResponse.getBody().getPaymentId());
		response.setMessage(message);
		response.setTransactionId(UUID.randomUUID());

		return ResponseEntity.ok().body(response);
	}

}
