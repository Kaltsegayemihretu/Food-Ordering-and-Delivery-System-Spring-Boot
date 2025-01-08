package com.example.Food.Ordering.and.Delivery.System.service;

import com.example.Food.Ordering.and.Delivery.System.model.FoodItem;
import com.example.Food.Ordering.and.Delivery.System.model.Order;
import com.example.Food.Ordering.and.Delivery.System.model.User;
import com.example.Food.Ordering.and.Delivery.System.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private FoodItemService foodItemService;

    public Order placeOrder(Long userId, List<Long> foodItemIds) {
        Optional<User> userOpt = userService.getUserById(userId);
        if (!userOpt.isPresent()) {
            throw new RuntimeException("User not found");
        }

        List<FoodItem> foodItems = foodItemService.getFoodItemsByIds(foodItemIds);

        Order order = new Order();
        order.setUser(userOpt.get());
        order.setFoodItems(foodItems);
        order.setStatus("Pending");  // Set initial order status

        return orderRepository.save(order);
    }

    public List<Order> getOrdersForUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    // Additional methods for updating order status, etc.
}
