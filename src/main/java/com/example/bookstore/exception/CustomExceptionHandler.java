// exception/CustomExceptionHandler.java
package com.example.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


/** * Custom exception handler for the Bookstore application.
 * This class handles exceptions thrown by the application and returns appropriate HTTP responses.
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    /** * Handles BookNotFoundException.
     * @param ex the exception
     * @return a ResponseEntity with a NOT_FOUND status and the exception message
     */
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> handleBookNotFound(BookNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);

    }

    /** * Handles MethodArgumentNotValidException.
     * @param ex the exception
     * @return a ResponseEntity with a BAD_REQUEST status and a map of field errors
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }
}
