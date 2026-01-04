package com.kiwipay.onboarding.loan.application.internal.queryservices;

import com.kiwipay.onboarding.loan.application.internal.dto.LoanResponse;
import com.kiwipay.onboarding.loan.domain.model.aggregates.Loan;
import com.kiwipay.onboarding.loan.domain.model.exceptions.LoanBusinessException;
import com.kiwipay.onboarding.loan.domain.model.valueobjects.LoanStatus;
import com.kiwipay.onboarding.loan.domain.services.LoanQueryService;
import com.kiwipay.onboarding.loan.infrastructure.persistence.jpa.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of Query Service for Loan operations
 * Handles all read-only operations
 */
@Service
@Transactional(readOnly = true)
public class LoanQueryServiceImpl implements LoanQueryService {

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public LoanResponse getLoanById(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(LoanBusinessException::loanNotFound);
        return mapToResponse(loan);
    }

    @Override
    public Page<LoanResponse> getAllLoans(Pageable pageable) {
        Page<Loan> loans = loanRepository.findAll(pageable);
        return loans.map(this::mapToResponse);
    }

    @Override
    public List<LoanResponse> getLoansByClientId(Long clientId) {
        List<Loan> loans = loanRepository.findByClientIdOrderByCreatedAtDesc(clientId);
        return loans.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<LoanResponse> getLoansByStatus(LoanStatus status) {
        List<Loan> loans = loanRepository.findByLoanStatusOrderByCreatedAtDesc(status);
        return loans.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ========== MAPPER ==========

    private LoanResponse mapToResponse(Loan loan) {
        LoanResponse response = new LoanResponse();
        response.setId(loan.getId());
        response.setClientId(loan.getClientId());
        response.setClinicalDataId(loan.getClinicalDataId());
        response.setIncome(loan.getIncome());
        response.setQuotaNumber(loan.getQuotaNumber());
        response.setMaf(loan.getMaf());
        response.setGroup(loan.getGroup());
        response.setSegment(loan.getSegment());
        response.setEmploymentStatus(loan.getEmploymentStatus());
        response.setClassification(loan.getClassification());
        response.setFinalRate(loan.getFinalRate());
        response.setExperianRate(loan.getExperianRate());
        response.setAdditionalRate(loan.getAdditionalRate());
        response.setInitial(loan.getInitial());
        response.setLoanStatus(loan.getLoanStatus());

        // Timestamps
        response.setCreatedAt(loan.getCreatedAt());
        response.setUpdatedAt(loan.getUpdatedAt());
        response.setSignatureAt(loan.getSignatureAt());
        response.setApprovedByRiskAt(loan.getApprovedByRiskAt());
        response.setDisbursementAt(loan.getDisbursementAt());

        return response;
    }
}
