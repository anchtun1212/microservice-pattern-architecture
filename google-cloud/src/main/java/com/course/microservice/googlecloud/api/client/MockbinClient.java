package com.course.microservice.googlecloud.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.course.microservice.googlecloud.api.response.MockbinResponse;

@FeignClient(name = "mockbinClient", url = "https://mockbin.org")
public interface MockbinClient {

	@GetMapping(value = "/status/{code}/{reason}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<MockbinResponse> status(@PathVariable(name = "code") int code,
			@PathVariable(name = "reason") String reason);

	@GetMapping(value = "/delay/{millis}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<MockbinResponse> delay(@PathVariable(name = "millis") long millis);

}
