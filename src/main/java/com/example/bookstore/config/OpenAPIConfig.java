package com.example.bookstore.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for OpenAPI (Swagger) documentation.
 * This class sets up the OpenAPI configuration for the Bookstore API.
 */
@Configuration
public class OpenAPIConfig {

    /**
     * Creates and configures the OpenAPI bean.
     * @return The configured OpenAPI instance.
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Bookstore API")
                        .description("API documentation for the Bookstore application")
                        .version("1.0"));
    }
}
