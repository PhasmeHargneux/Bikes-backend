package com.bike.ecommerce.service;

import com.bike.ecommerce.exception.ResourceNotFoundException;
import com.bike.ecommerce.model.Accessory;
import com.bike.ecommerce.repository.AccessoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessoryService {

    @Autowired
    private AccessoryRepository accessoryRepository;

    public List<Accessory> getAllAccessories() {
        return accessoryRepository.findAll();
    }

    public Accessory getAccessoryById(Long id) {
        return accessoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accessory not found with id: " + id));
    }

    public List<Accessory> filterByPrice(Double minPrice, Double maxPrice) {
        return accessoryRepository.findByPriceRange(minPrice, maxPrice);
    }
}
