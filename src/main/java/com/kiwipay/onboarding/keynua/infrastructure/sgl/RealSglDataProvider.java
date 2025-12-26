package com.kiwipay.onboarding.keynua.infrastructure.sgl;

import com.kiwipay.onboarding.keynua.domain.services.SglDataProvider;
import com.kiwipay.onboarding.keynua.dto.SglLoanData;

/**
 * Real SGL Data Provider (stub)
 * TODO: Implement actual SGL integration when available
 */
public class RealSglDataProvider implements SglDataProvider {

    @Override
    public SglLoanData getLoanData(String loanId) {
        throw new UnsupportedOperationException(
                "Real SGL integration not implemented yet. " +
                        "Set keynua.use-mock-sgl=true to use mock data provider.");
    }
}
