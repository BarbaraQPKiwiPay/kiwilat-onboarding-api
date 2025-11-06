package com.kiwipay.onboarding.titular.interfaces.rest;

import com.kiwipay.onboarding.titular.application.internal.dto.ClinicBranchDto;
import com.kiwipay.onboarding.titular.application.internal.dto.ClinicDto;
import com.kiwipay.onboarding.titular.domain.services.CatalogQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clinics")
@Tag(name = "Clinics", description = "Clinics catalog endpoints")
@CrossOrigin(origins = "*")
public class ClinicCatalogController {

    @Autowired
    private CatalogQueryService catalogQueryService;

    @Operation(summary = "Get clinics by medical category")
    @GetMapping
    public ResponseEntity<List<ClinicDto>> getClinicsByCategory(
            @RequestParam(required = false) String categoryId) {
        List<ClinicDto> clinics;
        if (categoryId != null) {
            clinics = catalogQueryService.getClinicsByCategory(categoryId);
        } else {
            // Si no se especifica categoría, devolver lista vacía o todas las clínicas
            clinics = List.of();
        }
        return ResponseEntity.ok(clinics);
    }

    @Operation(summary = "Search clinics by name and category")
    @GetMapping(":search")
    public ResponseEntity<List<ClinicDto>> searchClinics(
            @RequestParam String q,
            @RequestParam(required = false) String categoryId) {
        List<ClinicDto> clinics = catalogQueryService.searchClinics(q, categoryId);
        return ResponseEntity.ok(clinics);
    }

    @Operation(summary = "Get branches by clinic")
    @GetMapping("/{clinicId}/branches")
    public ResponseEntity<List<ClinicBranchDto>> getBranchesByClinic(
            @PathVariable String clinicId) {
        List<ClinicBranchDto> branches = catalogQueryService.getBranchesByClinic(clinicId);
        return ResponseEntity.ok(branches);
    }
}