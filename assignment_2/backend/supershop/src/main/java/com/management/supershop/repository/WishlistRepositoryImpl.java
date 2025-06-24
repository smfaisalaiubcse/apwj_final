package com.management.supershop.repository;

import com.management.supershop.model.Wishlist;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishlistRepositoryImpl implements WishlistRepository {
    private final JdbcTemplate jdbcTemplate;

    public WishlistRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Wishlist> WISHLIST_ROW_MAPPER = (rs, rowNum) -> {
        Wishlist wishlist = new Wishlist();
        wishlist.setId(rs.getLong("id"));
        wishlist.setUserId(rs.getLong("user_id"));
        wishlist.setProductId(rs.getLong("product_id"));
        return wishlist;
    };

    @Override
    public void addProductToWishlist(Long userId, Long productId) {
        String query = "INSERT INTO wishlists (user_id, product_id) VALUES (?, ?)";
        jdbcTemplate.update(query, userId, productId);
    }

    @Override
    public List<Wishlist> getWishlistByUserId(Long userId) {
        String query = "SELECT * FROM wishlists WHERE user_id = ?";
        return jdbcTemplate.query(query, WISHLIST_ROW_MAPPER, userId);
    }

    @Override
    public void removeProductFromWishlist(Long userId, Long productId) {
        String query = "DELETE FROM wishlists WHERE user_id = ? AND product_id = ?";
        jdbcTemplate.update(query, userId, productId);
    }
}