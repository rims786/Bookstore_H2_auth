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


## API Documentation with Swagger UI

This application uses Swagger (OpenAPI 3.0) for API documentation. Swagger UI provides an interactive interface to explore and test the API endpoints.

### Accessing Swagger UI

Once the application is running, you can access the Swagger documentation in two ways:

1. Main Swagger UI: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
2. Direct link: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Available Endpoints

The API provides the following endpoints for managing books:

- `GET /books` - Retrieve all books
- `GET /books/{id}` - Retrieve a specific book by ID
- `POST /books` - Create a new book
- `PUT /books/{id}` - Update an existing book
- `DELETE /books/{id}` - Delete a book

### Testing API Endpoints

1. **View All Books**
   - Click on `GET /books`
   - Click "Try it out"
   - Click "Execute"

2. **Create a New Book**
   - Click on `POST /books`
   - Click "Try it out"
   - Enter book details in the Request body:
   ```json
   {
     "title": "Example Book",
     "author": "John Doe",
     "isbn": "1234567890",
     "price": 29.99
   }
   ```
   - Click "Execute"

3. **Get a Specific Book**
   - Click on `GET /books/{id}`
   - Click "Try it out"
   - Enter the book ID
   - Click "Execute"

4. **Update a Book**
   - Click on `PUT /books/{id}`
   - Click "Try it out"
   - Enter the book ID
   - Provide updated book details in the Request body
   - Click "Execute"

5. **Delete a Book**
   - Click on `DELETE /books/{id}`
   - Click "Try it out"
   - Enter the book ID
   - Click "Execute"

### API Documentation Formats

The OpenAPI specification is available in two formats:
- JSON format: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)
- YAML format: [http://localhost:8080/v3/api-docs.yaml](http://localhost:8080/v3/api-docs.yaml)

### Response Codes

The API uses standard HTTP response codes:
- `200 OK` - Success
- `201 Created` - Resource successfully created
- `204 No Content` - Success (for DELETE operations)
- `400 Bad Request` - Invalid input
- `404 Not Found` - Resource not found

### Notes
- All endpoints that accept request bodies expect JSON format
- The swagger interface allows testing all endpoints directly from the browser
- Request/Response examples are available for each endpoint in the Swagger UI


## Project Structure

   bookstore/
   ├── src/
   │   ├── main/
   │   │   ├── java/
   │   │   │   └── com/
   │   │   │       └── example/
   │   │   │           └── bookstore/
   │   │   │               ├── DemoApplication.java
   │   │   │               ├── config/
   │   │   │               │   └── OpenAPIConfig.java
   │   │   │               │   └── SecurityConfig.java
   │   │   │               ├── controller/
   │   │   │               │   └── BookController.java
   │   │   │               ├── exception/
   │   │   │               │   └── BookNotFoundException.java
   │   │   │               │   └── CustomExceptionHandler.java
   │   │   │               ├── model/
   │   │   │               │   └── Book.java
   │   │   │               ├── repository/
   │   │   │               │   └── BookRepository.java
   │   │   │               └── service/
   │   │   │                   └── BookService.java
   │   │   └── resources/
   │   │       ├── application.properties
   │   │       ├── data.sql
   │   │       ├── schema.sql
   │   │       ├── static/
   │   │       └── templates/
   │   └── test/
   │       └── java/
   │           └── com/
   │               └── example/
   │                   └── bookstore/
   │                       └── controller/
   │                       	└── BookControllerTest.java│                       
   └── service/
   │                       	└── BookServiceTest.java
   │                       └── DemoApplicationTests.java
   ├── .gitignore
   ├── README.md
   ├── mvnw
   ├── mvnw.cmd
   └── pom.xml

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE.md file for details

## Contact
   - Rimmy Uddin
   - rimmy2008@gmail.com
