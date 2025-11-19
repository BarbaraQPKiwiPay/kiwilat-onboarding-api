package com.kiwipay.onboarding.integration.interfaces.rest;

import com.kiwipay.onboarding.integration.application.internal.dto.SGLIntegrationResponse;
import com.kiwipay.onboarding.integration.application.internal.dto.SGLLeadRequest;
import com.kiwipay.onboarding.integration.domain.services.SGLIntegrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for SGL system integration.
 * Provides endpoints for receiving lead data from SGL and processing it into Onboarding system.
 */
@RestController
@RequestMapping("/api/v1/integrations")
@Tag(name = "SGL Integration", 
     description = "Integration endpoints for SGL system to send lead data to Onboarding")
@Slf4j
public class SGLIntegrationController {

    @Autowired
    private SGLIntegrationService sglIntegrationService;

    @PostMapping("/leads")
    @Operation(
        summary = "Receive and process lead from SGL",
        description = "Processes lead data from SGL system and creates corresponding entities in Onboarding system"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Lead processed successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = SGLIntegrationResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid request data - Check required fields and data formats",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = SGLIntegrationResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = SGLIntegrationResponse.class)
            )
        )
    })
    public ResponseEntity<SGLIntegrationResponse> receiveLead(
            @RequestBody SGLLeadRequest request) {
        
        log.info("Received SGL lead request for IdLead: {}", request.getIdLead());
        
        SGLIntegrationResponse response = sglIntegrationService.processLead(request);
        
        HttpStatus status = response.isSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        
        log.info("SGL lead processing completed for IdLead: {} with success: {}", 
                request.getIdLead(), response.isSuccess());
        
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping("/health")
    @Operation(
        summary = "Health check for SGL integration",
        description = "Simple health check endpoint to verify the integration service is running"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Service is healthy"
    )
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("SGL Integration service is running");
    }
}