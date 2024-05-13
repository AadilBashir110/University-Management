package com.adil.universitymanagement.controller;

import com.adil.universitymanagement.config.AuthenticationService;
import com.adil.universitymanagement.entity.User;
import com.adil.universitymanagement.payload.response.AuthenticationResponse;
import com.adil.universitymanagement.payload.request.LoginRequest;
import com.adil.universitymanagement.payload.request.RegisterRequest;
import com.adil.universitymanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService service;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register( @RequestBody RegisterRequest request) {
        Optional<User> isExist = userRepository.findByUsername(request.getUsername());
        if(isExist.isPresent()){
            throw new RuntimeException("This email already used with another account");
        }
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
