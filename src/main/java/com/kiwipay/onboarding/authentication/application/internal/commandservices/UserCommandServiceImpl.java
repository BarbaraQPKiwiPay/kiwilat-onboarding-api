package com.kiwipay.onboarding.authentication.application.internal.commandservices;

import com.kiwipay.onboarding.authentication.domain.model.aggregates.User;
import com.kiwipay.onboarding.authentication.domain.model.entities.Role;
import com.kiwipay.onboarding.authentication.domain.services.UserCommandService;
import com.kiwipay.onboarding.authentication.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.kiwipay.onboarding.authentication.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserCommandServiceImpl implements UserCommandService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(String username, String password, String firstName, 
                          String lastName, String email, List<String> roleNames) {
        // Create user
        User user = new User(
            username,
            passwordEncoder.encode(password),
            firstName,
            lastName,
            email
        );

        // Add roles to user
        if (roleNames != null && !roleNames.isEmpty()) {
            for (String roleName : roleNames) {
                Role role = roleRepository.findByName(roleName)
                    .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
                user.addRole(role);
            }
        }

        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}