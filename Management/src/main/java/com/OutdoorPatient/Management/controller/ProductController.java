package com.OutdoorPatient.Management.controller;

import com.OutdoorPatient.Management.model.Product;
import com.OutdoorPatient.Management.repository.ProductRepository;
import com.OutdoorPatient.Management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public List<Product> getAll() {
        return service.getAllProducts();
    }

    @PostMapping("/batch")
    public List<Product> addProducts(@RequestBody List<Product> products) {
        for (Product product : products) {
            service.addProduct(product);
        }
        return service.getAllProducts();
    }

    @PostMapping
    public List<Product> add(@RequestBody Product product) {
        service.addProduct(product);
        return service.getAllProducts();
    }

    @PutMapping
    public void update(@RequestBody Product product) {
        service.updateProduct(product);
    }

    @PutMapping("/batch")
    public List<Product> updateProducts(@RequestBody List<Product> products) {
        for (Product product : products) {
            service.updateProduct(product);
        }
        return service.getAllProducts();
    }
    @GetMapping("/expiring")
    public List<Product> getExpiring() {
        return service.getExpiringProducts();
    }

    @GetMapping("/discounted")
    public List<Product> getDiscounted() {
        return service.getDiscountedProducts();
    }

    @GetMapping("/totals")
    public List<ProductRepository.CategoryTotal> getCategoryTotals() {
        return service.getTotalPriceByCategory();
    }
}
