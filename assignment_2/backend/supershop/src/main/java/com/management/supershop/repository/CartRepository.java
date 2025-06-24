package com.management.supershop.repository;

import com.management.supershop.model.Cart;
import java.util.List;

public interface CartRepository {
    void addToCart(Long userId, Long productId, Integer quantity);
    void updateCartQuantity(Long userId, Long productId, Integer quantity);
    void removeProductFromCart(Long userId, Long productId);
    List<Cart> getCartByUserId(Long userId);
}