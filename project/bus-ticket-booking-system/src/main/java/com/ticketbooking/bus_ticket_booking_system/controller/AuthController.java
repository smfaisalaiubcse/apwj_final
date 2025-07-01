package com.ticketbooking.bus_ticket_booking_system.controller;

import com.ticketbooking.bus_ticket_booking_system.model.User;
import com.ticketbooking.bus_ticket_booking_system.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signup(@RequestBody User user) {
        authService.signup(user);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully.");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
        String token = authService.login(user.getEmail(), user.getPassword());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
}
