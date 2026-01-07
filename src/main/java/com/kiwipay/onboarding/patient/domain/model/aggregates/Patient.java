package com.kiwipay.onboarding.patient.domain.model.aggregates;

import com.kiwipay.onboarding.shared.domain.valueobjects.DocumentType;
import com.kiwipay.onboarding.shared.domain.valueobjects.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * Patient Aggregate Root
 * Represents the patient undergoing the medical procedure
 * Related to a Loan, not directly to a Client
 */
@Setter
@Getter
@Entity
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "loan_id", nullable = false)
    private Long loanId;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type")
    private DocumentType documentType;

    @Column(name = "document_number")
    private String documentNumber;

    @Column(name = "first_names")
    private String firstNames;

    @Column(name = "last_names")
    private String lastNames;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String phone;
    private String email;

    @Column(name = "district_id")
    private String districtId;

    @Column(name = "address_line1")
    private String addressLine1;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    public Patient(Long loanId, DocumentType documentType, String documentNumber, String firstNames,
            String lastNames, Gender gender, String phone, String email, String districtId, String addressLine1) {
        this.loanId = loanId;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.firstNames = firstNames;
        this.lastNames = lastNames;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.districtId = districtId;
        this.addressLine1 = addressLine1;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = OffsetDateTime.now();
        updatedAt = OffsetDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = OffsetDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}