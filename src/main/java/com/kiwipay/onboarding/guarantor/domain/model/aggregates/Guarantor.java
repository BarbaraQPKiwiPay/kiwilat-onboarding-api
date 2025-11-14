package com.kiwipay.onboarding.guarantor.domain.model.aggregates;

import com.kiwipay.onboarding.guarantor.domain.model.valueobjects.GuarantorAddress;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "guarantors")
public class Guarantor {
    @Id
    private String guarantorId;

    @Column(nullable = false, name = "client_id", unique = true)
    private Long clientId;

    @Column(nullable = false, name = "document_type")
    private String documentType;

    @Column(nullable = false, name = "document_number")
    private String documentNumber;

    @Column(nullable = false, precision = 10, scale = 2, name = "monthly_income")
    private BigDecimal monthlyIncome;

    @Column(nullable = false, name = "first_names")
    private String firstNames;

    @Column(nullable = false, name = "last_names")
    private String lastNames;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "marital_status")
    private MaritalStatus maritalStatus;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Embedded
    private GuarantorAddress address;

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @Column(nullable = false, name = "updated_at")
    private LocalDateTime updatedAt;

    public Guarantor() {}

    public Guarantor(String guarantorId, Long clientId, String documentType, String documentNumber,
                    BigDecimal monthlyIncome, String firstNames, String lastNames, Gender gender,
                    MaritalStatus maritalStatus, String email, String phone, GuarantorAddress address) {
        this.guarantorId = guarantorId;
        this.clientId = clientId;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.monthlyIncome = monthlyIncome;
        this.firstNames = firstNames;
        this.lastNames = lastNames;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void updateDetails(String documentType, String documentNumber, BigDecimal monthlyIncome,
                             String firstNames, String lastNames, Gender gender, MaritalStatus maritalStatus,
                             String email, String phone, GuarantorAddress address) {
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.monthlyIncome = monthlyIncome;
        this.firstNames = firstNames;
        this.lastNames = lastNames;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.updatedAt = LocalDateTime.now();
    }

    public enum Gender {
        M, F
    }

    public enum MaritalStatus {
        SINGLE, MARRIED, DIVORCED, WIDOWED
    }
}