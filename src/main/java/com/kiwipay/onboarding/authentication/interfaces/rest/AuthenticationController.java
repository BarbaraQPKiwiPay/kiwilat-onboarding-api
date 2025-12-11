package com.kiwipay.onboarding.authentication.interfaces.rest;

import com.kiwipay.onboarding.authentication.application.internal.dto.AuthenticationResponse;
import com.kiwipay.onboarding.authentication.application.internal.dto.LoginRequest;
import com.kiwipay.onboarding.authentication.domain.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Authentication", description = "Authentication management endpoints")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticate user and return JWT token")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            System.out.println("Login attempt for username: " + loginRequest.getUsername());
            AuthenticationResponse response = authenticationService.login(loginRequest);
            System.out.println("Login successful for username: " + loginRequest.getUsername());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.err.println("Login failed for username: " + loginRequest.getUsername());
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/validate")
    @Operation(summary = "Validate token", description = "Validate if JWT token is still valid")
    public ResponseEntity<String> validateToken() {
        return ResponseEntity.ok("Token is valid");
    }
}