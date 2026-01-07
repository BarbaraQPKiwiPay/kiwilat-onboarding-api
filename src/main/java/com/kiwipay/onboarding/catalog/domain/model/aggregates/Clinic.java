package com.kiwipay.onboarding.catalog.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clinic Aggregate Root
 * Represents a medical clinic that offers procedures
 * Belongs to a MedicalCategory
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Clinic {
    @Id
    private String id;

    private String name;

    @Column(name = "medical_category_id")
    private String medicalCategoryId;

    public Clinic(String id, String name, String medicalCategoryId) {
        this.id = id;
        this.name = name;
        this.medicalCategoryId = medicalCategoryId;
    }
}