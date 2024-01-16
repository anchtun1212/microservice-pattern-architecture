package com.course.microservice.eshop.accounting.api.server;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.microservice.eshop.accounting.api.client.EshopMockbinClient;
import com.course.microservice.eshop.accounting.api.request.LedgerRequest;
import com.course.microservice.eshop.accounting.api.response.LedgerResponse;

@RestController
@RequestMapping("/api/eshop/accounting")
public class EshopAccountingApi {

	@Autowired
	private EshopMockbinClient mockbinClient;

	@PostMapping(value = "/ledger", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LedgerResponse> ledger(@RequestBody LedgerRequest ledgerRequest) {
		var response = new LedgerResponse();

		if (ledgerRequest.getCreditAmount() < 0 || ledgerRequest.getDebitAmount() < 0) {
			var message = String.format("Invalid amount (less than 0). Credit : %.2f, debit : %.2f",
					ledgerRequest.getCreditAmount(), ledgerRequest.getDebitAmount());
			response.setMessage(message);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		var mockbinStatus = ThreadLocalRandom.current().nextBoolean() ? HttpStatus.OK.value()
				: HttpStatus.GATEWAY_TIMEOUT.value();
		var mockbinResponse = mockbinClient.status(mockbinStatus, "Dummy mockbin status is " + mockbinStatus);

		var message = String.format("Ledger created. Credit : %.2f, debit : %.2f, mockbin status : %s",
				ledgerRequest.getCreditAmount(), ledgerRequest.getDebitAmount(),
				mockbinResponse.getBody().getMessage());
		response.setMessage(message);
		response.setLedgerId(UUID.randomUUID());

		return ResponseEntity.ok().body(response);
	}

}
