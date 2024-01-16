package com.course.microservice.eshop.payment.exception.handler;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.course.microservice.eshop.payment.api.response.ErrorMessage;

import feign.FeignException;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = { FeignException.class })
	public ResponseEntity<ErrorMessage> handleIoException(FeignException e, HttpServletResponse response) {
		var errorResponse = new ErrorMessage();
		errorResponse.setMessage("API call trows exception: " + e.getMessage());

		var status = e.getCause() instanceof java.net.ConnectException ? HttpStatus.SERVICE_UNAVAILABLE.value()
				: e.status();

		return ResponseEntity.status(status).body(errorResponse);
	}

}