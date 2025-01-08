package com.example.Food.Ordering.and.Delivery.System.repository;

import com.example.Food.Ordering.and.Delivery.System.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    // Additional custom queries can be added here if needed
}
