package com.bike.ecommerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class RootController {

    @GetMapping("/")
    public Map<String, Object> index() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Welcome to the Bike E-commerce API");
        response.put("status", "Running");
        response.put("auth_requirement",
                "All protected endpoints require an 'Authentication' header with the secret token.");

        Map<String, Object> endpoints = new LinkedHashMap<>();

        // Bikes Endpoints
        Map<String, String> bikes = new LinkedHashMap<>();
        bikes.put("GET /bikes", "List all bikes");
        bikes.put("GET /bikes/{id}", "Get bike details");
        bikes.put("GET /bikes/search", "Search bikes (params: category, minPrice, maxPrice)");
        endpoints.put("Bikes", bikes);

        // Accessories Endpoints
        Map<String, String> accessories = new LinkedHashMap<>();
        accessories.put("GET /accessories", "List all accessories");
        accessories.put("GET /accessories/{id}", "Get accessory details");
        accessories.put("GET /accessories/search", "Search accessories (params: minPrice, maxPrice)");
        endpoints.put("Accessories", accessories);

        // Cart Endpoints
        Map<String, String> cart = new LinkedHashMap<>();
        cart.put("GET /carts/{userId}", "Get user cart");
        cart.put("POST /carts/{userId}/bikes/{bikeId}", "Add bike to cart");
        cart.put("DELETE /carts/{userId}/bikes/{bikeId}", "Remove bike from cart");
        cart.put("POST /carts/{userId}/accessories/{accId}", "Add accessory to cart");
        cart.put("DELETE /carts/{userId}/accessories/{accId}", "Remove accessory from cart");
        endpoints.put("Cart", cart);

        // Database Console
        endpoints.put("H2 Console", "/h2-console (JDBC URL: jdbc:h2:mem:bikedb)");

        response.put("endpoints", endpoints);

        return response;
    }
}
