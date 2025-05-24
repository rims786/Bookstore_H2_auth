package com.example.bookstore.controller;

import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the BookController class.
 * This class tests the REST API endpoints for the Book entity.
 */
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    // Logger instance for logging information and errors
    private static final Logger logger = LoggerFactory.getLogger(BookControllerTest.class);

    // MockMvc instance for simulating HTTP requests
    @Autowired
    private MockMvc mockMvc;

    // Service instance for handling book-related business logic
    @Autowired
    private BookService bookService;

    // Repository instance for accessing book data
    @Autowired
    private BookRepository bookRepository;

    // ObjectMapper instance for serializing/deserializing JSON data
    @Autowired
    private BookController bookController;

    // ObjectMapper instance for converting objects to JSON
    @Autowired
    private ObjectMapper objectMapper;

    // Test book instance used in multiple tests
    private Book testBook;

    /**
     * Set up a method to be executed before each test.
     * This method cleans the database and creates a test book.
     */
    @BeforeEach
    void setUp() {
        // Clean the database first
        bookRepository.deleteAll();
        logger.debug("Deleted all existing books from the database");
        // Then create our test book
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        // Save the book to the repository
        testBook = bookService.addBook(book);

    }

    /**
     * Test cases for the BookController class.
     * Each test case checks the behavior of the REST API endpoints.
     */
    @Test
    @WithMockUser(roles = "USER")
    void userShouldGetAllBooks() throws Exception {
        mockMvc.perform(get("/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].title").value("Test Book"))
                .andExpect(jsonPath("$[0].author").value("Test Author"));
        logger.info("GET /books test completed successfully");

    }

    /**
     * Test case for getting a book by ID.
     * It checks if the correct book is returned.
     */
    @Test
    @WithMockUser(roles = "USER")
    void userShouldGetBookById() throws Exception {
        mockMvc.perform(get("/books/{id}", testBook.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Book"))
                .andExpect(jsonPath("$.author").value("Test Author"));
        logger.info("GET /book with ID test completed successfully");

    }

    /**
     * Test case for getting a book by ID that does not exist.
     * It checks if a 404 Not Found status is returned.
     */
    @Test
    @WithMockUser(roles = "USER")
    void userShouldNotBeAbleToCreateBook() throws Exception {
        Book newBook = new Book();
        newBook.setTitle("New Book");
        newBook.setAuthor("New Author");

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newBook)))
                .andDo(print())
                .andExpect(status().isForbidden());
        logger.info("GET /books test completed successfully");

    }

    /**
     * Test case for getting a book by ID that does not exist.
     * It checks if a 404 Not Found status is returned.
     */
    @Test
    @WithMockUser(roles = "USER")
    void userShouldNotBeAbleToDeleteBook() throws Exception {
        mockMvc.perform(delete("/books/{id}", testBook.getId()))
                .andDo(print())
                .andExpect(status().isForbidden());
        logger.info("DELETE /book with ID test completed successfully");

    }

    /**
     * Test case for getting a book by ID that does not exist.
     * It checks if a 404 Not Found status is returned.
     */
    @Test
    @WithMockUser(roles = "USER")
    void userShouldNotBeAbleToUpdateBook() throws Exception {
        Book updatedBook = new Book();
        updatedBook.setTitle("Updated Title");
        updatedBook.setAuthor("Updated Author");

        mockMvc.perform(put("/books/{id}", testBook.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedBook)))
                .andDo(print())
                .andExpect(status().isForbidden());
        logger.info("PUT /book with ID test completed successfully");

    }

    /**
     * Test case for creating a book with invalid data.
     * It checks if a 400 Bad Request status is returned.
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturn400WhenCreatingInvalidBook() throws Exception {
        Book invalidBook = new Book();
        // Book without title and author should be invalid

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidBook)))
                .andDo(print())
                .andExpect(status().isBadRequest());
        logger.info("Invalid book test completed successfully");

    }

    /**
     * Test case for creating a book with valid data.
     * It checks if the book is created successfully.
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void adminShouldCreateBook() throws Exception {
        Book newBook = new Book();
        newBook.setTitle("New Book");
        newBook.setAuthor("New Author");

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newBook)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("New Book"))
                .andExpect(jsonPath("$.author").value("New Author"));
        logger.info("POST /new book test completed successfully");

    }

    /**
     * Test case for updating a book with valid data.
     * It checks if the book is updated successfully.
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void adminShouldUpdateBook() throws Exception {
        Book updatedBook = new Book();
        updatedBook.setTitle("Updated Title");
        updatedBook.setAuthor("Updated Author");

        mockMvc.perform(put("/books/{id}", testBook.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedBook)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"))
                .andExpect(jsonPath("$.author").value("Updated Author"));
        logger.info("PUT /book with ID test completed successfully");

    }

    /**
     * Test case for deleting a book.
     * It checks if the book is deleted successfully.
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void adminShouldDeleteBook() throws Exception {
        mockMvc.perform(delete("/books/{id}", testBook.getId()))
                .andDo(print())
                .andExpect(status().isNoContent());

        // Verify the book is deleted
        mockMvc.perform(get("/books/{id}", testBook.getId()))
                .andExpect(status().isNotFound());
        logger.info("DELETE /book with ID test completed successfully");

    }

}
