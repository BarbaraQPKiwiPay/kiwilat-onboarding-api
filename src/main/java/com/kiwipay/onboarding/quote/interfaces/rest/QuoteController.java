package com.kiwipay.onboarding.quote.interfaces.rest;

import com.kiwipay.onboarding.quote.application.internal.dto.QuoteCreateRequest;
import com.kiwipay.onboarding.quote.application.internal.dto.QuoteResponse;
import com.kiwipay.onboarding.quote.domain.services.QuoteCommandService;
import com.kiwipay.onboarding.quote.domain.services.QuoteQueryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class QuoteController {
    @Autowired
    private QuoteCommandService quoteCommandService;
    @Autowired
    private QuoteQueryService quoteQueryService;

    @PostMapping("/clients/{clientId}/quotes")
    public ResponseEntity<QuoteResponse> createQuote(
            @PathVariable Long clientId,
            @Valid @RequestBody QuoteCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(quoteCommandService.createQuote(clientId, request));
    }

    @GetMapping("/clients/{clientId}/quotes")
    public ResponseEntity<List<QuoteResponse>> getQuotesByClientId(@PathVariable Long clientId) {
        return ResponseEntity.ok(quoteQueryService.getQuotesByClientId(clientId));
    }

    @GetMapping("/quotes/{quoteId}")
    public ResponseEntity<QuoteResponse> getQuoteById(@PathVariable Long quoteId) {
        return ResponseEntity.ok(quoteQueryService.getQuoteById(quoteId));
    }

    @PutMapping("/quotes/{quoteId}")
    public ResponseEntity<QuoteResponse> updateQuote(
            @PathVariable Long quoteId,
            @Valid @RequestBody QuoteCreateRequest request) {
        return ResponseEntity.ok(quoteCommandService.updateQuote(quoteId, request));
    }

    @PatchMapping("/quotes/{quoteId}")
    public ResponseEntity<QuoteResponse> patchQuote(
            @PathVariable Long quoteId,
            @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(quoteCommandService.patchQuote(quoteId, updates));
    }

    @DeleteMapping("/quotes/{quoteId}")
    public ResponseEntity<Void> deleteQuote(@PathVariable Long quoteId) {
        quoteCommandService.deleteQuote(quoteId);
        return ResponseEntity.noContent().build();
    }
}
