package com.kiwipay.onboarding.client.application.internal.queryservices;

import com.kiwipay.onboarding.client.application.internal.dto.PatientResponse;
import com.kiwipay.onboarding.client.application.internal.dto.PatientSummaryResponse;
import com.kiwipay.onboarding.client.domain.model.aggregates.Patient;
import com.kiwipay.onboarding.client.domain.services.PatientQueryService;
import com.kiwipay.onboarding.client.infrastructure.persistence.jpa.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Patient Query Service Implementation
 * Handles read operations for Patient aggregate
 */
@Service
public class PatientQueryServiceImpl implements PatientQueryService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public PatientResponse getPatientById(Long loanId, Long patientId) {
        return patientRepository.findByIdAndLoanId(patientId, loanId)
                .map(this::toPatientResponse)
                .orElse(null);
    }

    @Override
    public List<PatientSummaryResponse> getPatientsByLoanId(Long loanId) {
        return patientRepository.findByLoanId(loanId).stream()
                .map(this::toPatientSummaryResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientResponse> getAllPatientsByLoanId(Long loanId) {
        return patientRepository.findByLoanId(loanId).stream()
                .map(this::toPatientResponse)
                .collect(Collectors.toList());
    }

    private PatientResponse toPatientResponse(Patient patient) {
        PatientResponse response = new PatientResponse();
        response.setId(patient.getId());
        response.setLoanId(patient.getLoanId());
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