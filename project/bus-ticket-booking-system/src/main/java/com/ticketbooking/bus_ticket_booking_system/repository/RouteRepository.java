package com.ticketbooking.bus_ticket_booking_system.repository;

import com.ticketbooking.bus_ticket_booking_system.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RouteRepository {
    @Autowired
    private JdbcTemplate jdbc;

    public int save(String name) {
        String sql = "INSERT INTO route (name) VALUES (?)";
        jdbc.update(sql, name);
        return jdbc.queryForObject("SELECT route_id FROM route WHERE name = ?", Integer.class, name);
    }

    public Optional<Route> findByName(String name) {
        String sql = "SELECT * FROM route WHERE name = ?";
        return jdbc.query(sql, rs -> {
            if (rs.next()) {
                Route r = new Route();
                r.setRouteId(rs.getInt("route_id"));
                r.setName(rs.getString("name"));
                return Optional.of(r);
            }
            return Optional.empty();
        }, name);
    }
}

