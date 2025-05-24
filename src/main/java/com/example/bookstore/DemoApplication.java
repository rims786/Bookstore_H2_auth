package com.example.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Bookstore application.
 * This class serves as the entry point for the Spring Boot application.
 */
@SpringBootApplication
public class DemoApplication {

	/**
	 * The main method that starts the Spring Boot application.
	 * @param args Command-line arguments passed to the application.
	 */
	public static void main(String[] args) {
		// Start the Spring Boot application
		SpringApplication.run(DemoApplication.class, args);

	}

}
