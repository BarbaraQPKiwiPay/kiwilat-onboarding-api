package com.kiwipay.onboarding.titular.interfaces.rest;

import com.kiwipay.onboarding.titular.application.internal.dto.ProvinceDto;
import com.kiwipay.onboarding.titular.domain.services.LocationQueryService;
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
    public ResponseEntity<Map<String, List<ProvinceDto>>> getProvinces(
            @RequestParam(required = false) String departmentId) {
        List<ProvinceDto> provinces = departmentId != null
            ? locationQueryService.getProvincesByDepartmentId(departmentId)
            : locationQueryService.getAllProvinces();
        return ResponseEntity.ok(Map.of("data", provinces));
    }
}
