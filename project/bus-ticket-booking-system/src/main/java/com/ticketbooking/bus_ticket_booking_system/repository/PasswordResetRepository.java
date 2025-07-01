package com.ticketbooking.bus_ticket_booking_system.repository;

import com.ticketbooking.bus_ticket_booking_system.model.PasswordResetToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PasswordResetRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public void save(PasswordResetToken token) {
        String sql = "INSERT INTO password_reset_token (email, token, expiration_time) VALUES (?, ?, ?)";
        jdbc.update(sql, token.getEmail(), token.getToken(), token.getExpirationTime());
    }

    public Optional<PasswordResetToken> findValidToken(String token) {
        String sql = "SELECT * FROM password_reset_token WHERE token = ? AND used = false AND expiration_time > NOW()";
        return jdbc.query(sql, rs -> {
            if (rs.next()) {
                PasswordResetToken prt = new PasswordResetToken();
                prt.setId(rs.getInt("id"));
                prt.setEmail(rs.getString("email"));
                prt.setToken(rs.getString("token"));
                prt.setExpirationTime(rs.getTimestamp("expiration_time").toLocalDateTime());
                prt.setUsed(rs.getBoolean("used"));
                return Optional.of(prt);
            }
            return Optional.empty();
        }, token);
    }

    public void markTokenUsed(String token) {
        jdbc.update("UPDATE password_reset_token SET used = true WHERE token = ?", token);
    }
}

