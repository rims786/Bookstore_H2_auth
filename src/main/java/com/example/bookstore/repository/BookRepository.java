// repository/BookRepository.java
package com.example.bookstore.repository;

import com.example.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Book entity.
 * This interface extends JpaRepository to provide CRUD operations.
 */
public interface BookRepository extends JpaRepository<Book, Long> {
}
