package com.kiwipay.onboarding.authentication.domain.services;

import com.kiwipay.onboarding.authentication.domain.model.aggregates.User;

import java.util.List;
import java.util.Optional;

public interface UserCommandService {
    User createUser(String username, String password, String firstName, String lastName, 
                   String email, List<String> roles);
    Optional<User> findByUsername(String username);
    List<User> findAllUsers();
    Optional<User> findById(Long id);
    void deleteUser(Long id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}