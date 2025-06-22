Inbox Notifications Microservice

This microservice is a part of the Fitness Web Application ecosystem. It handles real-time in-app notifications using an event-driven architecture with Apache Kafka, ensuring users receive timely messages related to registration, workout completions, and points earned.

------

Key Responsibilities

- Store and retrieve in-app inbox messages for users
- Listen to Kafka events to trigger notification creation
- Expose REST endpoints for fetching user-specific messages

------

Features

- Inbox Notifications API
- Users can retrieve their inbox messages, ordered by most recent.

------

Kafka Integration
- Listens for events (e.g., UserRegistered, WorkoutCompleted) and persists notifications.

Persistent Storage
- Inbox messages are stored using a relational database (MySQL).

RESTful Design
- Clean REST API with endpoint:

------

Kafka Events Processed

- UserRegistered → Triggers welcome inbox message
- WorkoutCompleted → Sends workout success message

------

Integration with Fitness App

This microservice is decoupled and communicates with the main Fitness App via:
- Apache Kafka for receiving event payloads
- OpenFeign from the main app to consume inbox API (if needed)

------

Prerequisites

- Java 17+
- Kafka and Zookeeper running
- MySQL database
- Maven
