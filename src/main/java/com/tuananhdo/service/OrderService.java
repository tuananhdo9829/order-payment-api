package com.tuananhdo.service;

import com.tuananhdo.dto.OrderRequest;
import com.tuananhdo.dto.OrderResponse;
import com.tuananhdo.entity.Order;
import com.tuananhdo.entity.Payment;
import com.tuananhdo.exception.PaymentException;
import com.tuananhdo.repository.OrderRepository;
import com.tuananhdo.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    public OrderService(OrderRepository orderRepository, PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }

    @Transactional(rollbackFor = PaymentException.class)
    public OrderResponse placeOrder(OrderRequest orderRequest) throws PaymentException {
        Order order = orderRequest.getOrder();
        order.setStatus("PROGRESS");
        orderRepository.save(order);

        Payment payment = orderRequest.getPayment();
        if (!payment.getType().equals("DEBIT")) {
            throw new PaymentException("Payment card type don't support !");
        }

        payment.setOrderId(order.getId());
        paymentRepository.save(payment);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderTrackingNumber(order.getOrderTrackingNumber());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setMessage("SUCCESS");

        return orderResponse;
    }

}
