package com.ticketbooking.bus_ticket_booking_system.controller;

import com.ticketbooking.bus_ticket_booking_system.dto.ChangePasswordRequest;
import com.ticketbooking.bus_ticket_booking_system.dto.UserProfileDto;
import com.ticketbooking.bus_ticket_booking_system.model.User;
import com.ticketbooking.bus_ticket_booking_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDto> getProfile(Authentication authentication) {
        String email = authentication.getName();
        UserProfileDto profile = userService.getUserProfile(email);
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/profile/update")
    public ResponseEntity<Map<String, String>> updateProfile(
            Authentication authentication,
            @RequestBody User updatedUser
    ) {
        String email = authentication.getName();
        userService.updateUserProfile(email, updatedUser);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Profile updated successfully.");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/password/change")
    public ResponseEntity<Map<String, String>> changePassword(
            @RequestBody ChangePasswordRequest request,
            Authentication authentication
    ) {
        String email = authentication.getName();
        userService.changePassword(email, request.getOldPassword(), request.getNewPassword());

        Map<String, String> response = new HashMap<>();
        response.put("message", "Password changed successfully.");
        return ResponseEntity.ok(response);
    }

}

