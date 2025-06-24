
package com.management.supershop.service;

import com.management.supershop.model.Product;
import com.management.supershop.model.ProductCategory;
import com.management.supershop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private static final BigDecimal EXPIRY_DISCOUNT = new BigDecimal("0.20"); // 20% discount

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> addProducts(List<Product> products) {
        return productRepository.saveAll(products);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        product.setId(id);
        productRepository.update(product);
        return product;
    }

    @Override
    public List<Product> getExpiringProducts() {
        LocalDate sevenDaysFromNow = LocalDate.now().plusDays(7);
        return productRepository.findAll().stream()
                .filter(product -> product.getExpiryDate() != null &&
                        product.getExpiryDate().isBefore(sevenDaysFromNow) &&
                        product.getExpiryDate().isAfter(LocalDate.now()))
                .collect(Collectors.toList());
    }

    @Override
    public Map<ProductCategory, BigDecimal> getTotalValueByCategory() {
        return productRepository.findAll().stream()
                .filter(Product::isAvailable)
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                product -> product.getPrice().multiply(new BigDecimal(product.getQuantity())),
                                BigDecimal::add
                        )
                ));
    }

    @Override
    public List<Product> getProductsByCategory(ProductCategory category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public void applyExpiryBasedDiscounts() {
        getExpiringProducts().forEach(product -> {
            BigDecimal discountedPrice = product.getPrice().multiply(BigDecimal.ONE.subtract(EXPIRY_DISCOUNT));
            product.setDiscount(EXPIRY_DISCOUNT);
            productRepository.update(product);
        });
    }

    @Override
    public void markExpiredProducts() {
        LocalDate today = LocalDate.now();
        productRepository.findAll().stream()
                .filter(product -> product.getExpiryDate() != null &&
                        product.getExpiryDate().isBefore(today))
                .forEach(product -> {
                    product.setAvailable(false);
                    productRepository.update(product);
                });
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.delete(id); // Calls the repository method to delete the product
    }
}