package com.ticketbooking.bus_ticket_booking_system.controller;

import com.ticketbooking.bus_ticket_booking_system.model.Route;
import com.ticketbooking.bus_ticket_booking_system.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping("/all")
    public List<Route> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    @GetMapping("/search")
    public ResponseEntity<?> getRouteByName(@RequestParam String name) {
        Optional<Route> route = routeService.findByName(name);

        if (route.isPresent()) {
            return ResponseEntity.ok(route.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Route not found with name: " + name);
        }
    }
}
