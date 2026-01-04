package com.kiwipay.onboarding.keynua.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractPayloadResponse {

    /**
     * The exact Keynua payload as JSON-serializable object
     */
    private Object payload;

    /**
     * Fields that were missing or defaulted
     */
    private List<String> missingFields;
}
