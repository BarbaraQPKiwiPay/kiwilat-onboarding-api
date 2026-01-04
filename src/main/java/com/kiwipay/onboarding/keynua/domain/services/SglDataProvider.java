package com.kiwipay.onboarding.keynua.domain.services;

import com.kiwipay.onboarding.keynua.dto.SglLoanData;

/**
 * SGL Data Provider interface
 * Provides loan and quote data from SGL system
 */
public interface SglDataProvider {

    /**
     * Gets loan data for the specified loan ID
     * 
     * @param loanId the loan ID
     * @return SGL loan data
     */
    SglLoanData getLoanData(String loanId);
}
