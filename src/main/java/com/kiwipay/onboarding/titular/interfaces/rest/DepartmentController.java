package com.kiwipay.onboarding.titular.interfaces.rest;

import com.kiwipay.onboarding.titular.application.internal.dto.DepartmentDto;
import com.kiwipay.onboarding.titular.domain.services.LocationQueryService;
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
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable String departmentId) {
        return locationQueryService.getDepartmentById(departmentId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}
