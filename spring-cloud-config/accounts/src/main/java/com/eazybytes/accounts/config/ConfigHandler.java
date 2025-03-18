package com.eazybytes.accounts.config;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "accounts")
public record ConfigHandler(String message, 
            Map<String, String> contactDetails, 
            List<Integer> onCallSupport) {
}