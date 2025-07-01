package com.ticketbooking.bus_ticket_booking_system.service;

import com.ticketbooking.bus_ticket_booking_system.model.Route;
import com.ticketbooking.bus_ticket_booking_system.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Optional<Route> findByName(String name) {
        return routeRepository.findByName(name);
    }
}

