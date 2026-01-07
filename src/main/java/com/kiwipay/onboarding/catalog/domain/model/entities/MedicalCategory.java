package com.kiwipay.onboarding.catalog.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * MedicalCategory Entity
 * Represents a category of medical procedures (e.g., cirugía estética,
 * odontología)
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class MedicalCategory {
    @Id
    private String id;

    private String name;

    public MedicalCategory(String id, String name) {
        this.id = id;
        this.name = name;
    }
}