package com.example.demo.repository;

import com.example.demo.model.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HouseholdRepository extends JpaRepository<Household, Long> {
    Optional<Household> findByName(String name);
    // Bạn có thể thêm các phương thức tìm kiếm tùy chỉnh ở đây nếu cần
}
