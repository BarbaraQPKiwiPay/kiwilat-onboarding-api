package com.kiwipay.onboarding.partner.domain.model.valueobjects;

/**
 * Enum representing the type of spouse relationship
 * Determines which foreign key should be populated
 */
public enum PartnerType {
    /**
     * Spouse of the loan client (titular)
     * Requires clientId to be populated
     */
    CLIENT,

    /**
     * Spouse of the guarantor (avalista)
     * Requires guarantorId to be populated
     */
    GUARANTOR,

    /**
     * Spouse of the patient (paciente del procedimiento)
     * Requires patientId to be populated
     */
    PATIENT
}
