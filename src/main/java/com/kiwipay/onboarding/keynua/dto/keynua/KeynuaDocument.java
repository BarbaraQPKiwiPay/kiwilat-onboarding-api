package com.kiwipay.onboarding.keynua.dto.keynua;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Document with name and base64 content (as per legacy flow requirement)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeynuaDocument {

    private String name;
    private String base64;
}
