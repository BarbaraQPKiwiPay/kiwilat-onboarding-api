package com.kiwipay.onboarding.keynua.dto.keynua;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Cavali-specific data for contract metadata
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KeynuaCavaliData {

    /**
     * Banking code - always "4"
     */
    private String banking;

    /**
     * Product code - always "41"
     */
    private String product;

    /**
     * Unique 8-digit code
     */
    private String uniqueCode;

    /**
     * Main client information
     */
    private KeynuaCavaliClient client;

    /**
     * Client representatives (e.g., spouse) - optional
     */
    private List<KeynuaCavaliRepresentative> representatives;

    /**
     * Guarantors list - optional
     */
    private List<KeynuaCavaliGuarantee> guarantees;

    /**
     * Full client name
     */
    private String clientName;

    /**
     * Domicile/address - defaults to "lima" if not provided
     */
    private String domicile;

    /**
     * Issue date in YYYY-MM-DD format
     */
    private String issueDate;

    /**
     * Issue place - always "Peru"
     */
    private String issuePlace;
}
