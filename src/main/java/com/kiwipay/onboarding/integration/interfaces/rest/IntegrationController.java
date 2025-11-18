package com.kiwipay.onboarding.integration.interfaces.rest;

import com.kiwipay.onboarding.integration.application.internal.dto.LeadIntegrationRequest;
import com.kiwipay.onboarding.integration.application.internal.dto.LeadIntegrationResponse;
import com.kiwipay.onboarding.integration.domain.model.exceptions.IntegrationBusinessException;
import com.kiwipay.onboarding.integration.domain.services.IntegrationCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller REST para manejar integraciones con sistemas externos (SGL)
 */
@RestController
@RequestMapping("/api/v1/integrations")
@Tag(name = "Integration", description = "Endpoints para integración con sistemas externos")
public class IntegrationController {

    private final IntegrationCommandService integrationCommandService;

    public IntegrationController(IntegrationCommandService integrationCommandService) {
        this.integrationCommandService = integrationCommandService;
    }

    @PostMapping("/leads")
    @Operation(
        summary = "Crear lead desde integración SGL",
        description = "Recibe datos de lead desde el sistema SGL y los persiste en la base de datos de Onboarding"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Lead creado exitosamente",
            content = @Content(schema = @Schema(implementation = LeadIntegrationResponse.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos inválidos en la solicitud"
        ),
        @ApiResponse(
            responseCode = "409",
            description = "Lead ya existe con el mismo documento"
        ),
        @ApiResponse(
            responseCode = "422",
            description = "Error de validación de reglas de negocio"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor"
        )
    })
    public ResponseEntity<LeadIntegrationResponse> createLead(
            @Parameter(description = "Datos del lead desde SGL", required = true)
            @Valid @RequestBody LeadIntegrationRequest request) {
        
        try {
            LeadIntegrationResponse response = integrationCommandService.createLead(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IntegrationBusinessException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(
                createErrorResponse(e.getMessage(), e.getErrorCode())
            );
        }
    }

    @PutMapping("/leads/{idLead}")
    @Operation(
        summary = "Actualizar lead existente",
        description = "Actualiza un lead existente con nuevos datos desde SGL"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lead actualizado exitosamente",
            content = @Content(schema = @Schema(implementation = LeadIntegrationResponse.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Lead no encontrado"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos inválidos en la solicitud"
        ),
        @ApiResponse(
            responseCode = "422",
            description = "Error de validación de reglas de negocio"
        )
    })
    public ResponseEntity<LeadIntegrationResponse> updateLead(
            @Parameter(description = "ID del lead en SGL", required = true)
            @PathVariable Long idLead,
            @Parameter(description = "Datos actualizados del lead", required = true)
            @Valid @RequestBody LeadIntegrationRequest request) {
        
        try {
            LeadIntegrationResponse response = integrationCommandService.updateLead(idLead, request);
            return ResponseEntity.ok(response);
        } catch (IntegrationBusinessException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(
                createErrorResponse(e.getMessage(), e.getErrorCode())
            );
        }
    }

    @PostMapping("/leads/upsert")
    @Operation(
        summary = "Crear o actualizar lead (Upsert)",
        description = "Crea un nuevo lead o actualiza uno existente basándose en el número de documento"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lead procesado exitosamente (creado o actualizado)",
            content = @Content(schema = @Schema(implementation = LeadIntegrationResponse.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos inválidos en la solicitud"
        ),
        @ApiResponse(
            responseCode = "422",
            description = "Error de validación de reglas de negocio"
        )
    })
    public ResponseEntity<LeadIntegrationResponse> upsertLead(
            @Parameter(description = "Datos del lead desde SGL", required = true)
            @Valid @RequestBody LeadIntegrationRequest request) {
        
        try {
            LeadIntegrationResponse response = integrationCommandService.upsertLead(request);
            return ResponseEntity.ok(response);
        } catch (IntegrationBusinessException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(
                createErrorResponse(e.getMessage(), e.getErrorCode())
            );
        }
    }

    /**
     * Endpoint de health check para validar conectividad
     */
    @GetMapping("/health")
    @Operation(
        summary = "Health check del servicio de integración",
        description = "Verifica que el servicio de integración esté funcionando correctamente"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Servicio funcionando correctamente"
    )
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Integration service is healthy");
    }

    /**
     * Manejo global de excepciones para este controller
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<LeadIntegrationResponse> handleGeneralException(Exception e) {
        LeadIntegrationResponse errorResponse = createErrorResponse(
            "Internal server error: " + e.getMessage(),
            "INTERNAL_SERVER_ERROR"
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<LeadIntegrationResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        LeadIntegrationResponse errorResponse = createErrorResponse(
            "Invalid argument: " + e.getMessage(),
            "INVALID_ARGUMENT"
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // Método helper para crear respuestas de error
    private LeadIntegrationResponse createErrorResponse(String message, String errorCode) {
        LeadIntegrationResponse errorResponse = new LeadIntegrationResponse();
        errorResponse.setStatus("ERROR");
        // Para errores, podríamos usar campos opcionales o crear un DTO específico de error
        return errorResponse;
    }
}