package com.kiwipay.onboarding.document.domain.model.valueobjects;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Document Type Enum
 * Defines the allowed types of documents in the system
 */
public enum DocumentType {
    DOCUMENTO_DE_IDENTIDAD("Documento de Identidad"),
    RECIBO_POR_SERVICIO("Recibo por Servicio"),
    COMPROBANTE_DE_INGRESO("Comprobante de Ingreso"),
    ORDEN_DE_SERVICIO_MEDICO("Orden de Servicio Médico"),
    FICHA_DE_RIESGOS("Ficha de Riesgos"),
    SUSTENTO_DE_INGRESOS("Sustento de Ingresos"),
    RECIBO_POR_HONORARIOS("Recibo por Honorarios");

    private final String displayName;

    DocumentType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    /**
     * Check if this document type is a risk document (FICHA_DE_RIESGOS)
     */
    public boolean isRiskDocument() {
        return this == FICHA_DE_RIESGOS;
    }

    /**
     * Get all document types allowed for client and guarantor uploads
     * (excludes FICHA_DE_RIESGOS which has its own dedicated endpoint)
     */
    public static List<DocumentType> getAllowedForClientAndGuarantor() {
        return Arrays.stream(values())
                .filter(type -> !type.isRiskDocument())
                .collect(Collectors.toList());
    }
}
