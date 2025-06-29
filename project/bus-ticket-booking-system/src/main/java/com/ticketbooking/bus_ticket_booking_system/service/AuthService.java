package com.ticketbooking.bus_ticket_booking_system.service;

import com.ticketbooking.bus_ticket_booking_system.config.JwtUtils;
import com.ticketbooking.bus_ticket_booking_system.model.Role;
import com.ticketbooking.bus_ticket_booking_system.model.User;
import com.ticketbooking.bus_ticket_booking_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    public void signup(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encode password
        if (user.getRole() == null) {
            user.setRole(Role.ROLE_CUSTOMER); // Default to CUSTOMER
        }
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user); // Save user to the database
    }

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid credentials!"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials!");
        }
        return jwtUtils.generateToken(user.getEmail(), user.getRole().name());
    }
}