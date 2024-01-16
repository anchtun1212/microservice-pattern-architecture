package com.course.microservice.eshop.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EshopPaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(EshopPaymentApplication.class, args);
	}

}
