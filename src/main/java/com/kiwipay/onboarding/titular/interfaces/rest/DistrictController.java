package com.kiwipay.onboarding.titular.interfaces.rest;

import com.kiwipay.onboarding.titular.application.internal.dto.DistrictDto;
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
@RequestMapping("/api/v1/districts")
public class DistrictController {
    
    @Autowired
    private LocationQueryService locationQueryService;

    @GetMapping
    public ResponseEntity<Map<String, List<DistrictDto>>> getDistricts(
            @RequestParam(required = false) String provinceId) {
        List<DistrictDto> districts = provinceId != null
            ? locationQueryService.getDistrictsByProvinceId(provinceId)
            : locationQueryService.getAllDistricts();
        return ResponseEntity.ok(Map.of("data", districts));
    }
}
