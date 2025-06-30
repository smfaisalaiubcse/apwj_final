package com.ticketbooking.bus_ticket_booking_system.repository;
import com.ticketbooking.bus_ticket_booking_system.model.BusCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BusCompanyRepository {
    @Autowired
    private JdbcTemplate jdbc;

    public int save(String name) {
        String sql = "INSERT INTO bus_company (name) VALUES (?)";
        jdbc.update(sql, name);
        return jdbc.queryForObject("SELECT company_id FROM bus_company WHERE name = ?", Integer.class, name);
    }

    public Optional<BusCompany> findByName(String name) {
        String sql = "SELECT * FROM bus_company WHERE name = ?";
        return jdbc.query(sql, rs -> {
            if (rs.next()) {
                BusCompany c = new BusCompany();
                c.setCompanyId(rs.getInt("company_id"));
                c.setName(rs.getString("name"));
                return Optional.of(c);
            }
            return Optional.empty();
        }, name);
    }
}
