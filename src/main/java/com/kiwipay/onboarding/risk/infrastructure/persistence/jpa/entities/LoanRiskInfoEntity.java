package com.kiwipay.onboarding.risk.infrastructure.persistence.jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "loan_risk_info")
@Getter
@Setter
@NoArgsConstructor
public class LoanRiskInfoEntity {
    
    @Id
    @Column(name = "client_id")
    private Long clientId;
    
    private String campaign;
    
    @Column(name = "loan_status")
    private String loanStatus;
    
    @Column(name = "application_date")
    private LocalDate applicationDate;
    
    private Integer installments;
    
    @Column(name = "monthly_installment", precision = 10, scale = 2)
    private BigDecimal monthlyInstallment;
    
    @Column(name = "requested_amount", precision = 12, scale = 2)
    private BigDecimal requestedAmount;
    
    public LoanRiskInfoEntity(Long clientId, String campaign, String loanStatus,
                            LocalDate applicationDate, Integer installments,
                            BigDecimal monthlyInstallment, BigDecimal requestedAmount) {
        this.clientId = clientId;
        this.campaign = campaign;
        this.loanStatus = loanStatus;
        this.applicationDate = applicationDate;
        this.installments = installments;
        this.monthlyInstallment = monthlyInstallment;
        this.requestedAmount = requestedAmount;
    }
}