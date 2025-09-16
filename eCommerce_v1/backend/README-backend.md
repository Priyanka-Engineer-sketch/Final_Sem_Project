# E‑Commerce Backend — README

> Production‑ready Spring Boot microservices for an AI‑powered, PWA‑fronted e‑commerce platform.

---

## Table of Contents
- [Overview](#overview)
- [Architecture](#architecture)
- [Services](#services)
- [Tech Stack](#tech-stack)
- [Monorepo Layout](#monorepo-layout)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Clone & Bootstrap](#clone--bootstrap)
  - [Configuration](#configuration)
  - [Run Locally (Dev Profile)](#run-locally-dev-profile)
  - [Run with Docker Compose](#run-with-docker-compose)
- [Database & Migrations](#database--migrations)
- [Security & Auth](#security--auth)
- [API Documentation](#api-documentation)
- [Events & Messaging](#events--messaging)
- [Observability](#observability)
- [Testing](#testing)
- [Seeding & Demo Data](#seeding--demo-data)
- [Build, Versioning & CI/CD](#build-versioning---cicd)
- [Performance & Scalability](#performance--scalability)
- [Environment Profiles](#environment-profiles)
- [Kubernetes (Optional)](#kubernetes-optional)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)
- [License](#license)

---

## Overview

This repository houses the **backend** for a cloud‑native e‑commerce system. It exposes RESTful APIs for catalog, cart, orders, payments, and more. It supports **AI‑powered recommendations**, **JWT authentication**, **event‑driven processing** (Kafka), and adheres to **12‑factor** and **clean architecture** principles.

**Status:** Actively developed  
**Runtime:** Java (Spring Boot 3.x), Java 17+  
**Packaging:** Maven (multi‑module) or Gradle alternative  
**Deploy:** Docker / Kubernetes

---

## Architecture

- **Gateway & Edge:** API Gateway / Ingress for routing, auth, rate limiting.
- **Core Microservices:**
  - **auth‑svc** — JWT/OAuth2, users, roles, sessions.
  - **user‑svc** — profile, addresses, preferences, GDPR tools.
  - **catalog‑svc** — products, categories, search.
  - **inventory‑svc** — stock levels, reservations.
  - **pricing‑svc** — prices, discounts, taxes.
  - **cart‑svc** — shopping carts.
  - **order‑svc** — checkout, order lifecycle, returns.
  - **payment‑svc** — payment intents, webhooks.
  - **reco‑svc** — recommendations (content‑based/collaborative/hybrid).
  - **notification‑svc** — email/SMS/push via providers.
  - **media‑svc** — media upload (e.g., S3/MinIO), signed URLs.
- **Shared Libraries:** common DTOs, error model, logging, tracing, test utils.
- **Data:** MongoDB (catalog, carts), Postgres (orders/payments), Redis (cache/session), Kafka (events), Object storage (MinIO/S3).

A classic **synchronous API** + **asynchronous events** blend: user‑facing requests are REST; domain events (e.g., `OrderPlaced`, `StockReserved`, `PaymentCaptured`) flow over Kafka for eventual processing.

---

## Services

Each service is a Spring Boot app with its own DB (polyglot where appropriate) and Dockerfile. Minimal boundary coupling via HTTP + events.

| Service | Port (dev) | DB | Description |
|---|---:|---|---|
| gateway | 8080 | — | Routes to downstream services; central auth/metrics. |
| auth‑svc | 8081 | Postgres | JWT/OAuth2, login, refresh tokens. |
| user‑svc | 8082 | Postgres | Profiles, addresses, preferences. |
| catalog‑svc | 8083 | MongoDB | Products, categories, search facets. |
| inventory‑svc | 8084 | Postgres | Stock, reservations, allocations. |
| pricing‑svc | 8085 | Postgres | Price lists, discount rules, tax. |
| cart‑svc | 8086 | MongoDB | Carts, merge on sign‑in, coupons. |
| order‑svc | 8087 | Postgres | Orders, state machine, returns. |
| payment‑svc | 8088 | Postgres | Payment intents, provider webhooks. |
| reco‑svc | 8089 | MongoDB | Personalized recommendations. |
| notification‑svc | 8090 | Postgres | Email/SMS/push. |
| media‑svc | 8091 | S3/MinIO | Uploads, presigned URLs. |

> Ports are defaults and can be overridden per profile.

---

## Tech Stack

- **Language/Framework:** Java 17+, Spring Boot 3.x, Spring Data, Spring Security
- **Build:** Maven (Wrapper provided), optional Gradle
- **Datastores:** PostgreSQL, MongoDB, Redis
- **Messaging:** Apache Kafka
- **Object Storage:** MinIO/S3
- **Docs:** OpenAPI/Swagger
- **Auth:** OAuth2 Resource Server + JWT (RSA256)
- **Testing:** JUnit 5, Testcontainers, WireMock, Rest Assured
- **Observability:** OpenTelemetry (traces/metrics/logs), Micrometer, Prometheus/Grafana
- **Deployment:** Docker, Docker Compose, Helm, Kubernetes

---

## Monorepo Layout

```
/backend
  /gateway
  /services
    /auth-svc
    /user-svc
    /catalog-svc
    /inventory-svc
    /pricing-svc
    /cart-svc
    /order-svc
    /payment-svc
    /reco-svc
    /notification-svc
    /media-svc
  /libs
    /common-dto
    /common-security
    /common-test
  /infra
    docker-compose.yml
    helm/
  pom.xml
```

---

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.9+ (Wrapper included: `./mvnw`)
- Docker 24+ / Docker Desktop
- Node 18+ (only if building API docs UI locally)
- `make` (optional convenience)

### Clone & Bootstrap

```bash
git clone <your-repo-url> ecom-backend
cd ecom-backend
./mvnw -q -DskipTests package
```

### Configuration

Copy environment template and adjust as needed:

```bash
cp .env.example .env
```

**`.env.example`** (excerpt):

```dotenv
# Common
APP_ENV=dev
JAVA_TOOL_OPTIONS=-Xms512m -Xmx1024m

# JWT / Auth
JWT_ISSUER=https://api.example.com
JWT_AUDIENCE=ecom-clients
JWT_PUBLIC_KEY_PATH=./infra/keys/jwt.pub
JWT_PRIVATE_KEY_PATH=./infra/keys/jwt.key

# Databases
POSTGRES_URL=jdbc:postgresql://postgres:5432/ecom
POSTGRES_USER=ecom
POSTGRES_PASSWORD=ecom
MONGO_URL=mongodb://mongo:27017/ecom
REDIS_URL=redis://redis:6379

# Kafka
KAFKA_BROKERS=broker:29092

# Object storage
S3_ENDPOINT=http://minio:9000
S3_BUCKET=ecom-media
S3_ACCESS_KEY=minioadmin
S3_SECRET_KEY=minioadmin
```

Service‑specific properties live in each module under `src/main/resources/application-*.yaml` and are merged by Spring profiles.

### Run Locally (Dev Profile)

Start core dependencies with Docker Compose and run one service from your IDE/terminal:

```bash
docker compose -f infra/docker-compose.core.yml up -d
./mvnw -pl services/catalog-svc -am spring-boot:run -Dspring-boot.run.profiles=dev
```

Or run **all services** (dev):

```bash
make up      # or: docker compose -f infra/docker-compose.dev.yml up --build
```

### Run with Docker Compose

One‑shot experience (build images then start stack):

```bash
make build   # ./mvnw -DskipTests package && docker build ...
make stack   # docker compose -f infra/docker-compose.dev.yml up -d
```

Once up, verify health:

- Gateway: `http://localhost:8080/actuator/health`
- Swagger UI (example): `http://localhost:8083/swagger-ui/index.html`

---

## Database & Migrations

- **Postgres:** managed by **Flyway** migrations per service (e.g., `order-svc/src/main/resources/db/migration`).
- **MongoDB:** managed via **mongock** or idempotent bootstrap scripts.
- **Redis:** used for ephemeral cache/sessions; no migrations.

Use **Testcontainers** in tests to guarantee consistent DB versions.

---

## Security & Auth

- OAuth2 Resource Server w/ **JWT** (RS256).
- Endpoints are secured by default; use `@PreAuthorize`/`@PermitAll` annotations for granularity.
- Token issuance via `auth-svc` (`/auth/login`, `/auth/refresh`).
- Roles: `ROLE_USER`, `ROLE_ADMIN`, `ROLE_SUPPORT`.
- CORS is enabled per service for the PWA domain(s).

**Example: Obtain Token**

```bash
curl -X POST http://localhost:8081/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"demo@shop.com","password":"demo123"}'
```

**Use Token:**

```bash
curl http://localhost:8083/api/v1/products \
  -H "Authorization: Bearer <JWT>"
```

---

## API Documentation

Each service ships OpenAPI docs at:

- JSON: `/v3/api-docs`
- UI: `/swagger-ui/index.html`

A consolidated spec can be generated via **openapi-aggregator** behind the gateway (optional).

---

## Events & Messaging

Kafka topics (convention):

- `order.events.v1`
- `payment.events.v1`
- `inventory.events.v1`
- `catalog.events.v1`
- `notification.events.v1`

Event schema is versioned (Avro/JSON‑Schema). Use **confluent‑schema‑registry** or equivalent when available.

---

## Observability

- **Tracing:** OpenTelemetry auto‑instrumentation → OTLP → Jaeger/Tempo
- **Metrics:** Micrometer → Prometheus → Grafana dashboards
- **Logs:** JSON logs w/ request ID and span context; log level via env (`LOG_LEVEL=INFO`)

Actuator endpoints (health, readiness, liveness) are enabled under `/actuator/**`.

---

## Testing

- **Unit tests:** JUnit 5, Mockito
- **Web tests:** Spring MVC test, Rest Assured
- **Integration tests:** Testcontainers (Postgres/Mongo/Kafka/Redis/MinIO)
- **Contract tests:** Spring Cloud Contract (optional)

Run all tests:

```bash
./mvnw clean verify
```

---

## Seeding & Demo Data

Dev profile seeds demo users, products, and coupons. Disable seeding with `seed.enabled=false` or via profile `prod` which never seeds data.

---

## Build, Versioning & CI/CD

- **Build:** `./mvnw -DskipTests package`
- **Artifacts:** Docker images tagged as `ghcr.io/org/service:<semver>-<gitsha>`
- **Versioning:** **SemVer** + conventional commits (`feat:`, `fix:`)
- **CI:** GitHub Actions/ GitLab CI pipelines
  - Lint → Test → Build → SBOM → Trivy scan → Push image → Deploy to staging
- **CD:** Argo CD/Flux for GitOps (optional)

---

## Performance & Scalability

- Stateless services; horizontal scale behind gateway.
- Caching at multiple layers (HTTP cache headers, Redis, local Caffeine).
- Idempotency keys on checkout/payment.
- Back‑pressure & circuit breakers (Resilience4j).

---

## Environment Profiles

- `dev` — relaxed security, verbose logging, seed data.
- `staging` — prod‑like infra with masked secrets.
- `prod` — hardened settings, no seeding, strict CORS, stricter timeouts.

Select via `SPRING_PROFILES_ACTIVE` or `--spring.profiles.active`.

---

## Kubernetes (Optional)

Helm charts under `infra/helm/*`. Minimal install example:

```bash
helm upgrade --install ecom-backend infra/helm/platform \
  --set image.tag=1.0.0 \
  --namespace ecom --create-namespace
```

Probes are configured:
- **livenessProbe:** `/actuator/health/liveness`
- **readinessProbe:** `/actuator/health/readiness`

---

## Troubleshooting

- **Port already in use:** stop conflicting service or change port in `application-dev.yaml`.
- **DB connection refused:** ensure `docker compose ps` shows `postgres`/`mongo` healthy.
- **JWT invalid:** verify clock skew and correct public key mounted.
- **Kafka timeouts:** check broker URL (`PLAINTEXT://broker:29092`) and topic ACLs.

---

## Contributing

1. Fork & create a feature branch.
2. Follow **Conventional Commits**.
3. Add/Update tests; run `./mvnw verify`.
4. Open a PR with context, screenshots (if applicable), and checklist.

See `CONTRIBUTING.md` and `CODE_OF_CONDUCT.md` for details.

---

## License

This project is licensed under the **MIT License**. See `LICENSE` for details.
