package com.kiwipay.onboarding.integration.application.internal.dto;

import com.kiwipay.onboarding.integration.domain.model.aggregates.Lead;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO para respuesta detallada de consulta de lead
 * Usado en endpoints GET para el frontend
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeadQueryResponse {

    // Identificadores
    private Long id;
    private Long sglLeadId;
    
    // Datos básicos del cliente (para Pantalla Datos clientes)
    private Integer documentType;
    private String documentNumber;
    private String fullName;
    private String firstName;
    private String paternalLastName;
    private String maternalLastName;
    private String maritalStatus;
    private String email;
    private String cellPhone;
    private String phone;
    private String address;
    
    // Datos clínicos (para Pantalla Datos clínicas)
    private String procedureInterest;
    private Integer clinicSiteId;
    private String clinicSiteName;
    private BigDecimal monthlyIncome;
    
    // Datos adicionales
    private String workSituation;
    private Integer age;
    
    // Metadatos
    private LocalDateTime registrationDate;
    private LocalDateTime modificationDate;
    
    // Cotizaciones
    private List<QuotationQueryResponse> quotations;
    
    // Campos enriquecidos desde catálogos (para el frontend)
    private String documentTypeName;        // Nombre del tipo de documento
    private String medicalCategoryName;     // Nombre de la categoría médica
    private String clinicName;              // Nombre de la clínica
    private String seatName;                // Nombre de la sede
    
    // Factory method para crear desde entidad Lead
    public static LeadQueryResponse from(Lead lead) {
        LeadQueryResponse response = new LeadQueryResponse();
        
        // Mapear identificadores
        response.setId(lead.getId());
        response.setSglLeadId(lead.getIdLead());
        
        // Mapear datos básicos
        response.setDocumentType(lead.getTddTipoDocumento());
        response.setDocumentNumber(lead.getNroDocumento());
        response.setFullName(lead.getNombres());
        response.setFirstName(lead.getNombres());
        response.setPaternalLastName(lead.getApellidoPaterno());
        response.setMaternalLastName(lead.getApellidoMaterno());
        response.setMaritalStatus(lead.getEstadoCivil());
        response.setEmail(lead.getEmail());
        response.setCellPhone(lead.getCelular());
        response.setPhone(lead.getTelefono());
        response.setAddress(lead.getDireccion());
        
        // Mapear datos clínicos
        response.setProcedureInterest(lead.getInteresProcedimiento());
        response.setClinicSiteId(lead.getTddSede());
        response.setClinicSiteName(lead.getSedeLead());
        response.setMonthlyIncome(lead.getIngreso());
        
        // Mapear datos adicionales
        response.setWorkSituation(lead.getLaboralSituation());
        response.setAge(lead.getEdad());
        
        // Mapear metadatos
        response.setRegistrationDate(lead.getFechaRegistro());
        response.setModificationDate(lead.getFechaModificacion());
        
        // Mapear cotizaciones
        if (lead.getQuotations() != null) {
            response.setQuotations(
                lead.getQuotations().stream()
                    .map(QuotationQueryResponse::from)
                    .collect(Collectors.toList())
            );
        }
        
        return response;
    }
}