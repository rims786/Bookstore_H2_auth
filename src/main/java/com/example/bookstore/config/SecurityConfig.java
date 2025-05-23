package com.example.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


/**
 * Security configuration for the Bookstore application.
 * Configures HTTP security, user details, and password encoding.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final String BOOKS_API_PATH = "/books/**";
    private static final String ROLE_ADMIN = "ADMIN";

    /** * Configures the security filter chain for the application.
     * @param http the HttpSecurity object
     * @return the configured SecurityFilterChain
     * @throws Exception if an error occurs during configuration
     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**")
                        .disable())
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .authorizeHttpRequests(authorize -> authorize
                        // H2 Console access
                        .requestMatchers("/h2-console/**").permitAll()
                        // Allow GET operations for all authenticated users
                        .requestMatchers(HttpMethod.GET, BOOKS_API_PATH).hasAnyRole("USER", ROLE_ADMIN)
                        // Restrict all other operations to ADMIN only
                        .requestMatchers(HttpMethod.POST, BOOKS_API_PATH).hasRole(ROLE_ADMIN)
                        .requestMatchers(HttpMethod.PUT, BOOKS_API_PATH).hasRole(ROLE_ADMIN)
                        .requestMatchers(HttpMethod.DELETE, BOOKS_API_PATH).hasRole(ROLE_ADMIN)
                        .anyRequest().authenticated()
                )
                .httpBasic(basic -> {})
                .build();
    }


    /** * Configures the password encoder for the application.
     * @return the configured PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }

    /** * Configures the user details service for the application.
     * @return the configured UserDetailsService
     */
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user123")) // Change this to a more secure password
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin123")) // Change this to a more secure password
                .roles(ROLE_ADMIN)
                .build();

        return new InMemoryUserDetailsManager(user, admin);

    }
}
