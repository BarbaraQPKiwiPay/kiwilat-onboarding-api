package com.kiwipay.onboarding.patient.application.internal.commandservices;

import com.kiwipay.onboarding.shared.domain.valueobjects.DocumentType;
import com.kiwipay.onboarding.shared.domain.valueobjects.Gender;
import com.kiwipay.onboarding.loan.infrastructure.persistence.jpa.LoanRepository;
import com.kiwipay.onboarding.patient.application.internal.dto.PatientCreateRequest;
import com.kiwipay.onboarding.patient.application.internal.dto.PatientResponse;
import com.kiwipay.onboarding.patient.application.internal.dto.PatientUpdateRequest;
import com.kiwipay.onboarding.patient.domain.model.aggregates.Patient;
import com.kiwipay.onboarding.patient.domain.model.exceptions.PatientBusinessException;
import com.kiwipay.onboarding.patient.domain.services.PatientCommandService;
import com.kiwipay.onboarding.patient.infrastructure.persistence.jpa.repositories.PatientRepository;
import com.kiwipay.onboarding.catalog.infrastructure.persistence.jpa.repositories.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Patient Command Service Implementation
 * Handles write operations for Patient aggregate
 */
@Service
public class PatientCommandServiceImpl implements PatientCommandService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public PatientResponse createPatient(Long loanId, PatientCreateRequest request) {
        // Verify that the loan exists
        if (!loanRepository.existsById(loanId)) {
            throw new RuntimeException("Loan not found with id: " + loanId);
        }

        // Validate districtId if provided
        if (request.getDistrictId() != null && !request.getDistrictId().isEmpty()) {
            if (!districtRepository.existsById(request.getDistrictId())) {
                throw PatientBusinessException.invalidDistrict(request.getDistrictId());
            }
        }

        Patient patient = new Patient(
                loanId,
                DocumentType.valueOf(request.getDocumentType()),
                request.getDocumentNumber(),
                request.getFirstNames(),
                request.getLastNames(),
                Gender.valueOf(request.getGender()),
                request.getPhone(),
                request.getEmail(),
                request.getDistrictId(),
                request.getAddressLine1());

        patient = patientRepository.save(patient);
        return toPatientResponse(patient);
    }

    @Override
    public PatientResponse updatePatient(Long loanId, Long patientId, PatientUpdateRequest request) {
        Patient existingPatient = patientRepository.findByIdAndLoanId(patientId, loanId)
                .orElseThrow(
                        () -> new RuntimeException("Patient not found with id: " + patientId + " for loan: " + loanId));

        // Update fields
        if (request.getDocumentType() != null) {
            existingPatient.setDocumentType(DocumentType.valueOf(request.getDocumentType()));
        }
        if (request.getDocumentNumber() != null) {
            existingPatient.setDocumentNumber(request.getDocumentNumber());
        }
        existingPatient.setFirstNames(request.getFirstNames());
        existingPatient.setLastNames(request.getLastNames());
        existingPatient.setGender(Gender.valueOf(request.getGender()));
        existingPatient.setPhone(request.getPhone());
        existingPatient.setEmail(request.getEmail());

        // Update districtId and addressLine1
        if (request.getDistrictId() != null) {
            // Validate districtId if provided
            if (!request.getDistrictId().isEmpty()) {
                if (!districtRepository.existsById(request.getDistrictId())) {
                    throw PatientBusinessException.invalidDistrict(request.getDistrictId());
                }
            }
            existingPatient.setDistrictId(request.getDistrictId());
        }

        if (request.getAddressLine1() != null) {
            existingPatient.setAddressLine1(request.getAddressLine1());
        }

        existingPatient = patientRepository.save(existingPatient);
        return toPatientResponse(existingPatient);
    }

    @Override
    public void deletePatient(Long loanId, Long patientId) {
        if (!patientRepository.existsByIdAndLoanId(patientId, loanId)) {
            throw new RuntimeException("Patient not found with id: " + patientId + " for loan: " + loanId);
        }
        patientRepository.deleteByIdAndLoanId(patientId, loanId);
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
        response.setDistrictId(patient.getDistrictId());
        response.setAddressLine1(patient.getAddressLine1());
        response.setCreatedAt(patient.getCreatedAt().toString());
        return response;
    }
}