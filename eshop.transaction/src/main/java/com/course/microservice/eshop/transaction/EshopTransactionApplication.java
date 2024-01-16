package com.course.microservice.eshop.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EshopTransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(EshopTransactionApplication.class, args);
	}

}
