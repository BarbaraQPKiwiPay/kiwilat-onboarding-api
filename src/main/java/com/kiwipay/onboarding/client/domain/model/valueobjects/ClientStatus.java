package com.kiwipay.onboarding.client.domain.model.valueobjects;

/**
 * Estados del flujo de evaluación de crédito
 * Controla las transiciones válidas en el proceso de onboarding
 */
public enum ClientStatus {
    
    // Estado inicial de todos los registros
    MANUAL,
    
    // Estados automáticos (por eventos del sistema)
    CREDITO_PRE_APROBADO,
    CREDITO_RECHAZADO,
    
    // Estados manuales (por acciones de usuario)
    DOCUMENTOS_COMPLETADOS,
    APROBADO_POR_ADV,
    OBSERVADO_POR_ADV,
    APROBADO_POR_RIESGOS,
    RECHAZO_POR_RIESGOS,
    OBSERVADO_POR_RIESGOS;
    
    /**
     * Obtiene los estados permitidos desde el estado actual
     */
    public ClientStatus[] getAllowedTransitions() {
        switch (this) {
            case MANUAL:
                return new ClientStatus[]{DOCUMENTOS_COMPLETADOS};
                
            case DOCUMENTOS_COMPLETADOS:
                return new ClientStatus[]{APROBADO_POR_ADV, OBSERVADO_POR_ADV};
                
            case APROBADO_POR_ADV:
                return new ClientStatus[]{APROBADO_POR_RIESGOS, RECHAZO_POR_RIESGOS, OBSERVADO_POR_RIESGOS};
                
            case OBSERVADO_POR_ADV:
                return new ClientStatus[]{DOCUMENTOS_COMPLETADOS};
                
            case OBSERVADO_POR_RIESGOS:
                // Permitir volver a APROBADO_POR_ADV, OBSERVADO_POR_ADV, DOCUMENTOS_COMPLETADOS
                return new ClientStatus[]{APROBADO_POR_ADV, OBSERVADO_POR_ADV, DOCUMENTOS_COMPLETADOS};
                
            // Estados finales - no permiten transiciones
            case APROBADO_POR_RIESGOS:
            case RECHAZO_POR_RIESGOS:
            case CREDITO_PRE_APROBADO:
            case CREDITO_RECHAZADO:
            default:
                return new ClientStatus[]{};
        }
    }
    
    /**
     * Verifica si la transición al nuevo estado es válida
     */
    public boolean canTransitionTo(ClientStatus newStatus) {
        ClientStatus[] allowed = getAllowedTransitions();
        for (ClientStatus status : allowed) {
            if (status == newStatus) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Verifica si es un estado final (no permite más cambios)
     */
    public boolean isFinalState() {
        return this == APROBADO_POR_RIESGOS || 
               this == RECHAZO_POR_RIESGOS ||
               this == CREDITO_PRE_APROBADO ||
               this == CREDITO_RECHAZADO;
    }
    
    /**
     * Verifica si permite subida de documentos
     */
    public boolean allowsDocumentUpload() {
        return this == MANUAL || this == OBSERVADO_POR_ADV || this == OBSERVADO_POR_RIESGOS;
    }
}