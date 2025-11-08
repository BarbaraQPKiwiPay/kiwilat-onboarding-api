package com.kiwipay.onboarding.clinicaldata.application.internal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClinicalDataResponse {
    private String id;
    private Long clientId;
    private String medicalCategoryId;
    private String clinicId;
    private String branchId;
    private String createdAt;
    private String updatedAt;
}