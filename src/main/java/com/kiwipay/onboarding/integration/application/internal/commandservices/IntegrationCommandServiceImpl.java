package com.kiwipay.onboarding.integration.application.internal.commandservices;

import com.kiwipay.onboarding.integration.application.internal.dto.LeadIntegrationRequest;
import com.kiwipay.onboarding.integration.application.internal.dto.LeadIntegrationResponse;
import com.kiwipay.onboarding.integration.application.internal.dto.QuotationDetailRequest;
import com.kiwipay.onboarding.integration.domain.model.aggregates.Lead;
import com.kiwipay.onboarding.integration.domain.model.entities.LeadQuotation;
import com.kiwipay.onboarding.integration.domain.model.exceptions.IntegrationBusinessException;
import com.kiwipay.onboarding.integration.domain.services.IntegrationCommandService;
import com.kiwipay.onboarding.integration.infrastructure.persistence.jpa.LeadRepository;
import com.kiwipay.onboarding.integration.infrastructure.persistence.jpa.LeadQuotationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

/**
 * Implementación del servicio de comandos de integración
 * Maneja la lógica de negocio para la creación y actualización de leads
 */
@Service
public class IntegrationCommandServiceImpl implements IntegrationCommandService {

    private final LeadRepository leadRepository;
    private final LeadQuotationRepository leadQuotationRepository;

    public IntegrationCommandServiceImpl(
            LeadRepository leadRepository,
            LeadQuotationRepository leadQuotationRepository) {
        this.leadRepository = leadRepository;
        this.leadQuotationRepository = leadQuotationRepository;
    }

    @Override
    @Transactional
    public LeadIntegrationResponse createLead(LeadIntegrationRequest request) {
        // Validaciones de negocio
        validateLeadRequest(request);
        
        // Verificar que no exista ya el lead
        if (leadExists(request)) {
            throw IntegrationBusinessException.leadAlreadyExists();
        }

        // Crear entidad Lead desde el request
        Lead lead = mapRequestToLead(request);
        
        // Crear entidades LeadQuotation
        List<LeadQuotation> quotations = mapQuotationsToEntities(request.getCotizacionDetalle(), lead);
        lead.getQuotations().addAll(quotations);

        // Persistir (JPA maneja la cascada automáticamente)
        Lead savedLead = leadRepository.save(lead);

        return mapLeadToResponse(savedLead);
    }

    @Override
    @Transactional
    public LeadIntegrationResponse updateLead(Long idLead, LeadIntegrationRequest request) {
        // Buscar lead existente
        Optional<Lead> existingLead = leadRepository.findByIdLead(idLead);
        if (existingLead.isEmpty()) {
            throw IntegrationBusinessException.leadNotFound();
        }

        // Validar request
        validateLeadRequest(request);

        Lead lead = existingLead.get();
        
        // Actualizar campos del lead
        updateLeadFields(lead, request);
        
        // Actualizar cotizaciones (eliminar existentes y crear nuevas)
        lead.getQuotations().clear();
        List<LeadQuotation> newQuotations = mapQuotationsToEntities(request.getCotizacionDetalle(), lead);
        lead.getQuotations().addAll(newQuotations);

        Lead savedLead = leadRepository.save(lead);
        return mapLeadToResponse(savedLead);
    }

    @Override
    @Transactional
    public LeadIntegrationResponse upsertLead(LeadIntegrationRequest request) {
        // Buscar si existe por documento
        Optional<Lead> existingLead = leadRepository.findByTddTipoDocumentoAndNroDocumento(
            request.getTddTipoDocumento(), 
            request.getNroDocumento()
        );

        if (existingLead.isPresent()) {
            return updateLead(existingLead.get().getIdLead(), request);
        } else {
            return createLead(request);
        }
    }

    // Métodos privados de validación y mapeo

    private void validateLeadRequest(LeadIntegrationRequest request) {
        if (request.getNroDocumento() == null || request.getNroDocumento().trim().isEmpty()) {
            throw IntegrationBusinessException.missingRequiredFields();
        }
        
        if (request.getTddTipoDocumento() == null) {
            throw IntegrationBusinessException.invalidDocumentType();
        }
        
        if (request.getCotizacionDetalle() == null || request.getCotizacionDetalle().isEmpty()) {
            throw IntegrationBusinessException.invalidQuotationData();
        }

        // Validar formato de email si no es "SINCORREO"
        if (request.getEmail() != null && 
            !request.getEmail().equals("SINCORREO") && 
            !isValidEmail(request.getEmail())) {
            throw IntegrationBusinessException.missingRequiredFields();
        }
    }

    private boolean leadExists(LeadIntegrationRequest request) {
        return leadRepository.existsByTddTipoDocumentoAndNroDocumentoAndMigrationOriginId(
            request.getTddTipoDocumento(),
            request.getNroDocumento(),
            request.getMigrationOriginId()
        );
    }

