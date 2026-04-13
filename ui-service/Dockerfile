# Use Maven with OpenJDK 11 for building
FROM maven:3.8.4-openjdk-11-slim AS build

# Copy the parent pom.xml
COPY pom.xml /app/pom.xml

# Copy the service pom.xml
COPY ui-service/pom.xml /app/ui-service/pom.xml

# Copy source code
COPY ui-service/src /app/ui-service/src

# Set working directory
WORKDIR /app

# Build the application
RUN mvn clean package -DskipTests -pl ui-service -am

# Use OpenJDK 11 JRE for runtime
FROM openjdk:11-jre-slim

# Copy the built JAR from the build stage
COPY --from=build /app/ui-service/target/*.jar app.jar

# Expose port 8083
EXPOSE 8083

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]