package com.ticketbooking.bus_ticket_booking_system.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @PreAuthorize("hasRole('ADMIN')") // Only ADMIN access
    @GetMapping("/stats")
    public String getStats() {
        return "Admin stats page";
    }
}
