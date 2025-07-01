package com.ticketbooking.bus_ticket_booking_system.controller;

import com.ticketbooking.bus_ticket_booking_system.model.Bus;
import com.ticketbooking.bus_ticket_booking_system.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/bus")
public class BusController {

    @Autowired
    private BusService busService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addBus(@RequestBody Bus bus) {
        busService.addBus(bus);
        return ResponseEntity.ok(Map.of("message", "Bus added successfully!"));
    }
}