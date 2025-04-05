package com.example.gatewayserver;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import com.example.gatewayserver.predicate.TimeBasedRoutePredicateFactory;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator buildRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(p -> p
						.path("/eazybank/accounts/**")
						.and()
						.predicate(new TimeBasedRoutePredicateFactory()
								.apply(new TimeBasedRoutePredicateFactory().new ConfigClass("morethan10iwouldsay")))
						.filters(f -> f
								.rewritePath("/eazybank/accounts/(?<segment>.*)", "/${segment}") // Filter 1
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()) // Filter 2
						)
						.uri("lb://ACCOUNTS"))
				.route(p -> p
						.path("/eazybank/loans/**")
						.and()
						.predicate(new TimeBasedRoutePredicateFactory()
								.apply(new TimeBasedRoutePredicateFactory().new ConfigClass("morethan10iwouldsay")))
						.filters(f -> f.rewritePath("/eazybank/loans/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://LOANS"))
				.route(p -> p
						.path("/eazybank/cards/**")
						.and()
						.predicate(new TimeBasedRoutePredicateFactory()
								.apply(new TimeBasedRoutePredicateFactory().new ConfigClass("morethan10iwouldsay")))
						.filters(f -> f.rewritePath("/eazybank/cards/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://CARDS"))
				.build();
	}
}