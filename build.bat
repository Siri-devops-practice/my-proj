@echo off
echo Building Emotion App Microservices...
echo.
mvn clean install -DskipTests
if errorlevel 1 (
    echo Build failed!
    exit /b 1
)
echo.
echo Build completed successfully!
echo.
echo Run the services:
echo   Terminal 1: mvn spring-boot:run -pl message-service
echo   Terminal 2: mvn spring-boot:run -pl user-service
echo   Terminal 3: mvn spring-boot:run -pl ui-service
echo.
echo Then open: http://localhost:8083
pause
