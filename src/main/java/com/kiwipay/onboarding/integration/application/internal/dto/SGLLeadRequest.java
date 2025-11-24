package com.kiwipay.onboarding.integration.application.internal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for receiving lead data from SGL system.
 * Uses @JsonIgnoreProperties to handle future field additions/removals gracefully.
 * Fields are mapped using @JsonProperty to match SGL's naming convention.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SGLLeadRequest {

    @JsonProperty("IdLead")
    private Long idLead;

    @JsonProperty("TddTipoDocumento")
    private Integer tddTipoDocumento;

    @JsonProperty("NroDocumento")
    private String nroDocumento;

    @JsonProperty("Nombres")
    private String nombres;

    @JsonProperty("ApellidoPaterno")
    private String apellidoPaterno;

    @JsonProperty("ApellidoMaterno")
    private String apellidoMaterno;

    @JsonProperty("Telefono")
    private String telefono;

    @JsonProperty("Email")
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
    private String ingreso;

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
    private List<SGLCotizacionDetalle> cotizacionDetalle;
}