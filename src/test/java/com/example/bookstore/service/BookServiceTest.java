package com.example.bookstore.service;

import com.example.bookstore.model.Book;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for the BookService class.
 * This class tests the validation logic of the Book model.
 */
class BookServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(BookServiceTest.class);
    private static Validator validator;

    /**
     * Set up the validator before all tests.
     */
    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        logger.info("Validator initialized");
    }

    /**
     * Test cases for book validation.
     * Each test case checks for different validation scenarios for the Book model.
     */
    @Test
    void whenAllFieldsCorrect_thenNoValidationViolations() {
        Book book = new Book(1L, "Test Book", "Test Author", 29.99);
        var violations = validator.validate(book);
        assertTrue(violations.isEmpty());
        assertEquals(0, violations.size());
        logger.info("Book validation passed: {}", book);
    }

    /**
     * Test case for when the title is blank.
     * It should return a validation violation.
     */
    @Test
    void whenTitleIsBlank_thenValidationViolation() {
        Book book = new Book(1L, "", "Test Author", 29.99);
        var violations = validator.validate(book);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("Title is mandatory", violations.iterator().next().getMessage());
        logger.info("Book validation failed: {}", book);

    }

    /**
     * Test case for when the author is blank.
     * It should return a validation violation.
     */
    @Test
    void whenAuthorIsBlank_thenValidationViolation() {
        Book book = new Book(1L, "Test Book", "", 29.99);
        var violations = validator.validate(book);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("Author is mandatory", violations.iterator().next().getMessage());
        logger.info("Book validation failed: {}", book);
    }

    /**
     * Test case for when the price is negative.
     * It should return a validation violation.
     */
    @Test
    void whenPriceIsNegative_thenValidationViolation() {
        Book book = new Book(1L, "Test Book", "Test Author", -1.0);
        var violations = validator.validate(book);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("Price must be non-negative", violations.iterator().next().getMessage());
        logger.info("Book validation failed: {}", book);
    }
}
