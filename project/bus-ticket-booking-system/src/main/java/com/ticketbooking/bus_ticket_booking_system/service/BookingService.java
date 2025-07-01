package com.ticketbooking.bus_ticket_booking_system.service;

import com.ticketbooking.bus_ticket_booking_system.model.Booking;
import com.ticketbooking.bus_ticket_booking_system.model.User;
import com.ticketbooking.bus_ticket_booking_system.repository.BookingRepository;
import com.ticketbooking.bus_ticket_booking_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepo;
    @Transactional
    public void bookSeat(Booking booking, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Set the authenticated user's ID
        booking.setUserId(user.getUserId());

        if (bookingRepo.isSeatAlreadyBooked(booking.getTripId(), booking.getSeatNumber())) {
            throw new RuntimeException("Seat " + booking.getSeatNumber() + " is already booked!");
        }

        bookingRepo.save(booking);
        bookingRepo.decreaseBusCapacityByTripId(booking.getTripId());
    }


    @Autowired
    private UserRepository userRepository;

    public List<Booking> getBookingsForUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return bookingRepo.findByUserId(user.getUserId());
    }

}

