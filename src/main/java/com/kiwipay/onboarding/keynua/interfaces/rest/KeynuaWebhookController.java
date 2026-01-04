package com.kiwipay.onboarding.keynua.interfaces.rest;

import com.kiwipay.onboarding.keynua.domain.services.KeynuaWebhookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Keynua Webhook Controller
 * Handles webhook callbacks from Keynua
 */
@RestController
@RequestMapping("/api/v1/keynua/webhook")
@Tag(name = "Keynua Webhook", description = "Webhook endpoint for Keynua event callbacks")
public class KeynuaWebhookController {

    private static final Logger log = LoggerFactory.getLogger(KeynuaWebhookController.class);

    private final KeynuaWebhookService webhookService;

    public KeynuaWebhookController(KeynuaWebhookService webhookService) {
        this.webhookService = webhookService;
    }

    /**
     * Keynua webhook handler
     */
    @PostMapping
    @Operation(summary = "Keynua webhook handler", description = "Receives and processes webhook events from Keynua")
    public ResponseEntity<Void> handleWebhook(
            @RequestHeader Map<String, String> headers,
            @RequestBody String payload) {

        log.info("Received Keynua webhook");
        log.debug("Headers: {}", headers);

        try {
            webhookService.processWebhook(headers, payload);
            return ResponseEntity.ok().build();

        } catch (SecurityException e) {
            // Invalid signature
            log.error("Webhook authentication failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        } catch (Exception e) {
            log.error("Error processing webhook", e);
            // Return 200 OK to avoid retries from Keynua
            // Log the error but don't expose details
            return ResponseEntity.ok().build();
        }
    }
}
