
package com.management.supershop.service;

import com.management.supershop.model.Product;
import com.management.supershop.model.ProductCategory;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ProductService {
    Product addProduct(Product product);
    List<Product> addProducts(List<Product> products);
    Product updateProduct(Long id, Product product);
    List<Product> getExpiringProducts();
    Map<ProductCategory, BigDecimal> getTotalValueByCategory();
    List<Product> getProductsByCategory(ProductCategory category);
    void applyExpiryBasedDiscounts();
    void markExpiredProducts();
    List<Product> getAllProducts();
    void deleteProduct(Long id);
}