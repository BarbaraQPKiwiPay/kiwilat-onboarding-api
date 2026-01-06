package com.kiwipay.onboarding.guarantor.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity(name = "GuarantorSpouse")
@Table(name = "guarantor_spouses")
public class Spouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "guarantor_id", unique = true)
    private String guarantorId;

    @Column(nullable = false, name = "document_type")
    private String documentType;

    @Column(nullable = false, name = "document_number")
    private String documentNumber;

    @Column(nullable = false, name = "first_names")
    private String firstNames;

    @Column(nullable = false, name = "last_names")
    private String lastNames;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @Column(nullable = false, name = "updated_at")
    private LocalDateTime updatedAt;

    public Spouse() {
    }

    public Spouse(String guarantorId, String documentType, String documentNumber,
            String firstNames, String lastNames, String email, String phone) {
        this.guarantorId = guarantorId;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.firstNames = firstNames;
        this.lastNames = lastNames;
        this.email = email;
        this.phone = phone;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void updateDetails(String documentType, String documentNumber,
            String firstNames, String lastNames, String email, String phone) {
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.firstNames = firstNames;
        this.lastNames = lastNames;
        this.email = email;
        this.phone = phone;
        this.updatedAt = LocalDateTime.now();
    }
}