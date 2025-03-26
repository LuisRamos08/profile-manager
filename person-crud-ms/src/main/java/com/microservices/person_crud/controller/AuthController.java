package com.microservices.person_crud.controller;

import com.microservices.person_crud.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtTokenService jwtTokenService;

    @GetMapping("/get-token")
    public ResponseEntity<String> getToken() {
        return ResponseEntity.ok(jwtTokenService.generateToken());
    }
}
