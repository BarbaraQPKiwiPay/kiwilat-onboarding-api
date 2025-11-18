package com.kiwipay.onboarding.integration.interfaces.rest;

import com.kiwipay.onboarding.catalog.domain.services.CatalogQueryService;
import com.kiwipay.onboarding.catalog.domain.services.LocationQueryService;
import com.kiwipay.onboarding.integration.domain.services.LeadMappingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/integrations")
@Tag(name = "Integration Validation", description = "Endpoints para validar integraciones con catálogos")
@CrossOrigin(origins = "*")
public class IntegrationValidationController {

    @Autowired
    private CatalogQueryService catalogQueryService;
    
    @Autowired
    private LocationQueryService locationQueryService;
    
    @Autowired
    private LeadMappingService leadMappingService;

    @Operation(summary = "Validar mapeo de tipo de documento")
    @GetMapping("/validate/document-type/{typeId}")
    public ResponseEntity<Map<String, Object>> validateDocumentTypeMapping(@PathVariable Integer typeId) {
        Map<String, Object> response = new HashMap<>();
        try {
            String mappedName = leadMappingService.getDocumentTypeName(typeId);
            response.put("success", true);
            response.put("typeId", typeId);
            response.put("mappedName", mappedName);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @Operation(summary = "Validar mapeo de categoría médica")
    @GetMapping("/validate/medical-category")
    public ResponseEntity<Map<String, Object>> validateMedicalCategoryMapping(@RequestParam String procedureInterest) {
        Map<String, Object> response = new HashMap<>();
        try {
            String mappedName = leadMappingService.getMedicalCategoryName(procedureInterest);
            response.put("success", true);
            response.put("originalText", procedureInterest);
            response.put("mappedCategory", mappedName);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @Operation(summary = "Validar mapeo de clínica/sede")
    @GetMapping("/validate/clinic-seat/{seatId}")
    public ResponseEntity<Map<String, Object>> validateClinicSeatMapping(@PathVariable Integer seatId) {
        Map<String, Object> response = new HashMap<>();
        try {
            LeadMappingService.ClinicSeatInfo clinicInfo = leadMappingService.getClinicSeatInfo(seatId);
            response.put("success", true);
            response.put("seatId", seatId);
            response.put("clinicName", clinicInfo.getClinicName());
            response.put("seatName", clinicInfo.getSeatName());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @Operation(summary = "Validar disponibilidad de catálogos")
    @GetMapping("/validate/catalogs")
    public ResponseEntity<Map<String, Object>> validateCatalogs() {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> catalogsStatus = new HashMap<>();
        
        // Validar catálogo de departamentos
        try {
            int departmentCount = locationQueryService.getAllDepartments().size();
            catalogsStatus.put("departments", Map.of("available", true, "count", departmentCount));
        } catch (Exception e) {
            catalogsStatus.put("departments", Map.of("available", false, "error", e.getMessage()));
        }
        
        // Validar catálogo de categorías médicas
        try {
            int categoryCount = catalogQueryService.getAllMedicalCategories().size();
            catalogsStatus.put("medicalCategories", Map.of("available", true, "count", categoryCount));
        } catch (Exception e) {
            catalogsStatus.put("medicalCategories", Map.of("available", false, "error", e.getMessage()));
        }
        
        response.put("success", true);
        response.put("catalogs", catalogsStatus);
        return ResponseEntity.ok(response);
    }
}