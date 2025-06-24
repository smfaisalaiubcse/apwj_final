package com.management.supershop.repository;

import com.management.supershop.model.User;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);
}