package com.trackingservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/tracking")
public class TrackingController {

    // Logger for this class
    private static final Logger logger = LoggerFactory.getLogger(TrackingController.class);

    /**
     * Health check endpoint
     * @return Status of the tracking service
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        logger.debug("Health check requested");

        // Create and return a simple status map
        Map<String, String> status = new HashMap<>();
        status.put("status", "UP");
        status.put("service", "tracking-service");

        logger.debug("Returning health status: {}", status);
        return ResponseEntity.ok(status);
    }

    /**
     * Creates a new tracking
     * @param tracking data received from customer service
     * @return Confirmation response
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> createTracking(@RequestBody Map<String, Object> tracking) {
        logger.info("Received tracking with ID: {}", tracking.get("trackingId"));
        logger.debug("Full tracking details: {}", tracking);

        // Process the tracking (in a real application, this would involve more logic)
        // For now, we just acknowledge receipt

        // Create response with tracking confirmation
        Map<String, Object> response = new HashMap<>();
        response.put("status", "ACCEPTED");
        response.put("message", "Tracking successfully processed");

        logger.info("Successfully processed tracking with ID: {}", tracking.get("trackingId"));
        return ResponseEntity.ok(response);
    }
}
