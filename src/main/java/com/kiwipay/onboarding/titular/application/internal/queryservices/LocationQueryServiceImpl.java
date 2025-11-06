package com.kiwipay.onboarding.titular.application.internal.queryservices;

import com.kiwipay.onboarding.titular.application.internal.dto.DepartmentDto;
import com.kiwipay.onboarding.titular.application.internal.dto.DistrictDto;
import com.kiwipay.onboarding.titular.application.internal.dto.ProvinceDto;
import com.kiwipay.onboarding.titular.domain.model.entities.Department;
import com.kiwipay.onboarding.titular.domain.model.entities.District;
import com.kiwipay.onboarding.titular.domain.model.entities.Province;
import com.kiwipay.onboarding.titular.domain.services.LocationQueryService;
import com.kiwipay.onboarding.titular.infrastructure.persistence.jpa.repositories.DepartmentRepository;
import com.kiwipay.onboarding.titular.infrastructure.persistence.jpa.repositories.DistrictRepository;
import com.kiwipay.onboarding.titular.infrastructure.persistence.jpa.repositories.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocationQueryServiceImpl implements LocationQueryService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public List<DepartmentDto> getAllDepartments() {
        return departmentRepository.findAll().stream()
            .map(this::toDepartmentDto)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<DepartmentDto> getDepartmentById(String id) {
        return departmentRepository.findById(id)
            .map(this::toDepartmentDto);
    }

    @Override
    public List<ProvinceDto> getAllProvinces() {
        return provinceRepository.findAll().stream()
            .map(this::toProvinceDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<ProvinceDto> getProvincesByDepartmentId(String departmentId) {
        return provinceRepository.findAll().stream()
            .filter(p -> p.getDepartment() != null && departmentId.equals(p.getDepartment().getId()))
            .map(this::toProvinceDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<DistrictDto> getAllDistricts() {
        return districtRepository.findAll().stream()
            .map(this::toDistrictDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<DistrictDto> getDistrictsByProvinceId(String provinceId) {
        return districtRepository.findAll().stream()
            .filter(d -> d.getProvince() != null && provinceId.equals(d.getProvince().getId()))
            .map(this::toDistrictDto)
            .collect(Collectors.toList());
    }

    // Mappers (private utility methods)
    private DepartmentDto toDepartmentDto(Department entity) {
        return new DepartmentDto(entity.getId(), entity.getName());
    }

    private ProvinceDto toProvinceDto(Province entity) {
        String departmentId = entity.getDepartment() != null ? entity.getDepartment().getId() : null;
        return new ProvinceDto(entity.getId(), entity.getName(), departmentId);
    }

    private DistrictDto toDistrictDto(District entity) {
        String provinceId = entity.getProvince() != null ? entity.getProvince().getId() : null;
        return new DistrictDto(entity.getId(), entity.getName(), provinceId);
    }
}
