# Use an official JDK runtime as the base image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR and config file into the container
COPY target/coin_change-1.0-SNAPSHOT.jar app.jar
COPY config.yml config.yml

# Expose the port your application runs on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar", "server", "config.yml"]

