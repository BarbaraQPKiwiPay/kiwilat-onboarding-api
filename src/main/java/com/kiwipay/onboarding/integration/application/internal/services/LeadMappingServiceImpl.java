package com.kiwipay.onboarding.integration.application.internal.services;

import com.kiwipay.onboarding.catalog.domain.services.CatalogQueryService;
import com.kiwipay.onboarding.integration.application.internal.dto.LeadQueryResponse;
import com.kiwipay.onboarding.integration.domain.services.LeadMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeadMappingServiceImpl implements LeadMappingService {
    
    @Autowired
    private CatalogQueryService catalogQueryService;
    
    @Override
    public LeadQueryResponse enrichWithCatalogData(LeadQueryResponse leadResponse) {
        // Este método no se usa directamente aquí, 
        // la lógica está implementada en IntegrationQueryServiceImpl
        return leadResponse;
    }
    
    @Override
    public String getDocumentTypeName(Integer tddTipoDocumento) {
        // Mapeo basado en los valores comunes
        switch (tddTipoDocumento) {
            case 1:
                return "DNI";
            case 2:
                return "Carnet de Extranjería";
            case 3:
                return "Pasaporte";
            default:
                return "Documento";
        }
    }
    
    @Override
    public String getMedicalCategoryName(String interesProcedimiento) {
        if (interesProcedimiento == null) return null;
        
        // Buscar en los catálogos existentes por nombre similar
        try {
            return catalogQueryService.getAllMedicalCategories()
                .stream()
                .filter(category -> category.getName().toLowerCase()
                    .contains(interesProcedimiento.toLowerCase()))
                .findFirst()
                .map(category -> category.getName())
                .orElse(interesProcedimiento); // Si no se encuentra, devolver el original
        } catch (Exception e) {
            return interesProcedimiento;
        }
    }
    
    @Override
    public ClinicSeatInfo getClinicSeatInfo(Integer tddSede) {
        // Por ahora mapeo básico, se puede expandir con lógica más compleja
        // según la integración real con los catálogos de clínicas
        try {
            // Aquí podrías implementar la lógica para buscar por tddSede
            // en los catálogos de clínicas y sucursales
            return new ClinicSeatInfo("Clínica " + tddSede, "Sede " + tddSede);
        } catch (Exception e) {
            return new ClinicSeatInfo("Clínica no encontrada", "Sede no encontrada");
        }
    }
}