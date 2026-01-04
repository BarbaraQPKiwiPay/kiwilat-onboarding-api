package com.kiwipay.onboarding.loan.domain.services;

import com.kiwipay.onboarding.loan.application.internal.dto.LoanCreateRequest;
import com.kiwipay.onboarding.loan.application.internal.dto.LoanResponse;
import com.kiwipay.onboarding.loan.application.internal.dto.LoanStatusChangeRequest;
import com.kiwipay.onboarding.loan.application.internal.dto.LoanUpdateRequest;

public interface LoanCommandService {

    /**
     * Creates a new loan
     * 
     * @param request Loan creation data
     * @return Created loan response
     */
    LoanResponse createLoan(LoanCreateRequest request);

    /**
     * Updates an existing loan (any field can be updated)
     * 
     * @param loanId  ID of the loan to update
     * @param request Update data
     * @return Updated loan response
     */
    LoanResponse updateLoan(Long loanId, LoanUpdateRequest request);

    /**
     * Changes the status of a loan
     * 
     * @param loanId  ID of the loan
     * @param request Status change request (includes userId)
     * @return Updated loan response
     */
    LoanResponse changeStatus(Long loanId, LoanStatusChangeRequest request);

    /**
     * Deletes a loan
     * 
     * @param loanId ID of the loan to delete
     */
    void deleteLoan(Long loanId);
}
