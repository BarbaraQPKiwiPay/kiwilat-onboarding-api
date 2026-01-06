package com.kiwipay.onboarding.partner.application.internal.queryservices;

import com.kiwipay.onboarding.partner.application.internal.dto.PartnerMapper;
import com.kiwipay.onboarding.partner.application.internal.dto.PartnerResponse;
import com.kiwipay.onboarding.partner.domain.services.PartnerQueryService;
import com.kiwipay.onboarding.partner.infrastructure.persistence.jpa.repositories.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of SpouseQueryService
 * Handles read-only operations
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PartnerQueryServiceImpl implements PartnerQueryService {

    private final PartnerRepository spouseRepository;
    private final PartnerMapper spouseMapper;

    @Override
    public Optional<PartnerResponse> getSpouseById(Long id) {
        return spouseRepository.findById(id)
                .map(spouseMapper::toResponse);
    }

    @Override
    public List<PartnerResponse> getSpousesByLoan(Long loanId) {
        return spouseRepository.findByLoanId(loanId).stream()
                .map(spouseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PartnerResponse> getSpouseByClient(Long clientId) {
        return spouseRepository.findByClientId(clientId)
                .map(spouseMapper::toResponse);
    }

    @Override
    public Optional<PartnerResponse> getSpouseByGuarantor(String guarantorId) {
        return spouseRepository.findByGuarantorId(guarantorId)
                .map(spouseMapper::toResponse);
    }

    @Override
    public Optional<PartnerResponse> getSpouseByPatient(Long patientId) {
        return spouseRepository.findByPatientId(patientId)
                .map(spouseMapper::toResponse);
    }
}
