package com.management.supershop.repository;

import com.management.supershop.model.Product;
import com.management.supershop.model.ProductCategory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product save(Product product) {
        String query = "INSERT INTO products (name, category, price, quantity, expiry_date, discount, available) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, product.getName(), product.getCategory().name(), product.getPrice(),
                product.getQuantity(), product.getExpiryDate(), product.getDiscount(), product.isAvailable());
        return product;
    }

    @Override
    public List<Product> saveAll(List<Product> products) {
        // Example implementation for saving a list of products (can use batch update)
        for (Product product : products) {
            save(product);
        }
        return products;
    }

    @Override
    public Product findById(Long id) {
        String query = "SELECT * FROM products WHERE id = ?";
        return jdbcTemplate.queryForObject(query, (rs, rowNum) -> {
            Product product = new Product();
            product.setId(rs.getLong("id"));
            product.setName(rs.getString("name"));
            product.setCategory(ProductCategory.valueOf(rs.getString("category")));
            product.setPrice(rs.getBigDecimal("price"));
            product.setQuantity(rs.getInt("quantity"));
            product.setExpiryDate(rs.getDate("expiry_date").toLocalDate());
            product.setDiscount(rs.getBigDecimal("discount"));
            product.setAvailable(rs.getBoolean("available"));
            return product;
        }, id);
    }

    @Override
    public List<Product> findAll() {
        String query = "SELECT * FROM products";
        return jdbcTemplate.query(query, (rs, rowNum) -> {
            Product product = new Product();
            product.setId(rs.getLong("id"));
            product.setName(rs.getString("name"));
            product.setCategory(ProductCategory.valueOf(rs.getString("category")));
            product.setPrice(rs.getBigDecimal("price"));
            product.setQuantity(rs.getInt("quantity"));
            product.setExpiryDate(rs.getDate("expiry_date") != null ? rs.getDate("expiry_date").toLocalDate() : null);
            product.setDiscount(rs.getBigDecimal("discount"));
            product.setAvailable(rs.getBoolean("available"));
            return product;
        });
    }

    @Override
    public List<Product> findByCategory(ProductCategory category) {
        String query = "SELECT * FROM products WHERE category = ?";
        return jdbcTemplate.query(query, (rs, rowNum) -> {
            Product product = new Product();
            product.setId(rs.getLong("id"));
            product.setName(rs.getString("name"));
            product.setCategory(ProductCategory.valueOf(rs.getString("category")));
            product.setPrice(rs.getBigDecimal("price"));
            product.setQuantity(rs.getInt("quantity"));
            product.setExpiryDate(rs.getDate("expiry_date") != null ? rs.getDate("expiry_date").toLocalDate() : null);
            product.setDiscount(rs.getBigDecimal("discount"));
            product.setAvailable(rs.getBoolean("available"));
            return product;
        }, category.name());
    }

    @Override
    public void update(Product product) {
        String query = "UPDATE products SET name = ?, category = ?, price = ?, quantity = ?, expiry_date = ?, discount = ?, available = ? WHERE id = ?";
        jdbcTemplate.update(query, product.getName(), product.getCategory().name(), product.getPrice(),
                product.getQuantity(), product.getExpiryDate(), product.getDiscount(), product.isAvailable(), product.getId());
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(query, id); // Implements the delete functionality
    }
}