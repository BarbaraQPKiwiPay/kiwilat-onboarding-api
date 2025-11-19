package com.kiwipay.onboarding.integration.domain.model.valueobjects;

/**
 * Value Object for mapping marital status between SGL and Onboarding systems.
 */
public enum SGLMaritalStatusMapping {
    SINGLE("SOLTERO", "SINGLE"),
    MARRIED("CASADO", "MARRIED"), 
    DIVORCED("DIVORCIADO", "DIVORCED"),
    WIDOWED("VIUDO", "WIDOWED"),
    COHABITING("CONVIVIENTE", "COHABITING");

    private final String sglStatus;
    private final String onboardingStatus;

    SGLMaritalStatusMapping(String sglStatus, String onboardingStatus) {
        this.sglStatus = sglStatus;
        this.onboardingStatus = onboardingStatus;
    }

    public String getSglStatus() {
        return sglStatus;
    }

    public String getOnboardingStatus() {
        return onboardingStatus;
    }

    /**
     * Maps SGL marital status to Onboarding marital status
     */
    public static String mapToOnboarding(String sglStatus) {
        if (sglStatus == null || sglStatus.trim().isEmpty()) {
            return SINGLE.getOnboardingStatus(); // Default
        }

        for (SGLMaritalStatusMapping mapping : values()) {
            if (mapping.getSglStatus().equalsIgnoreCase(sglStatus.trim())) {
                return mapping.getOnboardingStatus();
            }
        }

        // Handle English variants
        String normalizedStatus = sglStatus.trim().toUpperCase();
        return switch (normalizedStatus) {
            case "MARRIED" -> MARRIED.getOnboardingStatus();
            case "SINGLE" -> SINGLE.getOnboardingStatus();
            case "DIVORCED" -> DIVORCED.getOnboardingStatus();
            case "WIDOWED" -> WIDOWED.getOnboardingStatus();
            default -> SINGLE.getOnboardingStatus(); // Default fallback
        };
    }
}