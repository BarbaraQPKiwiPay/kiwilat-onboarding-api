package com.kiwipay.onboarding.authentication.domain.services;

import com.kiwipay.onboarding.authentication.application.internal.dto.AuthenticationResponse;
import com.kiwipay.onboarding.authentication.application.internal.dto.LoginRequest;
import com.kiwipay.onboarding.authentication.application.internal.dto.UserRegistrationRequest;
import com.kiwipay.onboarding.authentication.application.internal.dto.UserResponse;

import java.util.List;

public interface AuthenticationService {
    AuthenticationResponse login(LoginRequest loginRequest);
    UserResponse registerUser(UserRegistrationRequest request);
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);
    void deleteUser(Long id);
}