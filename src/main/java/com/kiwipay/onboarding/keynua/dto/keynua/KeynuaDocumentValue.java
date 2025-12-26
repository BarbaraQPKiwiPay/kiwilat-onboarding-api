package com.kiwipay.onboarding.keynua.dto.keynua;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeynuaDocumentValue {

    /**
     * Document type: "pe-dni" or "pe-ce"
     */
    private String type;

    /**
     * Document number
     */
    private String value;
}
