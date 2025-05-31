FROM openjdk:21-jdk-slim

WORKDIR /app

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

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
