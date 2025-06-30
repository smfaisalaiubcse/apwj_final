package com.ticketbooking.bus_ticket_booking_system.model;

public class BusCompany {
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int companyId;
    private String name;

    // Getters & Setters
}
