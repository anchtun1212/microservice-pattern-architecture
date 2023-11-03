package com.course.microservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PredicateConfig {

	@Bean
	public BffDesktopPredicateFactory desktopPredicateFactory() {
		return new BffDesktopPredicateFactory();
	}

	@Bean
	public BffMobilePredicateFactory mobilePredicateFactory() {
		return new BffMobilePredicateFactory();
	}

}
