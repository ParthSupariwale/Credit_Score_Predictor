package com.fintech.creditscoring.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    // Use a secure key for signing the JWT
    private final SecretKey key = Keys.hmacShaKeyFor("LplNt2k8SBNwBJzMubQiah1ddl4luoBVz9qnStt+yRM=".getBytes());

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(key) // Verify the token with the secret key
                .build()
                .parseSignedClaims(token) // Parse the signed claims
                .getPayload(); // Get the payload (claims)
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .claims(claims) // Set the claims
                .subject(subject) // Set the subject
                .issuedAt(new Date(System.currentTimeMillis())) // Set the issued at time
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Set the expiration time (10 hours)
                .signWith(key) // Sign the token with the secret key
                .compact(); // Compact the token into a string
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}