package com.kiwipay.onboarding.integration.interfaces.rest;

import com.kiwipay.onboarding.integration.application.internal.dto.SGLCotizacionDetalle;
import com.kiwipay.onboarding.integration.application.internal.dto.SGLLeadRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Testing controller to simulate SGL system behavior.
 * Provides mock data for testing the integration endpoints.
 */
@RestController
@RequestMapping("/api/v1/test/sgl")
@Tag(name = "SGL Testing", 
     description = "Testing endpoints to simulate SGL system data")
public class SGLTestController {

    @GetMapping("/mock-lead")
    @Operation(
        summary = "Generate mock SGL lead data",
        description = "Returns mock SGL lead data for testing integration endpoint"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Mock data generated successfully",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = SGLLeadRequest.class)
        )
    )
    public ResponseEntity<SGLLeadRequest> getMockLeadData() {
        SGLLeadRequest mockLead = createMockSGLLead();
        return ResponseEntity.ok(mockLead);
    }

    @GetMapping("/mock-lead/{scenario}")
    @Operation(
        summary = "Generate mock SGL lead data by scenario",
        description = "Returns different mock scenarios: basic, with-spouse, with-clinical, complete"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Mock data generated successfully"
    )
    public ResponseEntity<SGLLeadRequest> getMockLeadDataByScenario(
            @PathVariable String scenario) {
        
        SGLLeadRequest mockLead = switch (scenario.toLowerCase()) {
            case "basic" -> createBasicMockLead();
            case "with-spouse" -> createMockLeadWithSpouse();
            case "with-clinical" -> createMockLeadWithClinicalData();
            case "complete" -> createCompleteMockLead();
            default -> createMockSGLLead();
        };
        
        return ResponseEntity.ok(mockLead);
    }

    private SGLLeadRequest createMockSGLLead() {
        SGLLeadRequest lead = new SGLLeadRequest();
        
        // Basic identification
        lead.setIdLead(1063934L);
        lead.setTddTipoDocumento(1); // DNI
        lead.setNroDocumento("70734801");
        lead.setNombres("CARMEN CLARISSE CELESTE");
        lead.setApellidoPaterno("COLCHADO");
        lead.setApellidoMaterno("CRUZ");
        
        // Contact information
        lead.setTelefono("978617015");
        lead.setCelular("978617015");
        lead.setEmail("carmen.colchado@test.com");
        
        // Personal information
        lead.setEstadoCivil("SOLTERO");
        lead.setEdad("23");
        lead.setLaboralSituation("DEPENDIENTE");
        lead.setIngreso("2500");
        
        // Address
        lead.setDireccion("Av. Lima 123, Lima");
        
        // Medical information
        lead.setInteresProcedimiento("Dental");
        
        // Timestamps
        lead.setFechaRegistro(LocalDateTime.now().minusDays(1));
        lead.setFechaModificacion(LocalDateTime.now());
        
        // System fields
        lead.setIdUsuario("KIWIAPI");
        lead.setMigrationOriginId(1);
        
        // Quotation details
        SGLCotizacionDetalle cotizacion = new SGLCotizacionDetalle();
        cotizacion.setIdLeadCotizacionDetalle(13L);
        cotizacion.setTddTipoDocumento(1);
        cotizacion.setNroDocumento("70734801");
        cotizacion.setPlazo(18);
        cotizacion.setLaboralSituation("DEPENDIENTE");
        cotizacion.setGrupo("GRUPO 6");
        cotizacion.setSegmento("4");
        cotizacion.setCem(new BigDecimal("50.00"));
        cotizacion.setMaf(new BigDecimal("13041.78"));
        cotizacion.setIngreso(new BigDecimal("2500.00"));
        cotizacion.setCuotaKiwi(new BigDecimal("1208.9996"));
        cotizacion.setExperianResultadoEstado("RECHAZADO");
        cotizacion.setExperianRate(new BigDecimal("0.74"));
        cotizacion.setNewRate(new BigDecimal("60.60"));
        cotizacion.setRateDifferential(new BigDecimal("0.00"));
        cotizacion.setExperianResultadoTexto("Capacidad de endeudamiento mayor a 50%;Prestamo > 5000 Dolares, Cliente Normal");
        cotizacion.setResultadoFinalEstado("PRE APROBADO");
        cotizacion.setClasificacion("NORMAL");
        cotizacion.setCuotaActual(new BigDecimal("41.00"));
        cotizacion.setCuotaActualKiwi(new BigDecimal("1249.9996"));
        cotizacion.setEsCampania(false);
        cotizacion.setIdLead(1063934L);
        cotizacion.setIsActive(true);
        
        lead.setCotizacionDetalle(List.of(cotizacion));
        
        return lead;
    }

    private SGLLeadRequest createBasicMockLead() {
        SGLLeadRequest lead = new SGLLeadRequest();
        lead.setIdLead(2000001L);
        lead.setTddTipoDocumento(1);
        lead.setNroDocumento("12345678");
        lead.setNombres("MARIA ELENA");
        lead.setApellidoPaterno("GARCIA");
        lead.setApellidoMaterno("LOPEZ");
        lead.setTelefono("987654321");
        lead.setEmail("maria.garcia@email.com");
        lead.setFechaRegistro(LocalDateTime.now());
        return lead;
    }

    private SGLLeadRequest createMockLeadWithSpouse() {
        SGLLeadRequest lead = createBasicMockLead();
        lead.setIdLead(2000002L);
        lead.setEstadoCivil("CASADO");
        lead.setConyugeNombres("CARLOS ANTONIO PEREZ RUIZ");
        return lead;
    }

    private SGLLeadRequest createMockLeadWithClinicalData() {
        SGLLeadRequest lead = createBasicMockLead();
        lead.setIdLead(2000003L);
        lead.setTddSede(37);
        lead.setTddEspecialidad(5);
        lead.setInteresProcedimiento("Ortodoncia");
        return lead;
    }

    private SGLLeadRequest createCompleteMockLead() {
        SGLLeadRequest lead = createMockSGLLead();
        lead.setIdLead(2000004L);
        lead.setEstadoCivil("CASADO");
        lead.setConyugeNombres("LUIS MIGUEL TORRES VARGAS");
        lead.setTddSede(37);
        lead.setTddEspecialidad(5);
        return lead;
    }
}