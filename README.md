# Bike E-commerce Backend API

This is a Spring Boot REST API for a bike e-commerce backend, built with Java 17, Spring Data JPA, and H2 in-memory database.

## Technologies
- Spring Boot 3.2.1
- Spring Web
- Spring Data JPA
- H2 Database
- Maven

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Build and Run
1. Build the project:
   ```bash
   mvn clean install
   ```
2. Run the application:
   ```bash
   mvn spring-boot:run
   ```
The API will be available at `http://localhost:8080`.

## REST API Endpoints

### Bikes
- `GET /bikes` - Get all bikes
- `GET /bikes/{id}` - Get bike details
- `GET /bikes/search?category=...&minPrice=...&maxPrice=...` - Search bikes with optional filters

### Cart
- `GET /carts/{userId}` - Get user's cart
- `POST /carts/{userId}/bikes/{bikeId}` - Add a bike to the cart
- `DELETE /carts/{userId}/bikes/{bikeId}` - Remove a bike from the cart

## H2 Console
The H2 console is enabled at `http://localhost:8080/h2-console`.
- **JDBC URL:** `jdbc:h2:mem:bikedb`
- **Username:** `sa`
- **Password:** `password`

## Project Structure
- `com.bike.ecommerce` - Root package
  - `model` - Entity classes (Bike, Cart)
  - `repository` - Spring Data JPA repositories
  - `service` - Business logic layer
  - `controller` - REST controllers
  - `exception` - Custom exceptions