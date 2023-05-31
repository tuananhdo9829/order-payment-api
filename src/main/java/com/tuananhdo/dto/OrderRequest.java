package com.tuananhdo.dto;

import com.tuananhdo.entity.Order;
import com.tuananhdo.entity.Payment;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderRequest {
    private Order order;
    private Payment payment;
}
