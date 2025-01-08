package com.example.Food.Ordering.and.Delivery.System.controller;

import com.example.Food.Ordering.and.Delivery.System.model.Order;
import com.example.Food.Ordering.and.Delivery.System.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<Order> placeOrder(@RequestParam Long userId, @RequestBody List<Long> foodItemIds) {
        Order order = orderService.placeOrder(userId, foodItemIds);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersForUser(@PathVariable Long userId) {
        List<Order> orders = orderService.getOrdersForUser(userId);
        return ResponseEntity.ok(orders);
    }
}