    private Lead mapRequestToLead(LeadIntegrationRequest request) {
        Lead lead = new Lead();
        
        // Campos básicos
        lead.setIdLead(request.getIdLead());
        lead.setTddTipoDocumento(request.getTddTipoDocumento());
        lead.setNroDocumento(request.getNroDocumento());
        lead.setNombres(request.getNombres());
        lead.setApellidoPaterno(request.getApellidoPaterno());
        lead.setApellidoMaterno(request.getApellidoMaterno());
        lead.setEstadoCivil(request.getEstadoCivil());
        
        // Contacto
        lead.setEmail(request.getEmail());
        lead.setCelular(request.getCelular());
        lead.setTelefono(request.getTelefono());
        lead.setDireccion(request.getDireccion());
        
        // Datos clínicos
        lead.setInteresProcedimiento(request.getInteresProcedimiento());
        lead.setTddSede(request.getTddSede());
        lead.setSedeLead(request.getSedeLead());
        lead.setIngreso(request.getIngreso());
        
        // Datos adicionales
        lead.setLaboralSituation(request.getLaboralSituation());
        if (request.getEdad() != null) {
            lead.setEdad(Integer.parseInt(request.getEdad()));
        }
        
        // Metadatos
        lead.setMigrationOriginId(request.getMigrationOriginId());
        lead.setFechaRegistro(request.getFechaRegistro());
        lead.setFechaModificacion(request.getFechaModificacion());
        
        return lead;
    }

    private List<LeadQuotation> mapQuotationsToEntities(List<QuotationDetailRequest> quotationRequests, Lead lead) {
        List<LeadQuotation> quotations = new ArrayList<>();
        
        for (QuotationDetailRequest quotationRequest : quotationRequests) {
            LeadQuotation quotation = new LeadQuotation();
            
            quotation.setIdLeadCotizacionDetalle(quotationRequest.getIdLeadCotizacionDetalle());
            quotation.setLead(lead);
            quotation.setPlazo(quotationRequest.getPlazo());
            quotation.setLaboralSituation(quotationRequest.getLaboralSituation());
            quotation.setGrupo(quotationRequest.getGrupo());
            quotation.setSegmento(quotationRequest.getSegmento());
            quotation.setCem(quotationRequest.getCem());
            quotation.setMaf(quotationRequest.getMaf());
            quotation.setIngreso(quotationRequest.getIngreso());
            quotation.setCuotaKiwi(quotationRequest.getCuotaKiwi());
            quotation.setExperianResultadoEstado(quotationRequest.getExperianResultadoEstado());
            quotation.setExperianRate(quotationRequest.getExperianRate());
            quotation.setNewRate(quotationRequest.getNewRate());
            quotation.setRateDifferential(quotationRequest.getRateDifferential());
            quotation.setExperianResultadoTexto(quotationRequest.getExperianResultadoTexto());
            quotation.setResultadoFinalEstado(quotationRequest.getResultadoFinalEstado());
            quotation.setClasificacion(quotationRequest.getClasificacion());
            quotation.setCuotaActual(quotationRequest.getCuotaActual());
            quotation.setCuotaActualKiwi(quotationRequest.getCuotaActualKiwi());
            quotation.setEsCampania(quotationRequest.getEsCampania());
            quotation.setIsActive(quotationRequest.getIsActive());
            
            quotations.add(quotation);
        }
        
        return quotations;
    }

    private void updateLeadFields(Lead existingLead, LeadIntegrationRequest request) {
        existingLead.setNombres(request.getNombres());
        existingLead.setApellidoPaterno(request.getApellidoPaterno());
        existingLead.setApellidoMaterno(request.getApellidoMaterno());
        existingLead.setEstadoCivil(request.getEstadoCivil());
        existingLead.setEmail(request.getEmail());
        existingLead.setCelular(request.getCelular());
        existingLead.setTelefono(request.getTelefono());
        existingLead.setDireccion(request.getDireccion());
        existingLead.setInteresProcedimiento(request.getInteresProcedimiento());
        existingLead.setTddSede(request.getTddSede());
        existingLead.setSedeLead(request.getSedeLead());
        existingLead.setIngreso(request.getIngreso());
        existingLead.setLaboralSituation(request.getLaboralSituation());
        
        if (request.getEdad() != null) {
            existingLead.setEdad(Integer.parseInt(request.getEdad()));
        }
        
        existingLead.setFechaModificacion(request.getFechaModificacion());
    }

    private LeadIntegrationResponse mapLeadToResponse(Lead lead) {
        return LeadIntegrationResponse.success(lead);
    }

    private boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }
}