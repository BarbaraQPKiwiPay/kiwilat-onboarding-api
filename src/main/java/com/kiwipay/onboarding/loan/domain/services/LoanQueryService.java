package com.kiwipay.onboarding.loan.domain.services;

import com.kiwipay.onboarding.loan.application.internal.dto.LoanResponse;
import com.kiwipay.onboarding.loan.domain.model.valueobjects.LoanStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Query Service interface for Loan operations (Read)
 * Handles all read-only operations
 */
public interface LoanQueryService {

    /**
     * Gets a loan by ID
     * 
     * @param loanId ID of the loan
     * @return Loan response
     */
    LoanResponse getLoanById(Long loanId);

    /**
     * Gets all loans with pagination
     * 
     * @param pageable Pagination parameters
     * @return Page of loan responses
     */
    Page<LoanResponse> getAllLoans(Pageable pageable);

    /**
     * Gets all loans for a specific client
     * 
     * @param clientId ID of the client
     * @return List of loan responses
     */
    List<LoanResponse> getLoansByClientId(Long clientId);

    /**
     * Gets all loans with a specific status
     * 
     * @param status Loan status
     * @return List of loan responses
     */
    List<LoanResponse> getLoansByStatus(LoanStatus status);
}
