package com.bike.ecommerce.repository;

import com.bike.ecommerce.model.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface BikeRepository extends JpaRepository<Bike, Long> {
    @Query("SELECT b FROM Bike b WHERE " +
           "(:category IS NULL OR b.category = :category) AND " +
           "(:minPrice IS NULL OR b.price >= :minPrice) AND " +
           "(:maxPrice IS NULL OR b.price <= :maxPrice)")
    List<Bike> searchBikes(@Param("category") String category,
                          @Param("minPrice") Double minPrice,
                          @Param("maxPrice") Double maxPrice);
}
