package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public boolean authenticate(String username, String password) {
        return employeeRepository.findByName(username)
                .map(employee -> employee.getPhone().equals(password))
                .orElse(false);
    }
}
