package com.course.microservice.eshop.payment.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.course.microservice.eshop.payment.api.request.LedgerRequest;
import com.course.microservice.eshop.payment.api.response.LedgerResponse;

@FeignClient(name = "eshopAccountingClient", url = "${eshop.accounting.url:http://localhost:9999}")
public interface EshopAccountingClient {

	@PostMapping(value = "/api/eshop/accounting/ledger", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<LedgerResponse> ledger(@RequestBody LedgerRequest ledgerRequest);

}
