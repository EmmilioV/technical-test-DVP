package com.dvp.technical_test.infrastructure.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;

import javax.crypto.SecretKey;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.dvp.technical_test.infrastructure.config.AppConfig;

@Component
@AllArgsConstructor
public class JwtUtil {

    private final AppConfig appConfig;

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(appConfig.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .signWith(getSigningKey(), Jwts.SIG.HS256)
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)));
    }
}
