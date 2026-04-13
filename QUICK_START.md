# Quick Start Guide

## Prerequisites

- Java 11+
- Maven 3.6+
- Any modern browser
- Docker (optional, for containerized deployment)

## Build the Project

```bash
cd sorry_proj
mvn clean install -DskipTests
```

Or use the build script:
- **Windows:** `build.bat`
- **Linux/Mac:** `bash build.sh`

## Run the Services

### Option 1: Manual (Development)

Open 3 separate terminal windows:

#### Terminal 1: Message Service (Port 8081)
```bash
cd message-service
mvn spring-boot:run
```

#### Terminal 2: User Service (Port 8082)
```bash
cd user-service
mvn spring-boot:run
```

#### Terminal 3: UI Service (Port 8083)
```bash
cd ui-service
mvn spring-boot:run
```

**Or all at once (Windows only):**
```bash
runall.bat
```

### Option 2: Docker (Production)

Ensure Docker is installed and running.

```bash
# Build and run all services
docker-compose up --build

# Run in background
docker-compose up -d --build

# Stop services
docker-compose down
```

This will automatically build Docker images for each service and start them with proper networking.

## Access the Application

Once all services are running, open your browser:

```
http://localhost:8083
```

## How to Use

1. **Click "I'm Sorry"** box → Shows "I'M SORRY ❤️ ❤️ ❤️"
2. **Click "I Miss You"** box → Shows "I MISS YOU ❤️ ❤️ ❤️"
3. **Watch the hearts animate** for 3 seconds
4. **Check statistics** below the boxes
5. **Keep clicking** to see the counters update!

## Services Overview

| Service | Port | Purpose |
|---------|------|---------|
| Message Service | 8081 | Provides emotion messages |
| User Service | 8082 | Tracks clicks and reactions |
| UI Service | 8083 | Beautiful web interface |

## Troubleshooting

### Port Already in Use
```bash
# Find process using port 8081
netstat -ano | findstr :8081
# Kill process
taskkill /PID <PID> /F
```

### Service Won't Start
- Check Java is installed: `java -version`
- Check Maven is installed: `mvn -version`
- Check no other services running on that port

### UI Not Loading
- Ensure all 3 services are fully started
- Check browser console for errors (F12)
- Try clearing browser cache

## API Testing

Test the APIs directly:

```bash
# Get sorry message
curl http://localhost:8081/api/messages/sorry

# Get miss you message
curl http://localhost:8081/api/messages/miss-you

# Record a reaction
curl -X POST "http://localhost:8082/api/reactions/record?messageType=SORRY&reactionType=CLICKED"

# Get total clicks
curl http://localhost:8082/api/reactions/total-clicks

# Get UI health
curl http://localhost:8083/api/health
```

## Project Files

- `README.md` - Full documentation
- `UI_DEMO.md` - Detailed UI description
- `build.bat` - Windows build script
- `build.sh` - Linux/Mac build script
- `runall.bat` - Run all services (Windows)

## Next Steps

1. **Explore the UI** - Try clicking the boxes
2. **Watch the animations** - Beautiful heart animations
3. **Check statistics** - See real-time clicks
4. **Customize** - Modify colors, messages, or add features

## Support

Check the logs in each terminal for error messages. All services log to console with DEBUG level.

---

**Ready to express your emotions?** 💕

Visit http://localhost:8083 now!
