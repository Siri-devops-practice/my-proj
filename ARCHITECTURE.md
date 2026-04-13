# Architecture Overview

## Microservices Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                         Browser/Client                       │
│                                                               │
│  http://localhost:8083                                       │
└──────────────────────────────┬──────────────────────────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │   UI Service         │
                    │   (Port 8083)        │
                    │                      │
                    │  - Templates (HTML)  │
                    │  - Static Files      │
                    │  - Page Controller   │
                    └────────┬─────────────┘
                             │
                    ┌────────┴────────┐
                    │                 │
                    ▼                 ▼
         ┌──────────────────┐  ┌──────────────────┐
         │ Message Service  │  │ User Service     │
         │ (Port 8081)      │  │ (Port 8082)      │
         │                  │  │                  │
         │ GET /messages/   │  │ POST /reactions/ │
         │ sorry            │  │ record           │
         │ miss-you         │  │                  │
         │ love             │  │ GET /total-      │
         │                  │  │ clicks           │
         │ - Message Model  │  │                  │
         │ - Message Svc    │  │ - Reaction Mdl   │
         │ - Message Ctrl   │  │ - Reaction Svc   │
         │                  │  │ - Reaction Ctrl  │
         └──────────────────┘  └──────────────────┘
```

## Service Interactions

### User Clicks "I'm Sorry" Box

```
1. Browser (UI)
   │
   ├─→ Fetch /api/messages/sorry (Message Service)
   │   └─→ Returns Message Object with "I'M SORRY ❤️"
   │
   ├─→ POST /api/reactions/record (User Service)
   │   └─→ Records click and returns reaction
   │
   └─→ Update UI with popup and animations
```

### Real-time Statistics

```
UI Service polls User Service:
├─→ GET /api/reactions/total-clicks
├─→ GET /api/reactions/clicks/SORRY
└─→ GET /api/reactions/clicks/MISS_YOU

Updates statistics display in real-time
```

## Data Flow

### Message Delivery Flow

```
┌──────────────────────────────────────────────────────────┐
│ User clicks "I'm Sorry" box                               │
└─────────────────┬────────────────────────────────────────┘
                  │
                  ▼
┌──────────────────────────────────────────────────────────┐
│ JavaScript (app.js):                                      │
│ - Capture click event                                     │
│ - Call message API                                        │
└─────────────────┬────────────────────────────────────────┘
                  │
                  ▼ HTTP Request
┌──────────────────────────────────────────────────────────┐
│ Message Service (Port 8081):                              │
│ MessageController.getSorryMessage()                       │
│   └─→ MessageService.getSorryMessage()                    │
│       └─→ Creates Message object with "I'M SORRY..."     │
└─────────────────┬────────────────────────────────────────┘
                  │
                  ▼ JSON Response
┌──────────────────────────────────────────────────────────┐
│ UI receives:                                              │
│ {                                                         │
│   "messageId": "...",                                     │
│   "type": "SORRY",                                        │
│   "content": "I'M SORRY ❤️ ❤️ ❤️",                       │
│   "emoji": "😔",                                         │
│   "timestamp": 1681234567890                             │
│ }                                                         │
└─────────────────┬────────────────────────────────────────┘
                  │
                  ▼
┌──────────────────────────────────────────────────────────┐
│ Show popup with message                                   │
│ Animate hearts                                            │
│ Auto-close after 3 seconds                                │
└──────────────────────────────────────────────────────────┘
```

### Reaction Recording Flow

```
┌──────────────────────────────────────────────────────────┐
│ Message displayed successfully                            │
└─────────────────┬────────────────────────────────────────┘
                  │
                  ▼
┌──────────────────────────────────────────────────────────┐
│ JavaScript records the reaction:                          │
│ POST /api/reactions/record?messageType=SORRY&...          │
└─────────────────┬────────────────────────────────────────┘
                  │
                  ▼ HTTP Request
