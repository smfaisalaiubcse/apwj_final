package com.ticketbooking.bus_ticket_booking_system.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @PreAuthorize("hasRole('ADMIN')") // Only ADMIN access
    @GetMapping("/stats")
    public ResponseEntity<Map<String, String>> getStats() {
        return ResponseEntity.ok(Map.of("message", "Admin stats page"));
    }
}
