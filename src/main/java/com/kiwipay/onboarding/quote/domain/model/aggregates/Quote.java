package com.kiwipay.onboarding.quote.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
@Entity
@Table(name = "quotes")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String documentType;

    @Column(nullable = false)
    private String documentNumber;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal monthlyIncome;

    private String branchId;


    @Column(nullable = false)
    private Long clientId;


    public Quote() {}

    public Quote(String documentType, String documentNumber, BigDecimal monthlyIncome, String branchId, Long clientId) {
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.monthlyIncome = monthlyIncome;
        this.branchId = branchId;
        this.clientId = clientId;
    }

}
