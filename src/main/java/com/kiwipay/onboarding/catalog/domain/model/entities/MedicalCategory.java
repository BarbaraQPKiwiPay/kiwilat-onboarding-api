package com.kiwipay.onboarding.catalog.domain.model.entities;

import com.kiwipay.onboarding.catalog.domain.model.aggregates.Clinic;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MedicalCategory {
    @Id
    private String id;
    
    private String name;
    
    @OneToMany(mappedBy = "medicalCategory")
    @JsonManagedReference("category-clinics")
    private List<Clinic> clinics;
    
    public MedicalCategory(String id, String name) {
        this.id = id;
        this.name = name;
    }
}