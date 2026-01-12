package com.bike.ecommerce.service;

import com.bike.ecommerce.exception.ResourceNotFoundException;
import com.bike.ecommerce.model.Bike;
import com.bike.ecommerce.model.Cart;
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
}
