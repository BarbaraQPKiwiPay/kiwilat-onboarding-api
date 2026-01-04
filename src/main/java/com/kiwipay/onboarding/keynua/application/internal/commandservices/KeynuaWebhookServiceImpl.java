package com.kiwipay.onboarding.keynua.application.internal.commandservices;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiwipay.onboarding.keynua.config.KeynuaProperties;
import com.kiwipay.onboarding.keynua.domain.model.aggregates.KeynuaContractEntity;
import com.kiwipay.onboarding.keynua.domain.services.KeynuaWebhookService;
import com.kiwipay.onboarding.keynua.dto.keynua.KeynuaContractResponse;
import com.kiwipay.onboarding.keynua.dto.keynua.WebhookEvent;
import com.kiwipay.onboarding.keynua.infrastructure.client.KeynuaClient;
import com.kiwipay.onboarding.keynua.infrastructure.persistence.KeynuaContractRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Map;
import java.util.Optional;

/**
 * Keynua Webhook Service Implementation
 * Handles webhook event processing with HMAC signature verification
 */
@Service
public class KeynuaWebhookServiceImpl implements KeynuaWebhookService {

    private static final Logger log = LoggerFactory.getLogger(KeynuaWebhookServiceImpl.class);

    private final KeynuaClient keynuaClient;
    private final KeynuaContractRepository contractRepository;
    private final KeynuaProperties keynuaProperties;
    private final ObjectMapper objectMapper;

    public KeynuaWebhookServiceImpl(
            KeynuaClient keynuaClient,
            KeynuaContractRepository contractRepository,
            KeynuaProperties keynuaProperties) {
        this.keynuaClient = keynuaClient;
        this.contractRepository = contractRepository;
        this.keynuaProperties = keynuaProperties;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    @Transactional
    public void processWebhook(Map<String, String> headers, String payload) {
        log.info("Processing Keynua webhook");
        log.debug("Headers: {}", headers);
        log.debug("Payload: {}", payload);

        // Extract signature headers
        String timestamp = headers.get("x-keynua-webhook-ts");
        String signatureV2 = headers.get("x-keynua-webhook-sigv2");
        String signatureV1 = headers.get("x-keynua-webhook-sig");

        // Verify signature
        boolean signatureValid = false;
        if (signatureV2 != null && timestamp != null) {
            signatureValid = verifySignatureV2(signatureV2, timestamp, payload, keynuaProperties.getWebhookSecret());
            if (!signatureValid) {
                log.error("Invalid webhook signature (v2)");
                throw new SecurityException("Invalid webhook signature");
            }
        } else if (signatureV1 != null) {
            signatureValid = verifySignatureV1(signatureV1, payload, keynuaProperties.getWebhookSecret());
            if (!signatureValid) {
                log.error("Invalid webhook signature (v1)");
                throw new SecurityException("Invalid webhook signature");
            }
        } else {
            log.warn(
                    "No webhook signature headers found - processing anyway (configure webhook secret for production)");
        }

        // Parse webhook event
        try {
            WebhookEvent webhookEvent = objectMapper.readValue(payload, WebhookEvent.class);
            String eventType = webhookEvent.getEventType();
            String contractId = webhookEvent.getContractId();

            log.info("Webhook event type: {}, contractId: {}", eventType, contractId);

            // Route based on event type
            switch (eventType) {
                case "ContractItemUpdated":
                    handleContractItemUpdated(contractId);
                    break;
                case "ContractFinished":
                    handleContractFinished(contractId);
                    break;
                default:
                    log.warn("Unknown webhook event type: {}", eventType);
            }

            log.info("Webhook processed successfully");

        } catch (Exception e) {
            log.error("Error processing webhook payload", e);
            throw new RuntimeException("Failed to process webhook", e);
        }
    }

    private void handleContractItemUpdated(String contractId) {
        log.info("Processing ContractItemUpdated for contractId: {}", contractId);

        // Fetch latest contract data from Keynua
        KeynuaContractResponse contractResponse = keynuaClient.getContract(contractId);

        // Update entity in database
        Optional<KeynuaContractEntity> entityOpt = contractRepository.findByContractId(contractId);
        if (entityOpt.isPresent()) {
            KeynuaContractEntity entity = entityOpt.get();
            entity.setStatus(contractResponse.getStatus());
            contractRepository.save(entity);
            log.info("Contract status updated to: {}", contractResponse.getStatus());
        } else {
            log.warn("Contract not found in database: {}", contractId);
        }
    }

    private void handleContractFinished(String contractId) {
        log.info("Processing ContractFinished for contractId: {}", contractId);

        // Fetch latest contract data from Keynua
        KeynuaContractResponse contractResponse = keynuaClient.getContract(contractId);

        // Update entity status to COMPLETED
        Optional<KeynuaContractEntity> entityOpt = contractRepository.findByContractId(contractId);
        if (entityOpt.isPresent()) {
            KeynuaContractEntity entity = entityOpt.get();
            entity.setStatus("COMPLETED");
            contractRepository.save(entity);
            log.info("Contract marked as COMPLETED");
        } else {
            log.warn("Contract not found in database: {}", contractId);
        }
    }

    /**
     * Verifies webhook signature using HMAC SHA256 (v2)
     * Message format: timestamp + "." + payload
     */
    private boolean verifySignatureV2(String signature, String timestamp, String payload, String secret) {
        try {
            // Construct message
            String message = timestamp + "." + payload;

            // Compute HMAC SHA256
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            mac.init(secretKeySpec);
            byte[] hmacBytes = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));

            // Convert to hex string
            String expectedSignature = bytesToHex(hmacBytes);

            // Constant-time comparison
            return MessageDigest.isEqual(
                    signature.getBytes(StandardCharsets.UTF_8),
                    expectedSignature.getBytes(StandardCharsets.UTF_8));

        } catch (Exception e) {
            log.error("Error verifying signature v2", e);
            return false;
        }
    }

    /**
     * Verifies webhook signature using HMAC SHA256 (v1)
     * Message format: payload only
     */
    private boolean verifySignatureV1(String signature, String payload, String secret) {
        try {
            // Compute HMAC SHA256
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            mac.init(secretKeySpec);
            byte[] hmacBytes = mac.doFinal(payload.getBytes(StandardCharsets.UTF_8));

            // Convert to hex string
            String expectedSignature = bytesToHex(hmacBytes);

            // Constant-time comparison
            return MessageDigest.isEqual(
                    signature.getBytes(StandardCharsets.UTF_8),
                    expectedSignature.getBytes(StandardCharsets.UTF_8));

        } catch (Exception e) {
            log.error("Error verifying signature v1", e);
            return false;
        }
    }

    /**
     * Converts byte array to hexadecimal string
     */
    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
