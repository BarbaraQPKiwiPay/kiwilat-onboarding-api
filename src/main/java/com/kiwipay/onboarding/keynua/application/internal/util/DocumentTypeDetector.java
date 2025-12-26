package com.kiwipay.onboarding.keynua.application.internal.util;

/**
 * Utility class to detect document type (DNI vs CE) based on document number
 */
public class DocumentTypeDetector {

    /**
     * Detects document type from document number
     * 
     * @param documentNumber the document number to analyze
     * @return "pe-ce" for Carné de Extranjería, "pe-dni" for DNI (default)
     */
    public static String detectDocumentType(String documentNumber) {
        if (documentNumber == null || documentNumber.isEmpty()) {
            return "pe-dni";
        }

        // CE (Carné de Extranjería) typically has 9+ digits in Peru
        // DNI has exactly 8 digits
        String cleaned = documentNumber.replaceAll("[^0-9]", "");

        if (cleaned.length() > 8) {
            return "pe-ce";
        }

        return "pe-dni";
    }

    /**
     * Gets the display title for a document type
     * 
     * @param documentType the document type ("pe-dni" or "pe-ce")
     * @return title string for display
     */
    public static String getDocumentTitle(String documentType) {
        if ("pe-ce".equals(documentType)) {
            return "N° CE";
        }
        return "N° DNI";
    }
}
