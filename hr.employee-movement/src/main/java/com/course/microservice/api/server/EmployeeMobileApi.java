package com.course.microservice.api.server;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.microservice.api.response.PlainMessage;

@RestController
@RequestMapping("/mobile/api")
public class EmployeeMobileApi {

	@GetMapping(value = "/employee")
	public ResponseEntity<PlainMessage> bff() {
		var responseBody = new PlainMessage("This is response for mobile API from /employee");
		return ResponseEntity.ok().body(responseBody);
	}

}
