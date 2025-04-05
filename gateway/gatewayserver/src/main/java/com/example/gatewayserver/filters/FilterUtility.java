package com.example.gatewayserver.filters;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class FilterUtility {

    public final String CORRELATION_ID = "eazyBank-correlation-id";

    public String getCorrelationId(HttpHeaders requestHeaders) {
        // Check if the Header contains value with a the given KEY.
        if (requestHeaders.get(CORRELATION_ID) != null) {
            // If the header value exists, extract it and return it.
            List<String> requestHeaderList = requestHeaders.get(CORRELATION_ID);
            // findFirst() because in our case only one header value will be present.
            return requestHeaderList.stream().findFirst().get();
        } else {
            return null;
        }
    }

    // Setting the request header value
    public ServerWebExchange setCorrelationID(ServerWebExchange exchange, String correlationID) {
        return this.setRequestHeader(exchange, CORRELATION_ID, correlationID);
    }

    // Helper for setting the request header
    public ServerWebExchange setRequestHeader(ServerWebExchange exchange, String name, String value) {
        return exchange.mutate().request(exchange.getRequest().mutate().header(name, value).build()).build();
    }

}
