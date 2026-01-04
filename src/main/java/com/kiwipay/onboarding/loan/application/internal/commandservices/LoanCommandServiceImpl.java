package com.kiwipay.onboarding.loan.application.internal.commandservices;

import com.kiwipay.onboarding.client.infrastructure.persistence.jpa.repositories.ClientRepository;
import com.kiwipay.onboarding.clinicaldata.infrastructure.persistence.jpa.repositories.ClinicalDataRepository;
import com.kiwipay.onboarding.loan.application.internal.dto.LoanCreateRequest;
import com.kiwipay.onboarding.loan.application.internal.dto.LoanResponse;
import com.kiwipay.onboarding.loan.application.internal.dto.LoanStatusChangeRequest;
import com.kiwipay.onboarding.loan.application.internal.dto.LoanUpdateRequest;
import com.kiwipay.onboarding.loan.domain.model.aggregates.Loan;
import com.kiwipay.onboarding.loan.domain.model.exceptions.LoanBusinessException;
import com.kiwipay.onboarding.loan.domain.services.LoanCommandService;
import com.kiwipay.onboarding.loan.infrastructure.persistence.jpa.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of Command Service for Loan operations
 * Handles all write/state-changing operations
 */
@Service
public class LoanCommandServiceImpl implements LoanCommandService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClinicalDataRepository clinicalDataRepository;

    @Override
    @Transactional
    public LoanResponse createLoan(LoanCreateRequest request) {
        // Validate client exists
        if (!clientRepository.existsById(request.getClientId())) {
            throw LoanBusinessException.clientNotFound();
        }

        // Validate clinical data if provided
        if (request.getClinicalDataId() != null &&
                !clinicalDataRepository.existsById(request.getClinicalDataId())) {
            throw LoanBusinessException.clinicalDataNotFound();
        }

        // Create loan entity
        Loan loan = new Loan();
        loan.setClientId(request.getClientId());
        loan.setClinicalDataId(request.getClinicalDataId());
        loan.setIncome(request.getIncome());
        loan.setQuotaNumber(request.getQuotaNumber());
        loan.setMaf(request.getMaf());
        loan.setGroup(request.getGroup());
        loan.setSegment(request.getSegment());
        loan.setEmploymentStatus(request.getEmploymentStatus());
        loan.setClassification(request.getClassification());
        loan.setFinalRate(request.getFinalRate());
        loan.setExperianRate(request.getExperianRate());
        loan.setAdditionalRate(request.getAdditionalRate());
        loan.setInitial(request.getInitial());

        // Save loan
        Loan savedLoan = loanRepository.save(loan);

        return mapToResponse(savedLoan);
    }

    @Override
    @Transactional
    public LoanResponse updateLoan(Long loanId, LoanUpdateRequest request) {
        // Find loan
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(LoanBusinessException::loanNotFound);

        // Update only provided fields
        if (request.getClientId() != null) {
            // Validate new client exists
            if (!clientRepository.existsById(request.getClientId())) {
                throw LoanBusinessException.clientNotFound();
            }
            loan.setClientId(request.getClientId());
        }

        if (request.getClinicalDataId() != null) {
            // Validate clinical data exists
            if (!clinicalDataRepository.existsById(request.getClinicalDataId())) {
                throw LoanBusinessException.clinicalDataNotFound();
            }
            loan.setClinicalDataId(request.getClinicalDataId());
        }

        if (request.getIncome() != null)
            loan.setIncome(request.getIncome());
        if (request.getQuotaNumber() != null)
            loan.setQuotaNumber(request.getQuotaNumber());
        if (request.getMaf() != null)
            loan.setMaf(request.getMaf());
        if (request.getGroup() != null)
            loan.setGroup(request.getGroup());
        if (request.getSegment() != null)
            loan.setSegment(request.getSegment());
        if (request.getEmploymentStatus() != null)
            loan.setEmploymentStatus(request.getEmploymentStatus());
        if (request.getClassification() != null)
            loan.setClassification(request.getClassification());
        if (request.getFinalRate() != null)
            loan.setFinalRate(request.getFinalRate());
        if (request.getExperianRate() != null)
            loan.setExperianRate(request.getExperianRate());
        if (request.getAdditionalRate() != null)
            loan.setAdditionalRate(request.getAdditionalRate());
        if (request.getInitial() != null)
            loan.setInitial(request.getInitial());

        // Save updated loan
        Loan updatedLoan = loanRepository.save(loan);

        return mapToResponse(updatedLoan);
    }

    @Override
    @Transactional
    public LoanResponse changeStatus(Long loanId, LoanStatusChangeRequest request) {
        // Find loan
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(LoanBusinessException::loanNotFound);

        // Change status (includes user tracking)
        try {
            loan.changeStatus(request.getNewStatus(), request.getUserId(), request.getReason());
        } catch (IllegalStateException e) {
            throw LoanBusinessException.invalidStatusTransition(
                    loan.getLoanStatus().name(),
                    request.getNewStatus().name());
        }

        // Save updated loan
        Loan updatedLoan = loanRepository.save(loan);

        return mapToResponse(updatedLoan);
    }

    @Override
    @Transactional
    public void deleteLoan(Long loanId) {
        // Find loan
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(LoanBusinessException::loanNotFound);

        // Check if loan can be deleted (business rule: only PENDING loans)
        if (loan.getLoanStatus().isFinalState()) {
            throw LoanBusinessException.cannotDeleteLoan("Cannot delete loan in final state");
        }

        loanRepository.delete(loan);
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
