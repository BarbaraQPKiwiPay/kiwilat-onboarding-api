package com.kiwipay.onboarding.keynua.config;

import com.kiwipay.onboarding.keynua.domain.services.SglDataProvider;
import com.kiwipay.onboarding.keynua.infrastructure.sgl.MockSglDataProvider;
import com.kiwipay.onboarding.keynua.infrastructure.sgl.RealSglDataProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableConfigurationProperties(KeynuaProperties.class)
public class KeynuaConfig {

    /**
     * WebClient bean configured for Keynua API calls
     */
    @Bean
    public WebClient keynuaWebClient(KeynuaProperties properties) {
        return WebClient.builder()
                .baseUrl(properties.getApiHost())
                .defaultHeader("X-api-key", properties.getApiKey())
                .defaultHeader("Authorization", properties.getAuthToken())
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    /**
     * SGL Data Provider bean - returns mock or real implementation based on
     * configuration
     */
    @Bean
    public SglDataProvider sglDataProvider(KeynuaProperties properties) {
        if (properties.isUseMockSgl()) {
            return new MockSglDataProvider();
        } else {
            return new RealSglDataProvider();
        }
    }
}
