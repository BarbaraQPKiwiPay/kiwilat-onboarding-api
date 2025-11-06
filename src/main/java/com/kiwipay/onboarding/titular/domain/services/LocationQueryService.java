package com.kiwipay.onboarding.titular.domain.services;

import com.kiwipay.onboarding.titular.application.internal.dto.DepartmentDto;
import com.kiwipay.onboarding.titular.application.internal.dto.DistrictDto;
import com.kiwipay.onboarding.titular.application.internal.dto.ProvinceDto;

import java.util.List;
import java.util.Optional;

public interface LocationQueryService {
    List<DepartmentDto> getAllDepartments();
    Optional<DepartmentDto> getDepartmentById(String id);
    
    List<ProvinceDto> getAllProvinces();
    List<ProvinceDto> getProvincesByDepartmentId(String departmentId);
    
    List<DistrictDto> getAllDistricts();
    List<DistrictDto> getDistrictsByProvinceId(String provinceId);
}
