// controller/BookController.java
package com.example.bookstore.controller;

import com.example.bookstore.model.Book;
import com.example.bookstore.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
@RequestMapping("/books")
@Tag(name = "Book Controller", description = "Operations pertaining to books in the BookStore")
public class BookController {
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    private final BookService service;

    @Autowired
    public BookController(BookService service) {
        this.service = service;

    }

    @Operation(summary = "Get all books", description = "Retrieves a list of all books in the system")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of books")

    @GetMapping
    public List<Book> getBooks() {
        return service.getAllBooks();
    }

    @Operation(summary = "Get a book by ID", description = "Retrieves a specific book by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the book"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })

    @GetMapping("/{id}")
    public Book getBook(
            @Parameter(description = "ID of the book to retrieve") @PathVariable Long id) {
        logger.info("Book with ID {} requested", id);
        return service.getBook(id);
    }


    @Operation(summary = "Create a new book", description = "Adds a new book to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(
            @Parameter(description = "Book object to be added") @RequestBody @Valid Book book) {
        logger.info("Book {} added", book);
        return service.addBook(book);
    }


    @Operation(summary = "Update a book", description = "Updates an existing book's information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book successfully updated"),
            @ApiResponse(responseCode = "404", description = "Book not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping("/{id}")
    public Book updateBook(
            @Parameter(description = "ID of the book to update") @PathVariable Long id,
            @Parameter(description = "Updated book object") @RequestBody @Valid Book book) {
        logger.info("Book with ID {} updated to {}", id, book);
        return service.updateBook(id, book);
    }




    @Operation(summary = "Delete a book", description = "Removes a book from the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Book successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(
            @Parameter(description = "ID of the book to delete") @PathVariable Long id) {
        logger.info("Book with ID {} deleted", id);
        service.deleteBook(id);
    }

}
