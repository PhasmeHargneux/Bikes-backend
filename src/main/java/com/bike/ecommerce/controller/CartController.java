package com.bike.ecommerce.controller;

import com.bike.ecommerce.model.Cart;
import com.bike.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public Cart getCart(@PathVariable Long userId) {
        return cartService.getCartByUserId(userId);
    }

    @PostMapping("/{userId}/bikes/{bikeId}")
    public Cart addBikeToCart(@PathVariable Long userId, @PathVariable Long bikeId) {
        return cartService.addBikeToCart(userId, bikeId);
    }

    @DeleteMapping("/{userId}/bikes/{bikeId}")
    public Cart removeBikeFromCart(@PathVariable Long userId, @PathVariable Long bikeId) {
        return cartService.removeBikeFromCart(userId, bikeId);
    }

    @PostMapping("/{userId}/accessories/{accessoryId}")
    public Cart addAccessoryToCart(@PathVariable Long userId, @PathVariable Long accessoryId) {
        return cartService.addAccessoryToCart(userId, accessoryId);
    }

    @DeleteMapping("/{userId}/accessories/{accessoryId}")
    public Cart removeAccessoryFromCart(@PathVariable Long userId, @PathVariable Long accessoryId) {
        return cartService.removeAccessoryFromCart(userId, accessoryId);
    }
}
