package com.kiwipay.onboarding.client.interfaces.rest;

import com.kiwipay.onboarding.client.application.internal.dto.ClientCreateRequest;
import com.kiwipay.onboarding.client.application.internal.dto.ClientResponse;
import com.kiwipay.onboarding.client.application.internal.dto.ClientUpdateRequest;
import com.kiwipay.onboarding.client.domain.model.exceptions.ClientBusinessException;
import com.kiwipay.onboarding.client.domain.services.ClientCommandService;
import com.kiwipay.onboarding.client.domain.services.ClientQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
@Tag(name = "Titular Controller")
public class ClientController {

    @Autowired
    private ClientCommandService clientCommandService;

    @Autowired
    private ClientQueryService clientQueryService;

    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody ClientCreateRequest request) {
        try {
            ClientResponse response = clientCommandService.createClient(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (ClientBusinessException e) {
            return ResponseEntity.status(e.getHttpStatus())
                .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
        }
    }

    @GetMapping("/{id}")
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
    public ResponseEntity<List<ClientResponse>> getAllClients() {
        List<ClientResponse> clients = clientQueryService.getAllClients();
        return ResponseEntity.ok(clients);
    }

    @PutMapping("/{id}")
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