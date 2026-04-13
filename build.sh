#!/bin/bash
echo "Building Emotion App Microservices..."
echo ""
mvn clean install -DskipTests
if [ $? -ne 0 ]; then
    echo "Build failed!"
    exit 1
fi
echo ""
echo "Build completed successfully!"
echo ""
echo "Run the services:"
echo "  Terminal 1: mvn spring-boot:run -pl message-service"
echo "  Terminal 2: mvn spring-boot:run -pl user-service"
echo "  Terminal 3: mvn spring-boot:run -pl ui-service"
echo ""
echo "Then open: http://localhost:8083"
