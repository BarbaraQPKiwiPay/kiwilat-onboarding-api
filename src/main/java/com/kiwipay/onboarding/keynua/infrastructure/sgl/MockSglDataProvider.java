package com.kiwipay.onboarding.keynua.infrastructure.sgl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiwipay.onboarding.keynua.domain.services.SglDataProvider;
import com.kiwipay.onboarding.keynua.dto.SglLoanData;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.math.BigDecimal;

/**
 * Mock SGL Data Provider
 * Returns mock data from fixture file for testing
 */
public class MockSglDataProvider implements SglDataProvider {

    private static final Logger log = LoggerFactory.getLogger(MockSglDataProvider.class);
    private SglLoanData mockData;

    @PostConstruct
    public void init() {
        log.warn("=".repeat(80));
        log.warn("USING MOCK SGL DATA PROVIDER - NOT FOR PRODUCTION");
        log.warn("=".repeat(80));

        try {
            ClassPathResource resource = new ClassPathResource("mock/sgl/lead.json");
            if (resource.exists()) {
                ObjectMapper mapper = new ObjectMapper();
                mockData = mapper.readValue(resource.getInputStream(), SglLoanData.class);
                log.info("Loaded mock SGL data from resource file");
            } else {
                log.warn("Mock SGL resource file not found, using default values");
                createDefaultMockData();
            }
        } catch (Exception e) {
            log.error("Error loading mock SGL data, using defaults", e);
            createDefaultMockData();
        }
    }

    private void createDefaultMockData() {
        mockData = new SglLoanData();
        mockData.setLoanId("MOCK_LOAN_123");
        mockData.setAmount(new BigDecimal("10000.00"));
        mockData.setTermMonths(12);
        mockData.setMaf(new BigDecimal("500.00"));
        mockData.setMonthlyPayment(new BigDecimal("950.00"));
        mockData.setInterestRate(new BigDecimal("15.50"));
        mockData.setClientId("CLIENT_001");
    }

    @Override
    public SglLoanData getLoanData(String loanId) {
        log.info("Returning mock SGL data for loanId: {}", loanId);

        // In mock mode, we return the same data for all loanIds
        // but update the loanId field to match the request
        SglLoanData data = new SglLoanData();
        data.setLoanId(loanId);
        data.setAmount(mockData.getAmount());
        data.setTermMonths(mockData.getTermMonths());
        data.setMaf(mockData.getMaf());
        data.setMonthlyPayment(mockData.getMonthlyPayment());
        data.setInterestRate(mockData.getInterestRate());
        data.setClientId(mockData.getClientId());

        return data;
    }
}
