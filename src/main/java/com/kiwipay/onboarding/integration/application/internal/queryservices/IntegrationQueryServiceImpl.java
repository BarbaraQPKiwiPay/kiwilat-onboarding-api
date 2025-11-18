package com.kiwipay.onboarding.integration.application.internal.queryservices;

import com.kiwipay.onboarding.integration.application.internal.dto.LeadQueryResponse;
import com.kiwipay.onboarding.integration.application.internal.dto.LeadSummaryResponse;
import com.kiwipay.onboarding.integration.domain.model.aggregates.Lead;
import com.kiwipay.onboarding.integration.domain.services.IntegrationQueryService;
import com.kiwipay.onboarding.integration.domain.services.LeadMappingService;
import com.kiwipay.onboarding.integration.infrastructure.persistence.jpa.LeadRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de consultas de integración
 */
@Service
@Transactional(readOnly = true)
public class IntegrationQueryServiceImpl implements IntegrationQueryService {

    private final LeadRepository leadRepository;
    private final LeadMappingService leadMappingService;

    public IntegrationQueryServiceImpl(LeadRepository leadRepository, LeadMappingService leadMappingService) {
        this.leadRepository = leadRepository;
        this.leadMappingService = leadMappingService;
    }

    @Override
    public Optional<LeadQueryResponse> findLeadById(Long leadId) {
        return leadRepository.findById(leadId)
                .map(this::enrichLeadResponse);
    }
    
    private LeadQueryResponse enrichLeadResponse(Lead lead) {
        LeadQueryResponse response = LeadQueryResponse.from(lead);
        return enrichWithCatalogData(response);
    }
    
    private LeadQueryResponse enrichWithCatalogData(LeadQueryResponse response) {
        // Enriquecer con nombre del tipo de documento
        if (response.getDocumentType() != null) {
            String documentTypeName = leadMappingService.getDocumentTypeName(response.getDocumentType());
            response.setDocumentTypeName(documentTypeName);
        }
        
        // Enriquecer con nombre de categoría médica
        if (response.getProcedureInterest() != null) {
            String categoryName = leadMappingService.getMedicalCategoryName(response.getProcedureInterest());
            response.setMedicalCategoryName(categoryName);
        }
        
        // Enriquecer con información de clínica/sede
        if (response.getClinicSiteId() != null) {
            LeadMappingService.ClinicSeatInfo clinicInfo = leadMappingService.getClinicSeatInfo(response.getClinicSiteId());
            response.setClinicName(clinicInfo.getClinicName());
            response.setSeatName(clinicInfo.getSeatName());
        }
        
        return response;
    }

    @Override
    public Optional<LeadQueryResponse> findLeadByDocumentNumber(String documentNumber) {
        return leadRepository.findByNroDocumento(documentNumber)
                .map(this::enrichLeadResponse);
    }

    @Override
    public Optional<LeadQueryResponse> findLeadBySglId(Long sglLeadId) {
        return leadRepository.findByIdLead(sglLeadId)
                .map(this::enrichLeadResponse);
    }

    @Override
    public List<LeadSummaryResponse> findLeadsWithFilters(
            String procedureInterest,
            Integer clinicSite,
            LocalDateTime dateFrom,
            LocalDateTime dateTo,
            int page,
            int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("fechaRegistro").descending());
        
        // Si no hay filtros específicos, usar findAll con paginación
        if (procedureInterest == null && clinicSite == null && dateFrom == null && dateTo == null) {
            return leadRepository.findAll(pageable).getContent().stream()
                    .map(LeadSummaryResponse::from)
                    .collect(Collectors.toList());
        }

        // Aplicar filtros individuales
        List<Lead> leads;
        
        if (procedureInterest != null && !procedureInterest.trim().isEmpty()) {
            leads = leadRepository.findByInteresProcedimientoContainingIgnoreCase(procedureInterest);
        } else if (clinicSite != null) {
            leads = leadRepository.findByTddSede(clinicSite);
        } else if (dateFrom != null) {
            leads = leadRepository.findByFechaRegistroAfter(dateFrom);
        } else {
            leads = leadRepository.findAll();
        }

        // Aplicar filtros adicionales en memoria si hay múltiples filtros
        return leads.stream()
                .filter(lead -> clinicSite == null || lead.getTddSede().equals(clinicSite))
                .filter(lead -> procedureInterest == null || 
                        (lead.getInteresProcedimiento() != null && 
                         lead.getInteresProcedimiento().toLowerCase().contains(procedureInterest.toLowerCase())))
                .filter(lead -> dateFrom == null || lead.getFechaRegistro().isAfter(dateFrom))
                .filter(lead -> dateTo == null || lead.getFechaRegistro().isBefore(dateTo))
                .skip((long) page * size)
                .limit(size)
                .map(LeadSummaryResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<LeadSummaryResponse> findLeadsWithActiveQuotations() {
        return leadRepository.findLeadsWithActiveQuotations().stream()
                .map(LeadSummaryResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<LeadSummaryResponse> findLeadsByQuotationStatus(String quotationStatus) {
        return leadRepository.findLeadsByQuotationStatus(quotationStatus).stream()
                .map(LeadSummaryResponse::from)
                .collect(Collectors.toList());
    }
}