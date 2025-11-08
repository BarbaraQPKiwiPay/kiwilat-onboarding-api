package com.kiwipay.onboarding.titular.interfaces.rest;

import com.kiwipay.onboarding.titular.application.internal.dto.PatientCreateRequest;
import com.kiwipay.onboarding.titular.application.internal.dto.PatientResponse;
import com.kiwipay.onboarding.titular.application.internal.dto.PatientSummaryResponse;
import com.kiwipay.onboarding.titular.application.internal.dto.PatientUpdateRequest;
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
    public ResponseEntity<PatientResponse> createPatient(
            @PathVariable Long clientId,
            @RequestBody PatientCreateRequest request) {
        try {
            PatientResponse response = patientCommandService.createPatient(clientId, request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PatientSummaryResponse>> getPatientsByClient(@PathVariable Long clientId) {
        List<PatientSummaryResponse> patients = patientQueryService.getPatientsByClientId(clientId);
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<PatientResponse> getPatientById(
            @PathVariable Long clientId,
            @PathVariable Long patientId) {
        PatientResponse response = patientQueryService.getPatientById(clientId, patientId);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{patientId}")
    public ResponseEntity<PatientResponse> updatePatient(
            @PathVariable Long clientId,
            @PathVariable Long patientId,
            @RequestBody PatientUpdateRequest request) {
        try {
            PatientResponse response = patientCommandService.updatePatient(clientId, patientId, request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<Void> deletePatient(
            @PathVariable Long clientId,
            @PathVariable Long patientId) {
        try {
            patientCommandService.deletePatient(clientId, patientId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}