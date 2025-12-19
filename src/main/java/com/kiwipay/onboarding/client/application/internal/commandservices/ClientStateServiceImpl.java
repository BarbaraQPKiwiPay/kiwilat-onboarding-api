package com.kiwipay.onboarding.client.application.internal.commandservices;

import com.kiwipay.onboarding.client.domain.model.aggregates.Client;
import com.kiwipay.onboarding.client.domain.model.valueobjects.ClientStatus;
import com.kiwipay.onboarding.client.domain.services.ClientStateService;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio de estados del cliente
 * Centraliza toda la lógica de transiciones de estado
 */
@Service
public class ClientStateServiceImpl implements ClientStateService {
    
    @Override
    public void markDocumentosCompletados(Client client, String reason) {
        client.changeStatus(ClientStatus.DOCUMENTOS_COMPLETADOS, reason);
    }
    
    @Override
    public void aprobarPorAdv(Client client, String reason) {
        client.changeStatus(ClientStatus.APROBADO_POR_ADV, reason);
    }
    
    @Override
    public void observarPorAdv(Client client, String reason) {
        client.changeStatus(ClientStatus.OBSERVADO_POR_ADV, reason);
    }
    
    @Override
    public void aprobarPorRiesgos(Client client, String reason) {
        client.changeStatus(ClientStatus.APROBADO_POR_RIESGOS, reason);
    }
    
    @Override
    public void rechazarPorRiesgos(Client client, String reason) {
        client.changeStatus(ClientStatus.RECHAZO_POR_RIESGOS, reason);
    }
    
    @Override
    public void observarPorRiesgos(Client client, String reason) {
        client.changeStatus(ClientStatus.OBSERVADO_POR_RIESGOS, reason);
    }
    
    @Override
    public String[] getAllowedActions(ClientStatus currentStatus) {
        switch (currentStatus) {
            case MANUAL:
                return new String[]{"DOCUMENTOS_COMPLETADOS"};
            case DOCUMENTOS_COMPLETADOS:
                return new String[]{"APROBAR_POR_ADV", "OBSERVAR_POR_ADV"};
            case APROBADO_POR_ADV:
                return new String[]{"APROBAR_POR_RIESGOS", "RECHAZAR_POR_RIESGOS", "OBSERVAR_POR_RIESGOS"};
            case OBSERVADO_POR_ADV:
                return new String[]{"DOCUMENTOS_COMPLETADOS"};
            case OBSERVADO_POR_RIESGOS:
                // Corregido: permitir volver a APROBADO_POR_ADV, OBSERVADO_POR_ADV y DOCUMENTOS_COMPLETADOS
                return new String[]{"APROBADO_POR_ADV", "OBSERVADO_POR_ADV", "DOCUMENTOS_COMPLETADOS"};
            // Estados finales
            case APROBADO_POR_RIESGOS:
            case RECHAZO_POR_RIESGOS:
            case CREDITO_PRE_APROBADO:
            case CREDITO_RECHAZADO:
            default:
                return new String[]{};
        }
    }
    
    @Override
    public boolean canUploadDocuments(Client client) {
        return client.allowsDocumentUpload();
    }
}