package com.management.supershop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserRoleController {

    @GetMapping("/role")
    public ResponseEntity<Map<String, String>> getUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().stream()
                .findFirst()
                .map(Object::toString)
                .orElse("ROLE_ANONYMOUS");
        
        Map<String, String> response = new HashMap<>();
        response.put("role", role.replace("ROLE_", "")); // Remove the ROLE_ prefix
        
        return ResponseEntity.ok(response);
    }
}