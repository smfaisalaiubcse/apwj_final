package com.ticketbooking.bus_ticket_booking_system.service;

import com.ticketbooking.bus_ticket_booking_system.dto.UserProfileDto;
import com.ticketbooking.bus_ticket_booking_system.model.User;
import com.ticketbooking.bus_ticket_booking_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserProfileDto getUserProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserProfileDto(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt()
        );
    }


    public void updateUserProfile(String email, User updatedData) {
        User existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setName(updatedData.getName());
        existingUser.setEmail(updatedData.getEmail());

        userRepository.updateProfile(existingUser);
    }
}

