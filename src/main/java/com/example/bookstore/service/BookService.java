// service/BookService.java
package com.example.bookstore.service;

import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Service class for managing books.
 */
@Service
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepo;

    /**
     * Constructor for BookService.
     *
     * @param bookRepo The repository for book operations.
     */
    @Autowired
    public BookService(BookRepository bookRepo) {
        this.bookRepo = bookRepo;

    }

    /**
     * Fetches all books from the repository.
     *
     * @return List of all books.
     */
    public List<Book> getAllBooks() {
        logger.info("Fetching all books");
        return bookRepo.findAll();

    }

    /**
     * Fetches a book by its ID.
     *
     * @param id The ID of the book to fetch.
     * @return The book with the specified ID.
     * @throws BookNotFoundException If the book is not found.
     */
    public Book getBook(Long id) {
        logger.info("Fetching book with ID: {}", id);
        return bookRepo.findById(id).orElseThrow(() -> new BookNotFoundException(id));

    }

    /**
     * Adds a new book to the repository.
     *
     * @param book The book to be added.
     * @return The added book.
     */
    public Book addBook(Book book) {
        logger.info("Adding new book: {}", book.getTitle());
        return bookRepo.save(book);

    }

    /**
     * Updates an existing book in the repository.
     *
     * @param id   The ID of the book to update.
     * @param book The updated book data.
     * @return The updated book.
     * @throws BookNotFoundException If the book is not found.
     */
    public Book updateBook(Long id, Book book) {
        if (!bookRepo.existsById(id)) {
            logger.warn("Book not found with ID: {}", id);
            throw new BookNotFoundException(id);
        }
        book.setId(id);
        logger.info("Updating book with ID: {}", id);
        return bookRepo.save(book);

    }

    /**
     * Deletes a book from the repository.
     *
     * @param id The ID of the book to delete.
     * @throws BookNotFoundException If the book is not found.
     */
    public void deleteBook(Long id) {
        if (!bookRepo.existsById(id)) {
            logger.warn("Book not found with ID: {}", id);
            throw new BookNotFoundException(id);
        }
        bookRepo.deleteById(id);
        logger.info("Book with ID {} deleted", id);
    }
}
