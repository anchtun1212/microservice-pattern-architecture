package com.course.microservice.config;

import java.net.URI;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayRouteConfig {

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder,
			BffDesktopPredicateFactory desktopPredicateFactory, BffMobilePredicateFactory mobilePredicateFactory) {
		var backendUriDesktop = URI.create("http://localhost:8881/desktop");
		var backendUriMobile = URI.create("http://localhost:8881/mobile");

		return builder.routes().route("desktop_route",
				r -> r.predicate(desktopPredicateFactory.apply(new BffDesktopPredicateFactory.Config()))
						.filters(f -> f.stripPrefix(1).prefixPath(backendUriDesktop.getPath())).uri(backendUriDesktop))
				.route("mobile_route",
						r -> r.predicate(mobilePredicateFactory.apply(new BffMobilePredicateFactory.Config()))
								.filters(f -> f.stripPrefix(1).prefixPath(backendUriMobile.getPath()))
								.uri(backendUriMobile))
				.build();
	}

}
