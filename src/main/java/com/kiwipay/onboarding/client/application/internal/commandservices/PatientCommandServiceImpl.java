package com.kiwipay.onboarding.client.application.internal.commandservices;

import com.kiwipay.onboarding.client.application.internal.dto.PatientCreateRequest;
import com.kiwipay.onboarding.client.application.internal.dto.PatientResponse;
import com.kiwipay.onboarding.client.application.internal.dto.PatientUpdateRequest;
import com.kiwipay.onboarding.client.domain.model.aggregates.Patient;
import com.kiwipay.onboarding.client.domain.model.entities.Address;
import com.kiwipay.onboarding.client.domain.model.valueobjects.DocumentType;
import com.kiwipay.onboarding.client.domain.model.valueobjects.Gender;
import com.kiwipay.onboarding.client.domain.services.PatientCommandService;
import com.kiwipay.onboarding.client.infrastructure.persistence.jpa.repositories.ClientRepository;
import com.kiwipay.onboarding.client.infrastructure.persistence.jpa.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class PatientCommandServiceImpl implements PatientCommandService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public PatientResponse createPatient(Long clientId, PatientCreateRequest request) {
        // Verificar que el cliente existe
        if (!clientRepository.existsById(clientId)) {
            throw new RuntimeException("Client not found with id: " + clientId);
        }

        Address address = new Address(
            request.getAddress().getDepartmentId(),
            request.getAddress().getProvinceId(),
            request.getAddress().getDistrictId(),
            request.getAddress().getLine1()
        );

        Patient patient = new Patient(
            clientId,
            DocumentType.valueOf(request.getDocumentType()),
            request.getDocumentNumber(),
            request.getFirstNames(),
            request.getLastNames(),
            Gender.valueOf(request.getGender()),
            request.getPhone(),
            request.getEmail(),
            address
        );

        patient = patientRepository.save(patient);
        return toPatientResponse(patient);
    }

    @Override
    public PatientResponse updatePatient(Long clientId, Long patientId, PatientUpdateRequest request) {
        Patient existingPatient = patientRepository.findByIdAndClientId(patientId, clientId)
            .orElseThrow(() -> new RuntimeException("Patient not found with id: " + patientId + " for client: " + clientId));

        // Actualizar campos
        existingPatient.setFirstNames(request.getFirstNames());
        existingPatient.setLastNames(request.getLastNames());
        existingPatient.setGender(Gender.valueOf(request.getGender()));
        existingPatient.setPhone(request.getPhone());
        existingPatient.setEmail(request.getEmail());
        existingPatient.setUpdatedAt(OffsetDateTime.now());

        if (request.getAddress() != null) {
            Address updatedAddress = new Address(
                request.getAddress().getDepartmentId(),
                request.getAddress().getProvinceId(),
                request.getAddress().getDistrictId(),
                request.getAddress().getLine1()
            );
            existingPatient.setAddress(updatedAddress);
        }

        existingPatient = patientRepository.save(existingPatient);
        return toPatientResponse(existingPatient);
    }

    @Override
    public void deletePatient(Long clientId, Long patientId) {
        if (!patientRepository.existsByIdAndClientId(patientId, clientId)) {
            throw new RuntimeException("Patient not found with id: " + patientId + " for client: " + clientId);
        }
        patientRepository.deleteByIdAndClientId(patientId, clientId);
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
}