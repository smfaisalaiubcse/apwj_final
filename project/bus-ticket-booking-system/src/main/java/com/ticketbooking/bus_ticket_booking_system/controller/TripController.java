package com.ticketbooking.bus_ticket_booking_system.controller;

import com.ticketbooking.bus_ticket_booking_system.model.Trip;
import com.ticketbooking.bus_ticket_booking_system.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/buses")
public class TripController {

    @Autowired
    private TripService tripService;

    @GetMapping("/search")
    public List<Trip> searchTrips(
            @RequestParam String route,
            @RequestParam String fromDate,
            @RequestParam String toDate
    ) {
        LocalDateTime from = LocalDateTime.parse(fromDate + "T00:00:00");
        LocalDateTime to = LocalDateTime.parse(toDate + "T23:59:59");

        return tripService.searchAvailableTrips(route, from, to);
    }
}


