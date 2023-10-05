package com.course.microservice.api.server;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.microservice.api.response.PlainMessage;

@RestController
@RequestMapping("/desktop/api")
public class EmployeeDesktopApi {

	@GetMapping(value = "/employee")
	public ResponseEntity<PlainMessage> bff() {
		var responseBody = new PlainMessage("This is response for desktop API from /employee");
		return ResponseEntity.ok().body(responseBody);
	}

}
