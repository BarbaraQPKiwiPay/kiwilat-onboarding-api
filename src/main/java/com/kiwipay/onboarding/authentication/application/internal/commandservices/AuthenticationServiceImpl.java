package com.kiwipay.onboarding.authentication.application.internal.commandservices;

import com.kiwipay.onboarding.authentication.application.internal.dto.*;
import com.kiwipay.onboarding.authentication.domain.model.aggregates.User;
import com.kiwipay.onboarding.authentication.domain.services.AuthenticationService;
import com.kiwipay.onboarding.authentication.domain.services.UserCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserCommandService userCommandService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public AuthenticationResponse login(LoginRequest loginRequest) {
        try {
            // Authenticate user
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
                )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = (User) userDetails;

            // Generate JWT token
            String token = jwtTokenUtil.generateToken(
                user.getUsername(),
                user.getRoleNames(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
            );

            return new AuthenticationResponse(
                token,
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRoleNames()
            );

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password", e);
        }
    }

    @Override
    public UserResponse registerUser(UserRegistrationRequest request) {
        // Check if username already exists
        if (userCommandService.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username is already taken!");
        }

        // Check if email already exists
        if (request.getEmail() != null && userCommandService.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email is already in use!");
        }

        // Create user
        User user = userCommandService.createUser(
            request.getUsername(),
            request.getPassword(),
            request.getFirstName(),
            request.getLastName(),
            request.getEmail(),
            request.getRoles()
        );

        return convertToUserResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userCommandService.findAllUsers().stream()
            .map(this::convertToUserResponse)
            .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userCommandService.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return convertToUserResponse(user);
    }

    @Override
    public void deleteUser(Long id) {
        userCommandService.deleteUser(id);
    }

    private UserResponse convertToUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setEnabled(user.getEnabled());
        response.setRoles(user.getRoleNames());
        response.setCreatedAt(user.getCreatedAt() != null ? user.getCreatedAt().toString() : null);
        response.setUpdatedAt(user.getUpdatedAt() != null ? user.getUpdatedAt().toString() : null);
        return response;
    }
}