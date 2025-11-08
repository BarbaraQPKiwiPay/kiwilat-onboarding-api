package com.kiwipay.onboarding.titular.application.internal.queryservices;

import com.kiwipay.onboarding.titular.application.internal.dto.PatientResponse;
import com.kiwipay.onboarding.titular.application.internal.dto.PatientSummaryResponse;
import com.kiwipay.onboarding.client.domain.model.aggregates.Patient;
import com.kiwipay.onboarding.client.domain.services.PatientQueryService;
import com.kiwipay.onboarding.titular.infrastructure.persistence.jpa.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientQueryServiceImpl implements PatientQueryService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public PatientResponse getPatientById(Long clientId, Long patientId) {
        return patientRepository.findByIdAndClientId(patientId, clientId)
            .map(this::toPatientResponse)
            .orElse(null);
    }

    @Override
    public List<PatientSummaryResponse> getPatientsByClientId(Long clientId) {
        return patientRepository.findByClientId(clientId).stream()
            .map(this::toPatientSummaryResponse)
            .collect(Collectors.toList());
    }

    private PatientResponse toPatientResponse(Patient patient) {
        PatientResponse response = new PatientResponse();
        response.setId(patient.getId());
        response.setClientId(patient.getClientId());
        response.setDocumentType(patient.getDocumentType().name());
        response.setDocumentNumber(patient.getDocumentNumber());
        response.setFirstNames(patient.getFirstNames());
        response.setLastNames(patient.getLastNames());
        response.setGender(patient.getGender().name());
        response.setPhone(patient.getPhone());
        response.setEmail(patient.getEmail());

        if (patient.getAddress() != null) {
            PatientResponse.AddressDto addressDto = new PatientResponse.AddressDto();
            addressDto.setDepartmentId(patient.getAddress().getDepartmentId());
            addressDto.setProvinceId(patient.getAddress().getProvinceId());
            addressDto.setDistrictId(patient.getAddress().getDistrictId());
            addressDto.setLine1(patient.getAddress().getLine1());
            response.setAddress(addressDto);
        }

        response.setCreatedAt(patient.getCreatedAt().toString());
        return response;
    }

    private PatientSummaryResponse toPatientSummaryResponse(Patient patient) {
        PatientSummaryResponse response = new PatientSummaryResponse();
        response.setId(patient.getId());
        response.setDocumentType(patient.getDocumentType().name());
        response.setDocumentNumber(patient.getDocumentNumber());
        response.setFirstNames(patient.getFirstNames());
        response.setLastNames(patient.getLastNames());
        response.setGender(patient.getGender().name());
        return response;
    }
}