package com.catalog.controller;

import com.catalog.model.Product;
import com.catalog.service.ProductSortingService;
import com.catalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Arrays;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ProductSortingService sortingService;
    
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }
    
    @GetMapping("/sort")
    public List<Product> getSortedProducts(
            @RequestParam(defaultValue = "price") String sortBy) {
        List<Product> products = productRepository.findAll();
        sortingService.quickSort(products, sortBy);
        return products;
    }
    
    @GetMapping("/sort/multiple")
    public List<Product> getSortedProductsByMultipleCriteria(
            @RequestParam String criteria) {
        List<Product> products = productRepository.findAll();
        List<String> sortingCriteria = Arrays.asList(criteria.split(","));
        sortingService.sortByMultipleCriteria(products, sortingCriteria);
        return products;
    }
    
    // Helper endpoint to initialize some sample data
    @PostMapping("/init")
    public String initializeData() {
        productRepository.saveAll(Arrays.asList(
            new Product("Laptop", 999.99, 4.5, 150, "Electronics"),
            new Product("Smartphone", 699.99, 4.7, 200, "Electronics"),
            new Product("Headphones", 149.99, 4.3, 80, "Electronics"),
            new Product("Coffee Maker", 79.99, 4.1, 50, "Appliances"),
            new Product("Gaming Mouse", 59.99, 4.8, 120, "Electronics"),
            new Product("Desk Chair", 199.99, 4.2, 30, "Furniture"),
            new Product("Monitor", 299.99, 4.6, 100, "Electronics"),
            new Product("Keyboard", 89.99, 4.4, 90, "Electronics"),
            new Product("Tablet", 499.99, 4.5, 110, "Electronics"),
            new Product("Smart Watch", 249.99, 4.3, 70, "Electronics")
        ));
        return "Sample data initialized";
    }
}