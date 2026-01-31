package com.gearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gearup.dtos.AuthenticationResult;
import com.gearup.security.requestDto.LoginRequest;
import com.gearup.security.requestDto.SignupRequest;
import com.gearup.services.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth") 
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        AuthenticationResult result = authService.login(loginRequest);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        String response = authService.register(signUpRequest);
        return ResponseEntity.ok(new com.gearup.security.responseDto.MessageResponse(response));
    }
}