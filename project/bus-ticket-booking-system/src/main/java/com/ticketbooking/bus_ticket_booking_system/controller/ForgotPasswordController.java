package com.ticketbooking.bus_ticket_booking_system.controller;

import com.ticketbooking.bus_ticket_booking_system.service.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth/password")
public class ForgotPasswordController {

    @Autowired private ForgotPasswordService forgotPasswordService;

    @PostMapping("/forgot")
    public ResponseEntity<Map<String, String>> sendResetEmail(@RequestParam String email) {
        forgotPasswordService.sendResetToken(email);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Reset token sent to email.");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/reset")
    public ResponseEntity<Map<String, String>> resetPassword(
            @RequestParam String token,
            @RequestParam String newPassword
    ) {
        forgotPasswordService.resetPassword(token, newPassword);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Password reset successful.");
        return ResponseEntity.ok(response);
    }
}

