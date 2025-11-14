package com.kiwipay.onboarding.risk.interfaces.rest;

import com.kiwipay.onboarding.risk.application.internal.dto.LoanRiskInfoResponse;
import com.kiwipay.onboarding.risk.application.internal.dto.MedicalRiskInfoResponse;
import com.kiwipay.onboarding.risk.application.internal.dto.PatientRiskInfoResponse;
import com.kiwipay.onboarding.risk.domain.services.RiskQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Tag(
    name = "Risk Management", 
    description = "Read-only endpoints for retrieving risk assessment information from external risk evaluation platform. Includes patient risk profile, loan information, and medical data analysis."
)
public class RiskController {

    @Autowired
    private RiskQueryService riskQueryService;

    @GetMapping("/clients/{clientId}/risk/patient-info")
    @Operation(
        summary = "Get patient risk information",
        description = "Retrieves comprehensive patient risk profile including personal data, contact information, income details, and Experian credit classification data from external risk platform"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Patient risk information retrieved successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = PatientRiskInfoResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Client not found in risk system - The specified clientId does not exist in the risk evaluation platform",
            content = @Content(mediaType = "application/json")
        ),
        @ApiResponse(
            responseCode = "503", 
            description = "Risk platform unavailable - External risk evaluation service is temporarily unavailable",
            content = @Content(mediaType = "application/json")
        )
    })
    public ResponseEntity<PatientRiskInfoResponse> getPatientRiskInfo(
            @PathVariable Long clientId) {
        PatientRiskInfoResponse response = riskQueryService.getPatientRiskInfo(clientId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/clients/{clientId}/risk/loan-info")
    @Operation(
        summary = "Get loan risk information",
        description = "Retrieves loan-related risk information including campaign details, loan status, application data, installment information, and requested amounts from risk evaluation system"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Loan risk information retrieved successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = LoanRiskInfoResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Client or loan information not found in risk system",
            content = @Content(mediaType = "application/json")
        ),
        @ApiResponse(
            responseCode = "503", 
            description = "Risk platform unavailable - External risk evaluation service is temporarily unavailable",
            content = @Content(mediaType = "application/json")
        )
    })
    public ResponseEntity<LoanRiskInfoResponse> getLoanRiskInfo(
            @PathVariable Long clientId) {
        LoanRiskInfoResponse response = riskQueryService.getLoanRiskInfo(clientId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/clients/{clientId}/risk/medical-info")
    @Operation(
        summary = "Get medical risk information",
        description = "Retrieves medical risk assessment data including medical category classification, assigned medical center, and branch information from external medical risk evaluation system"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Medical risk information retrieved successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = MedicalRiskInfoResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Client medical information not found in risk system",
            content = @Content(mediaType = "application/json")
        ),
        @ApiResponse(
            responseCode = "503", 
            description = "Medical risk platform unavailable - External medical evaluation service is temporarily unavailable",
            content = @Content(mediaType = "application/json")
        )
    })
    public ResponseEntity<MedicalRiskInfoResponse> getMedicalRiskInfo(
            @PathVariable Long clientId) {
        MedicalRiskInfoResponse response = riskQueryService.getMedicalRiskInfo(clientId);
        return ResponseEntity.ok(response);
    }
}