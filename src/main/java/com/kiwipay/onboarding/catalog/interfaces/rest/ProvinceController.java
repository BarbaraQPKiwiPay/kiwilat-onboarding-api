package com.kiwipay.onboarding.catalog.interfaces.rest;

import com.kiwipay.onboarding.catalog.application.internal.dto.ProvinceDto;
import com.kiwipay.onboarding.catalog.domain.exceptions.CatalogBusinessException;
import com.kiwipay.onboarding.catalog.domain.services.LocationQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/provinces")
public class ProvinceController {
    
    @Autowired
    private LocationQueryService locationQueryService;

    @GetMapping
    public ResponseEntity<?> getProvinces(
            @RequestParam(required = false) String departmentId) {
        try {
            List<ProvinceDto> provinces = departmentId != null
                ? locationQueryService.getProvincesByDepartmentId(departmentId)
                : locationQueryService.getAllProvinces();
            return ResponseEntity.ok(Map.of("data", provinces));
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
