package com.kiwipay.onboarding.integration.interfaces.rest;

import com.kiwipay.onboarding.integration.application.internal.dto.LeadQueryResponse;
import com.kiwipay.onboarding.integration.application.internal.dto.LeadSummaryResponse;
import com.kiwipay.onboarding.integration.domain.services.IntegrationQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Controller REST para consultar leads integrados
 * Endpoints para el frontend de Onboarding
 */
@RestController
@RequestMapping("/api/v1/leads")
@Tag(name = "Leads Query", description = "Endpoints para consultar leads integrados desde SGL")
public class LeadQueryController {

    private final IntegrationQueryService integrationQueryService;

    public LeadQueryController(IntegrationQueryService integrationQueryService) {
        this.integrationQueryService = integrationQueryService;
    }

    @GetMapping("/{leadId}")
    @Operation(
        summary = "Obtener lead por ID",
        description = "Obtiene los datos completos de un lead específico para las pantallas del frontend"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lead encontrado",
            content = @Content(schema = @Schema(implementation = LeadQueryResponse.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Lead no encontrado"
        )
    })
    public ResponseEntity<LeadQueryResponse> getLeadById(
            @Parameter(description = "ID interno del lead", required = true)
            @PathVariable Long leadId) {
        
        Optional<LeadQueryResponse> lead = integrationQueryService.findLeadById(leadId);
        
        return lead.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/document/{documentNumber}")
    @Operation(
        summary = "Obtener lead por número de documento",
        description = "Busca un lead por su número de documento para autocompletar datos en formularios"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lead encontrado",
            content = @Content(schema = @Schema(implementation = LeadQueryResponse.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Lead no encontrado"
        )
    })
    public ResponseEntity<LeadQueryResponse> getLeadByDocument(
            @Parameter(description = "Número de documento del lead", required = true)
            @PathVariable String documentNumber) {
        
        Optional<LeadQueryResponse> lead = integrationQueryService.findLeadByDocumentNumber(documentNumber);
        
        return lead.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/sgl/{sglLeadId}")
    @Operation(
        summary = "Obtener lead por ID de SGL",
        description = "Busca un lead por su ID original en el sistema SGL"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lead encontrado",
            content = @Content(schema = @Schema(implementation = LeadQueryResponse.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Lead no encontrado"
        )
    })
    public ResponseEntity<LeadQueryResponse> getLeadBySglId(
            @Parameter(description = "ID del lead en SGL", required = true)
            @PathVariable Long sglLeadId) {
        
        Optional<LeadQueryResponse> lead = integrationQueryService.findLeadBySglId(sglLeadId);
        
        return lead.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(
        summary = "Listar leads con filtros",
        description = "Lista leads aplicando filtros opcionales para pantallas de gestión"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de leads",
            content = @Content(schema = @Schema(implementation = LeadSummaryResponse.class))
        )
    })
    public ResponseEntity<List<LeadSummaryResponse>> getLeadsWithFilters(
            @Parameter(description = "Filtro por interés de procedimiento")
            @RequestParam(required = false) String procedureInterest,
            
            @Parameter(description = "Filtro por sede clínica")
            @RequestParam(required = false) Integer clinicSite,
            
            @Parameter(description = "Fecha desde (YYYY-MM-DDTHH:mm:ss)")
            @RequestParam(required = false) 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateFrom,
            
            @Parameter(description = "Fecha hasta (YYYY-MM-DDTHH:mm:ss)")
            @RequestParam(required = false) 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTo,
            
            @Parameter(description = "Número de página (base 0)")
            @RequestParam(defaultValue = "0") int page,
            
            @Parameter(description = "Tamaño de página")
            @RequestParam(defaultValue = "20") int size) {
        
        List<LeadSummaryResponse> leads = integrationQueryService.findLeadsWithFilters(
            procedureInterest, clinicSite, dateFrom, dateTo, page, size
        );
        
        return ResponseEntity.ok(leads);
    }

    @GetMapping("/active-quotations")
    @Operation(
        summary = "Listar leads con cotizaciones activas",
        description = "Obtiene leads que tienen al menos una cotización activa para seguimiento"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Lista de leads con cotizaciones activas"
    )
    public ResponseEntity<List<LeadSummaryResponse>> getLeadsWithActiveQuotations() {
        List<LeadSummaryResponse> leads = integrationQueryService.findLeadsWithActiveQuotations();
        return ResponseEntity.ok(leads);
    }

    @GetMapping("/quotation-status/{status}")
    @Operation(
        summary = "Listar leads por estado de cotización",
        description = "Filtra leads por el estado de sus cotizaciones (PRE APROBADO, RECHAZADO, etc.)"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Lista de leads con el estado de cotización especificado"
    )
    public ResponseEntity<List<LeadSummaryResponse>> getLeadsByQuotationStatus(
            @Parameter(description = "Estado de la cotización", required = true)
            @PathVariable String status) {
        
        List<LeadSummaryResponse> leads = integrationQueryService.findLeadsByQuotationStatus(status);
        return ResponseEntity.ok(leads);
    }
}