package com.microservices.person_crud.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class JwtTokenService {

    private final KeyPair keyPair;

    public JwtTokenService(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    public String generateToken() {
        PrivateKey privateKey = keyPair.getPrivate();
        return Jwts.builder()
                .subject(UUID.randomUUID().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 86400000)) // 1 día de validez
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            PublicKey publicKey = keyPair.getPublic();
            Jws<Claims> claims = Jwts.parser()
                    .verifyWith(publicKey)
                    .build()
                    .parseSignedClaims(token);

            if (claims.getPayload().getExpiration().before(new Date())) {
                throw new RuntimeException("Token expired");
            }

            return true;
        } catch (Exception e) {
            throw new RuntimeException("Invalid token", e);
        }
    }
}
