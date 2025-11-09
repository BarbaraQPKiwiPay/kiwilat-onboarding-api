package com.kiwipay.onboarding.quote.interfaces.rest;

import com.kiwipay.onboarding.quote.application.internal.dto.QuoteCreateRequest;
import com.kiwipay.onboarding.quote.application.internal.dto.QuoteResponse;
import com.kiwipay.onboarding.quote.domain.services.QuoteCommandService;
import com.kiwipay.onboarding.quote.domain.services.QuoteQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Quote Controller", description = "Manages quotes for clients")
public class QuoteController {
    @Autowired
    private QuoteCommandService quoteCommandService;
    @Autowired
    private QuoteQueryService quoteQueryService;

    @PostMapping("/clients/{clientId}/quotes")
    @Operation(summary = "Create a new quote for a client", description = "Creates a new quote associated with the specified client")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Quote created successfully"),
        @ApiResponse(responseCode = "404", description = "Client not found"),
        @ApiResponse(responseCode = "422", description = "Invalid document number or monthly income"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<QuoteResponse> createQuote(
            @PathVariable Long clientId,
            @Valid @RequestBody QuoteCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(quoteCommandService.createQuote(clientId, request));
    }

    @GetMapping("/clients/{clientId}/quotes")
    @Operation(summary = "Get all quotes by client ID", description = "Retrieves all quotes associated with the specified client")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Quotes retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<QuoteResponse>> getQuotesByClientId(@PathVariable Long clientId) {
        return ResponseEntity.ok(quoteQueryService.getQuotesByClientId(clientId));
    }

    @GetMapping("/quotes/{quoteId}")
    @Operation(summary = "Get quote by ID", description = "Retrieves a specific quote by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Quote retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Quote not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<QuoteResponse> getQuoteById(@PathVariable Long quoteId) {
        return ResponseEntity.ok(quoteQueryService.getQuoteById(quoteId));
    }

    @PutMapping("/quotes/{quoteId}")
    @Operation(summary = "Update quote completely", description = "Updates all fields of an existing quote")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Quote updated successfully"),
        @ApiResponse(responseCode = "404", description = "Quote not found"),
        @ApiResponse(responseCode = "422", description = "Invalid document number or monthly income"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<QuoteResponse> updateQuote(
            @PathVariable Long quoteId,
            @Valid @RequestBody QuoteCreateRequest request) {
        return ResponseEntity.ok(quoteCommandService.updateQuote(quoteId, request));
    }

    @PatchMapping("/quotes/{quoteId}")
    @Operation(summary = "Update quote partially", description = "Updates specific fields of an existing quote")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Quote updated successfully"),
        @ApiResponse(responseCode = "404", description = "Quote not found"),
        @ApiResponse(responseCode = "422", description = "Invalid field values"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<QuoteResponse> patchQuote(
            @PathVariable Long quoteId,
            @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(quoteCommandService.patchQuote(quoteId, updates));
    }

    @DeleteMapping("/quotes/{quoteId}")
    @Operation(summary = "Delete quote", description = "Deletes an existing quote")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Quote deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Quote not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteQuote(@PathVariable Long quoteId) {
        quoteCommandService.deleteQuote(quoteId);
        return ResponseEntity.noContent().build();
    }
}
