package com.example.Food.Ordering.and.Delivery.System.repository;

import com.example.Food.Ordering.and.Delivery.System.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Custom query method to find orders by user ID
    List<Order> findByUserId(Long userId);
}
