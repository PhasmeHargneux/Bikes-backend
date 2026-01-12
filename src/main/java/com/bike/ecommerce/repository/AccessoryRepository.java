package com.bike.ecommerce.repository;

import com.bike.ecommerce.model.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface AccessoryRepository extends JpaRepository<Accessory, Long> {
    @Query("SELECT a FROM Accessory a WHERE " +
            "(:minPrice IS NULL OR a.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR a.price <= :maxPrice)")
    List<Accessory> findByPriceRange(@Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice);
}
