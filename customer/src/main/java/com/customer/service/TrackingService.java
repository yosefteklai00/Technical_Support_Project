package com.customer.service;

import com.customer.model.Order;
import com.customer.model.Tracking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class TrackingService {

    private static final Logger logger = LoggerFactory.getLogger(TrackingService.class);

    private final RestTemplate restTemplate;
    private final String trackingServiceUrl;

    public TrackingService(RestTemplate restTemplate,
                        @Value("${tracking-service.url}") String trackingServiceUrl) {
        this.restTemplate = restTemplate;
        this.trackingServiceUrl = trackingServiceUrl;
    }

    public Map<String, Object> trackBehaviour(Tracking tracking) {
        logger.info("Creating tracking with ID: {}", tracking.getTrackingId());
        logger.debug("Tracking details: {}", tracking);

        try {
            // Convert Order to Map for the request
            Map<String, Object> trackingMap = new HashMap<>();
            trackingMap.put("orderId", tracking.getTrackingId());
            trackingMap.put("customerId", tracking.getCustomerId());

            // Make request to tracking service
            String url = trackingServiceUrl + "/tracking";
            logger.debug("Sending tracking to: {}", url);

            ResponseEntity<Map> response = restTemplate.postForEntity(url, trackingMap, Map.class);

            logger.info("tracking service response status: {}", response.getStatusCode());
            logger.debug("tracking service response body: {}", response.getBody());

            return response.getBody();
        } catch (Exception e) {
            logger.error("Error creating tracking: {}", e.getMessage(), e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "ERROR");
            errorResponse.put("message", "Failed to create tracking: " + e.getMessage());
            return errorResponse;
        }
    }
}
