package com.OutdoorPatient.Management.service;


import com.OutdoorPatient.Management.model.Product;
import com.OutdoorPatient.Management.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public void addProduct(Product product) {
        Product existing = repo.findExisting(product.getName(), product.getCategory(), product.getExpiryDate());
        if (existing != null) {
            product.setId(existing.getId());
            repo.updateQuantityAndPrice(product); // Increase quantity + optionally update price/discount
        } else {
            repo.save(product);
        }
    }

    public void updateProduct(Product product) {
        repo.update(product); // Full update
    }

    public List<Product> getExpiringProducts() {
        return repo.findExpiringIn7Days();
    }

    public List<Product> getDiscountedProducts() {
        return getExpiringProducts().stream()
                .filter(product -> product.getDiscountPercent() != null)
                .collect(Collectors.toList());
    }


    public List<ProductRepository.CategoryTotal> getTotalPriceByCategory() {
        return repo.findTotalPriceByCategory();
    }
}

