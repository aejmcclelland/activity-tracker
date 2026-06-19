# Activity Tracker API

A small Spring Boot backend for tracking activities such as running, cycling,
walking, jump rope, and strength training.

This project is intended to demonstrate clean first-version backend fundamentals
for a junior portfolio project. It focuses on a simple CRUD API,
request/response DTOs, validation, layered application structure, JPA
persistence, and controller testing.

## Learning Goals

- Build a Spring Boot CRUD REST API.
- Use DTOs to separate incoming requests and outgoing responses from JPA
  entities.
- Validate request data with Bean Validation.
- Keep a clear controller, service, and repository structure.
- Persist activity data using Spring Data JPA with an H2 in-memory database.
- Test controller behaviour with MockMvc.

## Tech Stack

- Java 21
- Spring Boot 4.0.6
- Spring Web MVC
- Spring Data JPA
- Bean Validation
- H2 database
- Maven Wrapper
- JUnit and MockMvc

## Project Structure

```text
activity-tracker/
├── pom.xml
├── mvnw
├── mvnw.cmd
├── src/
│   ├── main/
│   │   ├── java/com/andrew/activity_tracker/
│   │   │   ├── ActivityTrackerApplication.java
│   │   │   ├── controller/
│   │   │   │   └── ActivityController.java
│   │   │   ├── dto/
│   │   │   │   ├── ActivityRequest.java
│   │   │   │   └── ActivityResponse.java
│   │   │   ├── model/
│   │   │   │   ├── Activity.java
│   │   │   │   └── ActivityType.java
│   │   │   ├── repository/
│   │   │   │   └── ActivityRepository.java
│   │   │   └── service/
│   │   │       └── ActivityService.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/andrew/activity_tracker/
│           ├── ActivityTrackerApplicationTests.java
│           └── controller/
│               └── ActivityControllerTests.java
```

## Running Tests

```bash
./mvnw clean test
```

## Running Locally

```bash
./mvnw spring-boot:run
```

By default, the API runs on:

```text
http://localhost:8080
```

## API Endpoints

| Method   | Path                   | Purpose                     |
| -------- | ---------------------- | --------------------------- |
| `POST`   | `/api/activities`      | Create a new activity       |
| `GET`    | `/api/activities`      | Get all activities          |
| `GET`    | `/api/activities/{id}` | Get one activity by ID      |
| `PUT`    | `/api/activities/{id}` | Update an existing activity |
| `DELETE` | `/api/activities/{id}` | Delete an activity          |

## Supported Activity Types

- `RUNNING`
- `CYCLING`
- `JUMP_ROPE`
- `WALKING`
- `STRENGTH`

## Example Create Request

```json
{
	"activityType": "RUNNING",
	"activityDate": "2026-06-01",
	"durationMinutes": 30,
	"distanceMiles": 3.2,
	"notes": "Easy run"
}
```

## Example Response

```json
{
	"id": 1,
	"activityType": "RUNNING",
	"activityDate": "2026-06-01",
	"durationMinutes": 30,
	"distanceMiles": 3.2,
	"notes": "Easy run",
	"createdAt": "2026-06-01T10:15:30",
	"updatedAt": "2026-06-01T10:15:30"
}
```

## Validation Rules

| Field             | Rule                                                      |
| ----------------- | --------------------------------------------------------- |
| `activityType`    | Required. Must be one of the supported activity types.    |
| `activityDate`    | Required. Uses ISO date format, for example `2026-06-01`. |
| `durationMinutes` | Required. Must be at least `1`.                           |
| `distanceMiles`   | Optional. Must be zero or positive if supplied.           |
| `notes`           | Optional. Maximum length is 500 characters.               |

Invalid requests return a `400 Bad Request` response.

## Test Coverage Summary

The project includes:

- A Spring Boot context-load smoke test.
- MockMvc controller tests for creating activities.
- MockMvc controller tests for listing activities.
- MockMvc controller tests for getting an activity by ID.
- MockMvc controller tests for updating activities.
- MockMvc controller tests for deleting activities.
- MockMvc coverage for selected validation and not-found cases.

## Deliberate Non-Goals

The following features are deliberately postponed to keep this project focused
on first-version backend fundamentals:

- Authentication
- Users
- Frontend
- Docker
- PostgreSQL
- Database migrations
- Pagination and filtering
- Dashboards
- Training-plan features

## What I Learned

This project demonstrates how to structure a small Spring Boot backend around
clear responsibilities: controllers handle HTTP requests, DTOs define the API
contract, validation protects incoming data, services contain application logic,
and repositories manage persistence. It also shows how MockMvc can be used to
test REST endpoints without manually running the application.
