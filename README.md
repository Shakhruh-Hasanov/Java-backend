# Java-backend
Backend projects
📦 E-Commerce Backend API

🚀 Overview
This project is a backend e-commerce application built using Spring Boot. It provides RESTful APIs for managing users, products, and orders, with transactional order processing and relational data modeling.
The system simulates real-world e-commerce functionality including inventory management, order creation, and data integrity handling.

🛠 Tech Stack
- Java
- Spring Boot
- Spring Data JPA (Hibernate)
- PostgreSQL
- Maven
- REST APIs
- Postman (for API testing)
  
📚 Features
👤 User Management
  - Create and manage users
  - Store user details in PostgreSQL database
    
🛍 Product Management
  - Add, update, and retrieve products
  - Track product inventory (stock)
    
📦 Order Processing
  - Create orders with multiple products
  - Automatically calculate total order amount
  - Reduce product stock after purchase
  - Maintain transactional integrity using @Transactional
    
🔗 Relational Data Modeling
  - User → Order (One-to-Many)
  - Order → OrderItem (One-to-Many)
  - Product → OrderItem (Many-to-One)
    
🧱 Architecture
The application follows a layered architecture:
    Controller → Service → Repository → Database
    
    - Controller: Handles HTTP requests
    - Service: Contains business logic
    - Repository: Manages database interactions
    - Entity: Defines database models
🔄 API Endpoints
Users
  - POST /users → Create user
  - GET /users → Get all users
Products
  - POST /products → Create product
  - GET /products → Get all products
Orders
  - POST /orders → Create order


🧠 Key Concepts Demonstrated
  - REST API design
  - JPA/Hibernate entity relationships
  - Transaction management
  - DTO usage for request handling
  - Layered backend architecture
  - Data integrity and business logic separation
🚧 Future Improvements Needed
  - Add authentication & authorization (Spring Security, JWT)
  - Implement order status tracking
  - Add pagination and filtering
  - Create frontend (React)
  - Deploy to cloud (AWS/Docker)

👨‍💻 Author
Shakhruh Hasanov
