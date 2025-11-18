package com.kiwipay.onboarding.integration.application.internal.dto;

import com.kiwipay.onboarding.integration.domain.model.entities.LeadQuotation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * DTO para respuesta de cotización en consultas
 * Usado dentro de LeadQueryResponse
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuotationQueryResponse {

    private Long id;
    private Long sglQuotationId;
    
    // Datos del cotizador
    private Integer termInMonths;
    private String workSituation;
    private String group;
    private String segment;
    private BigDecimal cem;
    private BigDecimal maf;
    private BigDecimal monthlyIncome;
    private BigDecimal kiwiQuota;
    
    // Resultados de Experian (para Pantalla Riesgo)
    private String experianResultStatus;
    private BigDecimal experianRate;
    private BigDecimal newRate;
    private BigDecimal rateDifferential;
    private String experianResultText;
    
    // Resultado final
    private String finalResultStatus;
    private String classification;
    private BigDecimal currentQuota;
    private BigDecimal currentKiwiQuota;
    private Boolean isCampaign;
    private Boolean isActive;
    
    // Factory method para crear desde entidad LeadQuotation
    public static QuotationQueryResponse from(LeadQuotation quotation) {
        QuotationQueryResponse response = new QuotationQueryResponse();
        
        response.setId(quotation.getId());
        response.setSglQuotationId(quotation.getIdLeadCotizacionDetalle());
        
        // Mapear datos del cotizador
        response.setTermInMonths(quotation.getPlazo());
        response.setWorkSituation(quotation.getLaboralSituation());
        response.setGroup(quotation.getGrupo());
        response.setSegment(quotation.getSegmento());
        response.setCem(quotation.getCem());
        response.setMaf(quotation.getMaf());
        response.setMonthlyIncome(quotation.getIngreso());
        response.setKiwiQuota(quotation.getCuotaKiwi());
        
        // Mapear resultados de Experian
        response.setExperianResultStatus(quotation.getExperianResultadoEstado());
        response.setExperianRate(quotation.getExperianRate());
        response.setNewRate(quotation.getNewRate());
        response.setRateDifferential(quotation.getRateDifferential());
        response.setExperianResultText(quotation.getExperianResultadoTexto());
        
        // Mapear resultado final
        response.setFinalResultStatus(quotation.getResultadoFinalEstado());
        response.setClassification(quotation.getClasificacion());
        response.setCurrentQuota(quotation.getCuotaActual());
        response.setCurrentKiwiQuota(quotation.getCuotaActualKiwi());
        response.setIsCampaign(quotation.getEsCampania());
        response.setIsActive(quotation.getIsActive());
        
        return response;
    }
}