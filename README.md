# Weather App — Secure Full-Stack Demo Application

A full-stack weather application demonstrating **enterprise-grade authentication**, **clean backend architecture**, and **containerized infrastructure**.

This project is designed as a **portfolio and learning project** to showcase practical backend and full-stack engineering skills rather than as a production system.

---

## Overview

The application provides:

- Public weather forecast data from an external API  
- Secure storage and retrieval of weather observations  
- OAuth2 / OpenID Connect authentication using Keycloak  
- JWT-protected REST API  
- Docker-based local infrastructure  

---

## Architecture

┌────────────┐ ┌──────────────┐
│ Frontend   JWT   Backend    
│ Angular    ─▶  Spring Boot │
│ (8088) │ │       (8083) │
└─────▲──────┘ └─────▲────────┘
│   OAuth2    │      JPA
│     ▼
┌─────┴──────┐ ┌──────────────┐
│  Keycloak  │ │  PostgreSQL  │
│   (8080)   │ │    (5432)    │
└────────────┘ └──────────────┘

---

## Technology Stack

### Backend
- Java 21  
- Spring Boot 3  
- Spring Security (OAuth2 Resource Server)  
- JPA / Hibernate  
- PostgreSQL  
- Maven  

### Frontend
- Angular  
- TypeScript  
- Fetch API  
- Keycloak JavaScript adapter  

### Infrastructure
- Docker & Docker Compose  
- Keycloak (Quarkus distribution)  
- PostgreSQL  
- Nginx (frontend container)  

---

## Authentication & Security

Authentication is implemented using **Keycloak** as an Identity Provider.

- Protocol: OAuth2 / OpenID Connect  
- Token format: JWT  
- Backend acts as an OAuth2 Resource Server  
- Tokens are passed via `Authorization: Bearer <token>`  

### Access Control

| Endpoint             | Access Level                |
|----------------------|-----------------------------|
| `/api/health`        | Public                      |
| `/api/forecast`      | Public                      |
| `/api/me`            | Authenticated               |
| `/api/observations` | Authenticated (`ROLE_USER`) |

---

## API Endpoints

### `GET /api/health`
Health check endpoint for backend monitoring.  
**Access:** Public  

---

### `GET /api/forecast?city=Berlin`
Returns weather forecast data retrieved from the Open-Meteo API.  
**Access:** Public  

---

### `GET /api/me`
Diagnostic endpoint for inspecting the authenticated principal.

Returns:
- Subject  
- Username  
- Issuer  
- Granted authorities  
- Raw JWT claims  

**Access:** Authenticated  

---

### `GET /api/observations`
Returns a paginated list of stored weather observations.  
**Access:** Authenticated  

---

### `POST /api/observations`
Creates a new weather observation entry.  
**Access:** Authenticated  

---

## Docker Setup

The entire application can be started locally using Docker Compose:

docker compose up -d

## Services

| Service     | Port |
|------------|------|
| Frontend   | 8088 |
| Backend    | 8083 |
| Keycloak   | 8080 |
| PostgreSQL | 5432 |

Keycloak is configured to use PostgreSQL instead of the default in-memory database, ensuring persistence across restarts.

---

## Test User

After startup, a test user can be used to log in:

- **Username:** `test`  
- **Password:** `test123`  
- **Realm:** `weather`  

---

## Swagger API Documentation

Swagger UI is available at:

http://localhost:8083/swagger-ui/index.html

For protected endpoints, a valid JWT must be provided using the **Authorize** button.

---

## Backend Architecture

The backend follows a layered architecture inspired by **Domain-Driven Design** principles:

- `domain` — core business models and abstractions  
- `application` — application services and use cases  
- `infrastructure` — persistence, security, external API clients  
- `presentation` — REST controllers  

This structure improves **maintainability**, **testability**, and **separation of concerns**.

---

## Purpose of the Project

This project serves as a demonstration of real-world backend engineering skills, including:

- Secure API design  
- OAuth2 / OIDC authentication flows  
- JWT handling  
- Dockerized development environments  
- Clean and maintainable backend architecture  

It is intended for **portfolio and educational purposes**, not for production deployment.

---

## Possible Improvements

- Refresh token handling and rotation  
- Fine-grained role-based authorization  
- Frontend route guards  
- Centralized logging and monitoring  
- CI/CD pipeline integration  

---

## Author

Developed as a personal learning and portfolio project  
to demonstrate backend and full-stack development capabilities.

