package com.eazybytes.loans.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties("loans")
public record LoanAppnConfig(String description, Map<String,String> owner, List<Long> mobile) {
}
