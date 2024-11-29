package com.example.demo.service;

import com.example.demo.model.Food;
import com.example.demo.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    // Get all Food products
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    // Get a Food product by ID
    public Optional<Food> getFoodById(Long id) {
        return foodRepository.findById(id);
    }

    // Add a new Food product
    public Food addFood(Food food) {
        validateFoodDates(food);
        return foodRepository.save(food);

    }

    // Update an existing Food product by ID
    public Food updateFood(Long id, Food foodDetails) {
        validateFoodDates(foodDetails);
        return foodRepository.findById(id)
                .map(existingFood -> {
                    existingFood.setName(foodDetails.getName());
                    existingFood.setPrice(foodDetails.getPrice());
                    existingFood.setQuantity(foodDetails.getQuantity());
                    existingFood.setImportDate(foodDetails.getImportDate());
                    existingFood.setExpiryDate(foodDetails.getExpiryDate());
                    return foodRepository.save(existingFood);
                })
                .orElseThrow(() -> new RuntimeException("Food not found with ID " + id));
    }

    // Delete a Food product by ID
    public void deleteFood(Long id) {
        foodRepository.deleteById(id);
    }
    private void validateFoodDates(Food food) {
        Date importDate = food.getImportDate();
        Date expiryDate = food.getExpiryDate();

        if (importDate == null || expiryDate == null) {
            throw new IllegalArgumentException("Import date and expiry date cannot be null.");
        }

        if (!expiryDate.after(importDate)) {
            throw new IllegalArgumentException("Expiry date must be after import date.");
        }
    }
}

