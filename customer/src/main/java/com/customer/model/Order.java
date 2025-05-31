package com.customer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String orderId;
    private String customerId;
    private String productId;
    private int quantity;
    private double price;
    private LocalDateTime orderDate;

    public static Order createDummyOrder() {
        return Order.builder()
                .orderId(UUID.randomUUID().toString())
                .customerId("CUST-" + (int)(Math.random() * 1000))
                .productId("PROD-" + (int)(Math.random() * 100))
                .quantity((int)(Math.random() * 10) + 1)
                .price(Math.round(Math.random() * 1000 * 100.0) / 100.0)
                .orderDate(LocalDateTime.now())
                .build();
    }
}
