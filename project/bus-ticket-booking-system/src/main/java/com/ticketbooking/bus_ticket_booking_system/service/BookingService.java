package com.ticketbooking.bus_ticket_booking_system.service;

import com.ticketbooking.bus_ticket_booking_system.model.Booking;
import com.ticketbooking.bus_ticket_booking_system.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepo;
    @Transactional
    public void bookSeat(Booking booking) {
        // Check if already booked
        if (bookingRepo.isSeatAlreadyBooked(booking.getTripId(), booking.getSeatNumber())) {
            throw new RuntimeException("Seat " + booking.getSeatNumber() + " is already booked!");
        }

        bookingRepo.save(booking);
        bookingRepo.decreaseBusCapacityByTripId(booking.getTripId()); // 👈 NEW
    }
}

