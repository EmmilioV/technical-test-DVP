package com.dvp.technical_test.infrastructure.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dvp.technical_test.infrastructure.config.AppConfig;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AppConfig appConfig;

    public CustomUserDetailsService(AppConfig appUserConfig) {
        this.appConfig = appUserConfig;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!username.equals(appConfig.getUsername())) {
            throw new UsernameNotFoundException("User not found");
        }

        return User.builder()
                .username(appConfig.getUsername())
                .password(appConfig.getPassword())
                .build();
    }
}
