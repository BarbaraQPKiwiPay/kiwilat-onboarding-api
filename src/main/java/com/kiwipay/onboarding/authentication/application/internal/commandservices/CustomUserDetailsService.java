package com.kiwipay.onboarding.authentication.application.internal.commandservices;

import com.kiwipay.onboarding.authentication.domain.services.UserCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserCommandService userCommandService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Loading user details for username: " + username);
        try {
            UserDetails userDetails = userCommandService.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
            System.out.println("User found: " + username + " with authorities: " + userDetails.getAuthorities());
            return userDetails;
        } catch (Exception e) {
            System.err.println("Error loading user: " + username + " - " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}