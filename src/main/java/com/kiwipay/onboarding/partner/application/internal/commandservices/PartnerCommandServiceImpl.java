package com.kiwipay.onboarding.partner.application.internal.commandservices;

import com.kiwipay.onboarding.client.infrastructure.persistence.jpa.repositories.ClientRepository;
import com.kiwipay.onboarding.guarantor.infrastructure.persistence.jpa.GuarantorRepository;
import com.kiwipay.onboarding.loan.infrastructure.persistence.jpa.LoanRepository;
import com.kiwipay.onboarding.patient.infrastructure.persistence.jpa.repositories.PatientRepository;
import com.kiwipay.onboarding.partner.application.internal.dto.CreatePartnerRequest;
import com.kiwipay.onboarding.partner.application.internal.dto.PartnerMapper;
import com.kiwipay.onboarding.partner.application.internal.dto.PartnerResponse;
import com.kiwipay.onboarding.partner.application.internal.dto.UpdatePartnerRequest;
import com.kiwipay.onboarding.partner.domain.model.aggregates.Partner;
import com.kiwipay.onboarding.partner.domain.model.exceptions.PartnerBusinessException;
import com.kiwipay.onboarding.partner.domain.model.valueobjects.PartnerType;
import com.kiwipay.onboarding.partner.domain.services.PartnerCommandService;
import com.kiwipay.onboarding.partner.infrastructure.persistence.jpa.repositories.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of SpouseCommandService
 * Handles create, update, and delete operations with business validation
 */
@Service
@RequiredArgsConstructor
public class PartnerCommandServiceImpl implements PartnerCommandService {

    private final PartnerRepository spouseRepository;
    private final PartnerMapper spouseMapper;

    // Repositories for FK validation
    private final LoanRepository loanRepository;
    private final ClientRepository clientRepository;
    private final GuarantorRepository guarantorRepository;
    private final PatientRepository patientRepository;

    @Override
    @Transactional
    public PartnerResponse createSpouse(CreatePartnerRequest request) {
        // Validate loan exists
        if (!loanRepository.existsById(request.getLoanId())) {
            throw new PartnerBusinessException("Loan with ID " + request.getLoanId() + " does not exist");
        }

        // Validate FK based on spouse type and check uniqueness
        validateOwnerAndUniqueness(request);

        // Check document number uniqueness
        spouseRepository.findByDocumentNumber(request.getDocumentNumber())
                .ifPresent(existing -> {
                    throw new PartnerBusinessException("Spouse with document number " +
                            request.getDocumentNumber() + " already exists");
                });

        // Create and save spouse
        Partner spouse = spouseMapper.toEntity(request);
        Partner savedSpouse = spouseRepository.save(spouse);

        return spouseMapper.toResponse(savedSpouse);
    }

    @Override
    @Transactional
    public PartnerResponse updateSpouse(Long id, UpdatePartnerRequest request) {
        Partner spouse = spouseRepository.findById(id)
                .orElseThrow(() -> new PartnerBusinessException("Spouse with ID " + id + " not found"));

        // Check document number uniqueness (excluding current spouse)
        spouseRepository.findByDocumentNumber(request.getDocumentNumber())
                .ifPresent(existing -> {
                    if (!existing.getId().equals(id)) {
                        throw new PartnerBusinessException("Spouse with document number " +
                                request.getDocumentNumber() + " already exists");
                    }
                });

        // Update entity
        spouseMapper.updateEntity(spouse, request);

        // Save and return
        Partner updatedSpouse = spouseRepository.save(spouse);
        return spouseMapper.toResponse(updatedSpouse);
    }

    @Override
    @Transactional
    public void deleteSpouse(Long id) {
        if (!spouseRepository.existsById(id)) {
            throw new PartnerBusinessException("Spouse with ID " + id + " not found");
        }

        spouseRepository.deleteById(id);
    }

    /**
     * Validates that the owner entity exists and has no existing spouse
     */
    private void validateOwnerAndUniqueness(CreatePartnerRequest request) {
        PartnerType type = request.getSpouseType();

        switch (type) {
            case CLIENT:
                if (request.getClientId() == null) {
                    throw new PartnerBusinessException("CLIENT spouse requires clientId");
                }
                if (!clientRepository.existsById(request.getClientId())) {
                    throw new PartnerBusinessException("Client with ID " + request.getClientId() + " does not exist");
                }
                // Check uniqueness
                spouseRepository.findByClientId(request.getClientId())
                        .ifPresent(existing -> {
                            throw new PartnerBusinessException("Client with ID " + request.getClientId() +
                                    " already has a spouse");
                        });
                break;

            case GUARANTOR:
                if (request.getGuarantorId() == null) {
                    throw new PartnerBusinessException("GUARANTOR spouse requires guarantorId");
                }
                if (!guarantorRepository.existsById(request.getGuarantorId())) {
                    throw new PartnerBusinessException("Guarantor with ID " + request.getGuarantorId() +
                            " does not exist");
                }
                // Check uniqueness
                spouseRepository.findByGuarantorId(request.getGuarantorId())
                        .ifPresent(existing -> {
                            throw new PartnerBusinessException("Guarantor with ID " + request.getGuarantorId() +
                                    " already has a spouse");
                        });
                break;

            case PATIENT:
                if (request.getPatientId() == null) {
                    throw new PartnerBusinessException("PATIENT spouse requires patientId");
                }
                if (!patientRepository.existsById(request.getPatientId())) {
                    throw new PartnerBusinessException("Patient with ID " + request.getPatientId() + " does not exist");
                }
                // Check uniqueness
                spouseRepository.findByPatientId(request.getPatientId())
                        .ifPresent(existing -> {
                            throw new PartnerBusinessException("Patient with ID " + request.getPatientId() +
                                    " already has a spouse");
                        });
                break;

            default:
                throw new PartnerBusinessException("Unknown spouse type: " + type);
        }
    }
}
