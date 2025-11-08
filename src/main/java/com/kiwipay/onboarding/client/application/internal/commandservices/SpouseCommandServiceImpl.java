package com.kiwipay.onboarding.titular.application.internal.commandservices;

import com.kiwipay.onboarding.titular.application.internal.dto.SpouseCreateRequest;
import com.kiwipay.onboarding.titular.application.internal.dto.SpousePatchRequest;
import com.kiwipay.onboarding.titular.application.internal.dto.SpouseResponse;
import com.kiwipay.onboarding.titular.application.internal.dto.SpouseUpdateRequest;
import com.kiwipay.onboarding.client.domain.model.aggregates.Client;
import com.kiwipay.onboarding.client.domain.model.aggregates.Spouse;
import com.kiwipay.onboarding.client.domain.model.exceptions.SpouseBusinessException;
import com.kiwipay.onboarding.client.domain.model.valueobjects.DocumentType;
import com.kiwipay.onboarding.client.domain.model.valueobjects.MaritalStatus;
import com.kiwipay.onboarding.client.domain.services.SpouseCommandService;
import com.kiwipay.onboarding.titular.infrastructure.persistence.jpa.repositories.ClientRepository;
import com.kiwipay.onboarding.titular.infrastructure.persistence.jpa.repositories.SpouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@Transactional
public class SpouseCommandServiceImpl implements SpouseCommandService {

    @Autowired
    private SpouseRepository spouseRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public SpouseResponse createSpouse(Long clientId, SpouseCreateRequest request) {
        Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new RuntimeException("Client not found with id: " + clientId));
        
        validateClientMaritalStatus(client);
        
        if (spouseRepository.existsByClientId(clientId)) {
            throw SpouseBusinessException.spouseAlreadyExists();
        }
        
        if (spouseRepository.existsByDocumentNumber(request.getDocumentNumber())) {
            throw SpouseBusinessException.documentAlreadyLinked();
        }
        
        validateDocumentFormat(request.getDocumentType(), request.getDocumentNumber());
        
        // 5. Crear el cónyuge
        Spouse spouse = new Spouse(
            clientId,
            DocumentType.valueOf(request.getDocumentType()),
            request.getDocumentNumber(),
            request.getFirstNames(),
            request.getLastNames(),
            request.getEmail(),
            request.getPhone()
        );
        
        spouse = spouseRepository.save(spouse);
        return toSpouseResponse(spouse);
    }

    @Override
    public SpouseResponse updateSpouse(Long clientId, SpouseUpdateRequest request) {
        Spouse existingSpouse = spouseRepository.findByClientId(clientId)
            .orElseThrow(SpouseBusinessException::spouseNotFound);
        
        existingSpouse.setFirstNames(request.getFirstNames());
        existingSpouse.setLastNames(request.getLastNames());
        existingSpouse.setEmail(request.getEmail());
        existingSpouse.setPhone(request.getPhone());
        existingSpouse.setUpdatedAt(OffsetDateTime.now());
        
        existingSpouse = spouseRepository.save(existingSpouse);
        return toSpouseResponse(existingSpouse);
    }

    @Override
    public SpouseResponse patchSpouse(Long clientId, SpousePatchRequest request) {
        Spouse existingSpouse = spouseRepository.findByClientId(clientId)
            .orElseThrow(SpouseBusinessException::spouseNotFound);
        
        if (request.getFirstNames() != null) {
            existingSpouse.setFirstNames(request.getFirstNames());
        }
        if (request.getLastNames() != null) {
            existingSpouse.setLastNames(request.getLastNames());
        }
        if (request.getEmail() != null) {
            existingSpouse.setEmail(request.getEmail());
        }
        if (request.getPhone() != null) {
            existingSpouse.setPhone(request.getPhone());
        }
        existingSpouse.setUpdatedAt(OffsetDateTime.now());
        
        existingSpouse = spouseRepository.save(existingSpouse);
        return toSpouseResponse(existingSpouse);
    }

    @Override
    public void deleteSpouse(Long clientId) {
        if (!spouseRepository.existsByClientId(clientId)) {
            throw SpouseBusinessException.spouseNotFound();
        }
        spouseRepository.deleteByClientId(clientId);
    }

    private void validateClientMaritalStatus(Client client) {
        MaritalStatus maritalStatus = client.getMaritalStatus();
        if (maritalStatus != MaritalStatus.MARRIED && maritalStatus != MaritalStatus.COHABITANT) {
            throw SpouseBusinessException.clientNotMarried();
        }
    }

    private void validateDocumentFormat(String documentType, String documentNumber) {
        DocumentType type = DocumentType.valueOf(documentType);
        switch (type) {
            case DNI:
                if (documentNumber.length() != 8) {
                    throw new IllegalArgumentException("DNI must have exactly 8 digits");
                }
                break;
            case CE:
                if (documentNumber.length() < 9 || documentNumber.length() > 12) {
                    throw new IllegalArgumentException("CE must have between 9 and 12 characters");
                }
                break;
            case PASS:
                if (documentNumber.length() < 8 || documentNumber.length() > 12) {
                    throw new IllegalArgumentException("Passport must have between 8 and 12 characters");
                }
                break;
        }
    }

    private SpouseResponse toSpouseResponse(Spouse spouse) {
        SpouseResponse response = new SpouseResponse();
        response.setId(spouse.getId());
        response.setClientId(spouse.getClientId());
        response.setDocumentType(spouse.getDocumentType().name());
        response.setDocumentNumber(spouse.getDocumentNumber());
        response.setFirstNames(spouse.getFirstNames());
        response.setLastNames(spouse.getLastNames());
        response.setEmail(spouse.getEmail());
        response.setPhone(spouse.getPhone());
        response.setCreatedAt(spouse.getCreatedAt().toString());
        return response;
    }
}