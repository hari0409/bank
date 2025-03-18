package com.eazybytes.cards.config;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("cards")
public record CardAppnConfig(String description, Map<String, String> owner, List<Long> mobile) {
}
