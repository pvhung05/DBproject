package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee createEmployee(Employee employee) {
        if (employeeRepository.findByPhone(employee.getPhone()).isPresent()) {
            throw new IllegalArgumentException("Phone number already exists!");
        }
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            employee.setName(employeeDetails.getName());
            employee.setPhone(employeeDetails.getPhone());
            employee.setBirthDate(employeeDetails.getBirthDate());
            employee.setAddress(employeeDetails.getAddress());
            return employeeRepository.save(employee);
        }
        return null;
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
    public boolean login(String name, String phone) {
        return employeeRepository.findByName(name)
                .map(employee -> employee.getPhone().equals(phone))
                .orElse(false);
    }
}