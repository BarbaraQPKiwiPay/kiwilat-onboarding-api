package com.kiwipay.onboarding.client.interfaces.rest;

import com.kiwipay.onboarding.client.application.internal.dto.PatientCreateRequest;
import com.kiwipay.onboarding.client.application.internal.dto.PatientResponse;
import com.kiwipay.onboarding.client.application.internal.dto.PatientUpdateRequest;
import com.kiwipay.onboarding.client.domain.services.PatientCommandService;
import com.kiwipay.onboarding.client.domain.services.PatientQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("/api/v1")
@Tag(
    name = "Patient Management", 
    description = "Managing patient information including personal data, contact details, and address information"
)
public class PatientController {

    @Autowired
    private PatientCommandService patientCommandService;

    @Autowired
    private PatientQueryService patientQueryService;

    @PostMapping("/clients/{clientId}/patients")
    @Operation(
        summary = "Create a new patient",
        description = "Creates a new patient for a specific client with complete personal information, contact details and address"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201", 
            description = "Patient created successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = PatientResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "Invalid request data - Check required fields and data formats",
            content = @Content(mediaType = "application/json")
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Client not found - The specified clientId does not exist",
            content = @Content(mediaType = "application/json")
        ),
        @ApiResponse(
            responseCode = "409", 
            description = "Conflict - Patient with same document number already exists",
            content = @Content(mediaType = "application/json")
        )
    })
    public ResponseEntity<PatientResponse> createPatient(
            @PathVariable Long clientId,
            @RequestBody PatientCreateRequest request) {
        PatientResponse response = patientCommandService.createPatient(clientId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/clients/{clientId}/patients")
    @Operation(
        summary = "Get all patients for a client",
        description = "Retrieves a complete list of all patients associated with a specific client, including full contact information and addresses"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "List of patients retrieved successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = PatientResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Client not found - The specified clientId does not exist",
            content = @Content(mediaType = "application/json")
        )
    })
    public ResponseEntity<List<PatientResponse>> getAllPatients(
            @PathVariable Long clientId) {
        List<PatientResponse> patients = patientQueryService.getAllPatientsByClientId(clientId);
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/clients/{clientId}/patients/{patientId}")
    @Operation(
        summary = "Get specific patient details",
        description = "Retrieves detailed information of a specific patient including personal data, phone, email, and complete address (department, province, district)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Patient details retrieved successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = PatientResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Patient or client not found - Check that both clientId and patientId exist and are associated",
            content = @Content(mediaType = "application/json")
        )
    })
    public ResponseEntity<PatientResponse> getPatientById(
            @PathVariable Long clientId,
            @PathVariable Long patientId) {
        PatientResponse patient = patientQueryService.getPatientById(clientId, patientId);
        if (patient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patient);
    }

    @PutMapping("/clients/{clientId}/patients/{patientId}")
    @Operation(
        summary = "Update patient information",
        description = "Updates complete patient information including personal data, contact details, and address information"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Patient updated successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = PatientResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "Invalid request data - Check required fields and data formats",
            content = @Content(mediaType = "application/json")
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Patient or client not found",
            content = @Content(mediaType = "application/json")
        )
    })
    public ResponseEntity<PatientResponse> updatePatient(
            @PathVariable Long clientId,
            @PathVariable Long patientId,
            @RequestBody PatientUpdateRequest request) {
        PatientResponse response = patientCommandService.updatePatient(clientId, patientId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/clients/{clientId}/patients/{patientId}")
    @Operation(
        summary = "Delete a patient",
        description = "Permanently deletes a patient and all associated data. This action cannot be undone."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204", 
            description = "Patient deleted successfully"
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Patient or client not found",
            content = @Content(mediaType = "application/json")
        ),
        @ApiResponse(
            responseCode = "409", 
            description = "Cannot delete patient - Patient may have associated records that prevent deletion",
            content = @Content(mediaType = "application/json")
        )
    })
    public ResponseEntity<Void> deletePatient(
            @PathVariable Long clientId,
            @PathVariable Long patientId) {
        patientCommandService.deletePatient(clientId, patientId);
        return ResponseEntity.noContent().build();
    }
}