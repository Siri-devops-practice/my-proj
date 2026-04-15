# Use Maven with OpenJDK 11 for building
FROM maven:3.8.4-openjdk-11-slim AS build
WORKDIR /app
# Copy the entire project
COPY . .

# Build the application
RUN mvn clean package -DskipTests -pl message-service -am

# Use OpenJDK 11 JRE for runtime
FROM openjdk:11-jre-slim
WORKDIR /app
# Copy the built JAR from the build stage
COPY --from=build /app/message-service/target/*.jar app.jar

# Expose port 8081
EXPOSE 8081

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]