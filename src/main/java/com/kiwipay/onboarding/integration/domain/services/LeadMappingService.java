package com.kiwipay.onboarding.integration.domain.services;

import com.kiwipay.onboarding.integration.application.internal.dto.LeadQueryResponse;

public interface LeadMappingService {
    
    /**
     * Enriquecer la respuesta del lead con datos de catálogos
     * @param leadResponse Response básico del lead
     * @return Response enriquecido con nombres de catálogos
     */
    LeadQueryResponse enrichWithCatalogData(LeadQueryResponse leadResponse);
    
    /**
     * Obtener nombre del tipo de documento por ID
     * @param tddTipoDocumento ID del tipo de documento
     * @return Nombre del tipo de documento
     */
    String getDocumentTypeName(Integer tddTipoDocumento);
    
    /**
     * Obtener nombre de la categoría médica por texto
     * @param interesProcedimiento Texto del interés/procedimiento
     * @return Nombre de la categoría médica
     */
    String getMedicalCategoryName(String interesProcedimiento);
    
    /**
     * Obtener información de la clínica/sede por ID
     * @param tddSede ID de la sede
     * @return Información de clínica y sede
     */
    ClinicSeatInfo getClinicSeatInfo(Integer tddSede);
    
    /**
     * Clase interna para información de clínica/sede
     */
    class ClinicSeatInfo {
        private final String clinicName;
        private final String seatName;
        
        public ClinicSeatInfo(String clinicName, String seatName) {
            this.clinicName = clinicName;
            this.seatName = seatName;
        }
        
        public String getClinicName() { return clinicName; }
        public String getSeatName() { return seatName; }
    }
}