package com.example.demo.repository;

import com.example.demo.model.Employee;
import com.example.demo.model.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByName(String name);
    Optional<Employee> findById(long id);
    Optional<Employee> findByPhone(String phone);



}
