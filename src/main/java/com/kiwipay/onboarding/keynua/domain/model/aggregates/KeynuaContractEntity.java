package com.kiwipay.onboarding.keynua.domain.model.aggregates;

import com.kiwipay.onboarding.keynua.dto.request.RequestType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Keynua Contract Entity
 * Stores contract information for idempotency and status tracking
 * 
 * TODO: Create database migration with Flyway/Liquibase/manual SQL
 * Table: keynua_contracts
 */
@Entity
@Table(name = "keynua_contracts", indexes = {
        @Index(name = "idx_loan_id", columnList = "loan_id"),
        @Index(name = "idx_contract_id", columnList = "contract_id", unique = true)
})
@Data
@NoArgsConstructor
public class KeynuaContractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "loan_id", nullable = false)
    private String loanId;

    @Column(name = "contract_id", nullable = false, unique = true)
    private String contractId;

    @Column(name = "template_id", nullable = false)
    private String templateId;

    @Column(name = "request_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private RequestType requestType;

    /**
     * Status: CREATED, PENDING, SIGNED, COMPLETED, FAILED, EXPIRED
     */
    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "short_code")
    private String shortCode;

    /**
     * JWT token for signing - can be very long (500+ characters)
     */
    @Column(name = "signer_token", length = 1000)
    private String signerToken;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
