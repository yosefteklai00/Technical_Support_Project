FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/*.jar app.jar


RUN mkdir -p /app/ssl
COPY /src/main/resources/*.jks /app/ssl

COPY /src/main/resources/start.sh /app/

# Expose HTTPS port

EXPOSE 8443

CMD ["./start.sh"]