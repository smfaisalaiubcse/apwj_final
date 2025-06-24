package com.management.supershop.service;

import com.management.supershop.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    private final OrderRepository orderRepository;

    public ReportServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Map<String, Object> generateMonthlySalesReport() {
        Map<String, Object> report = new HashMap<>();
        report.put("totalSalesPerCategory", orderRepository.getTotalSalesPerCategory());
        report.put("totalRevenue", orderRepository.getTotalRevenue());
        report.put("totalOrders", orderRepository.getTotalOrders());
        report.put("bestSellingProducts", orderRepository.getBestSellingProducts());
        return report;
    }
}