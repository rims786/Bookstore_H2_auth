// exception/BookNotFoundException.java
package com.example.bookstore.exception;

/**
 * Custom exception to be thrown when a book is not found in the database.
 */
public class BookNotFoundException extends RuntimeException {

    /**
     * Constructor for BookNotFoundException.
     *
     * @param id the ID of the book that was not found
     */
    public BookNotFoundException(Long id) {
        // Call the superclass constructor with a message indicating the book ID
        super("Book not found with ID: " + id);

    }
}
