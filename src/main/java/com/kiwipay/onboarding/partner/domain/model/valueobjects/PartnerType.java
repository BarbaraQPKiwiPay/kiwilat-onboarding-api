package com.kiwipay.onboarding.partner.domain.model.valueobjects;

/**
 * Enum representing the type of partner relationship
 * Determines which foreign key should be populated
 */
public enum PartnerType {
    /**
     * Partner of the loan client (titular)
     * Requires clientId to be populated
     */
    CLIENT,

    /**
     * Partner of the guarantor (avalista)
     * Requires guarantorId to be populated
     */
    GUARANTOR,

    /**
     * Partner of the patient (paciente del procedimiento)
     * Requires patientId to be populated
     */
    PATIENT
}
