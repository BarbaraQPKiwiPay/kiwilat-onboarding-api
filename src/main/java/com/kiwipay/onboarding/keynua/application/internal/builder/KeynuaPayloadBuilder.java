package com.kiwipay.onboarding.keynua.application.internal.builder;

import com.kiwipay.onboarding.client.application.internal.dto.ClientResponse;
import com.kiwipay.onboarding.partner.application.internal.dto.PartnerResponse;
import com.kiwipay.onboarding.guarantor.application.internal.dto.GuarantorResponse;
import com.kiwipay.onboarding.keynua.dto.SglLoanData;
import com.kiwipay.onboarding.keynua.dto.keynua.KeynuaContractRequest;
import com.kiwipay.onboarding.keynua.dto.request.RequestType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Main Keynua Payload Builder
 * Orchestrates payload building using appropriate strategy
 */
@Component
public class KeynuaPayloadBuilder {

    private final SingleSignerStrategy singleSignerStrategy;
    private final MultipleSignersStrategy multipleSignersStrategy;

    public KeynuaPayloadBuilder(SingleSignerStrategy singleSignerStrategy,
            MultipleSignersStrategy multipleSignersStrategy) {
        this.singleSignerStrategy = singleSignerStrategy;
        this.multipleSignersStrategy = multipleSignersStrategy;
    }

    /**
     * Builds Keynua contract payload using appropriate strategy
     * 
     * @param loanId            loan ID
     * @param requestType       single or multiple signers
     * @param templateId        Keynua template ID
     * @param client            main client
     * @param clientPartner     client partner (optional)
     * @param guarantors        list of guarantors (optional)
     * @param guarantorPartners map of guarantor partners (optional)
     * @param sglData           SGL loan data
     * @return payload result with request and missing fields
     */
    public PayloadResult buildPayload(
            String loanId,
            RequestType requestType,
            String templateId,
            ClientResponse client,
            PartnerResponse clientPartner,
            List<GuarantorResponse> guarantors,
            Map<Long, PartnerResponse> guarantorPartners,
            SglLoanData sglData) {

        // Build context
        PayloadBuilderContext context = PayloadBuilderContext.builder()
                .loanId(loanId)
                .templateId(templateId)
                .client(client)
                .clientPartner(clientPartner)
                .guarantors(guarantors)
                .guarantorPartners(guarantorPartners != null ? guarantorPartners : new HashMap<>())
                .sglData(sglData)
                .build();

        // Select strategy based on request type
        SignerStrategy strategy;
        if (requestType == RequestType.SINGLE_SIGNER) {
            strategy = singleSignerStrategy;
        } else {
            strategy = multipleSignersStrategy;
        }

        // Build payload
        KeynuaContractRequest payload = strategy.buildPayload(context);

        // Return result with missing fields
        return new PayloadResult(payload, context.getMissingFields());
    }

    /**
     * Result of payload building
     */
    @Data
    @AllArgsConstructor
    public static class PayloadResult {
        private KeynuaContractRequest payload;
        private List<String> missingFields;
    }
}
