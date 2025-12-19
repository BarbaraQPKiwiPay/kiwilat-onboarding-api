package com.kiwipay.onboarding.client.domain.services;

import com.kiwipay.onboarding.client.domain.model.aggregates.Client;
import com.kiwipay.onboarding.client.domain.model.valueobjects.ClientStatus;

/**
 * Servicio de dominio para gestión de estados del cliente
 * Centraliza la lógica de transiciones y validaciones de estado
 */
public interface ClientStateService {
    
    /**
     * Cambia el estado del cliente a DOCUMENTOS_COMPLETADOS
     * @param client Cliente a actualizar
     * @param reason Razón del cambio
     */
    void markDocumentosCompletados(Client client, String reason);
    
    /**
     * Cambia el estado del cliente a APROBADO_POR_ADV
     * @param client Cliente a actualizar
     * @param reason Razón del cambio
     */
    void aprobarPorAdv(Client client, String reason);
    
    /**
     * Cambia el estado del cliente a OBSERVADO_POR_ADV
     * @param client Cliente a actualizar
     * @param reason Razón del cambio
     */
    void observarPorAdv(Client client, String reason);
    
    /**
     * Cambia el estado del cliente a APROBADO_POR_RIESGOS
     * @param client Cliente a actualizar
     * @param reason Razón del cambio
     */
    void aprobarPorRiesgos(Client client, String reason);
    
    /**
     * Cambia el estado del cliente a RECHAZO_POR_RIESGOS
     * @param client Cliente a actualizar
     * @param reason Razón del cambio
     */
    void rechazarPorRiesgos(Client client, String reason);
    
    /**
     * Cambia el estado del cliente a OBSERVADO_POR_RIESGOS
     * @param client Cliente a actualizar
     * @param reason Razón del cambio
     */
    void observarPorRiesgos(Client client, String reason);
    
    /**
     * Obtiene las acciones permitidas para el estado actual del cliente
     * @param currentStatus Estado actual
     * @return Array de acciones permitidas
     */
    String[] getAllowedActions(ClientStatus currentStatus);
    
    /**
     * Valida si el cliente permite subida de documentos
     * @param client Cliente a validar
     * @return true si permite subida de documentos
     */
    boolean canUploadDocuments(Client client);
}