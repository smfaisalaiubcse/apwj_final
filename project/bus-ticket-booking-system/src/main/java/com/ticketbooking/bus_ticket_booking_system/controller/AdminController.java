package com.ticketbooking.bus_ticket_booking_system.controller;

import com.ticketbooking.bus_ticket_booking_system.model.Trip;
import com.ticketbooking.bus_ticket_booking_system.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private TripService tripService;

    @PreAuthorize("hasRole('ADMIN')") // Only ADMIN access
    @GetMapping("/stats")
    public ResponseEntity<Map<String, String>> getStats() {
        return ResponseEntity.ok(Map.of("message", "Admin stats page"));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/trips/create")
    public ResponseEntity<String> createTrip(@RequestBody Trip trip) {
        tripService.createTrip(trip);
        return ResponseEntity.ok("Trip created successfully!");
    }
}
