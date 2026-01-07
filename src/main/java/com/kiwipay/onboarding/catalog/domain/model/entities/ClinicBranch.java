package com.kiwipay.onboarding.catalog.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ClinicBranch Entity
 * Represents a physical branch/location of a Clinic
 * Belongs to a Clinic
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class ClinicBranch {
    @Id
    private String id;

    private String name;

    @Column(name = "clinic_id")
    private String clinicId;

    public ClinicBranch(String id, String name, String clinicId) {
        this.id = id;
        this.name = name;
        this.clinicId = clinicId;
    }
}