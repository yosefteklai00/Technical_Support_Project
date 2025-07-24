# Technical Support Engineer Challenge

## Scenario Overview

You are a Technical Support Engineer at ShopWave, a fictional e-commerce company that uses a microservices architecture to power its online shopping platform. Your role involves supporting internal teams and external customers who integrate with ShopWave's services.

Today, you're handling incidents related to ShopWave's core microservices. Your goal is to diagnose and resolve issues while maintaining clear communication with stakeholders.

## Architecture Overview

ShopWave's platform consists of several microservices:

```
┌─────────────────┐     ┌─────────────────┐     
│                 │     │                 │                
│  Customer       │────▶│  Order          
│  Service        │     │  Service        │    
│  (Port: 8080)   │     │  (Port: 8443)   │    
│                 │     │                 │     
└─────────────────┘     └─────────────────┘    
        │                     
        │                       
        ▼                    
┌─────────────────┐     
│                 │    
│  Tracking       │    
│  Service        │     
│  (Port: 8081)   │    
│                 │     
└─────────────────┘     
```

### Key Services:
- **Customer Service**: Entry point for customer interactions (Port 8080)
- **Order Service**: Processes customer orders via HTTPS (Port 8443)
- **Tracking Service**: Provides shipment tracking information (Port 8081)

## Prerequisites

To complete this challenge, you'll need:

1. Docker and Docker Compose installed on your system
2. Basic familiarity with terminal/command line
3. Basic understanding of microservices architecture
4. 1-2 hours of uninterrupted time

## Getting Started

1. Clone this repository to your local machine
2. Navigate to the project directory
3. Start the environment with:
   ```
   docker-compose up -d
   ```
4. Verify all services are running:
   ```
   docker-compose ps
   ```

## Generating Test Traffic

You can generate test traffic by accessing these endpoints from your browser:

- Create a new order: http://localhost:8080/api/customer/order
- Track an order: http://localhost:8080/api/customer/tracking

## Important Contacts

During this challenge, you may need to communicate with the following stakeholders:

- **Account Management Team**: account-managers@shopwave.example.com
  - Responsible for client relationships and escalations

- **Executive Leadership**: executive-leads@shopwave.example.com
  - Keen interest on major impacting incidents 


## Challenge 1: Order Processing Failure

**Incident Description:**
The Account Management team has reported that several enterprise customers cannot process orders. The issue appears to be affecting all customers trying to place orders through the API.

**Your Tasks:**
1. Diagnose the root cause of the order processing failure
2. Implement a solution to restore order processing functionality
3. Ensure relevant stakeholders are kept up to date


**Success Criteria:**
- Orders can be successfully processed through the API
- You can explain the root cause and solution in both technical and non-technical terms
- Your communication with stakeholders is clear and appropriate

## Challenge 2: Tracking Data Issue

**Incident Description:**
The Analytics team has reported they are not receiving any tracking data from the tracking service. This is affecting their ability to generate shipping performance reports for customers.

**Your Tasks:**
1. Diagnose why tracking data is not flowing from the tracking service
2. Implement a solution to restore data flow

**Success Criteria:**
- Tracking data is flowing properly to downstream systems
- You can explain the root cause and solution in both technical and non-technical terms
- Your communication with stakeholders is clear and appropriate