┌──────────────────────────────────────────────────────────┐
│ User Service (Port 8082):                                │
│ ReactionController.recordReaction()                       │
│   └─→ UserReactionService.recordReaction()              │
│       └─→ Creates UserReaction & stores in Map           │
│       └─→ Increments clickCounter                        │
└─────────────────┬────────────────────────────────────────┘
                  │
                  ▼ JSON Response
┌──────────────────────────────────────────────────────────┐
│ {                                                         │
│   "reactionId": "...",                                    │
│   "messageType": "SORRY",                                 │
│   "reactionType": "CLICKED",                              │
│   "timestamp": 1681234567890,                             │
│   "userSession": "..."                                    │
│ }                                                         │
└─────────────────┬────────────────────────────────────────┘
                  │
                  ▼
┌──────────────────────────────────────────────────────────┐
│ Update statistics in UI                                   │
│ Call GET /api/reactions/total-clicks                      │
│ Call GET /api/reactions/clicks/SORRY                      │
│ Call GET /api/reactions/clicks/MISS_YOU                   │
└──────────────────────────────────────────────────────────┘
```

## Technology Stack

```
Frontend (Browser)
├─ HTML5 (Semantic structure)
├─ CSS3 (Animations, gradients, flexbox)
└─ JavaScript (Fetch API, DOM manipulation)

Backend (Java/Spring Boot)
├─ Message Service
│  ├─ Spring Boot Web
│  ├─ REST Controller
│  └─ Service/Model layers
├─ User Service
│  ├─ Spring Boot Web
│  ├─ REST Controller
│  └─ Service/Model layers
└─ UI Service
   ├─ Spring Boot Web
   ├─ Thymeleaf
   └─ Static file serving

Build & Deployment
├─ Maven (Build)
├─ Java 11 (Runtime)
└─ Spring Boot (Framework)
```

## API Contracts

### Message Service API
```
Endpoint: /api/messages/{type}
Method: GET
Response:
{
  "messageId": string (UUID),
  "type": string (SORRY|MISS_YOU|LOVE),
  "content": string (Full message text),
  "emoji": string (Emoji representation),
  "timestamp": long (Unix timestamp)
}
```

### User Service API
```
Endpoint: /api/reactions/record
Method: POST
Params:
  - messageType: SORRY or MISS_YOU
  - reactionType: CLICKED
Response:
{
  "reactionId": string (UUID),
  "messageType": string,
  "reactionType": string,
  "timestamp": long,
  "userSession": string (UUID)
}

Endpoint: /api/reactions/total-clicks
Method: GET
Response: 5 (integer)

Endpoint: /api/reactions/clicks/{messageType}
Method: GET
Response: 3 (integer)
```

## Deployment Model

Each microservice is independently deployable:

```
┌─────────────────┐  ┌─────────────────┐  ┌─────────────────┐
│   Message Svc   │  │   User Svc      │  │   UI Svc        │
│   (JAR)         │  │   (JAR)         │  │   (JAR)         │
│   Port 8081     │  │   Port 8082     │  │   Port 8083     │
└─────────────────┘  └─────────────────┘  └─────────────────┘
        ↑                    ↑                      ↑
        └────────────────────┴──────────────────────┘
                 Run Independently
```

## Scalability Considerations

- Services can run on different machines
- Load balancer can route to multiple instances
- Stateless services (except in-memory data)
- REST communication allows loose coupling
- Easy to add caching layer
- Database can be external (currently in-memory)

## Future Architecture Enhancements

```
├─ API Gateway (central entry point)
├─ Service Discovery (Eureka/Consul)
├─ Message Queue (RabbitMQ/Kafka)
├─ Caching Layer (Redis)
├─ Database Persistence (PostgreSQL)
├─ Authentication Service (JWT/OAuth)
├─ Monitoring & Logging (ELK Stack)
├─ Service Mesh (Istio)
└─ Container Orchestration (Kubernetes)
```

---

**Simple, Clean, and Scalable** microservices architecture!
