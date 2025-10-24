package com.catalog.service;

import com.catalog.model.Product;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Comparator;

@Service
public class ProductSortingService {
    
    // QuickSort implementation for products
    public void quickSort(List<Product> products, String sortBy) {
        quickSort(products, 0, products.size() - 1, getComparator(sortBy));
    }
    
    private void quickSort(List<Product> products, int low, int high, Comparator<Product> comparator) {
        if (low < high) {
            int pi = partition(products, low, high, comparator);
            quickSort(products, low, pi - 1, comparator);
            quickSort(products, pi + 1, high, comparator);
        }
    }
    
    private int partition(List<Product> products, int low, int high, Comparator<Product> comparator) {
        Product pivot = products.get(high);
        int i = (low - 1);
        
        for (int j = low; j < high; j++) {
            if (comparator.compare(products.get(j), pivot) <= 0) {
                i++;
                swap(products, i, j);
            }
        }
        
        swap(products, i + 1, high);
        return i + 1;
    }
    
    private void swap(List<Product> products, int i, int j) {
        Product temp = products.get(i);
        products.set(i, products.get(j));
        products.set(j, temp);
    }
    
    // Get appropriate comparator based on sorting criteria
    private Comparator<Product> getComparator(String sortBy) {
        switch (sortBy.toLowerCase()) {
            case "price":
                return Comparator.comparingDouble(Product::getPrice);
            case "category":
                return Comparator.comparing(Product::getCategory).thenComparing(Product::getName);
            case "rating":
                return Comparator.comparingDouble(Product::getRating).reversed(); // Higher ratings first
            case "popularity":
                return Comparator.comparingInt(Product::getPopularity).reversed(); // More popular first
            default:
                return Comparator.comparing(Product::getName); // Default to name sorting
        }
    }
    
    // Method to sort products by multiple criteria
    public void sortByMultipleCriteria(List<Product> products, List<String> criteria) {
        Comparator<Product> compositeComparator = null;
        
        for (String criterion : criteria) {
            if (compositeComparator == null) {
                compositeComparator = getComparator(criterion);
            } else {
                compositeComparator = compositeComparator.thenComparing(getComparator(criterion));
            }
        }
        
        if (compositeComparator != null) {
            products.sort(compositeComparator);
        }
    }
}