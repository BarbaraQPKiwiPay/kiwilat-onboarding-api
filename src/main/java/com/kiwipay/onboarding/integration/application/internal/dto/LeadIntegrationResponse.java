package com.kiwipay.onboarding.integration.application.internal.dto;

import com.kiwipay.onboarding.integration.domain.model.aggregates.Lead;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeadIntegrationResponse {
    
    private Long id;
    private Long sglLeadId;
    private String documentNumber;
    private String fullName;
    private String email;
    private String status;
    private Integer quotationCount;
    private OffsetDateTime createdAt;
    
    // Factory method para crear desde Lead
    public static LeadIntegrationResponse from(Lead lead) {
        return new LeadIntegrationResponse(
            lead.getId(),
            lead.getSglLeadId(),
            lead.getDocumentNumber(),
            lead.getFullName(),
            lead.getEmail(),
            "CREATED",
            lead.getQuotations() != null ? lead.getQuotations().size() : 0,
            lead.getCreatedAt()
        );
    }
    
    // Factory method para respuesta de éxito
    public static LeadIntegrationResponse success(Lead lead) {
        LeadIntegrationResponse response = from(lead);
        response.setStatus("SUCCESS");
        return response;
    }
    
    // Factory method para respuesta de actualización
    public static LeadIntegrationResponse updated(Lead lead) {
        LeadIntegrationResponse response = from(lead);
        response.setStatus("UPDATED");
        return response;
    }
}