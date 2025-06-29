package com.ticketbooking.bus_ticket_booking_system.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Role {
    ROLE_ADMIN,
    ROLE_CUSTOMER,
    ROLE_STAFF;

    @JsonCreator
    public static Role from(String role) {
        return Role.valueOf("ROLE_" + role.toUpperCase()); // Prefix "ROLE_" to match enum constants
    }
}