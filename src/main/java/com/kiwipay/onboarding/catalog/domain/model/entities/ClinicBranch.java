package com.kiwipay.onboarding.catalog.domain.model.entities;

import com.kiwipay.onboarding.catalog.domain.model.aggregates.Clinic;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ClinicBranch {
    @Id
    private String id;
    
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "clinic_id")
    @JsonBackReference("clinic-branches")
    private Clinic clinic;
    
    public ClinicBranch(String id, String name, Clinic clinic) {
        this.id = id;
        this.name = name;
        this.clinic = clinic;
    }
}