package com.management.supershop.repository;

import com.management.supershop.model.Cart;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartRepositoryImpl implements CartRepository {
    private final JdbcTemplate jdbcTemplate;

    public CartRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Cart> CART_ROW_MAPPER = (rs, rowNum) -> {
        Cart cart = new Cart();
        cart.setId(rs.getLong("id"));
        cart.setUserId(rs.getLong("user_id"));
        cart.setProductId(rs.getLong("product_id"));
        cart.setQuantity(rs.getInt("quantity"));
        return cart;
    };

    @Override
    public void addToCart(Long userId, Long productId, Integer quantity) {
        String query = "INSERT INTO carts (user_id, product_id, quantity) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, userId, productId, quantity);
    }

    @Override
    public void updateCartQuantity(Long userId, Long productId, Integer quantity) {
        String query = "UPDATE carts SET quantity = ? WHERE user_id = ? AND product_id = ?";
        jdbcTemplate.update(query, quantity, userId, productId);
    }

    @Override
    public void removeProductFromCart(Long userId, Long productId) {
        String query = "DELETE FROM carts WHERE user_id = ? AND product_id = ?";
        jdbcTemplate.update(query, userId, productId);
    }

    @Override
    public List<Cart> getCartByUserId(Long userId) {
        String query = "SELECT * FROM carts WHERE user_id = ?";
        return jdbcTemplate.query(query, CART_ROW_MAPPER, userId);
    }
}