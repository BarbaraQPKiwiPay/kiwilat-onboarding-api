package com.kiwipay.onboarding.integration.application.internal.dto;

import com.kiwipay.onboarding.integration.domain.model.aggregates.Lead;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO para respuesta resumida de lead
 * Usado en listados y búsquedas
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeadSummaryResponse {

    private Long id;
    private Long sglLeadId;
    private String documentNumber;
    private String fullName;
    private String email;
    private String cellPhone;
    private String procedureInterest;
    private String clinicSiteName;
    private BigDecimal monthlyIncome;
    private Integer quotationsCount;
    private String bestQuotationStatus;
    private LocalDateTime registrationDate;
    
    // Factory method para crear desde entidad Lead
    public static LeadSummaryResponse from(Lead lead) {
        LeadSummaryResponse response = new LeadSummaryResponse();
        
        response.setId(lead.getId());
        response.setSglLeadId(lead.getIdLead());
        response.setDocumentNumber(lead.getNroDocumento());
        response.setFullName(lead.getNombres());
        response.setEmail(lead.getEmail());
        response.setCellPhone(lead.getCelular());
        response.setProcedureInterest(lead.getInteresProcedimiento());
        response.setClinicSiteName(lead.getSedeLead());
        response.setMonthlyIncome(lead.getIngreso());
        response.setRegistrationDate(lead.getFechaRegistro());
        
        if (lead.getQuotations() != null) {
            response.setQuotationsCount(lead.getQuotations().size());
            
            // Buscar la mejor cotización (estado más favorable)
            response.setBestQuotationStatus(
                lead.getQuotations().stream()
                    .map(q -> q.getResultadoFinalEstado())
                    .filter(status -> status != null)
                    .findFirst()
                    .orElse("PENDING")
            );
        } else {
            response.setQuotationsCount(0);
            response.setBestQuotationStatus("NO_QUOTATIONS");
        }
        
        return response;
    }
}