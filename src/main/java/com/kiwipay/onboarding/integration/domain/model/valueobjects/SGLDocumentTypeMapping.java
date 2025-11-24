package com.kiwipay.onboarding.integration.domain.model.valueobjects;

/**
 * Value Object for mapping SGL document types to Onboarding document types.
 * Provides consistent mapping logic that can be easily extended.
 */
public enum SGLDocumentTypeMapping {
    DNI(1, "DNI"),
    CE(2, "CE"),
    PASSPORT(3, "PASS"),
    RUC(4, "RUC");

    private final Integer sglCode;
    private final String onboardingCode;

    SGLDocumentTypeMapping(Integer sglCode, String onboardingCode) {
        this.sglCode = sglCode;
        this.onboardingCode = onboardingCode;
    }

    public Integer getSglCode() {
        return sglCode;
    }

    public String getOnboardingCode() {
        return onboardingCode;
    }

    /**
     * Maps SGL document type code to Onboarding document type string
     */
    public static String mapToOnboarding(Integer sglCode) {
        if (sglCode == null) {
            return DNI.getOnboardingCode(); // Default
        }

        for (SGLDocumentTypeMapping mapping : values()) {
            if (mapping.getSglCode().equals(sglCode)) {
                return mapping.getOnboardingCode();
            }
        }
        
        return DNI.getOnboardingCode(); // Default fallback
    }

    /**
     * Maps Onboarding document type to SGL code
     */
    public static Integer mapToSGL(String onboardingCode) {
        if (onboardingCode == null) {
            return DNI.getSglCode(); // Default
        }

        for (SGLDocumentTypeMapping mapping : values()) {
            if (mapping.getOnboardingCode().equalsIgnoreCase(onboardingCode.trim())) {
                return mapping.getSglCode();
            }
        }
        
        return DNI.getSglCode(); // Default fallback
    }
}