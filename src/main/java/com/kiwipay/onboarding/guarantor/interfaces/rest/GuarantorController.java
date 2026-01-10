package com.kiwipay.onboarding.guarantor.interfaces.rest;

import com.kiwipay.onboarding.guarantor.application.internal.dto.GuarantorCreateRequest;
import com.kiwipay.onboarding.guarantor.application.internal.dto.GuarantorResponse;
import com.kiwipay.onboarding.guarantor.domain.services.GuarantorCommandService;
import com.kiwipay.onboarding.guarantor.domain.services.GuarantorQueryService;
import io.swagger.v3.oas.annotations.Operation;
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
@Tag(name = "Guarantor Management", description = "Manages guarantors for loans")
public class GuarantorController {

    @Autowired
    private GuarantorCommandService guarantorCommandService;

    @Autowired
    private GuarantorQueryService guarantorQueryService;

    @PostMapping("/loans/{loanId}/guarantors")
    @Operation(summary = "Create guarantor", description = "Create a new guarantor for a loan")
    public ResponseEntity<GuarantorResponse> createGuarantor(
            @PathVariable Long loanId,
            @Valid @RequestBody GuarantorCreateRequest request) {
        GuarantorResponse response = guarantorCommandService.createGuarantor(loanId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/loans/{loanId}/guarantors")
    @Operation(summary = "Get guarantors by loan", description = "Retrieve all guarantors for a specific loan")
    public ResponseEntity<List<GuarantorResponse>> getGuarantorsByLoan(@PathVariable Long loanId) {
        List<GuarantorResponse> guarantors = guarantorQueryService.getGuarantorsByLoanId(loanId);
        return ResponseEntity.ok(guarantors);
    }

    @GetMapping("/guarantors/{guarantorId}")
    @Operation(summary = "Get guarantor by ID", description = "Retrieve a specific guarantor")
    public ResponseEntity<GuarantorResponse> getGuarantorById(@PathVariable Long guarantorId) {
        GuarantorResponse guarantor = guarantorQueryService.getGuarantorById(guarantorId);
        return ResponseEntity.ok(guarantor);
    }

    @PutMapping("/guarantors/{guarantorId}")
    @Operation(summary = "Update guarantor", description = "Update an existing guarantor")
    public ResponseEntity<GuarantorResponse> updateGuarantor(
            @PathVariable Long guarantorId,
            @Valid @RequestBody GuarantorCreateRequest request) {
        GuarantorResponse response = guarantorCommandService.updateGuarantor(guarantorId, request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/guarantors/{guarantorId}")
    @Operation(summary = "Partially update guarantor", description = "Partially update guarantor fields")
    public ResponseEntity<GuarantorResponse> patchGuarantor(
            @PathVariable Long guarantorId,
            @RequestBody Map<String, Object> updates) {
        GuarantorResponse response = guarantorCommandService.patchGuarantor(guarantorId, updates);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/guarantors/{guarantorId}")
    @Operation(summary = "Delete guarantor", description = "Delete a guarantor")
    public ResponseEntity<Void> deleteGuarantor(@PathVariable Long guarantorId) {
        guarantorCommandService.deleteGuarantor(guarantorId);
        return ResponseEntity.noContent().build();
    }
}