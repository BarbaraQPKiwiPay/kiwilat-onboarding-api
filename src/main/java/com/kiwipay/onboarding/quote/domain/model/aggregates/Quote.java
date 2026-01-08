package com.kiwipay.onboarding.quote.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * Quote Aggregate Root
 * Represents a quote associated with a loan application.
 * Follows DDD principles - quotes belong to loans, not directly to clients.
 */
@Setter
@Getter
@Entity
@Table(name = "quotes")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "loan_id", nullable = false)
    private Long loanId;

    @Column(name = "document_type", nullable = false)
    private String documentType;

    @Column(name = "document_number", nullable = false)
    private String documentNumber;

    @Column(name = "monthly_income", nullable = false, precision = 10, scale = 2)
    private BigDecimal monthlyIncome;

    @Column(name = "branch_id")
    private String branchId;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    public Quote() {
    }

    public Quote(Long loanId, String documentType, String documentNumber, BigDecimal monthlyIncome, String branchId) {
        this.loanId = loanId;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.monthlyIncome = monthlyIncome;
        this.branchId = branchId;
        validate();
    }

    /**
     * Validates quote business rules.
     * 
     * @throws IllegalArgumentException if validation fails
     */
    public void validate() {
        if (loanId == null || loanId <= 0) {
            throw new IllegalArgumentException("Loan ID must be a positive number");
        }
        if (documentType == null || documentType.isBlank()) {
            throw new IllegalArgumentException("Document type cannot be null or blank");
        }
        if (documentNumber == null || documentNumber.isBlank()) {
            throw new IllegalArgumentException("Document number cannot be null or blank");
        }
        if (monthlyIncome == null || monthlyIncome.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Monthly income must be greater than 0");
        }
    }
}
