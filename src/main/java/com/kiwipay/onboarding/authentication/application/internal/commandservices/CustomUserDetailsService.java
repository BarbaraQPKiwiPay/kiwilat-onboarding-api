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
        return userCommandService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}