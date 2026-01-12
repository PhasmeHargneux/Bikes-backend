package com.bike.ecommerce.controller;

import com.bike.ecommerce.exception.BadRequestException;
import com.bike.ecommerce.model.Accessory;
import com.bike.ecommerce.service.AccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/accessories")
public class AccessoryController {

    @Autowired
    private AccessoryService accessoryService;

    private static final Set<String> ALLOWED_SEARCH_PARAMS = Set.of("minPrice", "maxPrice");

    @GetMapping
    public List<Accessory> getAllAccessories() {
        return accessoryService.getAllAccessories();
    }

    @GetMapping("/{id}")
    public Accessory getAccessoryById(@PathVariable Long id) {
        return accessoryService.getAccessoryById(id);
    }

    @GetMapping("/search")
    public List<Accessory> searchAccessories(
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam Map<String, String> allParams) {

        // Validate parameter keys
        for (String key : allParams.keySet()) {
            if (!ALLOWED_SEARCH_PARAMS.contains(key)) {
                throw new BadRequestException("Invalid query parameter: '" + key +
                        "'. Allowed parameters are: " + ALLOWED_SEARCH_PARAMS);
            }
        }

        return accessoryService.filterByPrice(minPrice, maxPrice);
    }
}
