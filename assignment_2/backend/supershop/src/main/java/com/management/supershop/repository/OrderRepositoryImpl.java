package com.management.supershop.repository;

import com.management.supershop.model.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private final JdbcTemplate jdbcTemplate;

    public OrderRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Order> ORDER_ROW_MAPPER = (rs, rowNum) -> {
        Order order = new Order();
        order.setId(rs.getLong("id"));
        order.setUserId(rs.getLong("user_id"));
        order.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime());
        order.setTotalPrice(rs.getBigDecimal("total_price"));
        return order;
    };

    @Override
    public void save(Order order) {
        String orderQuery = "INSERT INTO orders (user_id, order_date, total_price) VALUES (?, ?, ?)";
        jdbcTemplate.update(orderQuery, order.getUserId(), order.getOrderDate(), order.getTotalPrice());

        String orderIdQuery = "SELECT LAST_INSERT_ID()";
        Long orderId = jdbcTemplate.queryForObject(orderIdQuery, Long.class);

        order.getOrderItems().forEach(orderItem -> {
            String orderItemQuery = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(orderItemQuery, orderId, orderItem.getProductId(), orderItem.getQuantity(), orderItem.getPrice());
        });
    }

    @Override
    public List<Order> findOrdersByUserId(Long userId) {
        String query = "SELECT * FROM orders WHERE user_id = ?";
        return jdbcTemplate.query(query, ORDER_ROW_MAPPER, userId);
    }

    @Override
    public Order findOrderById(Long orderId) {
        String query = "SELECT * FROM orders WHERE id = ?";
        return jdbcTemplate.queryForObject(query, ORDER_ROW_MAPPER, orderId);
    }

    @Override
    public Map<String, BigDecimal> getTotalSalesPerCategory() {
        String query = "SELECT category, SUM(price * quantity) AS total_sales FROM order_items " +
                       "JOIN products ON order_items.product_id = products.id GROUP BY category";
        return jdbcTemplate.query(query, rs -> {
            Map<String, BigDecimal> map = new HashMap<>();
            while (rs.next()) {
                map.put(rs.getString("category"), rs.getBigDecimal("total_sales"));
            }
            return map;
        });
    }

    @Override
    public BigDecimal getTotalRevenue() {
        String query = "SELECT SUM(total_price) AS revenue FROM orders";
        return jdbcTemplate.queryForObject(query, BigDecimal.class);
    }

    @Override
    public int getTotalOrders() {
        String query = "SELECT COUNT(*) AS total_orders FROM orders";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }

    @Override
    public List<String> getBestSellingProducts() {
        String query = "SELECT products.name, SUM(order_items.quantity) AS total_quantity FROM order_items " +
                       "JOIN products ON order_items.product_id = products.id " +
                       "GROUP BY products.name ORDER BY total_quantity DESC LIMIT 5";
        return jdbcTemplate.query(query, (rs, rowNum) -> rs.getString("name"));
    }
}