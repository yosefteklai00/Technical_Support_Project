package com.orderservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller for handling order-related requests
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    // Logger for this class
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    /**
     * Health check endpoint
     * @return Status of the order service
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        logger.debug("Health check requested");

        // Create and return a simple status map
        Map<String, String> status = new HashMap<>();
        status.put("status", "UP");
        status.put("service", "order-service");

        logger.debug("Returning health status: {}", status);
        return ResponseEntity.ok(status);
    }

    /**
     * Creates a new order
     * @param order Order data received from customer service
     * @return Confirmation response
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> createOrder(@RequestBody Map<String, Object> order) {
        logger.info("Received order with ID: {}", order.get("orderId"));
        logger.debug("Full order details: {}", order);

        // Process the order (in a real application, this would involve more logic)
        // For now, we just acknowledge receipt

        // Create response with order confirmation
        Map<String, Object> response = new HashMap<>();
        response.put("status", "ACCEPTED");
        response.put("orderId", order.get("orderId"));
        response.put("message", "Order successfully processed");

        logger.info("Successfully processed order with ID: {}", order.get("orderId"));
        return ResponseEntity.ok(response);
    }
}
