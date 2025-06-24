package com.management.supershop.service;

import com.management.supershop.model.Wishlist;

import java.util.List;

public interface WishlistService {
    void addToWishlist(Long userId, Long productId);
    void removeFromWishlist(Long userId, Long productId);
    List<Wishlist> getUserWishlist(Long userId);
}