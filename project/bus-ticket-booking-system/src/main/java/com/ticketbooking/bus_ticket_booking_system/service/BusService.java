package com.ticketbooking.bus_ticket_booking_system.service;

import com.ticketbooking.bus_ticket_booking_system.model.Bus;
import com.ticketbooking.bus_ticket_booking_system.model.BusCompany;
import com.ticketbooking.bus_ticket_booking_system.model.Route;
import com.ticketbooking.bus_ticket_booking_system.repository.BusCompanyRepository;
import com.ticketbooking.bus_ticket_booking_system.repository.BusRepository;
import com.ticketbooking.bus_ticket_booking_system.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BusService {
    @Autowired
    private BusCompanyRepository companyRepo;
    @Autowired private RouteRepository routeRepo;
    @Autowired private BusRepository busRepo;

    public void addBus(Bus bus) {
        BusCompany company = bus.getCompany();

        // Check if company exists
        Optional<BusCompany> existingCompany = companyRepo.findByName(company.getName());
        if (existingCompany.isPresent()) {
            company = existingCompany.get();
        } else {
            int newCompanyId = companyRepo.save(company.getName());
            company.setCompanyId(newCompanyId);
        }

        bus.setCompany(company); // Make sure bus has updated company with ID

        // Save bus
        int busId = busRepo.save(bus);

        // Handle routes
        for (Route route : bus.getRoutes()) {
            Optional<Route> existingRoute = routeRepo.findByName(route.getName());
            if (existingRoute.isPresent()) {
                route = existingRoute.get();
            } else {
                int newRouteId = routeRepo.save(route.getName());
                route.setRouteId(newRouteId);
            }

            busRepo.saveBusRouteMapping(busId, route.getRouteId());
        }
    }

}

