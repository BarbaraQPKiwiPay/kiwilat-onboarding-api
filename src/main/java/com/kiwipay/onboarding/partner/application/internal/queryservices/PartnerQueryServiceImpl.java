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

    private final PartnerRepository partnerRepository;
    private final PartnerMapper partnerMapper;

    @Override
    public Optional<PartnerResponse> getPartnerById(Long id) {
        return partnerRepository.findById(id)
                .map(partnerMapper::toResponse);
    }

    @Override
    public List<PartnerResponse> getPartnersByLoan(Long loanId) {
        return partnerRepository.findByLoanId(loanId).stream()
                .map(partnerMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PartnerResponse> getPartnerByClient(Long clientId) {
        return partnerRepository.findByClientId(clientId)
                .map(partnerMapper::toResponse);
    }

    @Override
    public Optional<PartnerResponse> getPartnerByGuarantor(Long guarantorId) {
        return partnerRepository.findByGuarantorId(guarantorId)
                .map(partnerMapper::toResponse);
    }

    @Override
    public Optional<PartnerResponse> getPartnerByPatient(Long patientId) {
        return partnerRepository.findByPatientId(patientId)
                .map(partnerMapper::toResponse);
    }
}
