# Order Service

A scalable and production-ready Order Management Microservice built using Spring Boot following microservices architecture principles. The primary responsibility of this service is to manage customer orders including order creation, retrieval, filtering, searching, updating, and deletion operations.

The service is designed to work independently within a distributed system and communicates with other microservices such as user-service, product-catalogue-service, inventory-service, and payment-service using REST APIs and asynchronous messaging.

## Features
- Create new customer orders
- Retrieve order details
- Search orders by multiple criteria
- Filter orders based on status, date, user, or product
- Update existing orders
- Delete/cancel orders
- Pagination and sorting support
- RESTful API architecture
- Exception handling and validation
- Microservice-ready architecture
- Docker support
- Kafka event publishing support
- Production-ready configurations
- API documentation using Swagger/OpenAPI

## Tech Stack
- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Apache Kafka
- Maven
- Docker
- Swagger 
