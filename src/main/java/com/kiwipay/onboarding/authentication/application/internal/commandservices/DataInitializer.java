package com.kiwipay.onboarding.authentication.application.internal.commandservices;

import com.kiwipay.onboarding.authentication.domain.model.entities.Role;
import com.kiwipay.onboarding.authentication.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.kiwipay.onboarding.authentication.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCommandServiceImpl userCommandService;

    @Override
    public void run(String... args) throws Exception {
        // Initialize roles if they don't exist
        createRoleIfNotExists("SUPERADMIN", "Super Administrator with full access");
        createRoleIfNotExists("COMERCIAL", "Commercial team access");
        createRoleIfNotExists("ADV", "ADV team access");
        createRoleIfNotExists("RIESGOS", "Risk team access");

        // Create default SuperAdmin user if no users exist
        if (userRepository.count() == 0) {
            System.out.println("Creating default SuperAdmin user...");
            userCommandService.createUser(
                "superadmin",
                "admin123",
                "Super",
                "Administrator",
                "admin@kiwipay.com",
                Arrays.asList("SUPERADMIN")
            );
            System.out.println("Default SuperAdmin created: username=superadmin, password=admin123");
        }
    }

    private void createRoleIfNotExists(String roleName, String description) {
        if (!roleRepository.existsByName(roleName)) {
            Role role = new Role(roleName, description);
            roleRepository.save(role);
            System.out.println("Role created: " + roleName);
        }
    }
}