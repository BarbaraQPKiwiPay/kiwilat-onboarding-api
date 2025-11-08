package com.kiwipay.onboarding.clinicaldata.application.internal.commandservices;

import com.kiwipay.onboarding.clinicaldata.application.internal.dto.ClinicalDataCreateRequest;
import com.kiwipay.onboarding.clinicaldata.application.internal.dto.ClinicalDataResponse;
import com.kiwipay.onboarding.clinicaldata.domain.model.aggregates.ClinicalData;
import com.kiwipay.onboarding.clinicaldata.domain.model.exceptions.ClinicalDataBusinessException;
import com.kiwipay.onboarding.clinicaldata.domain.services.ClinicalDataCommandService;
import com.kiwipay.onboarding.catalog.infrastructure.persistence.jpa.repositories.ClinicBranchRepository;
import com.kiwipay.onboarding.clinicaldata.infrastructure.persistence.jpa.repositories.ClinicalDataRepository;
import com.kiwipay.onboarding.client.infrastructure.persistence.jpa.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@Transactional
public class ClinicalDataCommandServiceImpl implements ClinicalDataCommandService {

    @Autowired
    private ClinicalDataRepository clinicalDataRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClinicBranchRepository clinicBranchRepository;

    @Override
    public ClinicalDataResponse createClinicalData(Long clientId, ClinicalDataCreateRequest request) {
        // 1. Verificar que el cliente existe
        if (!clientRepository.existsById(clientId)) {
            throw new RuntimeException("Client not found with id: " + clientId);
        }

        // 2. Verificar que no existe ya clinical data para este cliente
        if (clinicalDataRepository.existsByClientId(clientId)) {
            throw ClinicalDataBusinessException.clinicalDataAlreadyExists();
        }



        // 4. Validar que la sede pertenece a la clínica
        if (!clinicBranchRepository.existsByIdAndClinicId(request.getBranchId(), request.getClinicId())) {
            throw ClinicalDataBusinessException.invalidBranchForClinic();
        }

        // 5. Crear los datos clínicos
        ClinicalData clinicalData = new ClinicalData(
            clientId,
            request.getMedicalCategoryId(),
            request.getClinicId(),
            request.getBranchId()
        );

        clinicalData = clinicalDataRepository.save(clinicalData);
        return toClinicalDataResponse(clinicalData);
    }

    @Override
    public ClinicalDataResponse updateClinicalData(Long clientId, ClinicalDataCreateRequest request) {
        ClinicalData existingData = clinicalDataRepository.findByClientId(clientId)
            .orElseThrow(ClinicalDataBusinessException::clinicalDataNotFound);



        // Validar que la sede pertenece a la clínica
        if (!clinicBranchRepository.existsByIdAndClinicId(request.getBranchId(), request.getClinicId())) {
            throw ClinicalDataBusinessException.invalidBranchForClinic();
        }

        // Actualizar campos
    existingData.setMedicalCategoryId(request.getMedicalCategoryId());
    existingData.setClinicId(request.getClinicId());
    existingData.setBranchId(request.getBranchId());
    existingData.setUpdatedAt(OffsetDateTime.now());

        existingData = clinicalDataRepository.save(existingData);
        return toClinicalDataResponse(existingData);
    }

    @Override
    public void deleteClinicalData(Long clientId) {
        if (!clinicalDataRepository.existsByClientId(clientId)) {
            throw ClinicalDataBusinessException.clinicalDataNotFound();
        }
        clinicalDataRepository.deleteByClientId(clientId);
    }

    private ClinicalDataResponse toClinicalDataResponse(ClinicalData clinicalData) {
        ClinicalDataResponse response = new ClinicalDataResponse();
    response.setId("CD-" + clinicalData.getId());
    response.setClientId(clinicalData.getClientId());
    response.setMedicalCategoryId(clinicalData.getMedicalCategoryId());
    response.setClinicId(clinicalData.getClinicId());
    response.setBranchId(clinicalData.getBranchId());
    response.setCreatedAt(clinicalData.getCreatedAt().toString());
    response.setUpdatedAt(clinicalData.getUpdatedAt() != null ? 
                clinicalData.getUpdatedAt().toString() : null);
        return response;
    }
}