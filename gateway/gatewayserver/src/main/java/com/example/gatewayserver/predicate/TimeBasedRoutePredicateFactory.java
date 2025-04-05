package com.example.gatewayserver.predicate;

import java.security.PublicKey;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class TimeBasedRoutePredicateFactory
        extends AbstractRoutePredicateFactory<TimeBasedRoutePredicateFactory.ConfigClass> {

    public TimeBasedRoutePredicateFactory() {
        super(ConfigClass.class);
    }

    public class ConfigClass {
        public String name;

        public ConfigClass(String name) {
            this.name = name;
        }
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("name");
    }

    @Override
    public Predicate<ServerWebExchange> apply(ConfigClass config) {
        return (exchange) -> {
            return config.name.length() > 10;
        };
    }
}
