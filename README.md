# Emotion App - Creative Message Platform

A beautiful, interactive microservices application to express emotions with "Sorry" and "Miss You" messages with heart emojis. Built with Java, Maven, and Spring Boot across 3 independent services.

## Quick Overview

- **3 Microservices** running on different ports
- **Interactive Message Boxes** that show emotional messages with hearts
- **Real-time Click Tracking** with statistics
- **Beautiful Creative UI** with animations and gradients
- **RESTful APIs** for message delivery and reaction tracking

## Project Structure

```
sorry_proj/
├── pom.xml                          # Parent POM (3 services)
├── message-service/                 # Message delivery
│   ├── pom.xml
│   └── src/main/java/com/emotion/message/
│       ├── MessageServiceApplication.java
│       ├── controller/MessageController.java
│       ├── service/MessageService.java
│       └── model/Message.java
├── user-service/                    # Reaction tracking  
│   ├── pom.xml
│   └── src/main/java/com/emotion/user/
│       ├── UserServiceApplication.java
│       ├── controller/ReactionController.java
│       ├── service/UserReactionService.java
│       └── model/UserReaction.java
└── ui-service/                      # Beautiful web interface
    ├── pom.xml
    └── src/main/resources/
        ├── templates/index.html     (Beautiful message page)
        ├── static/css/style.css     (Animations & gradients)
        └── static/js/app.js         (Interactive logic)
```

## Services

### 1. Message Service (Port 8081)
Manages emotion messages: "I'm Sorry", "I Miss You", "I Love You"

**API Endpoints:**
- `GET /api/messages/sorry` - Get sorry message
- `GET /api/messages/miss-you` - Get miss you message
- `GET /api/messages/love` - Get love message

### 2. User Service (Port 8082)
Tracks user clicks and reactions with statistics

**API Endpoints:**
- `POST /api/reactions/record` - Record a click
- `GET /api/reactions/total-clicks` - Get total clicks
- `GET /api/reactions/clicks/{messageType}` - Get clicks by type
- `GET /api/reactions/{reactionId}` - Get specific reaction

### 3. UI Service (Port 8083)
Beautiful web interface with interactive message boxes

**Access:** `http://localhost:8083`

## Build Instructions

### Build All Services
```bash
cd sorry_proj
mvn clean install -DskipTests
```

## Run Services

**Terminal 1 - Message Service (8081):**
```bash
cd message-service
mvn spring-boot:run
```

**Terminal 2 - User Service (8082):**
```bash
cd user-service
mvn spring-boot:run
```

**Terminal 3 - UI Service (8083):**
```bash
cd ui-service
mvn spring-boot:run
```

## Access the Application

Once all services are running:
```
http://localhost:8083
```

## UI Features

### Message Boxes
- **"I'm Sorry"** box (Pink gradient) - Click to show "I'M SORRY ❤️ ❤️ ❤️"
- **"I Miss You"** box (Dark pink) - Click to show "I MISS YOU ❤️ ❤️ ❤️"
- Smooth animations and hover effects
- Auto-closing popups (3 seconds)

### Visual Effects
- Animated gradient background
- Pulsing emoji displays
- Heart beating animations
- Smooth transitions and scale effects
- Responsive design (mobile & desktop)

### Real-time Statistics
- Total clicks counter
- "I'm Sorry" clicks counter
- "I Miss You" clicks counter
- Updates after each message click

## Technology Stack

- **Language:** Java 11
- **Build Tool:** Maven
- **Framework:** Spring Boot 2.7.14
- **Frontend:** HTML5, CSS3, Vanilla JavaScript
- **Styling:** Modern gradients, animations, flexbox
- **Communication:** REST APIs with CORS

## File Descriptions

### [index.html](ui-service/src/main/resources/templates/index.html)
Main page with:
- Emotional message boxes (Sorry/Miss You)
- Interactive popups with heart animations
- Live statistics display
- Beautiful header and footer

### [style.css](ui-service/src/main/resources/static/css/style.css)
Styling with:
- CSS gradients (pink to purple)
- Keyframe animations (pulse, heartBeat, popIn)
- Responsive grid layout
- Beautiful shadows and transitions
- Mobile support

### [app.js](ui-service/src/main/resources/static/js/app.js)
Interactive logic:
- Fetch message data from services
- Record user reactions
- Update statistics in real-time
- Popup management
- Keyboard shortcuts (Escape)

## API Response Examples

### Message API Response
```json
{
  "messageId": "550e8400-e29b-41d4-a716-446655440000",
  "type": "SORRY",
  "content": "I'M SORRY ❤️ ❤️ ❤️",
  "emoji": "😔",
  "timestamp": 1681234567890
}
```

### Reaction Response
```json
{
  "reactionId": "660e8400-e29b-41d4-a716-446655440001",
  "messageType": "SORRY",
  "reactionType": "CLICKED",
  "timestamp": 1681234567890,
  "userSession": "770e8400-e29b-41d4-a716-446655440002"
}
```

## Testing

This project includes both unit and controller-level functional tests for a more realistic development workflow.

### Added tests
- `message-service`
  - `MessageServiceTest` — validates message content, type, emoji, and IDs
  - `MessageControllerTest` — verifies REST endpoint responses for message APIs
- `user-service`
  - `UserReactionServiceTest` — validates reaction recording and click counts
  - `ReactionControllerTest` — verifies reaction endpoint behavior
- `ui-service`
  - `UIControllerTest` — validates health and info endpoints for UI service

### Recommended test types for real projects
- **Unit tests**: verify individual classes, services, and utility logic.
- **Controller / functional tests**: verify API paths and request/response behavior.
- **Integration tests**: validate multiple parts working together with Spring context.
- **End-to-end tests**: validate user flows across the whole app (browser/UI automation).
- **Smoke tests**: quick checks to confirm key services start and basic flows run.
- **Regression tests**: protect against previously fixed bugs.
- **Performance/load tests**: validate timing and behavior under stress.
- **Security tests**: validate authentication, authorization, and input validation.

### Running tests
Run all modules:
```bash
mvn test
```
Run a single module:
```bash
cd message-service && mvn test
```
Or for the UI service:
```bash
cd ui-service && mvn test
```

## Troubleshooting

### Port Already in Use
Change port in `application.properties`:
```properties
server.port=9001
```

### CORS Issues
All services have CORS enabled with `@CrossOrigin(origins = "*")`

### Services Not Communicating
Ensure all services are running on correct ports (8081, 8082, 8083)

## Future Enhancements

- User authentication
- Database persistence
- Message history
- Leaderboards
- Sound effects
- Dark mode
- Message sharing
- Social integration

## License

MIT License

---

**Created:** April 2026  
**Version:** 1.0.0
