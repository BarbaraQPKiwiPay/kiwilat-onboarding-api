package com.kiwipay.onboarding.integration.domain.model.entities;

import com.kiwipay.onboarding.integration.application.internal.dto.QuotationDetailRequest;
import com.kiwipay.onboarding.integration.domain.model.aggregates.Lead;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "lead_quotations")
public class LeadQuotation {
    
    @Id
    private Long id; // Usamos el IdLeadCotizacionDetalle original de SGL
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lead_id", nullable = false)
    private Lead lead;
    
    // Datos básicos de la cotización
    @Column(name = "tdd_tipo_documento", nullable = false)
    private Integer tddTipoDocumento;
    
    @Column(name = "document_number", nullable = false)
    private String documentNumber;
    
    @Column(name = "term_months", nullable = false)
    private Integer termMonths;
    
    @Column(name = "laboral_situation")
    private String laboralSituation;
    
    private String grupo;
    private String segmento;
    
    // Montos y tasas
    @Column(precision = 10, scale = 2)
    private BigDecimal cem;
    
    @Column(precision = 15, scale = 2)
    private BigDecimal maf;
    
    @Column(name = "monthly_income", precision = 10, scale = 2)
    private BigDecimal monthlyIncome;
    
    @Column(name = "kiwi_quota", precision = 15, scale = 4)
    private BigDecimal kiwiQuota;
    
    // Resultados de Experian (Pantalla Riesgo)
    @Column(name = "experian_result_status")
    private String experianResultStatus;
    
    @Column(name = "experian_rate", precision = 10, scale = 2)
    private BigDecimal experianRate;
    
    @Column(name = "new_rate", precision = 10, scale = 2)
    private BigDecimal newRate;
    
    @Column(name = "rate_differential", precision = 10, scale = 2)
    private BigDecimal rateDifferential;
    
    @Column(name = "experian_result_text", columnDefinition = "TEXT")
    private String experianResultText;
    
    // Resultado final y clasificación
    @Column(name = "final_result_status")
    private String finalResultStatus;
    
    @Column(name = "classification")
    private String classification;
    
    @Column(name = "current_quota", precision = 10, scale = 2)
    private BigDecimal currentQuota;
    
    @Column(name = "current_kiwi_quota", precision = 15, scale = 4)
    private BigDecimal currentKiwiQuota;
    
    @Column(name = "is_campaign")
    private Boolean isCampaign;
    
    @Column(name = "is_active")
    private Boolean isActive;
    
    // Constructor completo
    public LeadQuotation(Long id, Integer tddTipoDocumento, String documentNumber, Integer termMonths,
                        String laboralSituation, String grupo, String segmento, BigDecimal cem,
                        BigDecimal maf, BigDecimal monthlyIncome, BigDecimal kiwiQuota,
                        String experianResultStatus, BigDecimal experianRate, BigDecimal newRate,
                        BigDecimal rateDifferential, String experianResultText, String finalResultStatus,
                        String classification, BigDecimal currentQuota, BigDecimal currentKiwiQuota,
                        Boolean isCampaign, Boolean isActive) {
        this.id = id;
        this.tddTipoDocumento = tddTipoDocumento;
        this.documentNumber = documentNumber;
        this.termMonths = termMonths;
        this.laboralSituation = laboralSituation;
        this.grupo = grupo;
        this.segmento = segmento;
        this.cem = cem;
        this.maf = maf;
        this.monthlyIncome = monthlyIncome;
        this.kiwiQuota = kiwiQuota;
        this.experianResultStatus = experianResultStatus;
        this.experianRate = experianRate;
        this.newRate = newRate;
        this.rateDifferential = rateDifferential;
        this.experianResultText = experianResultText;
        this.finalResultStatus = finalResultStatus;
        this.classification = classification;
        this.currentQuota = currentQuota;
        this.currentKiwiQuota = currentKiwiQuota;
        this.isCampaign = isCampaign;
        this.isActive = isActive;
    }
    
    // Factory method para crear desde QuotationDetailRequest
    public static LeadQuotation fromQuotationDetail(QuotationDetailRequest detail) {
        return new LeadQuotation(
            detail.getIdLeadCotizacionDetalle(),
            detail.getTddTipoDocumento(),
            detail.getNroDocumento(),
            detail.getPlazo(),
            detail.getLaboralSituation(),
            detail.getGrupo(),
            detail.getSegmento(),
            detail.getCem(),
            detail.getMaf(),
            detail.getIngreso(),
            detail.getCuotaKiwi(),
            detail.getExperianResultadoEstado(),
            detail.getExperianRate(),
            detail.getNewRate(),
            detail.getRateDifferential(),
            detail.getExperianResultadoTexto(),
            detail.getResultadoFinalEstado(),
            detail.getClasificacion(),
            detail.getCuotaActual(),
            detail.getCuotaActualKiwi(),
            detail.getEsCampania(),
            detail.getIsActive()
        );
    }
    
    // Método de conveniencia para obtener el estado final legible
    public String getFriendlyFinalStatus() {
        if (finalResultStatus == null) return "Sin estado";
        
        return switch (finalResultStatus.toUpperCase()) {
            case "PRE APROBADO" -> "Pre-aprobado";
            case "APROBADO" -> "Aprobado";
            case "RECHAZADO" -> "Rechazado";
            case "PENDIENTE" -> "Pendiente";
            default -> finalResultStatus;
        };
    }
    
    // Método para verificar si está pre-aprobado
    public boolean isPreApproved() {
        return "PRE APROBADO".equalsIgnoreCase(finalResultStatus);
    }
    
    // Método para verificar si está aprobado
    public boolean isApproved() {
        return "APROBADO".equalsIgnoreCase(finalResultStatus);
    }
    
    // Método para verificar si está rechazado
    public boolean isRejected() {
        return "RECHAZADO".equalsIgnoreCase(finalResultStatus);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeadQuotation that = (LeadQuotation) o;
        return Objects.equals(id, that.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}