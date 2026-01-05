package com.kiwipay.onboarding.loan.domain.model.valueobjects;

/**
 * Estados del flujo de evaluación de préstamos
 * Controla las transiciones válidas en el proceso de onboarding
 */
public enum LoanStatus {

    // ESTADO INICIAL
    PRE_APPROVED,

    // FLUJO DE COMERCIAL
    DOCUMENTS_COMPLETED,

    // FLUJO ADV (Área de Ventas)
    APPROVED_BY_ADV,
    OBSERVED_BY_ADV,

    // FLUJO RIESGOS
    APPROVED_BY_RISK,
    REJECTED_BY_RISK,
    OBSERVED_BY_RISK,

    // ESTADOS FINALES
    SIGNED,
    DISBURSED;

    public LoanStatus[] getAllowedTransitions() {
        switch (this) {
            case PRE_APPROVED:
                return new LoanStatus[] { DOCUMENTS_COMPLETED };

            case DOCUMENTS_COMPLETED:
                return new LoanStatus[] { APPROVED_BY_ADV, OBSERVED_BY_ADV };

            case APPROVED_BY_ADV:
                return new LoanStatus[] { APPROVED_BY_RISK, REJECTED_BY_RISK, OBSERVED_BY_RISK };
            case OBSERVED_BY_ADV:
                return new LoanStatus[] { DOCUMENTS_COMPLETED };
            case OBSERVED_BY_RISK:
                return new LoanStatus[] { DOCUMENTS_COMPLETED };
            case APPROVED_BY_RISK:
                return new LoanStatus[] { SIGNED };
            case SIGNED:
                return new LoanStatus[] { DISBURSED };
            case REJECTED_BY_RISK:
            case DISBURSED:
            default:
                return new LoanStatus[] {};
        }
    }

    /**
     * Verifica si la transición al nuevo estado es válida
     */
    public boolean canTransitionTo(LoanStatus newStatus) {
        LoanStatus[] allowed = getAllowedTransitions();
        for (LoanStatus status : allowed) {
            if (status == newStatus) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica si es un estado final (no permite más cambios)
     */
    public boolean isFinalState() {
        return this == REJECTED_BY_RISK ||
                this == DISBURSED ||
                this == APPROVED_BY_RISK;
    }

    /**
     * Verifica si permite subida de documentos
     */
    public boolean allowsDocumentUpload() {
        return this == PRE_APPROVED ||
                this == OBSERVED_BY_ADV ||
                this == OBSERVED_BY_RISK ||
                this == DOCUMENTS_COMPLETED;
    }
}
