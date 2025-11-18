package com.kiwipay.onboarding.integration.domain.services;

import com.kiwipay.onboarding.integration.application.internal.dto.LeadIntegrationRequest;
import com.kiwipay.onboarding.integration.application.internal.dto.LeadIntegrationResponse;

/**
 * Servicio de dominio para comandos de integración con sistemas externos
 */
public interface IntegrationCommandService {

    /**
     * Crea un nuevo lead a partir de datos de integración
     * @param request Datos del lead desde SGL
     * @return Respuesta con el lead creado
     */
    LeadIntegrationResponse createLead(LeadIntegrationRequest request);

    /**
     * Actualiza un lead existente con nuevos datos de integración
     * @param idLead ID original del lead en SGL
     * @param request Datos actualizados del lead
     * @return Respuesta con el lead actualizado
     */
    LeadIntegrationResponse updateLead(Long idLead, LeadIntegrationRequest request);

    /**
     * Crea o actualiza un lead (upsert) basado en documento
     * @param request Datos del lead desde SGL
     * @return Respuesta con el lead creado o actualizado
     */
    LeadIntegrationResponse upsertLead(LeadIntegrationRequest request);
}