package com.kiwipay.onboarding.client.interfaces.rest;

import com.kiwipay.onboarding.client.application.internal.dto.SpouseCreateRequest;
import com.kiwipay.onboarding.client.application.internal.dto.SpousePatchRequest;
import com.kiwipay.onboarding.client.application.internal.dto.SpouseResponse;
import com.kiwipay.onboarding.client.application.internal.dto.SpouseUpdateRequest;
import com.kiwipay.onboarding.client.domain.model.exceptions.SpouseBusinessException;
import com.kiwipay.onboarding.client.domain.services.SpouseCommandService;
import com.kiwipay.onboarding.client.domain.services.SpouseQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clients/{clientId}/spouse")
@Tag(name = "Spouse Management", description = "Managing spouse information related to clients")
public class SpouseController {

    @Autowired
    private SpouseCommandService spouseCommandService;

    @Autowired
    private SpouseQueryService spouseQueryService;

    @PostMapping
    @Operation(summary = "Create spouse", description = "Creates a new spouse for a specific client")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Spouse created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request data"),
        @ApiResponse(responseCode = "404", description = "Client not found"),
        @ApiResponse(responseCode = "409", description = "Spouse already exists for this client"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> createSpouse(
            @PathVariable Long clientId,
            @RequestBody SpouseCreateRequest request) {
        try {
            SpouseResponse response = spouseCommandService.createSpouse(clientId, request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (SpouseBusinessException e) {
            return ResponseEntity.status(e.getHttpStatus())
                .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                .body(new ErrorResponse("VALIDATION_ERROR", e.getMessage()));
        }
    }

    @GetMapping
    @Operation(summary = "Get spouse information", description = "Retrieves spouse information for a specific client")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Spouse information retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Spouse not found for this client"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> getSpouse(@PathVariable Long clientId) {
        SpouseResponse response = spouseQueryService.getSpouseByClientId(clientId);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("SPOUSE_NOT_FOUND", "Spouse not found for this client"));
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Operation(summary = "Update spouse", description = "Updates spouse information for a specific client")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Spouse updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request data"),
        @ApiResponse(responseCode = "404", description = "Spouse or client not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> updateSpouse(
            @PathVariable Long clientId,
            @RequestBody SpouseUpdateRequest request) {
        try {
            SpouseResponse response = spouseCommandService.updateSpouse(clientId, request);
            return ResponseEntity.ok(response);
        } catch (SpouseBusinessException e) {
            return ResponseEntity.status(e.getHttpStatus())
                .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                .body(new ErrorResponse("VALIDATION_ERROR", e.getMessage()));
        }
    }

    @PatchMapping
    @Operation(summary = "Patch spouse", description = "Partially updates spouse information for a specific client")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Spouse patched successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request data"),
        @ApiResponse(responseCode = "404", description = "Spouse or client not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> patchSpouse(
            @PathVariable Long clientId,
            @RequestBody SpousePatchRequest request) {
        try {
            SpouseResponse response = spouseCommandService.patchSpouse(clientId, request);
            return ResponseEntity.ok(response);
        } catch (SpouseBusinessException e) {
            return ResponseEntity.status(e.getHttpStatus())
                .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                .body(new ErrorResponse("VALIDATION_ERROR", e.getMessage()));
        }
    }

    @DeleteMapping
    @Operation(summary = "Delete spouse", description = "Deletes the spouse of a specific client")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Spouse deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Spouse or client not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> deleteSpouse(@PathVariable Long clientId) {
        try {
            spouseCommandService.deleteSpouse(clientId);
            return ResponseEntity.noContent().build();
        } catch (SpouseBusinessException e) {
            return ResponseEntity.status(e.getHttpStatus())
                .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
        }
    }

    // Inner class for error responses
    public static class ErrorResponse {
        private String code;
        private String message;

        public ErrorResponse(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() { return code; }
        public String getMessage() { return message; }
    }
}