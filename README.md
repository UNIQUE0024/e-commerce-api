# Ecommerce API

E-commerce REST API built with Spring Boot

## Features
- Product CRUD operations
- Order management
- User authentication
- MySQL database
- RESTful API endpoints

## Technologies
- Java 17
- Spring Boot 3.2
- MySQL
- Maven
- JUnit 5

## Prerequisites
- Java 17 or higher
- Maven 3.9+
- MySQL 8.0+

## Setup Instructions

### 1. Clone the repository
```bash
git clone https://github.com/company/ecommerce-api.git
cd ecommerce-api
```

### 2. Configure Database
Update `application.properties` with your MySQL credentials

### 3. Build the project
```bash
mvn clean install
```

### 4. Run the application
```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

## API Endpoints

### Products
- `GET /api/products` - Get all products
- `GET /api/products/{id}` - Get product by ID
- `POST /api/products` - Create new product
- `PUT /api/products/{id}` - Update product
- `DELETE /api/products/{id}` - Delete product
- `GET /api/products/category/{category}` - Get products by category

## Running Tests
```bash
mvn test
```

## CI/CD Pipeline
This project uses Jenkins for continuous integration and deployment.
See `Jenkinsfile` for pipeline configuration.

## License
MIT License
