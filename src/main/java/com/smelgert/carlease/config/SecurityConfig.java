package com.smelgert.carlease.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Allow unauthenticated access to /test endpoint
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/test").permitAll()  // Whitelist /test endpoint
                        .anyRequest().authenticated()  // All other endpoints require authentication
                )
                // Add OAuth2 resource server configuration with JWT
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthenticationConverter())  // Configure JWT Authentication Converter
                        )
                );
        return http.build();
    }

    // Configure JWT authentication converter (optional customization)
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        return new JwtAuthenticationConverter();
    }

    // Configure JWT Decoder
    @Bean
    public JwtDecoder jwtDecoder() {
        // Assuming you are fetching keys from a JWKS endpoint
        return NimbusJwtDecoder.withJwkSetUri("https://your-auth-server/.well-known/jwks.json").build();
    }

    /*
    If your JWT tokens are signed with a symmetric secret key instead of public-private key pairs,
    you can configure the JwtDecoder like this:

    @Bean
    public JwtDecoder jwtDecoder() {
        String secretKey = "your-shared-secret-key";
        return NimbusJwtDecoder.withSecretKey(new SecretKeySpec(secretKey.getBytes(), "HMACSHA256")).build();
    }

    This configuration is useful if you’re using an HMAC-based signing method (e.g., HS256).
    */
}
