package com.management.supershop.repository;

import com.management.supershop.model.Product;
import com.management.supershop.model.ProductCategory;
import java.util.List;

public interface ProductRepository {
    Product save(Product product);
    List<Product> saveAll(List<Product> products);
    Product findById(Long id);
    List<Product> findAll();
    List<Product> findByCategory(ProductCategory category);
    void update(Product product);
    void delete(Long id);
}