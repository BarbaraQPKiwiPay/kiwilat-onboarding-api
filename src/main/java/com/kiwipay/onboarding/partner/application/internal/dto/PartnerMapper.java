package com.kiwipay.onboarding.partner.application.internal.dto;

import com.kiwipay.onboarding.partner.domain.model.aggregates.Partner;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between Spouse entity and DTOs
 */
@Component
public class PartnerMapper {

    /**
     * Convert Spouse entity to SpouseResponse DTO
     */
    public PartnerResponse toResponse(Partner spouse) {
        if (spouse == null) {
            return null;
        }

        return PartnerResponse.builder()
                .id(spouse.getId())
                .loanId(spouse.getLoanId())
                .spouseType(spouse.getSpouseType())
                .clientId(spouse.getClientId())
                .guarantorId(spouse.getGuarantorId())
                .patientId(spouse.getPatientId())
                .documentType(spouse.getDocumentType())
                .documentNumber(spouse.getDocumentNumber())
                .firstNames(spouse.getFirstNames())
                .lastNames(spouse.getLastNames())
                .email(spouse.getEmail())
                .phone(spouse.getPhone())
                .createdAt(spouse.getCreatedAt())
                .updatedAt(spouse.getUpdatedAt())
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
                request.getSpouseType(),
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
    public void updateEntity(Partner spouse, UpdatePartnerRequest request) {
        if (spouse == null || request == null) {
            return;
        }

        spouse.setDocumentType(request.getDocumentType());
        spouse.setDocumentNumber(request.getDocumentNumber());
        spouse.setFirstNames(request.getFirstNames());
        spouse.setLastNames(request.getLastNames());
        spouse.setEmail(request.getEmail());
        spouse.setPhone(request.getPhone());
    }
}
