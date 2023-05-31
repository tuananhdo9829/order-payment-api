package com.tuananhdo.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    @GetMapping
    public ResponseEntity<String> responsePayment() {
        return ResponseEntity.ok("Hello Pyament API");
    }
}
