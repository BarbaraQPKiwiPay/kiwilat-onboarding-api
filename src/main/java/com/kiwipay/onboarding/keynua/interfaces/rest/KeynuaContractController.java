package com.kiwipay.onboarding.keynua.interfaces.rest;

import com.kiwipay.onboarding.keynua.domain.services.KeynuaSigningService;
import com.kiwipay.onboarding.keynua.dto.request.CreateContractPayloadRequest;
import com.kiwipay.onboarding.keynua.dto.request.CreateContractRequest;
import com.kiwipay.onboarding.keynua.dto.response.ContractCreationResponse;
import com.kiwipay.onboarding.keynua.dto.response.ContractPayloadResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Keynua Contract Controller
 * Handles contract payload generation and contract creation endpoints
 */
@RestController
@RequestMapping("/api/v1/keynua/contracts")
@Tag(name = "Keynua Contracts", description = "Endpoints for Keynua digital signature contract management")
public class KeynuaContractController {

    private static final Logger log = LoggerFactory.getLogger(KeynuaContractController.class);

    private final KeynuaSigningService signingService;

    public KeynuaContractController(KeynuaSigningService signingService) {
        this.signingService = signingService;
    }

    /**
     * Generate Keynua contract payload (for QA/inspection)
     */
    @PostMapping("/payload")
    @Operation(summary = "Generate Keynua contract payload", description = "Returns the exact JSON payload that would be sent to Keynua API for QA verification")
    public ResponseEntity<ContractPayloadResponse> generatePayload(
            @Valid @RequestBody CreateContractPayloadRequest request) {
        log.info("Request to generate payload for loanId: {}", request.getLoanId());

        try {
            ContractPayloadResponse response = signingService.generateContractPayload(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error generating contract payload", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Create Keynua contract
     */
    @PostMapping
    @Operation(summary = "Create Keynua contract", description = "Creates a digital signature contract in Keynua and returns signing information")
    public ResponseEntity<?> createContract(
            @Valid @RequestBody CreateContractRequest request) {
        log.info("Request to create contract for loanId: {}", request.getLoanId());

        try {
            ContractCreationResponse response = signingService.createContract(request);
            return ResponseEntity.ok(response);
        } catch (org.springframework.web.server.ResponseStatusException e) {
            // Handle validation errors (409 Conflict for missing fields)
            log.error("Validation error creating contract: {}", e.getReason());
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ErrorResponse(e.getReason()));
        } catch (Exception e) {
            log.error("Error creating contract", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Failed to create contract: " + e.getMessage()));
        }
    }

    /**
     * Simple error response DTO
     */
    private record ErrorResponse(String message) {
    }
}
