package com.example.bookstore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a book entity in the bookstore application.
 */
@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    /** The unique identifier of the book. */
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The title of the book. */
    @NotBlank(message = "Title is mandatory")
    @Column(name = "title", nullable = false)
    private String title;

    /** The author of the book. */
    @NotBlank(message = "Author is mandatory")
    @Column(name = "author", nullable = false)
    private String author;

    /** The price of the book. */
    @Min(value = 0, message = "Price must be non-negative")
    @Column(name = "price")
    private double price;

}
