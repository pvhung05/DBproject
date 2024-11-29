package com.example.demo.controller;

import com.example.demo.model.Household;
import com.example.demo.service.HouseholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/households")
public class HouseholdController {

    private final HouseholdService householdService;

    @Autowired
    public HouseholdController(HouseholdService householdService) {
        this.householdService = householdService;
    }

    // Lấy tất cả sản phẩm gia dụng
    @GetMapping
    public List<Household> getAllHouseholds() {
        return householdService.getAllHouseholds();
    }

    // Lấy sản phẩm gia dụng theo ID
    @GetMapping("/{id}")
    public Optional<Household> getHouseholdById(@PathVariable Long id) {
        return householdService.getHouseholdById(id);
    }

    // Thêm mới sản phẩm gia dụng
    @PostMapping
    public Household addHousehold(@RequestBody Household household) {
        return householdService.addHousehold(household);
    }

    // Cập nhật sản phẩm gia dụng
    @PutMapping("/{id}")
    public Household updateHousehold(@PathVariable Long id, @RequestBody Household household) {
        return householdService.updateHousehold(id, household);
    }

    // Xóa sản phẩm gia dụng
    @DeleteMapping("/{id}")
    public void deleteHousehold(@PathVariable Long id) {
        householdService.deleteHousehold(id);
    }
}
