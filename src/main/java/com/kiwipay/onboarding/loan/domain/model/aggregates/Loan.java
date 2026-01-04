package com.kiwipay.onboarding.loan.domain.model.aggregates;

import com.kiwipay.onboarding.loan.domain.model.valueobjects.LoanStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * Loan Aggregate Root
 * Represents the main entity for loan management in the onboarding process
 */
@Entity
@Table(name = "loan")
@Getter
@Setter
@NoArgsConstructor
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Column(name = "clinical_data_id")
    private Long clinicalDataId;

    // ========== LOAN DATA ==========
    @Column(name = "income")
    private Double income;

    @Column(name = "quota_number")
    private Double quotaNumber;

    @Column(name = "maf")
    private Double maf;

    @Column(name = "loan_group", length = 50)
    private String group;

    @Column(name = "segment", length = 50)
    private String segment;

    @Column(name = "employment_status", length = 50)
    private String employmentStatus;

    @Column(name = "classification", length = 50)
    private String classification;

    @Column(name = "final_rate")
    private Double finalRate;

    @Column(name = "experian_rate")
    private Double experianRate;

    @Column(name = "additional_rate")
    private Double additionalRate;

    @Column(name = "initial")
    private Double initial;

    // ========== STATUS & AUDIT FIELDS ==========
    @Enumerated(EnumType.STRING)
    @Column(name = "loan_status", nullable = false, length = 50)
    private LoanStatus loanStatus = LoanStatus.PENDING;

    // ========== TIMESTAMPS ==========
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @Column(name = "signature_at")
    private OffsetDateTime signatureAt;

    @Column(name = "approved_by_risk_at")
    private OffsetDateTime approvedByRiskAt;

    @Column(name = "disbursement_at")
    private OffsetDateTime disbursementAt;

    @PrePersist
    protected void onCreate() {
        createdAt = OffsetDateTime.now();
        updatedAt = OffsetDateTime.now();
        if (loanStatus == null) {
            loanStatus = LoanStatus.PENDING;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = OffsetDateTime.now();
    }

    /**
     * Changes the loan status
     * 
     * @param newStatus The new status to transition to
     * @param userId    The ID of the user making the change (for future use)
     * @param reason    Optional reason for the change
     * @throws IllegalStateException if the transition is not allowed
     */
    public void changeStatus(LoanStatus newStatus, Long userId, String reason) {
        if (!this.loanStatus.canTransitionTo(newStatus)) {
            throw new IllegalStateException(
                    String.format("Invalid transition from %s to %s. Reason: %s",
                            this.loanStatus, newStatus, reason));
        }

        this.loanStatus = newStatus;

        // Set specific audit timestamps based on new status
        OffsetDateTime now = OffsetDateTime.now();
        switch (newStatus) {
            case APPROVED_BY_RISK:
                this.approvedByRiskAt = now;
                break;
            case SIGNED:
                this.signatureAt = now;
                break;
            case DISBURSED:
                this.disbursementAt = now;
                break;
            default:
                // No specific audit fields for other states
                break;
        }
    }

    /**
     * Determines if documents can be uploaded for this loan based on its status
     * 
     * @return true if documents can be uploaded, false otherwise
     */
    public boolean allowsDocumentUpload() {
        return loanStatus.allowsDocumentUpload();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Loan loan = (Loan) o;
        return Objects.equals(id, loan.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
