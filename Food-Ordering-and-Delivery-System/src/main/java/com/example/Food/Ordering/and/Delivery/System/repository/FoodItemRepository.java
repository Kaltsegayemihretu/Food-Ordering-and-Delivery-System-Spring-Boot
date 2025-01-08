package com.example.Food.Ordering.and.Delivery.System.repository;

import com.example.Food.Ordering.and.Delivery.System.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
    @Query("SELECT f FROM FoodItem f WHERE f.id IN :ids")
    List<FoodItem> findAllById(@Param("ids") List<Long> ids);
}

