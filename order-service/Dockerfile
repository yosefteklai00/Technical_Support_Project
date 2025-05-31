FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/*.jar app.jar


RUN mkdir -p /app/ssl

# Expose HTTPS port
EXPOSE 8443

ENTRYPOINT ["java", "-jar", "app.jar"]
