package com.kiwipay.onboarding.client.interfaces.rest;

import com.kiwipay.onboarding.client.application.internal.dto.ClientCreateRequest;
import com.kiwipay.onboarding.client.application.internal.dto.ClientResponse;
import com.kiwipay.onboarding.client.application.internal.dto.ClientUpdateRequest;
import com.kiwipay.onboarding.client.domain.model.exceptions.ClientBusinessException;
import com.kiwipay.onboarding.client.domain.services.ClientCommandService;
import com.kiwipay.onboarding.client.domain.services.ClientQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
@Tag(name = "Titular Management",
 description = "Managing titular information as name, identification, contact details, and address")
public class ClientController {

    @Autowired
    private ClientCommandService clientCommandService;

    @Autowired
    private ClientQueryService clientQueryService;

    @PostMapping
    @Operation(
        summary = "Create a new client",
        description = "Creates a new client with the provided information"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Client created successfully"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid request data - Check required fields and data formats"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error"
        )
    })
    public ResponseEntity<ClientResponse> createClient(@RequestBody ClientCreateRequest request) {
        ClientResponse response = clientCommandService.createClient(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Get client by ID",
        description = "Retrieves client information by its ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Client retrieved successfully",
            content= @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ClientResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Client not found - The specified clientId does not exist"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error - An unexpected error occurred while processing the request"
        )
    })
    public ResponseEntity<?> getClientById(@PathVariable Long id) {
        try {
            ClientResponse response = clientQueryService.getClientById(id);
            if (response == null) {
                throw ClientBusinessException.clientNotFound();
            }
            return ResponseEntity.ok(response);
        } catch (ClientBusinessException e) {
            return ResponseEntity.status(e.getHttpStatus())
                .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
        }
    }

    @GetMapping
    @Operation(
        summary = "Get all clients",
        description = "Retrieves a list of all clients"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Clients retrieved successfully"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error"
        )
    })
    public ResponseEntity<List<ClientResponse>> getAllClients() {
        List<ClientResponse> clients = clientQueryService.getAllClients();
        return ResponseEntity.ok(clients);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Update client information",
        description = "Updates the information of an existing client"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Client updated successfully"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid request data - Check required fields and data formats"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Client not found - The specified clientId does not exist"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error"
        )
    })
    public ResponseEntity<?> updateClient(@PathVariable Long id, @RequestBody ClientUpdateRequest request) {
        try {
            ClientResponse response = clientCommandService.updateClient(id, request);
            return ResponseEntity.ok(response);
        } catch (ClientBusinessException e) {
            return ResponseEntity.status(e.getHttpStatus())
                .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete client",
        description = "Deletes an existing client by its ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Client deleted successfully"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Client not found - The specified clientId does not exist"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error"
        )
    })
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        try {
            clientCommandService.deleteClient(id);
            return ResponseEntity.noContent().build();
        } catch (ClientBusinessException e) {
            return ResponseEntity.status(e.getHttpStatus())
                .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
        }
    }

    // Error response class
    public static class ErrorResponse {
        private String errorCode;
        private String message;

        public ErrorResponse(String errorCode, String message) {
            this.errorCode = errorCode;
            this.message = message;
        }

        public String getErrorCode() { return errorCode; }
        public String getMessage() { return message; }
    }
}