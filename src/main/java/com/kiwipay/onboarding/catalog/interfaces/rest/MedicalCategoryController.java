package com.kiwipay.onboarding.catalog.interfaces.rest;

import com.kiwipay.onboarding.catalog.application.internal.dto.MedicalCategoryDto;
import com.kiwipay.onboarding.catalog.domain.exceptions.CatalogBusinessException;
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
    public ResponseEntity<?> getAllMedicalCategories() {
        try {
            List<MedicalCategoryDto> categories = catalogQueryService.getAllMedicalCategories();
            return ResponseEntity.ok(categories);
        } catch (CatalogBusinessException e) {
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