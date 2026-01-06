package com.kiwipay.onboarding.partner.domain.model.aggregates;

import com.kiwipay.onboarding.shared.domain.valueobjects.DocumentType;
import com.kiwipay.onboarding.partner.domain.model.exceptions.PartnerBusinessException;
import com.kiwipay.onboarding.partner.domain.model.valueobjects.PartnerType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * Spouse Aggregate Root - Unified Implementation
 * 
 * Represents the spouse of a client, guarantor, or patient
 * Uses SpouseType discriminator to determine which FK should be populated
 * 
 * Business Rules:
 * - Only ONE of clientId, guarantorId, or patientId can be non-null
 * - The populated FK must match the spouseType
 * - CLIENT spouse requires clientId
 * - GUARANTOR spouse requires guarantorId
 * - PATIENT spouse requires patientId
 */
@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "partner", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "client_id" }),
        @UniqueConstraint(columnNames = { "guarantor_id" }),
        @UniqueConstraint(columnNames = { "patient_id" }),
        @UniqueConstraint(columnNames = { "document_number" })
})
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "loan_id", nullable = false)
    private Long loanId;

    @Enumerated(EnumType.STRING)
    @Column(name = "spouse_type", nullable = false, length = 20)
    private PartnerType spouseType;

    // Conditional Foreign Keys - Only ONE should be populated based on spouseType
    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "guarantor_id", length = 50)
    private String guarantorId;

    @Column(name = "patient_id")
    private Long patientId;

    // Spouse personal information
    @Enumerated(EnumType.STRING)
    @Column(name = "document_type", nullable = false, length = 20)
    private DocumentType documentType;

    @Column(name = "document_number", nullable = false, length = 20)
    private String documentNumber;

    @Column(name = "first_names", nullable = false, length = 100)
    private String firstNames;

    @Column(name = "last_names", nullable = false, length = 100)
    private String lastNames;

    @Column(length = 100)
    private String email;

    @Column(length = 20)
    private String phone;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    /**
     * Constructor for CLIENT spouse
     */
    public Partner(Long loanId, Long clientId, DocumentType documentType, String documentNumber,
            String firstNames, String lastNames, String email, String phone) {
        this.loanId = loanId;
        this.spouseType = PartnerType.CLIENT;
        this.clientId = clientId;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.firstNames = firstNames;
        this.lastNames = lastNames;
        this.email = email;
        this.phone = phone;
    }

    /**
     * Constructor for GUARANTOR spouse
     */
    public Partner(Long loanId, String guarantorId, DocumentType documentType, String documentNumber,
            String firstNames, String lastNames, String email, String phone) {
        this.loanId = loanId;
        this.spouseType = PartnerType.GUARANTOR;
        this.guarantorId = guarantorId;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.firstNames = firstNames;
        this.lastNames = lastNames;
        this.email = email;
        this.phone = phone;
    }

    /**
     * Generic constructor with spouseType
     */
    public Partner(Long loanId, PartnerType spouseType, Long clientId, String guarantorId, Long patientId,
            DocumentType documentType, String documentNumber, String firstNames, String lastNames,
            String email, String phone) {
        this.loanId = loanId;
        this.spouseType = spouseType;
        this.clientId = clientId;
        this.guarantorId = guarantorId;
        this.patientId = patientId;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.firstNames = firstNames;
        this.lastNames = lastNames;
        this.email = email;
        this.phone = phone;
    }

    @PrePersist
    protected void onCreate() {
        validate();
        createdAt = OffsetDateTime.now();
        updatedAt = OffsetDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        validate();
        updatedAt = OffsetDateTime.now();
    }

    /**
     * Validates business rules for FK consistency
     * Ensures only ONE FK is populated based on spouseType
     */
    public void validate() {
        if (spouseType == null) {
            throw new PartnerBusinessException("SpouseType cannot be null");
        }

        switch (spouseType) {
            case CLIENT:
                if (clientId == null) {
                    throw new PartnerBusinessException("CLIENT spouse must have clientId populated");
                }
                if (guarantorId != null || patientId != null) {
                    throw new PartnerBusinessException("CLIENT spouse must ONLY have clientId populated");
                }
                break;

            case GUARANTOR:
                if (guarantorId == null) {
                    throw new PartnerBusinessException("GUARANTOR spouse must have guarantorId populated");
                }
                if (clientId != null || patientId != null) {
                    throw new PartnerBusinessException("GUARANTOR spouse must ONLY have guarantorId populated");
                }
                break;

            case PATIENT:
                if (patientId == null) {
                    throw new PartnerBusinessException("PATIENT spouse must have patientId populated");
                }
                if (clientId != null || guarantorId != null) {
                    throw new PartnerBusinessException("PATIENT spouse must ONLY have patientId populated");
                }
                break;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Partner spouse = (Partner) o;
        return Objects.equals(id, spouse.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
