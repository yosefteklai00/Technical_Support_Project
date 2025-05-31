package com.customer.service;

import com.customer.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final RestTemplate restTemplate;
    private final String orderServiceUrl;

    public OrderService(RestTemplate restTemplate,
                        @Value("${order-service.url}") String orderServiceUrl) {
        this.restTemplate = restTemplate;
        this.orderServiceUrl = orderServiceUrl;
    }

    public Map<String, Object> createOrder(Order order) {
        logger.info("Creating order with ID: {}", order.getOrderId());
        logger.debug("Order details: {}", order);

        try {
            // Convert Order to Map for the request
            Map<String, Object> orderMap = new HashMap<>();
            orderMap.put("orderId", order.getOrderId());
            orderMap.put("customerId", order.getCustomerId());
            orderMap.put("productId", order.getProductId());
            orderMap.put("quantity", order.getQuantity());
            orderMap.put("price", order.getPrice());
            orderMap.put("orderDate", order.getOrderDate().toString());

            // Make request to order service
            String url = orderServiceUrl + "/order";
            logger.debug("Sending order to: {}", url);

            ResponseEntity<Map> response = restTemplate.postForEntity(url, orderMap, Map.class);

            logger.info("Order service response status: {}", response.getStatusCode());
            logger.debug("Order service response body: {}", response.getBody());

            return response.getBody();
        } catch (Exception e) {
            logger.error("Error creating order: {}", e.getMessage(), e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "ERROR");
            errorResponse.put("message", "Failed to create order: " + e.getMessage());
            return errorResponse;
        }
    }
}
