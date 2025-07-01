package com.ticketbooking.bus_ticket_booking_system.controller;

import com.ticketbooking.bus_ticket_booking_system.dto.BookingResponse;
import com.ticketbooking.bus_ticket_booking_system.model.Booking;
import com.ticketbooking.bus_ticket_booking_system.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponse> bookSeat(
            @RequestBody Booking booking,
            Authentication authentication
    ) {
        try {
            String email = authentication.getName(); // Extract from JWT
            bookingService.bookSeat(booking, email); // Pass email, not userId from payload

            BookingResponse response = new BookingResponse(
                    true,
                    "Seat " + booking.getSeatNumber() + " booked successfully!",
                    booking.getSeatNumber()
            );
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            BookingResponse response = new BookingResponse(
                    false,
                    ex.getMessage(),
                    booking.getSeatNumber()
            );
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }


    @GetMapping("/my")
    public ResponseEntity<?> getMyBookings(Authentication authentication) {
        String email = authentication.getName(); // From SecurityContext
        List<Booking> bookings = bookingService.getBookingsForUser(email);

        if (bookings.isEmpty()) {
            return ResponseEntity.ok(Collections.singletonMap("message", "No bookings yet."));
        }

        return ResponseEntity.ok(bookings);
    }

}

