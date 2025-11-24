package com.kiwipay.onboarding.integration.application.internal.services;

import com.kiwipay.onboarding.client.application.internal.dto.ClientCreateRequest;
import com.kiwipay.onboarding.client.application.internal.dto.ClientResponse;
import com.kiwipay.onboarding.client.domain.services.ClientCommandService;
import com.kiwipay.onboarding.integration.application.internal.dto.SGLIntegrationResponse;
import com.kiwipay.onboarding.integration.application.internal.dto.SGLLeadRequest;
import com.kiwipay.onboarding.integration.domain.services.SGLIntegrationService;
import com.kiwipay.onboarding.integration.domain.services.SGLMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of SGLIntegrationService.
 * Orchestrates the complete SGL lead processing workflow.
 */
@Service
@Slf4j
@Transactional
public class SGLIntegrationServiceImpl implements SGLIntegrationService {

    @Autowired
    private ClientCommandService clientCommandService;

    @Autowired
    private SGLMappingService sglMappingService;

    @Override
    public SGLIntegrationResponse processLead(SGLLeadRequest sglLead) {
        log.info("Starting SGL lead processing for IdLead: {}", sglLead.getIdLead());
        
        try {
            // 1. Validate incoming data
            sglMappingService.validateSGLLeadData(sglLead);
            
            // 2. Create Client
            ClientResponse client = createClientFromSGL(sglLead);
            log.info("Client created successfully with ID: {} for SGL Lead: {}", 
                    client.getId(), sglLead.getIdLead());
            
            // 3. Create related entities (spouse, clinical data, risk) if needed
            processRelatedEntities(client.getId(), sglLead);
            
            return SGLIntegrationResponse.success(client.getId(), sglLead.getIdLead());
            
        } catch (IllegalArgumentException e) {
            log.error("Validation error processing SGL Lead {}: {}", sglLead.getIdLead(), e.getMessage());
            return SGLIntegrationResponse.error(sglLead.getIdLead(), e.getMessage(), "VALIDATION_ERROR");
        } catch (Exception e) {
            log.error("Unexpected error processing SGL Lead {}: {}", sglLead.getIdLead(), e.getMessage(), e);
            return SGLIntegrationResponse.error(sglLead.getIdLead(), 
                "Internal error occurred while processing lead: " + e.getMessage());
        }
    }

    private ClientResponse createClientFromSGL(SGLLeadRequest sglLead) {
        ClientCreateRequest clientRequest = sglMappingService.mapToClientCreateRequest(sglLead);
        return clientCommandService.createClient(clientRequest);
    }

    private void processRelatedEntities(Long clientId, SGLLeadRequest sglLead) {
        // Process spouse data if available
        processSpouseData(clientId, sglLead);
        
        // Process clinical data if available
        processClinicalData(clientId, sglLead);
        
        // Process quotation/risk data if available
        processQuotationData(clientId, sglLead);
    }

    private void processSpouseData(Long clientId, SGLLeadRequest sglLead) {
        if (sglMappingService.hasSpouseData(sglLead)) {
            log.info("Spouse data detected for client {}, processing...", clientId);
            // TODO: Implement spouse creation when SpouseCommandService is available
            // Object spouseRequest = sglMappingService.mapToSpouseCreateRequest(sglLead);
            // spouseCommandService.createSpouse(clientId, spouseRequest);
        }
    }

    private void processClinicalData(Long clientId, SGLLeadRequest sglLead) {
        if (sglMappingService.hasClinicalData(sglLead)) {
            log.info("Clinical data detected for client {}, processing...", clientId);
            // TODO: Implement clinical data creation when ClinicalDataCommandService is available
            // Object clinicalDataRequest = sglMappingService.mapToClinicalDataCreateRequest(sglLead);
            // clinicalDataCommandService.createClinicalData(clientId, clinicalDataRequest);
        }
    }

    private void processQuotationData(Long clientId, SGLLeadRequest sglLead) {
        if (sglLead.getCotizacionDetalle() != null && !sglLead.getCotizacionDetalle().isEmpty()) {
            log.info("Processing {} quotation records for client {}", 
                    sglLead.getCotizacionDetalle().size(), clientId);
            
            sglLead.getCotizacionDetalle().forEach(cotizacion -> {
                try {
                    // TODO: Implement risk data creation when RiskCommandService is available
                    // Object riskRequest = sglMappingService.mapToRiskCreateRequest(clientId, cotizacion);
                    // riskCommandService.createRisk(clientId, riskRequest);
                    log.debug("Quotation detail processed for client {}: {}", clientId, cotizacion.getIdLeadCotizacionDetalle());
                } catch (Exception e) {
                    log.warn("Error processing quotation detail {} for client {}: {}", 
                            cotizacion.getIdLeadCotizacionDetalle(), clientId, e.getMessage());
                    // Continue processing other quotations even if one fails
                }
            });
        }
    }
}