package com.dvp.technical_test.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {
    @Value("${app.user.username}")
    private String username;

    @Value("${app.user.password}")
    private String password;

    @Value("${app.secretkey}")
    private String secretKey;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSecretKey() {
        return secretKey;
    }
}
