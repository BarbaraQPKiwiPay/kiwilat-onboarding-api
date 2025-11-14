package com.kiwipay.onboarding.risk.infrastructure.persistence.jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "medical_risk_info")
@Getter
@Setter
@NoArgsConstructor
public class MedicalRiskInfoEntity {
    
    @Id
    @Column(name = "client_id")
    private Long clientId;
    
    @Column(name = "medical_category")
    private String medicalCategory;
    
    @Column(name = "medical_center")
    private String medicalCenter;
    
    private String branch;
    
    public MedicalRiskInfoEntity(Long clientId, String medicalCategory, 
                               String medicalCenter, String branch) {
        this.clientId = clientId;
        this.medicalCategory = medicalCategory;
        this.medicalCenter = medicalCenter;
        this.branch = branch;
    }
}