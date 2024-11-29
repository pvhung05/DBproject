package com.example.demo.repository;

import com.example.demo.model.Food;  // Food model class
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
    // Custom queries can be defined here if needed
}
