package com.course.microservice.eshop.accounting.api.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckApi {

	@Value("${spring.application.name:accounting}")
	private String applicationName;

	@Value("${eshop.mockbin.url:Default value from source code}")
	private String myConfig;

	@GetMapping(value = "/config", produces = MediaType.TEXT_PLAIN_VALUE)
	public String config() {
		return "eshop.mockbin.url is " + myConfig;
	}

	@GetMapping(value = "/ping", produces = MediaType.TEXT_PLAIN_VALUE)
	public String healthCheck() {
		return applicationName + " is UP";
	}

}