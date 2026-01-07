package com.kiwipay.onboarding.partner.interfaces.rest;

import com.kiwipay.onboarding.partner.application.internal.dto.CreatePartnerRequest;
import com.kiwipay.onboarding.partner.application.internal.dto.PartnerResponse;
import com.kiwipay.onboarding.partner.application.internal.dto.UpdatePartnerRequest;
import com.kiwipay.onboarding.partner.domain.model.exceptions.PartnerBusinessException;
import com.kiwipay.onboarding.partner.domain.services.PartnerCommandService;
import com.kiwipay.onboarding.partner.domain.services.PartnerQueryService;

import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/api/v1/partners")
@Tag(name = "Partner Management", description = "Managing partner information including personal data, contact details, and address information. Partners are associated with loan applications.")
@RequiredArgsConstructor
public class PartnerController {

    private final PartnerCommandService partnerCommandService;
    private final PartnerQueryService partnerQueryService;

    /**
     * Create a new partner
     * POST /api/v1/partners
     */
    @PostMapping
    public ResponseEntity<PartnerResponse> createPartner(@Valid @RequestBody CreatePartnerRequest request) {
        try {
            PartnerResponse response = partnerCommandService.createPartner(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (PartnerBusinessException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get partner by ID
     * GET /api/v1/partners/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<PartnerResponse> getPartnerById(@PathVariable Long id) {
        return partnerQueryService.getPartnerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Update spouse
     * PUT /api/v1/spouses/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<PartnerResponse> updatePartner(
            @PathVariable Long id,
            @Valid @RequestBody UpdatePartnerRequest request) {
        try {
            PartnerResponse response = partnerCommandService.updatePartner(id, request);
            return ResponseEntity.ok(response);
        } catch (PartnerBusinessException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Delete partner
     * DELETE /api/v1/partners/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartner(@PathVariable Long id) {
        try {
            partnerCommandService.deletePartner(id);
            return ResponseEntity.noContent().build();
        } catch (PartnerBusinessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Get all partners for a loan
     * GET /api/v1/loans/{loanId}/partners
     */
    @GetMapping("/loan/{loanId}")
    public ResponseEntity<List<PartnerResponse>> getPartnersByLoan(@PathVariable Long loanId) {
        List<PartnerResponse> partners = partnerQueryService.getPartnersByLoan(loanId);
        return ResponseEntity.ok(partners);
    }

    /**
     * Get partner by client ID
     * GET /api/v1/clients/{clientId}/partner
     */
    @GetMapping("/client/{clientId}")
    public ResponseEntity<PartnerResponse> getPartnerByClient(@PathVariable Long clientId) {
        return partnerQueryService.getPartnerByClient(clientId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get partner by guarantor ID
     * GET /api/v1/guarantors/{guarantorId}/partner
     */
    @GetMapping("/guarantor/{guarantorId}")
    public ResponseEntity<PartnerResponse> getPartnerByGuarantor(@PathVariable String guarantorId) {
        return partnerQueryService.getPartnerByGuarantor(guarantorId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get partner by patient ID
     * GET /api/v1/patients/{patientId}/partner
     */
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<PartnerResponse> getPartnerByPatient(@PathVariable Long patientId) {
        return partnerQueryService.getPartnerByPatient(patientId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
