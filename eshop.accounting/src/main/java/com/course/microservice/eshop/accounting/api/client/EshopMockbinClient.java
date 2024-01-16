package com.course.microservice.eshop.accounting.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.course.microservice.eshop.accounting.api.response.MockbinResponse;

@FeignClient(name = "mockbinClient", url = "${eshop.mockbin.url:http://localhost:9999}")
public interface EshopMockbinClient {

	@GetMapping(value = "/status/{code}/{reason}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<MockbinResponse> status(@PathVariable(name = "code") int code,
			@PathVariable(name = "reason") String reason);

	@GetMapping(value = "/delay/{millis}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<MockbinResponse> delay(@PathVariable(name = "millis") long millis);

}