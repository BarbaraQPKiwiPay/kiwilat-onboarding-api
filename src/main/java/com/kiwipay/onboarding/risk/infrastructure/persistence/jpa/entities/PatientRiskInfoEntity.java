package com.kiwipay.onboarding.risk.infrastructure.persistence.jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "patient_risk_info")
@Getter
@Setter
@NoArgsConstructor
public class PatientRiskInfoEntity {
    
    @Id
    @Column(name = "client_id")
    private Long clientId;
    
    @Column(name = "last_names")
    private String lastNames;
    
    @Column(name = "first_names")
    private String firstNames;
    
    @Column(name = "document_number")
    private String documentNumber;
    
    @Column(name = "birth_date")
    private LocalDate birthDate;
    
    private Integer age;
    
    private String email;
    
    private String phone;
    
    @Column(name = "monthly_income", precision = 10, scale = 2)
    private BigDecimal monthlyIncome;
    
    // Experian fields
    @Column(name = "experian_classification")
    private String experianClassification;
    
    @Column(name = "experian_segment")
    private String experianSegment;
    
    @Column(name = "experian_group")
    private String experianGroup;
    
    @Column(name = "experian_score")
    private Integer experianScore;
    
    @Column(name = "experian_response")
    private String experianResponse;
    
    @Column(name = "experian_result")
    private String experianResult;
    
    public PatientRiskInfoEntity(Long clientId, String lastNames, String firstNames, 
                               String documentNumber, LocalDate birthDate, Integer age,
                               String email, String phone, BigDecimal monthlyIncome) {
        this.clientId = clientId;
        this.lastNames = lastNames;
        this.firstNames = firstNames;
        this.documentNumber = documentNumber;
        this.birthDate = birthDate;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.monthlyIncome = monthlyIncome;
    }
}