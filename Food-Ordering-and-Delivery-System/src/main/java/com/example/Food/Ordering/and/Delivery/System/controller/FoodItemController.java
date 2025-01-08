package com.example.Food.Ordering.and.Delivery.System.controller;

import com.example.Food.Ordering.and.Delivery.System.model.FoodItem;
import com.example.Food.Ordering.and.Delivery.System.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/food-items")
public class FoodItemController {

    @Autowired
    private FoodItemService foodItemService;

    @GetMapping
    public List<FoodItem> getAllFoodItems() {
        return foodItemService.getAllFoodItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodItem> getFoodItemById(@PathVariable Long id) {
        Optional<FoodItem> foodItem = foodItemService.getFoodItemById(id);
        return foodItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public FoodItem createFoodItem(@RequestBody FoodItem foodItem) {
        return foodItemService.saveFoodItem(foodItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodItem> updateFoodItem(@PathVariable Long id, @RequestBody FoodItem updatedFoodItem) {
        return foodItemService.getFoodItemById(id)
                .map(existingFoodItem -> {
                    existingFoodItem.setName(updatedFoodItem.getName());
                    existingFoodItem.setDescription(updatedFoodItem.getDescription());
                    existingFoodItem.setPrice(updatedFoodItem.getPrice());
                    existingFoodItem.setCategory(updatedFoodItem.getCategory());
                    return ResponseEntity.ok(foodItemService.saveFoodItem(existingFoodItem));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFoodItem(@PathVariable Long id) {
        if (foodItemService.getFoodItemById(id).isPresent()) {
            foodItemService.deleteFoodItem(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
