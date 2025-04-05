package com.eazybytes.gatewayserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class GatewayController {

    @GetMapping("/contactsupport")
    public Mono<ResponseEntity<String>> contactSupport() {
        return Mono.just(ResponseEntity.status(404)
                .body("An Error Occured. Please try again after some time or contact support team!"));
    }

}
