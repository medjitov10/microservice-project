package com.example.usersapi.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Component is a generic stereotype for any Spring-managed component
 */
@Component
public class JwtUtil implements Serializable {
    /**
     * set the expiration time of token to 5 hours
     */
    private static final long JTW_TOKEN_VALIDITY = 5 * 60 * 60;

    /**
     * secret key used to encrypt the password
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * These two methods are used to generate a token.
     * The encryption algorithm and time of expiration are set
     */
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();

        return doGenerateToken(claims, username);
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JTW_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
}
