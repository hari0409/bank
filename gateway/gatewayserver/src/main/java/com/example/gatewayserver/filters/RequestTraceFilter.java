package com.example.gatewayserver.filters;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component // Defined as an Annotation so that it will be loaded.
@Order(1) // Order defined to execute the filter in the give order
public class RequestTraceFilter implements GlobalFilter {
    @Autowired
    FilterUtility filterUtility;

    private static final Logger logger = LoggerFactory.getLogger(RequestTraceFilter.class);

    @Override
    // Implementing GlobalFilter means we have define this method.
    // Input:
    // 1. ServerWebExchange => Information about Request & Response
    // 2. GatewayFilterChain => All the filters are executed in a chain manner
    // becasue of which all filter method will take a chain, execute itself (filter)
    // and call the next filter in the chain
    // Output:
    // 1. Mono<Void> => Void because we are just executing the next filter in the
    // chain
    // Using Mono cause its a single object. Flux will be used for a collection of
    // object.
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
        if (isCorrelationIdPresent(requestHeaders)) {
            logger.debug("eazyBank-correlation-id found in RequestTraceFilter: {}",
                    filterUtility.getCorrelationId(requestHeaders));
        } else {
            String correlationID = generateCorrelationId();
            exchange = filterUtility.setCorrelationID(exchange, correlationID);
            logger.debug("eazyBank-correlation-id generated in RequestTraceFilter: {}", correlationID);
        }
        return chain.filter(exchange);
    }

    private String generateCorrelationId() {
        // Generate random ID
        return UUID.randomUUID().toString();
    }

    private boolean isCorrelationIdPresent(HttpHeaders requestHeaders) {
        // Check if the ID already exists in the header.
        if (filterUtility.getCorrelationId(requestHeaders) != null) {
            return true;
        } else {
            return false;
        }
    }

}

