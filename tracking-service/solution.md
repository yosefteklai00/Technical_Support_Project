# Java Spring Boot Network Troubleshooting Scenario

## Overview
This scenario simulates a network connectivity issue between two Java Spring Boot microservices running in Docker containers. The `customer` service attempts to communicate with the `tracking-service` but encounters connection failures due to a firewall rule blocking traffic on the target port.

## Diagnostic Process

### 1. Basic Application Test
**Objective**: Verify service status and identify error patterns in logs

```bash
# Check customer service logs
docker logs customer-service

# Check tracking service logs  
docker logs tracking-service

# Look for connection errors, timeouts, or HTTP status codes
```

**Expected Findings**:
- Customer service shows connection timeout/refused errors
- Tracking service may show no incoming connection attempts

### 2. Basic Connectivity Test
**Objective**: Test basic network reachability between containers

```bash
# From customer service container
ping tracking-service
```

**Expected Result**: Ping should succeed, confirming basic network connectivity

### 3. Port Connectivity Test
**Objective**: Test specific port accessibility

```bash
# Test port connectivity using multiple tools
telnet tracking-service 8081
nc -zv tracking-service 8081
nmap -p 8081 tracking-service
```

**Expected Results**:
- `telnet`: Connection refused or timeout
- `nc`: Connection refused
- `nmap`: Port shown as filtered or closed

### 4. Service Status Check
**Objective**: Verify tracking service is actually running and listening

```bash
# From tracking-service container
netstat -tlnp | grep 8081
ss -tlnp | grep 8081

# Or check process status
ps aux | grep java
```

**Expected Finding**: Service is running and listening on port 8081

### 5. Firewall Investigation
**Objective**: Identify firewall rules blocking the connection

```bash
# Check iptables rules
iptables -L
iptables -L INPUT -n --line-numbers

# Look for specific DROP rules targeting the customer service IP
```

**Expected Finding**: A DROP rule specifically targeting traffic from customer service's Docker IP to port 8081

## Solution Options

### Remove Specific Firewall Rule

```bash
# First, identify the line number of the DROP rule
iptables -L INPUT --line-numbers

# Delete the specific DROP rule (assuming it's line 3)
iptables -D INPUT 3

# Verify the rule has been removed
iptables -L INPUT -n
```

## Testing the Fix

After applying the solution, verify connectivity:

```bash
# Test port connectivity again
nc -zv tracking-service 8081
telnet tracking-service 8081

# Test application connectivity
curl http://tracking-service:8081/health

# Check application logs for successful connections
docker logs customer-service
```

## Learning Objectives

This scenario helps develop skills in:
- **Network Troubleshooting**: Systematic approach to diagnosing connectivity issues
- **Docker Networking**: Understanding container-to-container communication
- **Firewall Management**: Working with iptables rules and understanding traffic filtering
- **Service Architecture**: Troubleshooting microservice communication patterns
- **Command Line Tools**: Using various network diagnostic utilities

