package com.eazybytes.loans.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@ConfigurationProperties("loans")
@Getter
@Setter
public class LoanAppnConfig {
    private String message;
    private Map<String, String> contactDetails;
    private List<String> onCallSupport;
}
