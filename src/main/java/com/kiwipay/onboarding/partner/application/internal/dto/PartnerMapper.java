package com.kiwipay.onboarding.partner.application.internal.dto;

import com.kiwipay.onboarding.partner.domain.model.aggregates.Partner;
import org.springframework.stereotype.Component;

@Component
public class PartnerMapper {

    public PartnerResponse toResponse(Partner partner) {
        if (partner == null) {
            return null;
        }

        return PartnerResponse.builder()
                .id(partner.getId())
                .loanId(partner.getLoanId())
                .partnerType(partner.getPartnerType())
                .clientId(partner.getClientId())
                .guarantorId(partner.getGuarantorId())
                .patientId(partner.getPatientId())
                .documentType(partner.getDocumentType())
                .documentNumber(partner.getDocumentNumber())
                .firstNames(partner.getFirstNames())
                .lastNames(partner.getLastNames())
                .email(partner.getEmail())
                .phone(partner.getPhone())
                .createdAt(partner.getCreatedAt())
                .updatedAt(partner.getUpdatedAt())
                .build();
    }

    /**
     * Convert CreateSpouseRequest to Spouse entity
     */
    public Partner toEntity(CreatePartnerRequest request) {
        if (request == null) {
            return null;
        }

        return new Partner(
                request.getLoanId(),
                request.getPartnerType(),
                request.getClientId(),
                request.getGuarantorId(),
                request.getPatientId(),
                request.getDocumentType(),
                request.getDocumentNumber(),
                request.getFirstNames(),
                request.getLastNames(),
                request.getEmail(),
                request.getPhone());
    }

    /**
     * Update Spouse entity from UpdateSpouseRequest
     * Only updates personal information, not FKs or spouseType
     */
    public void updateEntity(Partner partner, UpdatePartnerRequest request) {
        if (partner == null || request == null) {
            return;
        }

        partner.setDocumentType(request.getDocumentType());
        partner.setDocumentNumber(request.getDocumentNumber());
        partner.setFirstNames(request.getFirstNames());
        partner.setLastNames(request.getLastNames());
        partner.setEmail(request.getEmail());
        partner.setPhone(request.getPhone());
    }
}
