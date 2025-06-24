package com.management.supershop.service;

import com.management.supershop.model.Order;

import java.util.List;

public interface OrderService {
    Order placeOrder(Long userId);
    List<Order> getUserOrders(Long userId);
    Order getOrderById(Long orderId);
}