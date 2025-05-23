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
        super("Book not found with ID: " + id);

    }
}
