package com.course.microservice.eshop.transaction.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.course.microservice.eshop.transaction.api.request.PaymentRequest;
import com.course.microservice.eshop.transaction.api.response.PaymentResponse;

@FeignClient(name = "eshopPaymentClient", url = "${eshop.payment.url:http://localhost:9999}")
public interface EshopPaymentClient {

	@PostMapping(value = "/api/eshop/payment", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<PaymentResponse> payment(@RequestBody PaymentRequest paymentRequest);

}
