# Java Spring Boot SSL Certificate Troubleshooting Scenario

## Overview
This scenario simulates an SSL certificate expiration issue between two Java Spring Boot microservices. The `customer` service attempts to make HTTPS calls to the `order-service` but encounters SSL handshake failures due to an expired certificate on the order-service.


## Diagnostic Process

### 1. Basic Application Test
**Objective**: Identify SSL-related errors in application logs

```bash
# Check customer service logs
docker logs customer-service

# Check order service logs
docker logs order-service
```

**Expected Findings**:
- Customer service shows SSL handshake failures, certificate validation errors
- Possible errors: `SSLHandshakeException`, `CertificateExpiredException`, `PKIX path validation failed`
- Order service may show SSL errors or failed connection attempts

### 2. Basic Connectivity Test
**Objective**: Test basic network reachability and HTTP vs HTTPS

```bash
# Test basic connectivity
ping order-service

# Test HTTPS port - this should fail with SSL errors
curl -v https://order-service:8443/health
```

**Expected Results**:
- Basic connectivity works
- HTTPS fails with SSL certificate errors

### 3. SSL Certificate Inspection
**Objective**: Examine the certificate details and expiration

```bash
# Check certificate details using openssl
openssl s_client -connect order-service:8443 -servername order-service

# Extract and examine certificate
echo | openssl s_client -connect order-service:8443 2>/dev/null | openssl x509 -noout -dates -subject -issuer

# Alternative using curl
curl -vI https://order-service:8443 2>&1 | grep -E "(expire|certificate|SSL)"
```

**Expected Finding**: Certificate shows expiration date in the past

### 4. KeyStore File Investigation
**Objective**: Locate and examine the current KeyStore file

```bash
# Find .jks files on the system one will be valid and another will be expired
find / -name "*.jks" 2>/dev/null


```
**Expected Finding**: KeyStore contains expired certificate(s)

## Solution Process

### Step 1: Verify the Alternative KeyStore
**Objective**: Confirm the alternative KeyStore contains valid certificates

```bash
# Check the alternative keystore
keytool -list -v -keystore /ssl/valid-cert.jks -storepass changeit

# Verify certificate validity dates
keytool -list -v -keystore /ssl/valid-cert.jks -storepass changeit | grep -A2 -B2 "Valid"

```


### Step 2: Replace the KeyStore File
**Objective**: Swap expired certificate with valid one

```bash
# Replace the keystore file
cp /ssl/expired-cert.jks /ssl/valid-cert.jks

```

### Step 4: Restart the Service
**Objective**: Apply certificate changes (required for external KeyStore changes)

```bash
# For Docker container
docker restart order-service

```

**Note**: Service restart is required because Java applications load KeyStore files at startup and cache the certificates in memory.

## Testing the Fix

### Verify SSL Certificate
```bash
# Test certificate validity
echo | openssl s_client -connect order-service:8443 2>/dev/null | openssl x509 -noout -dates

# Test HTTPS connectivity
curl -v https://order-service:8443/health
```

## Learning Objectives

This scenario develops skills in:
- **SSL/TLS Troubleshooting**: Understanding certificate expiration and validation
- **Java KeyStore Management**: Working with .jks files and keytool utility
- **Spring Boot SSL Configuration**: Understanding how Spring Boot handles SSL
- **File System Investigation**: Systematically searching for configuration files
