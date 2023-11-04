package com.course.microservice;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

import io.sentry.Sentry;

@SpringBootApplication
@EnableFeignClients
@EnableCaching
public class ApiCompositionApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApiCompositionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		while (true) {
			TimeUnit.SECONDS.sleep(1);

			try {
				throw new IllegalArgumentException("Now : " + LocalDateTime.now());
			} catch (Exception e) {
				Sentry.captureException(e);
			}
		}
	}

}
