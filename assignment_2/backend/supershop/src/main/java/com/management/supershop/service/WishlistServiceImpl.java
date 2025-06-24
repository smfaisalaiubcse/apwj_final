package com.management.supershop.service;

import com.management.supershop.model.Wishlist;
import com.management.supershop.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistServiceImpl implements WishlistService {
    private final WishlistRepository wishlistRepository;

    public WishlistServiceImpl(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public void addToWishlist(Long userId, Long productId) {
        wishlistRepository.addProductToWishlist(userId, productId);
    }

    @Override
    public void removeFromWishlist(Long userId, Long productId) {
        wishlistRepository.removeProductFromWishlist(userId, productId);
    }

    @Override
    public List<Wishlist> getUserWishlist(Long userId) {
        return wishlistRepository.getWishlistByUserId(userId);
    }
}