# Product Catalog Sorter

This project demonstrates the implementation of sorting algorithms (QuickSort) in a real-world e-commerce product catalog scenario. It provides REST APIs to sort products based on various criteria like price, rating, and popularity.

## Features

- QuickSort implementation for efficient product sorting
- Multiple sorting criteria support
- RESTful APIs for product management and sorting
- In-memory H2 database for product storage
- Sample data initialization endpoint

## API Endpoints

1. Initialize Sample Data:
```bash
curl -X POST http://localhost:8080/api/products/init
```

2. Sort products by a single criterion (price, rating, or popularity):
```bash
curl "http://localhost:8080/api/products/sort?sortBy=price"
curl "http://localhost:8080/api/products/sort?sortBy=rating"
curl "http://localhost:8080/api/products/sort?sortBy=popularity"
```

3. Sort products by multiple criteria:
```bash
# Sort by rating, then price
curl "http://localhost:8080/api/products/sort/multiple?criteria=rating,price"
```

## Algorithm Details

The implementation uses QuickSort algorithm with the following characteristics:
- Time Complexity: O(n log n) average case
- Space Complexity: O(log n) for recursion stack
- In-place sorting: Minimizes memory usage
- Stable sorting: Maintains relative order of equal elements

## Use Cases

1. E-commerce Product Listing:
   - Sort products by price (ascending) for budget-conscious shoppers
   - Sort by rating (descending) to show best-rated products first
   - Sort by popularity for trending items

2. Multi-criteria Sorting:
   - Sort by rating then price for best value products
   - Sort by popularity then rating for trending quality items

## Running the Application

1. Build the project:
```bash
mvn clean install
```

2. Run the application:
```bash
mvn spring-boot:run
```

3. Access the H2 console at:
```
http://localhost:8080/h2-console
```