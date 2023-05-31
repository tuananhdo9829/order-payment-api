package com.tuananhdo.controller;

import com.tuananhdo.dto.OrderRequest;
import com.tuananhdo.dto.OrderResponse;
import com.tuananhdo.exception.PaymentException;
import com.tuananhdo.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    private final OrderService orderService;

    public PaymentController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createPayment(@RequestBody OrderRequest orderRequest) throws PaymentException {
        return ResponseEntity.ok(orderService.placeOrder(orderRequest));
    }
}
