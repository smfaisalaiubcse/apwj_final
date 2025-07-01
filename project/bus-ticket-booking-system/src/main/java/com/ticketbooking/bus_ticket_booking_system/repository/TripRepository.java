package com.ticketbooking.bus_ticket_booking_system.repository;

import com.ticketbooking.bus_ticket_booking_system.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TripRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public List<Trip> searchTrips(String routeName, LocalDateTime from, LocalDateTime to) {
        String sql = """
            SELECT t.*
            FROM trip t
            JOIN route r ON t.route_id = r.route_id
            WHERE LOWER(r.name) = LOWER(?) AND t.departure_time BETWEEN ? AND ?
        """;


        return jdbc.query(sql, new Object[]{routeName, from, to}, (rs, rowNum) -> {
            Trip trip = new Trip();
            trip.setTripId(rs.getInt("trip_id"));
            trip.setBusId(rs.getInt("bus_id"));
            trip.setRouteId(rs.getInt("route_id"));
            trip.setDepartureTime(rs.getTimestamp("departure_time").toLocalDateTime());
            trip.setArrivalTime(rs.getTimestamp("arrival_time") != null ? rs.getTimestamp("arrival_time").toLocalDateTime() : null);
            trip.setPrice(rs.getDouble("price"));
            return trip;
        });
    }

    public void createTrip(Trip trip) {
        String sql = "INSERT INTO trip (bus_id, route_id, departure_time, arrival_time, price) VALUES (?, ?, ?, ?, ?)";
        jdbc.update(sql,
                trip.getBusId(),
                trip.getRouteId(),
                trip.getDepartureTime(),
                trip.getArrivalTime(),
                trip.getPrice()
        );
    }
}
