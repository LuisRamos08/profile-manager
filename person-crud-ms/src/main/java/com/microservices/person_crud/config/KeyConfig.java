package com.microservices.person_crud.config;

import com.microservices.person_crud.security.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

@Configuration
public class KeyConfig {

    @Bean
    public KeyPair keyPair() throws NoSuchAlgorithmException {
        return KeyGenerator.generateKeyPair();
    }
}
