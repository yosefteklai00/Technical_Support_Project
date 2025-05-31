package com.customer.controller;

import com.customer.model.Order;
import com.customer.model.Tracking;
import com.customer.service.OrderService;
import com.customer.service.TrackingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final OrderService orderService;
    private final TrackingService trackingService;


    public CustomerController(OrderService orderService, TrackingService trackingService) {
        this.orderService = orderService;
        this.trackingService = trackingService;
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        logger.debug("Health check requested");

        Map<String, String> status = new HashMap<>();
        status.put("status", "UP");
        status.put("service", "customer-service");

        return ResponseEntity.ok(status);
    }

    @GetMapping("/order")
    public ResponseEntity<Map<String, Object>> createOrder() {
        logger.info("Order endpoint called");

        // Create a dummy order
        Order dummyOrder = Order.createDummyOrder();
        logger.info("Created dummy order with ID: {}", dummyOrder.getOrderId());

        // Print dummy data
        logger.info("Dummy Order: Customer ID: {}, Product ID: {}, Quantity: {}, Price: ${}",
                dummyOrder.getCustomerId(),
                dummyOrder.getProductId(),
                dummyOrder.getQuantity(),
                dummyOrder.getPrice());

        // Send order to order service
        Map<String, Object> response = orderService.createOrder(dummyOrder);

        // Add the original order details to the response
        Map<String, Object> fullResponse = new HashMap<>(response);
        fullResponse.put("originalOrder", dummyOrder);

        return ResponseEntity.ok(fullResponse);
    }

    @GetMapping("/tracking")
    public ResponseEntity<Map<String, Object>> trackingOrder() {
        logger.info("tracking endpoint called");

        // Create a dummy track
        Tracking dummyTrack = Tracking.createDummyTrack();
        logger.info("Created dummy track with ID: {}", dummyTrack.getTrackingId());

        // Send order to track service
        Map<String, Object> response = trackingService.trackBehaviour(dummyTrack);

        // Add the original order details to the response
        Map<String, Object> fullResponse = new HashMap<>(response);
        fullResponse.put("originalmessage", dummyTrack);

        return ResponseEntity.ok(fullResponse);
    }
}
