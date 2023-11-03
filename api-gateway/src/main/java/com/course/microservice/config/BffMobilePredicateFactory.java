package com.course.microservice.config;

import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;

import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;

public class BffMobilePredicateFactory extends AbstractRoutePredicateFactory<BffMobilePredicateFactory.Config> {

	private final UserAgentAnalyzer uaa = UserAgentAnalyzer.newBuilder().hideMatcherLoadStats().dropTests()
			.withField(UserAgent.DEVICE_CLASS).withCache(10000).build();

	public BffMobilePredicateFactory() {
		super(Config.class);
	}

	public static class Config {
	}

	@Override
	public Predicate<ServerWebExchange> apply(Config config) {
		return exchange -> {
			var request = exchange.getRequest();
			var requestPath = request.getPath().subPath(0, 2).value();
			var userAgent = request.getHeaders().get(HttpHeaders.USER_AGENT).get(0);
			var deviceClass = uaa.parse(userAgent).getValue(UserAgent.DEVICE_CLASS);

			return "/bff".equalsIgnoreCase(requestPath)
					&& StringUtils.equalsAnyIgnoreCase(deviceClass, "mobile", "phone", "tablet");
		};
	}
}
