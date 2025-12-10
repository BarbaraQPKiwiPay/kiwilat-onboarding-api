package com.kiwipay.onboarding.authentication.interfaces.rest;

import com.kiwipay.onboarding.authentication.application.internal.dto.UserRegistrationRequest;
import com.kiwipay.onboarding.authentication.application.internal.dto.UserResponse;
import com.kiwipay.onboarding.authentication.domain.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/users")
@CrossOrigin(origins = "*")
@Tag(name = "User Management", description = "User management endpoints (SuperAdmin only)")
@SecurityRequirement(name = "bearerAuth")
public class UserManagementController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    @PreAuthorize("hasRole('SUPERADMIN')")
    @Operation(summary = "Register new user", description = "Register a new user (SuperAdmin only)")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRegistrationRequest request) {
        try {
            UserResponse response = authenticationService.registerUser(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('SUPERADMIN')")
    @Operation(summary = "Get all users", description = "Get list of all users (SuperAdmin only)")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = authenticationService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('SUPERADMIN')")
    @Operation(summary = "Get user by ID", description = "Get user details by ID (SuperAdmin only)")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        try {
            UserResponse user = authenticationService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SUPERADMIN')")
    @Operation(summary = "Delete user", description = "Delete user by ID (SuperAdmin only)")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            authenticationService.deleteUser(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}