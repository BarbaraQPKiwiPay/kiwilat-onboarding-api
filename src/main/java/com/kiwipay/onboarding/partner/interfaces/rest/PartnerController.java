package com.kiwipay.onboarding.partner.interfaces.rest;

import com.kiwipay.onboarding.partner.application.internal.dto.CreatePartnerRequest;
import com.kiwipay.onboarding.partner.application.internal.dto.PartnerResponse;
import com.kiwipay.onboarding.partner.application.internal.dto.UpdatePartnerRequest;
import com.kiwipay.onboarding.partner.domain.model.exceptions.PartnerBusinessException;
import com.kiwipay.onboarding.partner.domain.services.PartnerCommandService;
import com.kiwipay.onboarding.partner.domain.services.PartnerQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Spouse operations
 * Provides endpoints for CRUD operations on spouse records
 */
@RestController
@RequestMapping("/api/v1/spouses")
@RequiredArgsConstructor
public class PartnerController {

    private final PartnerCommandService spouseCommandService;
    private final PartnerQueryService spouseQueryService;

    /**
     * Create a new spouse
     * POST /api/v1/spouses
     */
    @PostMapping
    public ResponseEntity<PartnerResponse> createSpouse(@Valid @RequestBody CreatePartnerRequest request) {
        try {
            PartnerResponse response = spouseCommandService.createSpouse(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (PartnerBusinessException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get spouse by ID
     * GET /api/v1/spouses/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<PartnerResponse> getSpouseById(@PathVariable Long id) {
        return spouseQueryService.getSpouseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Update spouse
     * PUT /api/v1/spouses/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<PartnerResponse> updateSpouse(
            @PathVariable Long id,
            @Valid @RequestBody UpdatePartnerRequest request) {
        try {
            PartnerResponse response = spouseCommandService.updateSpouse(id, request);
            return ResponseEntity.ok(response);
        } catch (PartnerBusinessException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Delete spouse
     * DELETE /api/v1/spouses/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpouse(@PathVariable Long id) {
        try {
            spouseCommandService.deleteSpouse(id);
            return ResponseEntity.noContent().build();
        } catch (PartnerBusinessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Get all spouses for a loan
     * GET /api/v1/loans/{loanId}/spouses
     */
    @GetMapping("/loan/{loanId}")
    public ResponseEntity<List<PartnerResponse>> getSpousesByLoan(@PathVariable Long loanId) {
        List<PartnerResponse> spouses = spouseQueryService.getSpousesByLoan(loanId);
        return ResponseEntity.ok(spouses);
    }

    /**
     * Get spouse by client ID
     * GET /api/v1/clients/{clientId}/spouse
     */
    @GetMapping("/client/{clientId}")
    public ResponseEntity<PartnerResponse> getSpouseByClient(@PathVariable Long clientId) {
        return spouseQueryService.getSpouseByClient(clientId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get spouse by guarantor ID
     * GET /api/v1/guarantors/{guarantorId}/spouse
     */
    @GetMapping("/guarantor/{guarantorId}")
    public ResponseEntity<PartnerResponse> getSpouseByGuarantor(@PathVariable String guarantorId) {
        return spouseQueryService.getSpouseByGuarantor(guarantorId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get spouse by patient ID
     * GET /api/v1/patients/{patientId}/spouse
     */
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<PartnerResponse> getSpouseByPatient(@PathVariable Long patientId) {
        return spouseQueryService.getSpouseByPatient(patientId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
