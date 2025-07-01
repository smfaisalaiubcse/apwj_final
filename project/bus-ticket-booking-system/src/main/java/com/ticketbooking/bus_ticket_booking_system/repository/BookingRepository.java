package com.ticketbooking.bus_ticket_booking_system.repository;

import com.ticketbooking.bus_ticket_booking_system.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookingRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public void save(Booking booking) {
        String sql = "INSERT INTO booking (user_id, trip_id, seat_number) VALUES (?, ?, ?)";
        jdbc.update(sql, booking.getUserId(), booking.getTripId(), booking.getSeatNumber());
    }

    public boolean isSeatAlreadyBooked(int tripId, String seatNumber) {
        String sql = "SELECT COUNT(*) FROM booking WHERE trip_id = ? AND seat_number = ?";
        Integer count = jdbc.queryForObject(sql, Integer.class, tripId, seatNumber);
        return count != null && count > 0;
    }

    public void decreaseBusCapacityByTripId(int tripId) {
        String sql = """
        UPDATE bus
        SET capacity = capacity - 1
        WHERE bus_id = (
            SELECT bus_id FROM trip WHERE trip_id = ?
        )
    """;

        jdbc.update(sql, tripId);
    }
}

