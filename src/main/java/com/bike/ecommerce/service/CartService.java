package com.bike.ecommerce.service;

import com.bike.ecommerce.exception.ResourceNotFoundException;
import com.bike.ecommerce.model.Accessory;
import com.bike.ecommerce.model.Bike;
import com.bike.ecommerce.model.Cart;
import com.bike.ecommerce.repository.AccessoryRepository;
import com.bike.ecommerce.repository.BikeRepository;
import com.bike.ecommerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BikeRepository bikeRepository;

    @Autowired
    private AccessoryRepository accessoryRepository;

    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> cartRepository.save(new Cart(userId)));
    }

    @Transactional
    public Cart addBikeToCart(Long userId, Long bikeId) {
        Cart cart = getCartByUserId(userId);
        Bike bike = bikeRepository.findById(bikeId)
                .orElseThrow(() -> new ResourceNotFoundException("Bike not found with id: " + bikeId));

        cart.getBikes().add(bike);
        return cartRepository.save(cart);
    }

    @Transactional
    public Cart removeBikeFromCart(Long userId, Long bikeId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for user: " + userId));

        Bike bike = bikeRepository.findById(bikeId)
                .orElseThrow(() -> new ResourceNotFoundException("Bike not found with id: " + bikeId));

        if (!cart.getBikes().remove(bike)) {
            throw new ResourceNotFoundException("Bike not found in cart");
        }

        return cartRepository.save(cart);
    }

    @Transactional
    public Cart addAccessoryToCart(Long userId, Long accessoryId) {
        Cart cart = getCartByUserId(userId);
        Accessory accessory = accessoryRepository.findById(accessoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Accessory not found with id: " + accessoryId));

        cart.getAccessories().add(accessory);
        return cartRepository.save(cart);
    }

    @Transactional
    public Cart removeAccessoryFromCart(Long userId, Long accessoryId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for user: " + userId));

        Accessory accessory = accessoryRepository.findById(accessoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Accessory not found with id: " + accessoryId));

        if (!cart.getAccessories().remove(accessory)) {
            throw new ResourceNotFoundException("Accessory not found in cart");
        }

        return cartRepository.save(cart);
    }
}
