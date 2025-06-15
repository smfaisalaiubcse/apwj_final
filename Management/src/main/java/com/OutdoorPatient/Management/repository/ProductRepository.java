package com.OutdoorPatient.Management.repository;

import com.OutdoorPatient.Management.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ProductRepository {

    @Autowired
    private JdbcTemplate jdbc;

    private final RowMapper<Product> rowMapper = (rs, rowNum) -> {
        Product p = new Product();
        p.setId(rs.getInt("id"));
        p.setName(rs.getString("name"));
        p.setCategory(rs.getString("category"));
        p.setPrice(rs.getBigDecimal("price"));
        p.setExpiryDate(rs.getDate("expiry_date").toLocalDate());
        p.setDiscountPercent(rs.getBigDecimal("discount_percent"));
        p.setQuantity(rs.getInt("quantity"));
        return p;
    };

    public Product findExisting(String name, String category, LocalDate expiryDate) {
        List<Product> products = jdbc.query(
                "SELECT * FROM product WHERE name=? AND category=? AND expiry_date=?",
                rowMapper,
                name, category, java.sql.Date.valueOf(expiryDate)
        );
        return products.isEmpty() ? null : products.get(0);
    }

    public void save(Product p) {
        jdbc.update("INSERT INTO product (name, category, price, expiry_date, discount_percent, quantity) VALUES (?, ?, ?, ?, ?, ?)",
                p.getName(), p.getCategory(), p.getPrice(), p.getExpiryDate(), p.getDiscountPercent(), p.getQuantity());
    }

    public void updateQuantityAndPrice(Product p) {
        jdbc.update(
                "UPDATE product SET quantity = quantity + ?, price=?, discount_percent=? WHERE id=?",
                p.getQuantity(), p.getPrice(), p.getDiscountPercent(), p.getId()
        );
    }

    public void update(Product p) {
        jdbc.update("UPDATE product SET name=?, category=?, price=?, expiry_date=?, discount_percent=?, quantity=? WHERE id=?",
                p.getName(), p.getCategory(), p.getPrice(), p.getExpiryDate(), p.getDiscountPercent(), p.getQuantity(), p.getId());
    }

    public List<Product> findExpiringIn7Days() {
        return jdbc.query("SELECT * FROM product WHERE expiry_date BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 7 DAY)",
                rowMapper);
    }

    public List<CategoryTotal> findTotalPriceByCategory() {
        return jdbc.query(
                "SELECT category, SUM(price * quantity) AS total_price FROM product GROUP BY category",
                (rs, rowNum) -> new CategoryTotal(rs.getString("category"), rs.getBigDecimal("total_price"))
        );
    }

    public List<Product> findAll() {
        return jdbc.query("SELECT * FROM product", rowMapper);
    }

    public static class CategoryTotal {
        private final String category;
        private final BigDecimal totalPrice;

        public CategoryTotal(String category, BigDecimal totalPrice) {
            this.category = category;
            this.totalPrice = totalPrice;
        }

        public String getCategory() {
            return category;
        }

        public BigDecimal getTotalPrice() {
            return totalPrice;
        }
    }
}
