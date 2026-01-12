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

## Welcome Page
Navigating to the root path (`/`) provides a welcome message and a directory of all available API endpoints. This is useful for exploring the API without documentation.

## Authentication
To access protected endpoints (`/bikes/**`, `/accessories/**`, `/carts/**`), you must include the following header in your requests:
- **Header Key:** `Authentication`
- **Header Value:** `supermegasecrettoken`

The root path (`/`) and H2 Console remain public.

## REST API Endpoints

### Bikes
- `GET /bikes` - Get all bikes
- `GET /bikes/{id}` - Get bike details
- `GET /bikes/search?category=...&minPrice=...&maxPrice=...` - Search bikes with optional filters

### Accessories
- `GET /accessories` - Get all accessories
- `GET /accessories/{id}` - Get accessory details
- `GET /accessories/search?minPrice=...&maxPrice=...` - Search accessories by price range

### Cart
- `GET /carts/{userId}` - Get user's cart
- `POST /carts/{userId}/bikes/{bikeId}` - Add a bike to the cart
- `DELETE /carts/{userId}/bikes/{bikeId}` - Remove a bike from the cart
- `POST /carts/{userId}/accessories/{accessoryId}` - Add an accessory to the cart
- `DELETE /carts/{userId}/accessories/{accessoryId}` - Remove an accessory from the cart

## H2 Console
The H2 console is enabled at `http://localhost:8080/h2-console`.
- **JDBC URL:** `jdbc:h2:mem:bikedb`
- **Username:** `sa`
- **Password:** `password`

## Error Handling
The API uses standard HTTP status codes to indicate the success or failure of requests:
- **200 OK**: Request succeeded.
- **201 Created**: Resource (like a cart) was created successfully.
- **400 Bad Request**: Invalid parameters or typos in query strings (e.g., using `minprice` instead of `minPrice`).
- **401 Unauthorized**: Missing or invalid `Authentication` header.
- **404 Not Found**: The requested resource (Bike, Accessory, or Cart) does not exist.
- **500 Internal Server Error**: An unexpected error occurred on the server.

## Project Structure
- `com.bike.ecommerce` - Root package
  - `model` - Entity classes (Bike, Cart)
  - `repository` - Spring Data JPA repositories
  - `service` - Business logic layer
  - `controller` - REST controllers
  - `exception` - Custom exceptions