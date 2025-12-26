package com.kiwipay.onboarding.keynua.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractCreationResponse {

    private String contractId;
    private String shortCode;
    private String signerToken;
    private String signUrl;
    private String status;
}
