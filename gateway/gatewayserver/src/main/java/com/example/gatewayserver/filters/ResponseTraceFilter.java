package com.example.gatewayserver.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;

import reactor.core.publisher.Mono;

@Configuration
public class ResponseTraceFilter {
    private static final Logger logger = LoggerFactory.getLogger(ResponseTraceFilter.class);

    @Autowired
    private FilterUtility filterUtility;

    @Bean
    public GlobalFilter postGlobalFilter() {
        return (exchange, chain) -> {
            // .then() can be used to execute at the end after all the existing filters have
            // been executed.
            return chain.filter(exchange).then(
                    Mono.fromRunnable(() -> {
                        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
                        String correlationId = filterUtility.getCorrelationId(requestHeaders);
                        logger.debug("Updated the correlation id to the outbound headers: {}",
                                correlationId);
                        exchange.getResponse().getHeaders().add(filterUtility.CORRELATION_ID,
                                correlationId);
                    }));
        };
    }

    @Bean
    public GlobalFilter myCustomFilterPart() {
        return (exchange, chain) -> {
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                exchange.getResponse().addCookie(ResponseCookie.from("fuck-off", "fuck-iff").build());
            }));
        };
    }

}
