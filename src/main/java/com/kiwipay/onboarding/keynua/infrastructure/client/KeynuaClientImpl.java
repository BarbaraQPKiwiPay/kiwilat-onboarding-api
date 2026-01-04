package com.kiwipay.onboarding.keynua.infrastructure.client;

import com.kiwipay.onboarding.keynua.dto.keynua.KeynuaContractRequest;
import com.kiwipay.onboarding.keynua.dto.keynua.KeynuaContractResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Keynua HTTP Client Implementation
 * Uses WebClient for HTTP communication with Keynua API
 */
@Service
public class KeynuaClientImpl implements KeynuaClient {

    private static final Logger log = LoggerFactory.getLogger(KeynuaClientImpl.class);
    private final WebClient webClient;

    public KeynuaClientImpl(WebClient keynuaWebClient) {
        this.webClient = keynuaWebClient;
    }

    @Override
    public KeynuaContractResponse createContract(KeynuaContractRequest request) {
        log.info("Creating contract in Keynua with templateId: {}", request.getTemplateId());
        log.debug("Keynua request payload: {}", request);

        try {
            KeynuaContractResponse response = webClient.put()
                    .uri("/contracts/v1")
                    .bodyValue(request)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,
                            clientResponse -> clientResponse.bodyToMono(String.class).flatMap(body -> {
                                log.error("Keynua API 4xx error: {}", body);
                                return Mono.error(new KeynuaApiException(
                                        "Keynua API client error: " + body,
                                        clientResponse.statusCode().value()));
                            }))
                    .onStatus(HttpStatusCode::is5xxServerError,
                            clientResponse -> clientResponse.bodyToMono(String.class).flatMap(body -> {
                                log.error("Keynua API 5xx error: {}", body);
                                return Mono.error(new KeynuaApiException(
                                        "Keynua API server error: " + body,
                                        clientResponse.statusCode().value()));
                            }))
                    .bodyToMono(KeynuaContractResponse.class)
                    .block();

            log.info("Keynua contract created successfully: contractId={}",
                    response != null ? response.getContractId() : "null");
            log.debug("Keynua response: {}", response);

            return response;

        } catch (Exception e) {
            log.error("Error calling Keynua createContract API", e);
            throw new KeynuaApiException("Failed to create contract in Keynua: " + e.getMessage(), e);
        }
    }

    @Override
    public KeynuaContractResponse getContract(String contractId) {
        log.info("Fetching contract from Keynua: contractId={}", contractId);

        try {
            KeynuaContractResponse response = webClient.get()
                    .uri("/contracts/v1/{contractId}", contractId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,
                            clientResponse -> clientResponse.bodyToMono(String.class).flatMap(body -> {
                                log.error("Keynua API 4xx error fetching contract: {}", body);
                                return Mono.error(new KeynuaApiException(
                                        "Keynua API client error: " + body,
                                        clientResponse.statusCode().value()));
                            }))
                    .onStatus(HttpStatusCode::is5xxServerError,
                            clientResponse -> clientResponse.bodyToMono(String.class).flatMap(body -> {
                                log.error("Keynua API 5xx error fetching contract: {}", body);
                                return Mono.error(new KeynuaApiException(
                                        "Keynua API server error: " + body,
                                        clientResponse.statusCode().value()));
                            }))
                    .bodyToMono(KeynuaContractResponse.class)
                    .block();

            log.debug("Keynua contract fetched: {}", response);
            return response;

        } catch (Exception e) {
            log.error("Error calling Keynua getContract API", e);
            throw new KeynuaApiException("Failed to fetch contract from Keynua: " + e.getMessage(), e);
        }
    }
}
