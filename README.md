# Employee Payroll System

## Project Description
The Employee Payroll System is a Spring Boot application designed to manage employee and payroll information. It provides a secure API for performing CRUD operations on employee and payroll data, featuring OAuth 2.0 for authentication and authorization, and integrated Swagger UI for API documentation.

## Features
*   **Employee Management**: CRUD operations for employee records.
*   **Payroll Management**: CRUD operations for payroll records, linked to employees.
*   **OAuth 2.0 Authentication**: Secure API access using JWT bearer tokens.
*   **Role-Based Authorization**: Differentiate access based on user roles (e.g., USER, ADMIN).
*   **Swagger UI**: Interactive API documentation for easy testing and exploration.
*   **MySQL Database**: Persistent storage for employee and payroll data.

## Technologies Used
*   **Spring Boot**: Framework for building the application.
*   **Spring Security**: For authentication (OAuth 2.0, JWT) and authorization.
*   **Spring Data JPA**: For database interaction and ORM.
*   **MySQL**: Relational database.
*   **Maven**: Dependency management and build automation.
*   **Lombok**: To reduce boilerplate code (getters, setters, constructors).
*   **Springdoc OpenAPI (Swagger UI)**: For API documentation.
*   **Nimbus JOSE + JWT**: For JWT handling.

## Setup Instructions

### Prerequisites
*   Java 17 or higher
*   Maven 3.6.x or higher
*   MySQL Server

### Database Configuration
1.  Ensure your MySQL server is running.
2.  The application is configured to create the database `enrollment_payroll_db` if it doesn't exist.
3.  Update `src/main/resources/application.properties` with your MySQL credentials:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/enrollment_payroll_db?createDatabaseIfNotExist=true
    spring.datasource.username=root
    spring.datasource.password=your_mysql_password
    ```

### Build the Project
Navigate to the project root directory (`enrollmentpayrollsystem`) and run:
```bash
mvn clean install
```

## Running the Application
After building, you can run the Spring Boot application from the project root:
```bash
mvn spring-boot:run
```
The application will start on `http://localhost:8080`.

## API Endpoints

### 1. Authentication
*   **Get JWT Token**:
    *   **URL**: `POST /token`
    *   **Content-Type**: `application/json`
    *   **Body**:
        ```json
        {
            "username": "user",
            "password": "password"
        }
        ```
        or
        ```json
        {
            "username": "admin",
            "password": "password"
        }
        ```
    *   **Response**: A JWT bearer token string.

### 2. Protected API Endpoints
All API endpoints under `/api/**` require a valid JWT bearer token in the `Authorization` header.

*   **Base URL**: `http://localhost:8080/api`
*   **Example Request (using curl)**:
    ```bash
    curl -X GET 'http://localhost:8080/api/payroll/1' \
    -H 'accept: */*' \
    -H 'Authorization: Bearer YOUR_JWT_TOKEN_HERE'
    ```

### 3. API Documentation (Swagger UI)
Access the interactive API documentation at:
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Security Details
*   **User Credentials**:
    *   **Username**: `user`
    *   **Password**: `password`
    *   **Roles**: `USER`
*   **Admin Credentials**:
    *   **Username**: `admin`
    *   **Password**: `password`
    *   **Roles**: `ADMIN`, `USER`

These credentials are for testing purposes and are configured in `SecurityConfig.java` using `InMemoryUserDetailsManager`. For production, you would typically integrate with a proper user management system.

## Contributing
(Optional section for contribution guidelines)

## License
(Optional section for licensing information)
