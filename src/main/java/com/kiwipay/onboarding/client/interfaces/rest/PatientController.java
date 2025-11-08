package com.kiwipay.onboarding.client.interfaces.rest;

import com.kiwipay.onboarding.client.application.internal.dto.PatientCreateRequest;
import com.kiwipay.onboarding.client.application.internal.dto.PatientResponse;
import com.kiwipay.onboarding.client.application.internal.dto.PatientSummaryResponse;
import com.kiwipay.onboarding.client.application.internal.dto.PatientUpdateRequest;
import com.kiwipay.onboarding.client.domain.model.exceptions.PatientBusinessException;
import com.kiwipay.onboarding.client.domain.services.PatientCommandService;
import com.kiwipay.onboarding.client.domain.services.PatientQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients/{clientId}/patients")
@Tag(name = "Patient Controller")
public class PatientController {

    @Autowired
    private PatientCommandService patientCommandService;

    @Autowired
    private PatientQueryService patientQueryService;

    @PostMapping
    public ResponseEntity<?> createPatient(
            @PathVariable Long clientId,
            @RequestBody PatientCreateRequest request) {
        try {
            PatientResponse response = patientCommandService.createPatient(clientId, request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (PatientBusinessException e) {
            return ResponseEntity.status(e.getHttpStatus())
                .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<PatientSummaryResponse>> getPatientsByClient(@PathVariable Long clientId) {
        List<PatientSummaryResponse> patients = patientQueryService.getPatientsByClientId(clientId);
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<?> getPatientById(
            @PathVariable Long clientId,
            @PathVariable Long patientId) {
        try {
            PatientResponse response = patientQueryService.getPatientById(clientId, patientId);
            if (response == null) {
                throw PatientBusinessException.patientNotFound();
            }
            return ResponseEntity.ok(response);
        } catch (PatientBusinessException e) {
            return ResponseEntity.status(e.getHttpStatus())
                .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
        }
    }

    @PutMapping("/{patientId}")
    public ResponseEntity<?> updatePatient(
            @PathVariable Long clientId,
            @PathVariable Long patientId,
            @RequestBody PatientUpdateRequest request) {
        try {
            PatientResponse response = patientCommandService.updatePatient(clientId, patientId, request);
            return ResponseEntity.ok(response);
        } catch (PatientBusinessException e) {
            return ResponseEntity.status(e.getHttpStatus())
                .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
        }
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<?> deletePatient(
            @PathVariable Long clientId,
            @PathVariable Long patientId) {
        try {
            patientCommandService.deletePatient(clientId, patientId);
            return ResponseEntity.noContent().build();
        } catch (PatientBusinessException e) {
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