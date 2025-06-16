package com.management.supershop.repository;

import com.management.supershop.model.Product;
import com.management.supershop.model.ProductCategory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ProductRepositoryImpl implements ProductRepository {
    private final Map<Long, Product> productStore = new ConcurrentHashMap<>();
    private Long currentId = 1L;

    @Override
    public Product save(Product product) {
        product.setId(currentId++);
        productStore.put(product.getId(), product);
        return product;
    }

    @Override
    public List<Product> saveAll(List<Product> products) {
        return products.stream()
                .map(this::save)
                .collect(Collectors.toList());
    }

    @Override
    public Product findById(Long id) {
        return productStore.get(id);
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productStore.values());
    }

    @Override
    public List<Product> findByCategory(ProductCategory category) {
        return productStore.values().stream()
                .filter(product -> product.getCategory() == category)
                .collect(Collectors.toList());
    }

    @Override
    public void update(Product product) {
        productStore.put(product.getId(), product);
    }

    @Override
    public void delete(Long id) {
        productStore.remove(id);
    }
}