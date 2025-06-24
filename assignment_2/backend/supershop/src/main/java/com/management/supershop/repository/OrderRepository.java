package com.management.supershop.repository;

import com.management.supershop.model.Order;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface OrderRepository {
    void save(Order order);
    List<Order> findOrdersByUserId(Long userId);
    Order findOrderById(Long orderId);
    Map<String, BigDecimal> getTotalSalesPerCategory();
    BigDecimal getTotalRevenue();
    int getTotalOrders();
    List<String> getBestSellingProducts();
}