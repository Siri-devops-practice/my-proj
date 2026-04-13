@echo off
echo Starting Emotion App Services...
echo.
set PATH=%PATH%;C:\maven\apache-maven-3.9.14\bin
start "Message Service (8081)" cmd /k "mvn spring-boot:run -pl message-service -DskipTests"
timeout /t 3
start "User Service (8082)" cmd /k "mvn spring-boot:run -pl user-service -DskipTests"
timeout /t 3
start "UI Service (8083)" cmd /k "mvn spring-boot:run -pl ui-service -DskipTests"
echo.
echo All services are starting...
echo Please wait for them to fully initialize.
echo.
echo Access the app at: http://localhost:8083
pause
