package com.ticketbooking.bus_ticket_booking_system.service;

import com.ticketbooking.bus_ticket_booking_system.model.PasswordResetToken;
import com.ticketbooking.bus_ticket_booking_system.model.User;
import com.ticketbooking.bus_ticket_booking_system.repository.PasswordResetRepository;
import com.ticketbooking.bus_ticket_booking_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ForgotPasswordService {

    @Autowired
    private UserRepository userRepository;
    @Autowired private PasswordResetRepository resetRepository;
    @Autowired private EmailService emailService;
    @Autowired private BCryptPasswordEncoder passwordEncoder;

    public void sendResetToken(String email) {
        userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = UUID.randomUUID().toString();
        PasswordResetToken prt = new PasswordResetToken();
        prt.setEmail(email);
        prt.setToken(token);
        prt.setExpirationTime(LocalDateTime.now().plusMinutes(30));
        resetRepository.save(prt);

        // Email the token (or link)
        String subject = "Password Reset Request";
        String body = "Use this token to reset your password: " + token;
        emailService.sendSimpleEmail(email, subject, body);
    }

    public void resetPassword(String token, String newPassword) {
        PasswordResetToken prt = resetRepository.findValidToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid or expired token"));

        User user = userRepository.findByEmail(prt.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.updatePassword(user);

        resetRepository.markTokenUsed(token);
    }
}

