package com.kiwipay.onboarding.catalog.interfaces.rest;

import com.kiwipay.onboarding.catalog.application.internal.dto.DepartmentDto;
import com.kiwipay.onboarding.catalog.domain.exceptions.CatalogBusinessException;
import com.kiwipay.onboarding.catalog.domain.services.LocationQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {
    
    @Autowired
    private LocationQueryService locationQueryService;

    @GetMapping
    public ResponseEntity<Map<String, List<DepartmentDto>>> getAllDepartments() {
        List<DepartmentDto> departments = locationQueryService.getAllDepartments();
        return ResponseEntity.ok(Map.of("data", departments));
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<?> getDepartmentById(@PathVariable String departmentId) {
        try {
            return locationQueryService.getDepartmentById(departmentId)
                .map(ResponseEntity::ok)
                .orElseThrow(CatalogBusinessException::departmentNotFound);
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
