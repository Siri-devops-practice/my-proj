# Use Maven with OpenJDK 11 for building
FROM maven:3.8.4-openjdk-11-slim AS build

# Copy the parent pom.xml
COPY pom.xml /app/pom.xml

# Copy the service pom.xml
COPY message-service/pom.xml /app/message-service/pom.xml

# Copy source code
COPY message-service/src /app/message-service/src

# Set working directory
WORKDIR /app

# Build the application
RUN mvn clean package -DskipTests -pl message-service -am

# Use OpenJDK 11 JRE for runtime
FROM openjdk:11-jre-slim

# Copy the built JAR from the build stage
COPY --from=build /app/message-service/target/*.jar app.jar

# Expose port 8081
EXPOSE 8081

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]