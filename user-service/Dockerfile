# Use Maven with OpenJDK 11 for building
FROM maven:3.8.4-openjdk-11-slim AS build

# Copy the parent pom.xml
COPY pom.xml .

# Copy the service pom.xml
COPY user-service user-service


# Set working directory
WORKDIR /app

# Build the application
RUN mvn clean package -DskipTests -pl user-service -am

# Use OpenJDK 11 JRE for runtime
FROM openjdk:11-jre-slim

# Copy the built JAR from the build stage
COPY --from=build /app/user-service/target/*.jar app.jar

# Expose port 8082
EXPOSE 8082

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]