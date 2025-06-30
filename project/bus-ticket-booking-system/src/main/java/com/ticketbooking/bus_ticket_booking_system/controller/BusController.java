package com.ticketbooking.bus_ticket_booking_system.controller;
import com.ticketbooking.bus_ticket_booking_system.model.Bus;
import com.ticketbooking.bus_ticket_booking_system.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/bus")
public class BusController {

    @Autowired
    private BusService busService;

    @PostMapping("/add")
    public String addBus(@RequestBody Bus bus) {
        busService.addBus(bus);
        return "Bus added successfully!";
    }
}

