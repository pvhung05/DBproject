package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginDTO login) {
        boolean isAuthenticated = loginService.authenticate(login.getUsername(), login.getPassword());

        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(401).body("Invalid username or password.");
        }
    }
}
