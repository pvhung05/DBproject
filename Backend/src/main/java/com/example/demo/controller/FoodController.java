package com.example.demo.controller;

import com.example.demo.model.Food;
import com.example.demo.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    // Get all Food products
    @GetMapping
    public List<Food> getAllFoods() {
        return foodService.getAllFoods();
    }

    // Get a Food product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Food> getFoodById(@PathVariable Long id) {
        Optional<Food> food = foodService.getFoodById(id);
        return food.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Add a new Food product
    @PostMapping
    public ResponseEntity<Food> addFood(@RequestBody Food food) {
        Food createdFood = foodService.addFood(food);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFood);
    }

    // Update an existing Food product by ID
    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFood(@PathVariable Long id, @RequestBody Food foodDetails) {
        try {
            Food updatedFood = foodService.updateFood(id, foodDetails);

            return ResponseEntity.ok(updatedFood);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Delete a Food product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable Long id) {
        foodService.deleteFood(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
