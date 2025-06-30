package com.ticketbooking.bus_ticket_booking_system.repository;

import com.ticketbooking.bus_ticket_booking_system.model.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BusRepository {
    @Autowired
    private JdbcTemplate jdbc;

    public int save(Bus bus) {
        String sql = "INSERT INTO bus (name, capacity, company_id) VALUES (?, ?, ?)";
        jdbc.update(sql, bus.getName(), bus.getCapacity(), bus.getCompany().getCompanyId());
        return jdbc.queryForObject("SELECT bus_id FROM bus WHERE name = ?", Integer.class, bus.getName());
    }

    public void saveBusRouteMapping(int busId, int routeId) {
        String sql = "INSERT INTO bus_route (bus_id, route_id) VALUES (?, ?)";
        jdbc.update(sql, busId, routeId);
    }
}

