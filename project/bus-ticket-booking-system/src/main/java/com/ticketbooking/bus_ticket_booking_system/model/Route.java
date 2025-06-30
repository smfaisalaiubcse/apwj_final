package com.ticketbooking.bus_ticket_booking_system.model;

public class Route {
    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int routeId;
    private String name;

    // Getters & Setters
}
