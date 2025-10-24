# Algorithm Implementation Examples

This repository contains practical implementations of various algorithms in real-world scenarios using Java and Spring Boot.

## Projects

### 1. Friend Finder (BFS Implementation)
- Location: `/friend-finder`
- Algorithm: Breadth-First Search
- Use Case: Social Network Friend Discovery
- Features:
  - Friend network traversal
  - Shortest path finding
  - Distance-based friend recommendations

### 2. Product Catalog Sorter (QuickSort Implementation)
- Location: `/product-catalog`
- Algorithm: QuickSort
- Use Case: E-commerce Product Sorting
- Features:
  - Single-criterion sorting (price, rating, popularity)
  - Multi-criteria sorting (e.g., category and price, rating and price)
  - In-memory product catalog with sample data
  - RESTful API endpoints

#### API Endpoints
1. Initialize Sample Data:
```bash
curl -X POST http://localhost:8080/api/products/init
```

2. Single Criterion Sorting:
```bash
# Sort by price (ascending)
curl "http://localhost:8080/api/products/sort?sortBy=price"

# Sort by rating (highest first)
curl "http://localhost:8080/api/products/sort?sortBy=rating"

# Sort by popularity (highest first)
curl "http://localhost:8080/api/products/sort?sortBy=popularity"
```

3. Multi-criteria Sorting:
```bash
# Sort by category, then price
curl "http://localhost:8080/api/products/sort/multiple?criteria=category,price"

# Sort by rating, then price
curl "http://localhost:8080/api/products/sort/multiple?criteria=rating,price"
```

#### Implementation Details
- Algorithm: QuickSort
  - Time Complexity: O(n log n) average case
  - Space Complexity: O(log n)
  - In-place sorting
- Sorting Criteria:
  - Price: Ascending order
  - Rating: Descending order (highest first)
  - Popularity: Descending order (most popular first)
  - Category: Alphabetical order

## Running the Projects

Each project is a standalone Spring Boot application. To run a project:

1. Navigate to the project directory:
```bash
cd <project-directory>
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

## Testing

Each project includes a set of sample data and API endpoints for testing the implemented algorithms. Use the provided curl commands to test different functionalities.

## Upcoming Implementations
1. Content Search Engine (Binary Search)
2. Travel Route Optimizer (Dynamic Programming)
3. Network Router Simulator (Dijkstra's Algorithm)
4. File System Navigator (Tree Traversal)
