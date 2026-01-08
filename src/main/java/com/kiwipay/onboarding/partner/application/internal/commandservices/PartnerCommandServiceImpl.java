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
 * Implementation of PartnerCommandService
 * Handles create, update, and delete operations with business validation
 */
@Service
@RequiredArgsConstructor
public class PartnerCommandServiceImpl implements PartnerCommandService {

    private final PartnerRepository partnerRepository;
    private final PartnerMapper partnerMapper;

    // Repositories for FK validation
    private final LoanRepository loanRepository;
    private final ClientRepository clientRepository;
    private final GuarantorRepository guarantorRepository;
    private final PatientRepository patientRepository;

    @Override
    @Transactional
    public PartnerResponse createPartner(CreatePartnerRequest request) {
        // Validate loan exists
        if (!loanRepository.existsById(request.getLoanId())) {
            throw new PartnerBusinessException("Loan with ID " + request.getLoanId() + " does not exist");
        }

        // Validate FK based on partner type and check uniqueness
        validateOwnerAndUniqueness(request);

        // Check document number uniqueness
        partnerRepository.findByDocumentNumber(request.getDocumentNumber())
                .ifPresent(existing -> {
                    throw new PartnerBusinessException("Partner with document number " +
                            request.getDocumentNumber() + " already exists");
                });

        // Create and save partner
        Partner partner = partnerMapper.toEntity(request);
        Partner savedPartner = partnerRepository.save(partner);

        return partnerMapper.toResponse(savedPartner);
    }

    @Override
    @Transactional
    public PartnerResponse updatePartner(Long id, UpdatePartnerRequest request) {
        Partner partner = partnerRepository.findById(id)
                .orElseThrow(() -> new PartnerBusinessException("Partner with ID " + id + " not found"));

        // Check document number uniqueness (excluding current partner)
        partnerRepository.findByDocumentNumber(request.getDocumentNumber())
                .ifPresent(existing -> {
                    if (!existing.getId().equals(id)) {
                        throw new PartnerBusinessException("Partner with document number " +
                                request.getDocumentNumber() + " already exists");
                    }
                });

        // Update entity
        partnerMapper.updateEntity(partner, request);

        // Save and return
        Partner updatedPartner = partnerRepository.save(partner);
        return partnerMapper.toResponse(updatedPartner);
    }

    @Override
    @Transactional
    public void deletePartner(Long id) {
        if (!partnerRepository.existsById(id)) {
            throw new PartnerBusinessException("Partner with ID " + id + " not found");
        }

        partnerRepository.deleteById(id);
    }

    /**
     * Validates that the owner entity exists and has no existing partner
     */
    private void validateOwnerAndUniqueness(CreatePartnerRequest request) {
        PartnerType type = request.getPartnerType();

        switch (type) {
            case CLIENT:
                if (request.getClientId() == null) {
                    throw new PartnerBusinessException("CLIENT partner requires clientId");
                }
                if (!clientRepository.existsById(request.getClientId())) {
                    throw new PartnerBusinessException("Client with ID " + request.getClientId() + " does not exist");
                }
                // Check uniqueness
                partnerRepository.findByClientId(request.getClientId())
                        .ifPresent(existing -> {
                            throw new PartnerBusinessException("Client with ID " + request.getClientId() +
                                    " already has a partner");
                        });
                break;

            case GUARANTOR:
                if (request.getGuarantorId() == null) {
                    throw new PartnerBusinessException("GUARANTOR partner requires guarantorId");
                }
                if (!guarantorRepository.existsById(request.getGuarantorId())) {
                    throw new PartnerBusinessException("Guarantor with ID " + request.getGuarantorId() +
                            " does not exist");
                }
                // Check uniqueness
                partnerRepository.findByGuarantorId(request.getGuarantorId())
                        .ifPresent(existing -> {
                            throw new PartnerBusinessException("Guarantor with ID " + request.getGuarantorId() +
                                    " already has a partner");
                        });
                break;

            case PATIENT:
                if (request.getPatientId() == null) {
                    throw new PartnerBusinessException("PATIENT partner requires patientId");
                }
                if (!patientRepository.existsById(request.getPatientId())) {
                    throw new PartnerBusinessException("Patient with ID " + request.getPatientId() + " does not exist");
                }
                // Check uniqueness
                partnerRepository.findByPatientId(request.getPatientId())
                        .ifPresent(existing -> {
                            throw new PartnerBusinessException("Patient with ID " + request.getPatientId() +
                                    " already has a partner");
                        });
                break;

            default:
                throw new PartnerBusinessException("Unknown partner type: " + type);
        }
    }
}
