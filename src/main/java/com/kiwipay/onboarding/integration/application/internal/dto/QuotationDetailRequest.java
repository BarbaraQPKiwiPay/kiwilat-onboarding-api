package com.kiwipay.onboarding.integration.application.internal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class QuotationDetailRequest {
    
    @JsonProperty("IdLeadCotizacionDetalle")
    private Long idLeadCotizacionDetalle;
    
    @JsonProperty("TddTipoDocumento")
    private Integer tddTipoDocumento;
    
    @JsonProperty("NroDocumento")
    private String nroDocumento;
    
    @JsonProperty("Plazo")
    private Integer plazo;
    
    @JsonProperty("LaboralSituation")
    private String laboralSituation;
    
    @JsonProperty("Grupo")
    private String grupo;
    
    @JsonProperty("Segmento")
    private String segmento;
    
    @JsonProperty("Cem")
    private BigDecimal cem;
    
    @JsonProperty("Maf")
    private BigDecimal maf;
    
    @JsonProperty("Ingreso")
    private BigDecimal ingreso;
    
    @JsonProperty("CuotaKiwi")
    private BigDecimal cuotaKiwi;
    
    @JsonProperty("ExperianResultadoEstado")
    private String experianResultadoEstado;
    
    @JsonProperty("ExperianRate")
    private BigDecimal experianRate;
    
    @JsonProperty("NewRate")
    private BigDecimal newRate;
    
    @JsonProperty("RateDifferential")
    private BigDecimal rateDifferential;
    
    @JsonProperty("ExperianResultadoTexto")
    private String experianResultadoTexto;
    
    @JsonProperty("ResultadoFinalEstado")
    private String resultadoFinalEstado;
    
    @JsonProperty("Clasificacion")
    private String clasificacion;
    
    @JsonProperty("CuotaActual")
    private BigDecimal cuotaActual;
    
    @JsonProperty("CuotaActualKiwi")
    private BigDecimal cuotaActualKiwi;
    
    @JsonProperty("EsCampania")
    private Boolean esCampania;
    
    @JsonProperty("IdLead")
    private Long idLead;
    
    @JsonProperty("IsActive")
    private Boolean isActive;
}