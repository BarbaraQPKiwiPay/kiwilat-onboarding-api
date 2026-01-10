package com.kiwipay.onboarding.guarantor.domain.model.aggregates;

import com.kiwipay.onboarding.shared.domain.valueobjects.DocumentType;
import com.kiwipay.onboarding.shared.domain.valueobjects.Gender;
import com.kiwipay.onboarding.shared.domain.valueobjects.MaritalStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "guarantors")
public class Guarantor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "loan_id", nullable = false)
    private Long loanId;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type", nullable = false)
    private DocumentType documentType;

    @Column(name = "document_number", nullable = false)
    private String documentNumber;

    @Column(name = "monthly_income", nullable = false, precision = 10, scale = 2)
    private BigDecimal monthlyIncome;

    @Column(name = "first_names", nullable = false)
    private String firstNames;

    @Column(name = "last_names", nullable = false)
    private String lastNames;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "marital_status", nullable = false)
    private MaritalStatus maritalStatus;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(name = "district_id")
    private String districtId;

    @Column(name = "address_line1")
    private String addressLine1;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Guarantor() {
    }

    public Guarantor(Long loanId, DocumentType documentType, String documentNumber,
            BigDecimal monthlyIncome, String firstNames, String lastNames, Gender gender,
            MaritalStatus maritalStatus, String email, String phone, String districtId, String addressLine1) {
        this.loanId = loanId;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.monthlyIncome = monthlyIncome;
        this.firstNames = firstNames;
        this.lastNames = lastNames;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.email = email;
        this.phone = phone;
        this.districtId = districtId;
        this.addressLine1 = addressLine1;
    }

    public void updateDetails(DocumentType documentType, String documentNumber, BigDecimal monthlyIncome,
            String firstNames, String lastNames, Gender gender, MaritalStatus maritalStatus,
            String email, String phone, String districtId, String addressLine1) {
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.monthlyIncome = monthlyIncome;
        this.firstNames = firstNames;
        this.lastNames = lastNames;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.email = email;
        this.phone = phone;
        this.districtId = districtId;
        this.addressLine1 = addressLine1;
    }
}