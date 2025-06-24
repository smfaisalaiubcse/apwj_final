package com.management.supershop.service;

import com.management.supershop.model.Cart;
import com.management.supershop.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void addToCart(Long userId, Long productId, Integer quantity) {
        cartRepository.addToCart(userId, productId, quantity);
    }

    @Override
    public void updateCartQuantity(Long userId, Long productId, Integer quantity) {
        cartRepository.updateCartQuantity(userId, productId, quantity);
    }

    @Override
    public void removeProductFromCart(Long userId, Long productId) {
        cartRepository.removeProductFromCart(userId, productId);
    }

    @Override
    public List<Cart> getCartByUserId(Long userId) {
        return cartRepository.getCartByUserId(userId);
    }
}