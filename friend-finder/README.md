# Friend Finder Application

A Spring Boot application that implements social network traversal using Breadth-First Search (BFS) algorithm. The application allows users to manage friend relationships and find connections between users at various degrees of separation.

## Features

- **User Management**: Create and retrieve users
- **Friend Relationships**: Add bidirectional friend connections between users
- **Distance-Based Friend Search**: Find friends at specific degrees of separation using BFS
- **Shortest Path**: Find the shortest connection path between two users

## Technology Stack

- Java 17
- Spring Boot 2.7.0
- Spring Data JPA
- H2 Database (in-memory)
- Maven

## API Endpoints

### User Management
- `POST /api/friends/user` - Create a new user
  ```json
  {
    "name": "username"
  }
  ```
- `GET /api/friends/users` - Get all users

### Friend Management
- `POST /api/friends/{userId}/friend/{friendId}` - Add a friend relationship
- `GET /api/friends/{userId}/distance/{distance}` - Find friends at specific distance
- `GET /api/friends/path?sourceUserId={id}&targetUserId={id}` - Find shortest path between users

## Examples

### 1. Creating Users
```bash
curl -X POST -H "Content-Type: application/json" -d '{"name":"Alice"}' http://localhost:8080/api/friends/user
curl -X POST -H "Content-Type: application/json" -d '{"name":"Bob"}' http://localhost:8080/api/friends/user
```

### 2. Making Friends
```bash
curl -X POST http://localhost:8080/api/friends/1/friend/2
```

### 3. Finding Friends at Distance
```bash
curl http://localhost:8080/api/friends/1/distance/2
```

### 4. Finding Shortest Path
```bash
curl "http://localhost:8080/api/friends/path?sourceUserId=1&targetUserId=4"
```

## Implementation Details

### BFS Algorithm
The application uses Breadth-First Search to:
1. Find friends at specific distances
2. Calculate shortest paths between users

### Data Model
- User entity with bidirectional many-to-many relationships
- Uses JPA/Hibernate for persistence
- In-memory H2 database for development

## Building and Running

1. Clone the repository
```bash
git clone https://github.com/prabu-thanikachalam/codealgorithm.git
```

2. Navigate to the project directory
```bash
cd codealgorithm/friend-finder
```

3. Build the project
```bash
mvn clean install
```

4. Run the application
```bash
mvn spring-boot:run
```

The application will start on port 8080.

## Use Cases

1. **Social Network Analysis**
   - Find friends of friends
   - Discover connection paths between users
   - Analyze network distances

2. **Friend Recommendations**
   - Find users at specific degrees of separation
   - Identify potential connections

3. **Network Traversal**
   - Efficient graph traversal using BFS
   - Optimal path finding between users

## Project Structure

```
friend-finder/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── friendfinder/
│   │   │           ├── FriendFinderApplication.java
│   │   │           ├── controller/
│   │   │           │   └── FriendController.java
│   │   │           ├── model/
│   │   │           │   ├── User.java
│   │   │           │   └── NetworkData.java
│   │   │           ├── repository/
│   │   │           │   └── UserRepository.java
│   │   │           └── service/
│   │   │               ├── FriendService.java
│   │   │               └── FriendServiceImpl.java
│   │   └── resources/
│   │       └── application.properties
└── pom.xml
```