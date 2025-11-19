package com.kiwipay.onboarding.integration.application.internal.services;

import com.kiwipay.onboarding.client.application.internal.dto.ClientCreateRequest;
import com.kiwipay.onboarding.integration.application.internal.dto.SGLCotizacionDetalle;
import com.kiwipay.onboarding.integration.application.internal.dto.SGLLeadRequest;
import com.kiwipay.onboarding.integration.domain.model.valueobjects.SGLDocumentTypeMapping;
import com.kiwipay.onboarding.integration.domain.model.valueobjects.SGLMaritalStatusMapping;
import com.kiwipay.onboarding.integration.domain.services.SGLMappingService;
import org.springframework.stereotype.Service;

/**
 * Implementation of SGLMappingService.
 * Handles the mapping logic between SGL and Onboarding data structures.
 */
@Service
public class SGLMappingServiceImpl implements SGLMappingService {

    @Override
    public ClientCreateRequest mapToClientCreateRequest(SGLLeadRequest sglLead) {
        validateBasicClientData(sglLead);

        ClientCreateRequest request = new ClientCreateRequest();
        
        // Basic identification
        request.setDocumentType(SGLDocumentTypeMapping.mapToOnboarding(sglLead.getTddTipoDocumento()));
        request.setDocumentNumber(sglLead.getNroDocumento());
        
        // Names
        request.setFirstNames(sglLead.getNombres());
        request.setLastNames(buildFullLastName(sglLead.getApellidoPaterno(), sglLead.getApellidoMaterno()));
        
        // Contact information
        request.setPhone(getValidPhone(sglLead.getTelefono(), sglLead.getCelular()));
        request.setEmail(getValidEmail(sglLead.getEmail()));
        
        // Personal information
        request.setMaritalStatus(SGLMaritalStatusMapping.mapToOnboarding(sglLead.getEstadoCivil()));
        request.setGender("M"); // Default, as SGL doesn't provide gender info
        request.setBirthDate("1990-01-01"); // Default, will need to be updated manually
        
        // Address
        ClientCreateRequest.AddressDto address = new ClientCreateRequest.AddressDto();
        address.setLine1(getValidAddress(sglLead.getDireccion()));
        address.setDepartmentId("15"); // Lima by default
        address.setProvinceId("1501"); // Lima by default
        address.setDistrictId("150101"); // Lima by default
        request.setAddress(address);
        
        return request;
    }

    @Override
    public Object mapToSpouseCreateRequest(SGLLeadRequest sglLead) {
        // Will implement when we need spouse data from SGL
        // For now, return a basic spouse request structure
        if (!hasSpouseData(sglLead)) {
            return null;
        }
        
        // TODO: Create SpouseCreateRequest mapping when spouse structure is defined
        return null;
    }

    @Override
    public Object mapToClinicalDataCreateRequest(SGLLeadRequest sglLead) {
        // Will implement when clinical data mapping is needed
        if (!hasClinicalData(sglLead)) {
            return null;
        }
        
        // TODO: Create ClinicalDataCreateRequest mapping
        return null;
    }

    @Override
    public Object mapToRiskCreateRequest(Long clientId, SGLCotizacionDetalle cotizacion) {
        // TODO: Create RiskCreateRequest mapping from SGLCotizacionDetalle
        return null;
    }

    @Override
    public boolean hasSpouseData(SGLLeadRequest sglLead) {
        return sglLead.getConyugeNombres() != null && 
               !sglLead.getConyugeNombres().trim().isEmpty();
    }

    @Override
    public boolean hasClinicalData(SGLLeadRequest sglLead) {
        return sglLead.getTddSede() != null && sglLead.getTddSede() > 0 ||
               sglLead.getTddEspecialidad() != null && sglLead.getTddEspecialidad() > 0;
    }

    @Override
    public void validateSGLLeadData(SGLLeadRequest sglLead) {
        if (sglLead == null) {
            throw new IllegalArgumentException("SGL lead data cannot be null");
        }
        
        validateBasicClientData(sglLead);
    }

    // Private helper methods

    private void validateBasicClientData(SGLLeadRequest sglLead) {
        if (sglLead.getNroDocumento() == null || sglLead.getNroDocumento().trim().isEmpty()) {
            throw new IllegalArgumentException("Document number is required");
        }
        
        if (sglLead.getNombres() == null || sglLead.getNombres().trim().isEmpty()) {
            throw new IllegalArgumentException("First names are required");
        }
    }

    private String buildFullLastName(String apellidoPaterno, String apellidoMaterno) {
        StringBuilder fullName = new StringBuilder();
        
        if (apellidoPaterno != null && !apellidoPaterno.trim().isEmpty()) {
            fullName.append(apellidoPaterno.trim());
        }
        
        if (apellidoMaterno != null && !apellidoMaterno.trim().isEmpty()) {
            if (fullName.length() > 0) {
                fullName.append(" ");
            }
            fullName.append(apellidoMaterno.trim());
        }
        
        return fullName.length() > 0 ? fullName.toString() : "APELLIDO";
    }

    private String getValidPhone(String telefono, String celular) {
        // Prioritize celular over telefono
        if (celular != null && !celular.trim().isEmpty()) {
            return celular.trim();
        }
        
        if (telefono != null && !telefono.trim().isEmpty()) {
            return telefono.trim();
        }
        
        return "999999999"; // Default phone
    }

    private String getValidEmail(String email) {
        if (email == null || email.trim().isEmpty() || 
            "SINCORREO".equalsIgnoreCase(email.trim())) {
            return "noemail@kiwipay.com"; // Default email
        }
        
        return email.trim();
    }

    private String getValidAddress(String direccion) {
        if (direccion == null || direccion.trim().isEmpty()) {
            return "Dirección no proporcionada"; // Default address
        }
        
        return direccion.trim();
    }
}