package com.bike.ecommerce.service;

import com.bike.ecommerce.exception.ResourceNotFoundException;
import com.bike.ecommerce.model.Bike;
import com.bike.ecommerce.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {

    @Autowired
    private BikeRepository bikeRepository;

    public List<Bike> getAllBikes() {
        return bikeRepository.findAll();
    }

    public Bike getBikeById(Long id) {
        return bikeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bike not found with id: " + id));
    }

    public List<Bike> searchBikes(String category, Double minPrice, Double maxPrice) {
        return bikeRepository.searchBikes(category, minPrice, maxPrice);
    }
}
