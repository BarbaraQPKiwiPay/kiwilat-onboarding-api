package com.kiwipay.onboarding.catalog.interfaces.rest;

import com.kiwipay.onboarding.catalog.application.internal.dto.MedicalCategoryDto;
import com.kiwipay.onboarding.catalog.domain.services.CatalogQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medical-categories")
@Tag(name = "Medical Categories", description = "Medical categories catalog endpoints")
@CrossOrigin(origins = "*")
public class MedicalCategoryController {

    @Autowired
    private CatalogQueryService catalogQueryService;

    @Operation(summary = "Get all medical categories")
    @GetMapping
    public ResponseEntity<List<MedicalCategoryDto>> getAllMedicalCategories() {
        List<MedicalCategoryDto> categories = catalogQueryService.getAllMedicalCategories();
        return ResponseEntity.ok(categories);
    }
}