package com.course.microservice.eshop.accounting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EshopAccountingApplication {

	public static void main(String[] args) {
		SpringApplication.run(EshopAccountingApplication.class, args);
	}

}
