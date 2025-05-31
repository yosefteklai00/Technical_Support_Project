FROM openjdk:21-jdk-slim

# Install iptables for firewall simulation

RUN apt-get update && apt-get install -y \
    iptables \
    iproute2 \
    iputils-ping \
    telnet \
    netcat-openbsd \
    net-tools \
    curl \
    tcpdump \
    nmap \
    traceroute \
    && rm -rf /var/lib/apt/lists/*


COPY target/*.jar app.jar
COPY src/main/resources/firewall-setup.sh /firewall-setup.sh
RUN chmod +x /firewall-setup.sh

RUN echo '#!/bin/sh\niptables -A INPUT -p tcp --dport 8081 -s 172.16.0.0/12 -j DROP' > /firewall-setup.sh \
    && chmod +x /firewall-setup.sh

EXPOSE 8081

ENTRYPOINT ["/bin/bash", "-c", "/firewall-setup.sh && java -jar /app.jar"]
