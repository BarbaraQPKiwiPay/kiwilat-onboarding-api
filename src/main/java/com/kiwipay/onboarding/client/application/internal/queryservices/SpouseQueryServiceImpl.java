package com.kiwipay.onboarding.titular.application.internal.queryservices;

import com.kiwipay.onboarding.titular.application.internal.dto.SpouseResponse;
import com.kiwipay.onboarding.client.domain.model.aggregates.Spouse;
import com.kiwipay.onboarding.client.domain.services.SpouseQueryService;
import com.kiwipay.onboarding.titular.infrastructure.persistence.jpa.repositories.SpouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpouseQueryServiceImpl implements SpouseQueryService {

    @Autowired
    private SpouseRepository spouseRepository;

    @Override
    public SpouseResponse getSpouseByClientId(Long clientId) {
        return spouseRepository.findByClientId(clientId)
            .map(this::toSpouseResponse)
            .orElse(null);
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