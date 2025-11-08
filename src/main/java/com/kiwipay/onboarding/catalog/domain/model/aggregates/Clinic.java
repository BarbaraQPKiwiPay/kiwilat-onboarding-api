package com.kiwipay.onboarding.catalog.domain.model.aggregates;

import com.kiwipay.onboarding.catalog.domain.model.entities.ClinicBranch;
import com.kiwipay.onboarding.catalog.domain.model.entities.MedicalCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Clinic {
    @Id
    private String id;
    
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "medical_category_id")
    @JsonBackReference("category-clinics")
    private MedicalCategory medicalCategory;
    
    @OneToMany(mappedBy = "clinic")
    @JsonManagedReference("clinic-branches")
    private List<ClinicBranch> branches;
    
    public Clinic(String id, String name, MedicalCategory medicalCategory) {
        this.id = id;
        this.name = name;
        this.medicalCategory = medicalCategory;
    }
}