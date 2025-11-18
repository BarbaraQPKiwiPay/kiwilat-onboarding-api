package com.kiwipay.onboarding.integration.application.internal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LeadIntegrationRequest {
    
    @JsonProperty("IdLead")
    @NotNull(message = "IdLead is required")
    private Long idLead;
    
    @JsonProperty("TddTipoDocumento")
    @NotNull(message = "TddTipoDocumento is required")
    @Min(value = 1, message = "TddTipoDocumento must be between 1 and 4")
    @Max(value = 4, message = "TddTipoDocumento must be between 1 and 4")
    private Integer tddTipoDocumento;
    
    @JsonProperty("NroDocumento")
    @NotBlank(message = "NroDocumento is required")
    private String nroDocumento;
    
    @JsonProperty("Nombres")
    @NotBlank(message = "Nombres is required")
    private String nombres;
    
    @JsonProperty("ApellidoPaterno")
    private String apellidoPaterno;
    
    @JsonProperty("ApellidoMaterno")
    private String apellidoMaterno;
    
    @JsonProperty("Telefono")
    private String telefono;
    
    @JsonProperty("Email")
    @NotBlank(message = "Email is required")
    private String email;
    
    @JsonProperty("TddOrigenLead")
    private Integer tddOrigenLead;
    
    @JsonProperty("TddTipoLead")
    private Integer tddTipoLead;
    
    @JsonProperty("TddTipoProducto")
    private Integer tddTipoProducto;
    
    @JsonProperty("IdMarca")
    private Integer idMarca;
    
    @JsonProperty("DescModelo")
    private String descModelo;
    
    @JsonProperty("IdZona")
    private Integer idZona;
    
    @JsonProperty("IdPuntoVenta")
    private Integer idPuntoVenta;
    
    @JsonProperty("IdSupervisor")
    private Integer idSupervisor;
    
    @JsonProperty("IdVendedorAutoplan")
    private Integer idVendedorAutoplan;
    
    @JsonProperty("VendedorConcesionario")
    private Integer vendedorConcesionario;
    
    @JsonProperty("TddEstadoLead")
    private Integer tddEstadoLead;
    
    @JsonProperty("FechaRespuesta")
    private LocalDateTime fechaRespuesta;
    
    @JsonProperty("Observaciones")
    private String observaciones;
    
    @JsonProperty("FechaRegistro")
    private LocalDateTime fechaRegistro;
    
    @JsonProperty("FechaModificacion")
    private LocalDateTime fechaModificacion;
    
    @JsonProperty("IdUsuario")
    private String idUsuario;
    
    @JsonProperty("Celular")
    private String celular;
    
    @JsonProperty("TddDominioCorreo")
    private Integer tddDominioCorreo;
    
    @JsonProperty("TddTieneAuditoriaSupervisor")
    private Integer tddTieneAuditoriaSupervisor;
    
    @JsonProperty("TddTieneAuditoriaIC")
    private Integer tddTieneAuditoriaIC;
    
    @JsonProperty("AlertaPreventivaEnviada")
    private Boolean alertaPreventivaEnviada;
    
    @JsonProperty("AlertaCorrectivaEnviada")
    private Boolean alertaCorrectivaEnviada;
    
    @JsonProperty("AlertaCriticaEnviada")
    private Boolean alertaCriticaEnviada;
    
    @JsonProperty("TddMotivoDesestimado")
    private Integer tddMotivoDesestimado;
    
    @JsonProperty("TddMotivoSeguimiento")
    private Integer tddMotivoSeguimiento;
    
    @JsonProperty("IdConcesionarioRefiere")
    private Integer idConcesionarioRefiere;
    
    @JsonProperty("IdLandingLead")
    private Integer idLandingLead;
    
    @JsonProperty("TddSubEstadoLead")
    private Integer tddSubEstadoLead;
    
    @JsonProperty("TddTipoActivado")
    private Integer tddTipoActivado;
    
    @JsonProperty("TddSubEstadoAnulado")
    private Integer tddSubEstadoAnulado;
    
    @JsonProperty("Campania")
    private String campania;
    
    @JsonProperty("Tasa")
    private String tasa;
    
    @JsonProperty("TddSede")
    private Integer tddSede;
    
    @JsonProperty("TddEspecialidad")
    private Integer tddEspecialidad;
    
    @JsonProperty("TddSubEstadoPerdida")
    private Integer tddSubEstadoPerdida;
    
    @JsonProperty("MafMax")
    private Integer mafMax;
    
    @JsonProperty("Grupo")
    private Integer grupo;
    
    @JsonProperty("Segmento")
    private Integer segmento;
    
    @JsonProperty("LaboralSituation")
    private String laboralSituation;
    
    @JsonProperty("Edad")
    private String edad;
    
    @JsonProperty("Ingreso")
    private BigDecimal ingreso;
    
    @JsonProperty("InteresProcedimiento")
    private String interesProcedimiento;
    
    @JsonProperty("RecepcionistaLead")
    private String recepcionistaLead;
    
    @JsonProperty("SedeLead")
    private String sedeLead;
    
    @JsonProperty("MigrationOriginId")
    private Integer migrationOriginId;
    
    @JsonProperty("Direccion")
    private String direccion;
    
    @JsonProperty("ConyugeNombres")
    private String conyugeNombres;
    
    @JsonProperty("EstadoCivil")
    private String estadoCivil;
    
    @JsonProperty("CotizacionDetalle")
    @NotEmpty(message = "CotizacionDetalle cannot be empty")
    private List<QuotationDetailRequest> cotizacionDetalle;
}