package com.kiwipay.onboarding.clinicaldata.interfaces.rest;

import com.kiwipay.onboarding.clinicaldata.application.internal.dto.ClinicalDataCreateRequest;
import com.kiwipay.onboarding.clinicaldata.application.internal.dto.ClinicalDataResponse;
import com.kiwipay.onboarding.clinicaldata.domain.model.exceptions.ClinicalDataBusinessException;
import com.kiwipay.onboarding.clinicaldata.domain.services.ClinicalDataCommandService;
import com.kiwipay.onboarding.clinicaldata.domain.services.ClinicalDataQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clients/{clientId}/clinical-data")
@Tag(name = "Clinical Data", description = "Clinical data management endpoints")
@CrossOrigin(origins = "*")
public class ClinicalDataController {

    @Autowired
    private ClinicalDataCommandService clinicalDataCommandService;

    @Autowired
    private ClinicalDataQueryService clinicalDataQueryService;

    @Operation(summary = "Get clinical data for a client")
    @GetMapping
    public ResponseEntity<?> getClinicalData(@PathVariable Long clientId) {
        ClinicalDataResponse response = clinicalDataQueryService.getClinicalDataByClientId(clientId);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("CLINICAL_DATA_NOT_FOUND", "Clinical data not found for this client"));
        }
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Create clinical data for a client")
    @PostMapping
    public ResponseEntity<?> createClinicalData(
            @PathVariable Long clientId,
            @RequestBody ClinicalDataCreateRequest request) {
        try {
            ClinicalDataResponse response = clinicalDataCommandService.createClinicalData(clientId, request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (ClinicalDataBusinessException e) {
            return ResponseEntity.status(e.getHttpStatus())
                .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                .body(new ErrorResponse("VALIDATION_ERROR", e.getMessage()));
        }
    }

    @Operation(summary = "Update clinical data for a client")
    @PutMapping
    public ResponseEntity<?> updateClinicalData(
            @PathVariable Long clientId,
            @RequestBody ClinicalDataCreateRequest request) {
        try {
            ClinicalDataResponse response = clinicalDataCommandService.updateClinicalData(clientId, request);
            return ResponseEntity.ok(response);
        } catch (ClinicalDataBusinessException e) {
            return ResponseEntity.status(e.getHttpStatus())
                .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                .body(new ErrorResponse("VALIDATION_ERROR", e.getMessage()));
        }
    }

    @Operation(summary = "Delete clinical data for a client")
    @DeleteMapping
    public ResponseEntity<?> deleteClinicalData(@PathVariable Long clientId) {
        try {
            clinicalDataCommandService.deleteClinicalData(clientId);
            return ResponseEntity.noContent().build();
        } catch (ClinicalDataBusinessException e) {
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