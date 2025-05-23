# Bookstore API

A Spring Boot REST API for managing a bookstore's inventory. This application provides endpoints for creating, reading, updating, and deleting books.

## Prerequisites

- Java 21 or higher
- Maven 3.6.3 or higher
- Git

## Technology Stack

- Spring Boot 3.x
- Spring Security
- Spring Data JPA
- H2 Database
- JUnit 5
- Maven

## Getting Started

### Clone the Repository
```
    git clone <repository-url> cd bookstore

### Build the Application
```
    mvn clean install

``` 

### Run the Application
```
    mvn spring:boot run


The application will start on `http://localhost:8080`

## Database

The application uses H2 in-memory database. You can access the H2 console at:
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:bookstoredb`
- Username: `sa`
- Password: ` ` (empty)

## API Endpoints

### Books API

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET    | /books   | Get all books | USER, ADMIN |
| GET    | /books/{id} | Get book by ID | USER, ADMIN |
| POST   | /books   | Create new book | ADMIN |
| PUT    | /books/{id} | Update book | ADMIN |
| DELETE | /books/{id} | Delete book | ADMIN |

## Authentication

The API uses Basic Authentication with two predefined users:

1. Regular User
    - Username: `user`
    - Password: `user123`
    - Role: USER

2. Admin User
    - Username: `admin`
    - Password: `admin123`
    - Role: ADMIN

## Testing

### Run Unit Tests

```
    mvn test
```

### Run Integration Tests

```
    mvn verify
```

### Sample API Requests

#### Get All Books
```
    curl -X GET [http://localhost:8080/books](http://localhost:8080/books)
    -H 'Authorization: Basic dXNlcjp1c2VyMTIz'

#### Create New Book (Admin only)
```
    curl -X POST [http://localhost:8080/books](http://localhost:8080/books)
    -H 'Authorization: Basic YWRtaW46YWRtaW4xMjM='
    -H 'Content-Type: application/json'
    -d '{ "title": "Spring Boot in Action", "author": "Craig Walls", "price": 39.99 }'

## Error Handling

The API provides meaningful error messages for:
- Invalid input data
- Resource not found
- Unauthorized access
- Server errors

## Project Structure
src ├── main │ ├── java │ │ └── com.example.bookstore │ │ ├── config │ │ ├── controller │ │ ├── model │ │ ├── repository │ │ └── service │ └── resources │ ├── application.yml │ └── schema.sql └── test └── java └── com.example.bookstore └── controller


## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE.md file for details

## Contact
Rimmy Uddin
rimmy2008@gmail.com
