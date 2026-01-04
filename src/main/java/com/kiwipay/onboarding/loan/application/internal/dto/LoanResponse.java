package com.kiwipay.onboarding.loan.application.internal.dto;

import com.kiwipay.onboarding.loan.domain.model.valueobjects.LoanStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class LoanResponse {

    private Long id;
    private Long clientId;
    private Long clinicalDataId;

    private Double income;
    private Double quotaNumber;
    private Double maf;
    private String group;
    private String segment;
    private String employmentStatus;
    private String classification;
    private Double finalRate;
    private Double experianRate;
    private Double additionalRate;
    private Double initial;

    private LoanStatus loanStatus;

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private OffsetDateTime signatureAt;
    private OffsetDateTime approvedByRiskAt;
    private OffsetDateTime disbursementAt;
}
