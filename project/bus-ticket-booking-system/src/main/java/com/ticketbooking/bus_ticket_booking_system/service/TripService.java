package com.ticketbooking.bus_ticket_booking_system.service;

import com.ticketbooking.bus_ticket_booking_system.model.Trip;
import com.ticketbooking.bus_ticket_booking_system.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    public List<Trip> searchAvailableTrips(String routeName, LocalDateTime from, LocalDateTime to) {
        return tripRepository.searchTrips(routeName, from, to);
    }

    public void createTrip(Trip trip) {
        tripRepository.createTrip(trip);
    }
}


