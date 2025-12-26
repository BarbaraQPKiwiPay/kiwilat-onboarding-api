package com.kiwipay.onboarding.keynua.dto.keynua;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeynuaPrefilledItem {

    /**
     * Always "4" for document number field
     */
    private String target;

    /**
     * Title based on document type, e.g., "N° DNI", "N° CE"
     */
    private String title;

    private KeynuaDocumentValue value;
}
