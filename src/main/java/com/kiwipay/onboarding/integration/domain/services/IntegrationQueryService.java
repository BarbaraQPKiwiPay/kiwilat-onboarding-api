package com.kiwipay.onboarding.integration.domain.services;

import com.kiwipay.onboarding.integration.application.internal.dto.LeadQueryResponse;
import com.kiwipay.onboarding.integration.application.internal.dto.LeadSummaryResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Servicio de dominio para consultas de leads integrados
 */
public interface IntegrationQueryService {

    /**
     * Obtiene un lead por su ID interno
     * @param leadId ID interno del lead
     * @return Optional con el lead si existe
     */
    Optional<LeadQueryResponse> findLeadById(Long leadId);

    /**
     * Obtiene un lead por su número de documento
     * @param documentNumber Número de documento
     * @return Optional con el lead si existe
     */
    Optional<LeadQueryResponse> findLeadByDocumentNumber(String documentNumber);

    /**
     * Obtiene un lead por su ID original de SGL
     * @param sglLeadId ID original en SGL
     * @return Optional con el lead si existe
     */
    Optional<LeadQueryResponse> findLeadBySglId(Long sglLeadId);

    /**
     * Lista todos los leads con filtros opcionales
     * @param procedureInterest Filtro por interés de procedimiento
     * @param clinicSite Filtro por sede clínica
     * @param dateFrom Filtro por fecha desde
     * @param dateTo Filtro por fecha hasta
     * @param page Número de página
     * @param size Tamaño de página
     * @return Lista paginada de leads
     */
    List<LeadSummaryResponse> findLeadsWithFilters(
        String procedureInterest,
        Integer clinicSite, 
        LocalDateTime dateFrom,
        LocalDateTime dateTo,
        int page,
        int size
    );

    /**
     * Lista leads con cotizaciones activas
     * @return Lista de leads con al menos una cotización activa
     */
    List<LeadSummaryResponse> findLeadsWithActiveQuotations();

    /**
     * Lista leads por estado de cotización
     * @param quotationStatus Estado de la cotización
     * @return Lista de leads con cotizaciones en ese estado
     */
    List<LeadSummaryResponse> findLeadsByQuotationStatus(String quotationStatus);
}