package com.management.supershop.repository;

import com.management.supershop.model.Wishlist;
import java.util.List;

public interface WishlistRepository {
    void addProductToWishlist(Long userId, Long productId);
    List<Wishlist> getWishlistByUserId(Long userId);
    void removeProductFromWishlist(Long userId, Long productId);
}