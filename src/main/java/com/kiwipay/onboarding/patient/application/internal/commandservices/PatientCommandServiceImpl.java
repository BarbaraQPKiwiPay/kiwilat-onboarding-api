package com.kiwipay.onboarding.patient.application.internal.commandservices;

import com.kiwipay.onboarding.patient.domain.model.entities.Address;
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
import com.kiwipay.onboarding.catalog.infrastructure.persistence.jpa.repositories.DepartmentRepository;
import com.kiwipay.onboarding.catalog.infrastructure.persistence.jpa.repositories.ProvinceRepository;
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
    private DepartmentRepository departmentRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public PatientResponse createPatient(Long loanId, PatientCreateRequest request) {
        // Verify that the loan exists
        if (!loanRepository.existsById(loanId)) {
            throw new RuntimeException("Loan not found with id: " + loanId);
        }

        Address address = new Address(
                request.getAddress().getDepartmentId(),
                request.getAddress().getProvinceId(),
                request.getAddress().getDistrictId(),
                request.getAddress().getLine1());

        // Validate address catalog IDs
        validateAddress(address);

        Patient patient = new Patient(
                loanId,
                DocumentType.valueOf(request.getDocumentType()),
                request.getDocumentNumber(),
                request.getFirstNames(),
                request.getLastNames(),
                Gender.valueOf(request.getGender()),
                request.getPhone(),
                request.getEmail(),
                address);

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

        if (request.getAddress() != null) {
            Address updatedAddress = new Address(
                    request.getAddress().getDepartmentId(),
                    request.getAddress().getProvinceId(),
                    request.getAddress().getDistrictId(),
                    request.getAddress().getLine1());
            // Validate address catalog IDs
            validateAddress(updatedAddress);
            existingPatient.setAddress(updatedAddress);
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

    /**
     * Validates that the geographic IDs in the address exist in the catalog
     * 
     * @param address Address to validate
     * @throws PatientBusinessException if any ID is invalid
     */
    private void validateAddress(Address address) {
        if (address == null) {
            return; // Skip validation for null addresses
        }

        // Validate departmentId
        if (address.getDepartmentId() != null && !address.getDepartmentId().isEmpty()) {
            if (!departmentRepository.existsById(address.getDepartmentId())) {
                throw PatientBusinessException.invalidDepartment(address.getDepartmentId());
            }
        }

        // Validate provinceId
        if (address.getProvinceId() != null && !address.getProvinceId().isEmpty()) {
            if (!provinceRepository.existsById(address.getProvinceId())) {
                throw PatientBusinessException.invalidProvince(address.getProvinceId());
            }
        }

        // Validate districtId
        if (address.getDistrictId() != null && !address.getDistrictId().isEmpty()) {
            if (!districtRepository.existsById(address.getDistrictId())) {
                throw PatientBusinessException.invalidDistrict(address.getDistrictId());
            }
        }
    }
}