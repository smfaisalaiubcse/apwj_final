package com.management.supershop.controller;

import com.management.supershop.model.Product;
import com.management.supershop.model.ProductCategory;
import com.management.supershop.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Product>> addProducts(@RequestBody List<Product> products) {
        return ResponseEntity.ok(productService.addProducts(products));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @GetMapping("/expiring")
    public ResponseEntity<List<Product>> getExpiringProducts() {
        return ResponseEntity.ok(productService.getExpiringProducts());
    }

    @GetMapping("/category-value")
    public ResponseEntity<Map<ProductCategory, BigDecimal>> getTotalValueByCategory() {
        return ResponseEntity.ok(productService.getTotalValueByCategory());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable ProductCategory category) {
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }

    @PostMapping("/apply-discounts")
    public ResponseEntity<Void> applyExpiryBasedDiscounts() {
        productService.applyExpiryBasedDiscounts();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/mark-expired")
    public ResponseEntity<Void> markExpiredProducts() {
        productService.markExpiredProducts();
        return ResponseEntity.ok().build();
    }
}