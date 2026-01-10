package com.kiwipay.onboarding.keynua.application.internal.builder;

import com.kiwipay.onboarding.client.application.internal.dto.ClientResponse;
import com.kiwipay.onboarding.partner.application.internal.dto.PartnerResponse;
import com.kiwipay.onboarding.guarantor.application.internal.dto.GuarantorResponse;
import com.kiwipay.onboarding.keynua.dto.SglLoanData;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Context object for payload building
 * Contains all data needed to construct Keynua contract payload
 */
@Data
@Builder
public class PayloadBuilderContext {

    private String loanId;
    private String templateId;
    private ClientResponse client;
    private PartnerResponse clientPartner;
    private List<GuarantorResponse> guarantors;
    private Map<Long, PartnerResponse> guarantorPartners; // guarantorId -> partner
    private SglLoanData sglData;

    /**
     * Accumulator for missing/defaulted fields
     */
    @Builder.Default
    private List<String> missingFields = new ArrayList<>();

    /**
     * Helper to add a missing field to the list
     */
    public void addMissingField(String field) {
        if (!missingFields.contains(field)) {
            missingFields.add(field);
        }
    }
}
