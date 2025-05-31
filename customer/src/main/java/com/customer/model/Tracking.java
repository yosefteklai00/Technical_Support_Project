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
public class Tracking {
    private String trackingId;
    private String customerId;


    public static Tracking createDummyTrack() {
        return Tracking.builder()
                .trackingId(UUID.randomUUID().toString())
                .customerId("CUST-" + (int)(Math.random() * 1000))
                .build();
    }
}
