package com.ticketbooking.bus_ticket_booking_system.dto;

public class BookingResponse {
    private boolean success;
    private String message;
    private String seatNumber;

    public BookingResponse(boolean success, String message, String seatNumber) {
        this.success = success;
        this.message = message;
        this.seatNumber = seatNumber;
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public String getSeatNumber() { return seatNumber; }
}